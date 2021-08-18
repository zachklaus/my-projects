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

// Common Macros
#define mallocCheck(v,s,d) if ((v) == NULL) { printf("Failed to allocate memory for %s : size=%lu\n", "sizeof(d)*(s)", sizeof(d)*(s)); exit(-1); }
#define EPSILON 1.0E-6
#define G 1000
#define S 16

__global__ void Phase_1(float*, float*, long, long);
__global__ void Phase_2(float*, float*, long, long, long);
__global__ void Phase_3(float*, float*, long, long);

__device__ void multiplyMatrices(float*, float*, float*, int);

void print_matrix_array(float*, long, long, char*);
void MMScan(float***, float***, long, long, long);

//main
int main(int argc, char** argv) {
  //Check number of args
  if (argc <= 2) {
    printf("Number of argument is smaller than expected.\n");
    printf("Expecting N,B\n");
    exit(0);
  }

  if (atoi(argv[1]) % G != 0) {
    printf("N is not divisible by G = %d!\n", G);
    exit(0);
  }

  //char *end = 0;
  char *val = 0;
  //Read Parameters
  //Initialization of N
  val = argv[1];
  long N = atoi(val);

  //Initialization of B
  val = argv[2];
  long B = atoi(val);

  long tuning = 0;
  //Additional args?
  if(argc > 3)
    {
      val = argv[3];
      tuning = atoi(val);
    }

  ///Parameter checking
  if (!((N >= 1 && B >= 1))) {
    printf("The value of parameters are not valid.\n");
    exit(-1);
  }

  //CPU Memory Allocation
  long n, i, j; //k;

  float* _lin_X_seq = (float*)malloc(sizeof(float)*((N) * (B) * (B)));
  mallocCheck(_lin_X_seq, ((N) * (B) * (B)), float);
  float*** X_seq = (float***)malloc(sizeof(float**)*(N));
  mallocCheck(X_seq, (N), float**);
  for (n=0;n < N; n++) {
    X_seq[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(X_seq[n], (B), float*);
    for (i=0;i < B; i++) {
      X_seq[n][i] = &_lin_X_seq[(n*((B) * (B))) + (i*(B))];
    }
  }

  float* _lin_Y_seq = (float*)malloc(sizeof(float)*((N) * (B) * (B)));
  mallocCheck(_lin_Y_seq, ((N) * (B) * (B)), float);
  float*** Y_seq = (float***)malloc(sizeof(float**)*(N));
  mallocCheck(Y_seq, (N), float**);
  for (n=0;n < N; n++) {
    Y_seq[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(Y_seq[n], (B), float*);
    for (i=0;i < B; i++) {
      Y_seq[n][i] = &_lin_Y_seq[(n*((B) * (B))) + (i*(B))];
    }
  }

  float* _lin_X = (float*)malloc(sizeof(float)*((N) * (B) * (B)));
  mallocCheck(_lin_X, ((N) * (B) * (B)), float);
  float*** X = (float***)malloc(sizeof(float**)*(N));
  mallocCheck(X, (N), float**);
  for (n=0;n < N; n++) {
    X[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(X[n], (B), float*);
    for (i=0;i < B; i++) {
      X[n][i] = &_lin_X[(n*((B) * (B))) + (i*(B))];
    }
  }

  float* _lin_Y = (float*)malloc(sizeof(float)*((N) * (B) * (B)));
  mallocCheck(_lin_Y, ((N) * (B) * (B)), float);
  float*** Y = (float***)malloc(sizeof(float**)*(N));
  mallocCheck(Y, (N), float**);
  for (n=0;n < N; n++) {
    Y[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(Y[n], (B), float*);
    for (i=0;i < B; i++) {
      Y[n][i] = &_lin_Y[(n*((B) * (B))) + (i*(B))];
    }
  }

  //R1 and R2 allocations
  float* _lin_R1 = (float*)malloc(sizeof(float)*((G) * (B) * (B)));
  mallocCheck(_lin_R1, ((G) * (B) * (B)), float);
  float*** R1 = (float***)malloc(sizeof(float**)*(G));
  mallocCheck(R1, (G), float**);
  for (n=0;n < G; n++) {
    R1[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(R1[n], (B), float*);
    for (i=0;i < B; i++) {
      R1[n][i] = &_lin_R1[(n*((B) * (B))) + (i*(B))];
    }
  }

  float* _lin_R2 = (float*)malloc(sizeof(float)*((G) * (B) * (B)));
  mallocCheck(_lin_R2, ((G) * (B) * (B)), float);
  float*** R2 = (float***)malloc(sizeof(float**)*(G));
  mallocCheck(R2, (G), float**);
  for (n=0;n < G; n++) {
    R2[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(R2[n], (B), float*);
    for (i=0;i < B; i++) {
      R2[n][i] = &_lin_R2[(n*((B) * (B))) + (i*(B))];
    }
  }

  float* _lin_Temp = (float*)malloc(sizeof(float)*((N) * (B) * (B)));
  mallocCheck(_lin_Temp, ((N) * (B) * (B)), float);
  float*** Temp = (float***)malloc(sizeof(float**)*(N));
  mallocCheck(Temp, (N), float**);
  for (n=0;n < N; n++) {
    Temp[n] = (float**)malloc(sizeof(float*)*(B));
    mallocCheck(Temp[n], (B), float*);
    for (i=0;i < B; i++) {
      Temp[n][i] = &_lin_Temp[(n*((B) * (B))) + (i*(B))];
    }
  }

  //Initialization of rand
  srand((unsigned)time(NULL));

  //Input Initialization

#if defined (RANDOM)
  float x, y; //tmp;
  x = (float) rand();
  for(n=0; n <= N-1; n+=1) {
    y = (float) rand();
    for(i=0; i <= B-1; i+=1)
	   for(j=0; j <= B-1; j+=1) {
	      X[n][i][j] = y/(B*x);
        X_seq[n][i][j] = y/(B*x);
      }
      x = y;
    }
#else  // not random
  for(i=0; i <= B-1; i+=1)
    for(j=0; j <= B-1; j+=1) {
      X[0][i][j] = (float) 1.0;
      X_seq[0][i][j] = (float) 1.0;
    }  // all 1s
  for(n=1; n <= N-1; n+=1)
  {
      for(i=0; i <= B-1; i+=1)
	{
	  for(j=0; j <= B-1; j+=1)
	    {
#if defined (INTERACTIVE)
	      {
          float temp;
		      printf("X[%ld][%ld][%ld]= ", n, i, j);
		      scanf("%f", &temp);
          X[n][i][j] = temp;
          X_seq[n][i][j] = temp;
	      }
#else // neither random not interactive, i.e., default
{
	      X[n][i][j] = (float) (n+1)/((float) (B*n));
        X_seq[n][i][j] = (float) (n+1)/((float) (B*n));
}
#endif
	    }
	}
  }

#endif

  //Timing
  struct timeval time;
  double elapsed_time1, elapsed_time2;

  //Call the main computation

  //**************************************************************************//
  /*                     START OF THE SCAN COMPUTATION                        */
  //**************************************************************************//
  /* int p = omp_get_num_procs(); */
  /* printf("There are %ld threads\n", p); */

  gettimeofday(&time, NULL);
  elapsed_time1 = (((double) time.tv_sec) + ((double) time.tv_usec)/1000000);

  cudaSetDevice(0);

  // printf("X:\n");
  // print_matrix_array(_lin_X, N, B, "X");

  // printf("\nBEFORE:\n");
  // print_matrix_array(_lin_R1, G, B, "R1");
  //R1[0][0][0] = 5.5;

  float* X_GPU;
  float* Y_GPU;
  float* R1_GPU;
  float* R2_GPU;

  cudaMalloc(&X_GPU, (sizeof(float)*(N*B*B)));
  cudaMalloc(&Y_GPU, (sizeof(float)*(N*B*B)));
  cudaMalloc(&R1_GPU, (sizeof(float)*(G*B*B)));
  cudaMalloc(&R2_GPU, (sizeof(float)*(G*B*B)));

  cudaMemcpy(X_GPU, _lin_X, (sizeof(float)*(N*B*B)), cudaMemcpyHostToDevice);
  cudaMemcpy(Y_GPU, _lin_X, (sizeof(float)*(N*B*B)), cudaMemcpyHostToDevice);
  cudaMemcpy(R1_GPU, _lin_R1, (sizeof(float)*(G*B*B)), cudaMemcpyHostToDevice);
  cudaMemcpy(R2_GPU, _lin_R2, (sizeof(float)*(G*B*B)), cudaMemcpyHostToDevice);

  // printf("\nBEFORE:\n");
  // print_matrix_array(_lin_R1, G, B, "R1");

  Phase_1<<<G,S>>>(X_GPU, R1_GPU, N, B);

  cudaMemcpy(_lin_R1, R1_GPU, (sizeof(float)*(G*B*B)), cudaMemcpyDeviceToHost);

  // printf("\nR1 AFTER:\n");
  // print_matrix_array(_lin_R1, G, B, "R1");

  Phase_2<<<1,S>>>(R1_GPU, R2_GPU, N, B, G);

  cudaMemcpy(_lin_R2, R2_GPU, (sizeof(float)*(G*B*B)), cudaMemcpyDeviceToHost);

  // printf("\nR2 AFTER:\n");
  // print_matrix_array(_lin_R2, G, B, "R2");

  Phase_3<<<G,S>>>(R2_GPU, Y_GPU, N, B);

  cudaMemcpy(_lin_Y, Y_GPU, (sizeof(float)*(N*B*B)), cudaMemcpyDeviceToHost);

  // printf("\nY AFTER:\n");
  // print_matrix_array(_lin_Y, N, B, "Y");

  gettimeofday(&time, NULL);
  elapsed_time1 = (((double) time.tv_sec) + ((double) time.tv_usec)/1000000) - elapsed_time1;

  float ***tmp_ptr = Temp;
  Temp = Y;  Y = tmp_ptr; // swap Temp and Y so that the next call computes Y
			  // with the standard sequential algorithm

  gettimeofday(&time, NULL);
  elapsed_time2 = (((double) time.tv_sec) + ((double) time.tv_usec)/1000000);

  // the provided seqential algorithm

  MMScan(X_seq, Y_seq, 0, N-1, B);

  gettimeofday(&time, NULL);
  elapsed_time2 = (((double) time.tv_sec) + ((double) time.tv_usec)/1000000) - elapsed_time2;

  int xDirection,yDirection,zDirection;

  for (int i = 0; i < N*B*B; i++) {
    zDirection = i % B;
    yDirection = (i / B) % B;
    xDirection = i / (B * B);

    Y[xDirection][yDirection][zDirection] = _lin_Y[i];

  }

  //**************************************************************************//
  /*                       END OF THE SCAN COMPUTATION                        */
  /*                                                                          */
  /*                    PRINT OUTPUTS (DEPENDING ON FLAGS)                    */
  //**************************************************************************//

#ifdef INTERACTIVE
  //Print Outputs Interactively

  for(n=0; n <= N-1; n+=1)
    {
      printf("Y[%ld][i][j]= \n", n);
      for(i=0; i <= B-1; i+=1)
	{
	  for(j=0; j <= B-1; j+=1)
	    {
	      printf("%10g ", Y[n][i][j]);
	    }
	  printf("\n");
	}
      printf("\n");
    }
#endif

#ifdef VERBOSE
  //Print Inputs and Outputs (leading and trailing, no more than 5 each)

  //  First print the first five (X, Y)
  for(n=0; n <= min(N-1, 5); n+=1)
    {
      printf("\tX[%ld][i][j], \tY[%ld][i][j], \n", n, n);
      for(i=0; i <= B-1; i+=1)
	{
	  for(j=0; j <= B-1; j+=1)
	    {
	      printf("%10g ", X[n][i][j]);
	    }
	  printf("\t");
	  for(j=0; j <= B-1; j+=1)
	    {
	      printf("%10g ", Y[n][i][j]);
	    }
	  printf("\n");
	}
      printf("\n");
    }

  //  Then print the last five (X, Y)
  for(n=max(5, N-5); n <= N-1; n+=1)
    {
      printf("\tX[%ld][i][j], \tY[%ld][i][j], \n", n, n);
      for(i=0; i <= B-1; i+=1)
	{
	  for(j=0; j <= B-1; j+=1)
	    {
	      printf("%10g ", X[n][i][j]);
	    }
	  printf("\t");
	  for(j=0; j <= B-1; j+=1)
	    {
	      printf("%10g ", Y[n][i][j]);
	    }
	  printf("\n");
	}
      printf("\n");
    }
#endif

#if defined CHECKING
  // Compare the values in Y (CUDA result) and Y_seq (sequential result)
  long error_count = 0;

  for(n=0; n <= N-1; n+=1)
    {
      for(i=0; i <= B-1; i+=1)
	{
	  for(j=0; j <= B-1; j+=1)
	    {
	      if (fabs(Y[n][i][j]-Y_seq[n][i][j]) > EPSILON)
		{error_count += 1;
		  printf ("Y[%ldl][%ldl][%ldl] = %f, \tY_seq[%ldl][%ldl][%ldl] = %f\n",
			  n, i, j, Y[n][i][j], n, i, j, Y_seq[n][i][j]);
		}
	    }
	}
    }
  printf("The total number of errors is %ld\n", error_count);
#endif

  // timing information
  printf("Execution time for DNC:\t%lf sec.\n", elapsed_time1);
  printf("Execution time for SEQ:\t%lf sec.\n", elapsed_time2);

  //Memory Free
   free(_lin_X);
   for (n=0;n < N; n++) {
     free(X[n]);
   }
   free(X);

   free(_lin_Y);
   for (n=0;n < N; n++) {
     free(Y[n]);
  }
   free(Y);

   free(_lin_R1);
   for (n=0;n < G; n++) {
     free(R1[n]);
  }
   free(R1);

   free(_lin_R2);
   for (n=0;n < G; n++) {
     free(R2[n]);
  }
   free(R2);

   free(_lin_Temp);
   for (n=0;n < N; n++) {
     free(Temp[n]);
  }
   free(Temp);

   cudaFree(R1_GPU);
   cudaFree(R2_GPU);
   cudaFree(X_GPU);
   cudaFree(Y_GPU);

  return EXIT_SUCCESS;
}


//Common Macro undefs
#undef EPSILON
