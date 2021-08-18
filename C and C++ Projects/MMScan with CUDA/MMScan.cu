//Author: Zachary Klausner
//Class: CS475
//Assignment: PA5

// Includes
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <float.h>
#include <time.h>
#include <sys/time.h>
#include <sys/errno.h>
#include <omp.h>

#define max(x, y)   ((x)>(y) ? (x) : (y))
#define min(x, y)   ((x)>(y) ? (y) : (x))

__device__ void multiplyMatrices(float*, float*, float*, long);
void print_matrix_array(float*, long, long, char*);

void print_matrix_array(float* matrix, long N_or_G, long B, char* name) {
  long end = N_or_G*B*B;
  for (int i = 0; i < end; i++) {
    printf("%s[%d] = %f\n", name, i, matrix[i]);
  }
}

void MMScan(float ***X, float ***Y, long start, long end, long size){
  long n, i, j, k;
  for(i=0; i <= size-1; i+=1)
    {
      for(j=0; j <= size-1; j+=1)
	{
	  Y[start][i][j] = X[start][i][j];
	}
    }

#ifdef FAUX  // incorrect parallelization
#pragma omp parallel for
#endif // incorrect parallelization
  for(n=start+1; n <= end; n+=1)
    {
      for(i=0; i < size; i+=1)
	{
	  for(j=0; j < size; j+=1)
	    {
	      float acc = 0;
	      for(k=0; k<size; k++){
		acc = acc + Y[n-1][i][k] * X[n][k][j];
	      }
	      Y[n][i][j] = acc;
	    }
	}
    }
}

__global__ void Phase_1(float* X, float* R1,long N, long B) {

  float* T1 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T2 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T3 = (float*)malloc(sizeof(float)*((B) * (B)));

  float* identity = (float*)malloc(sizeof(float)*((B) * (B)));

  long G = gridDim.x;
  long n = N/G;
  long start = n * blockIdx.x * (B*B);
  long end = start + (G*B*B);

  int T1_index = 0;
  int T2_index = 0;
  int T3_index = 0;

  int row_loc = 0;
  int col_loc = 0;

  for (int i = 0; i < B*B; i++) {
    if (row_loc == col_loc) {
      identity[i] = 1.0;
    }
    if ((i+1) % B == 0 && i != 0) {
      row_loc++;
      col_loc = 0.0;
      continue;
    }
    col_loc++;
  }

  int identity_index = 0;
  for (int i = blockIdx.x*B*B; i < blockIdx.x*B*B + B*B; i++) {
    R1[i] = identity[identity_index];
    identity_index++;
  }

  for (int i = 0; i < n; i++) {

    T1_index = 0;
    for (int j = start + (i*B*B); j < (start + (i*B*B)) + B*B; j++) {
      T1[T1_index] = X[j];
      T1_index++;
    }

    T2_index = 0;
    for (int j = blockIdx.x*B*B; j < (blockIdx.x*B*B) + B*B; j++) {
      T2[T2_index] = R1[j];
      T2_index++;
    }

    multiplyMatrices(T1,T2,T3,B);

    __syncthreads();

    T3_index = 0;
    for (int j = blockIdx.x*B*B; j < (blockIdx.x*B*B) + B*B; j++) {
      R1[j] = T3[T3_index];
      T3_index++;
    }
  }
}

__global__ void Phase_2(float* R1, float* R2, long N, long B, long G) {

  float* T1 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T2 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T3 = (float*)malloc(sizeof(float)*((B) * (B)));

  int T1_index = 0;
  int T2_index = 0;
  int T3_index = 0;

  for (int i = 1; i < G; i++) {

    T1_index = 0;
    for (int j = (i-1)*B*B; j < ((i-1)*B*B) + B*B; j++) {
      T1[T1_index] = R2[j];
      T1_index++;
    }

    T2_index = 0;
    for (int j = i*B*B; j < (i*B*B) + B*B; j++) {
      T2[T2_index] = R1[j];
      T2_index++;
    }

    multiplyMatrices(T1,T2,T3,B);

    __syncthreads();

    T3_index = 0;
    for (int j = i*B*B; j < (i*B*B) + B*B; j++) {
      R2[j] = T3[T3_index];
      T3_index++;
    }
  }
}

__global__ void Phase_3(float* R2, float* Y, long N, long B) {

  float* T1 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T2 = (float*)malloc(sizeof(float)*((B) * (B)));
  float* T3 = (float*)malloc(sizeof(float)*((B) * (B)));

  int T1_index = 0;
  int T2_index = 0;
  int T3_index = 0;

  long G = gridDim.x;
  long n = N/G;
  long start = n * blockIdx.x * (B*B);
  long end = start + (G*B*B);

  if (blockIdx.x != 0) {
    T1_index = 0;
    for (int i = (blockIdx.x-1)*B*B; i < ((blockIdx.x-1)*B*B) + B*B; i++) {
      T1[T1_index] = R2[i];
      T1_index++;
    }
  }
  else {
    int row_loc = 0;
    int col_loc = 0;

    for (int i = 0; i < B*B; i++) {
      if (row_loc == col_loc) {
        T1[i] = 1.0;
      }
      if ((i+1) % B == 0 && i != 0) {
        row_loc++;
        col_loc = 0.0;
        continue;
      }
      col_loc++;
    }

    for (int i = 0; i < N; i ++) {
      T2_index = 0;
      for (int j = (i*B*B) + blockIdx.x*B*B; j < ((i*B*B)+blockIdx.x*B*B) + B*B; j++) {
        T2[T2_index] = Y[j];
        T2_index++;
      }

      multiplyMatrices(T1,T2,T3,B);

      __syncthreads();

      T3_index = 0;
      for (int j = (i*B*B) + blockIdx.x*B*B; j < ((i*B*B)+blockIdx.x*B*B) + B*B; j++) {
        Y[j] = T3[T3_index];
        T3_index++;
      }

      for (int j = 0; j < B*B; j++) {
        T1[j] = T3[j];
      }

    }
  }
}

__device__ void multiplyMatrices(float* matA, float* matB, float* matC, long width) {

  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
        float sum = 0.0;
        for (int k = 0; k < width; k++)
            sum = sum + matA[i * width + k] * matB[k * width + j];
            matC[i * width + j] = sum;
          }
  }

}
