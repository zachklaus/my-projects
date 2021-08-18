
#include "matmultKernel.h"

__global__ void MatMulKernel(Matrix A, Matrix B, Matrix C){


  float *Asub, *Bsub, *Csub;

  int thread_row = threadIdx.y;
  int thread_col = threadIdx.x;
  int block_row = blockIdx.y;
  int block_col = blockIdx.x;

  Csub = &C.elements[C.stride * FOOTPRINT_SIZE * block_row + FOOTPRINT_SIZE * block_col];

  float Cvalue0 = 0;
  float Cvalue1 = 0;
  float Cvalue2 = 0;
  float Cvalue3 = 0;

  for (int m = 0;  m < (A.width / FOOTPRINT_SIZE); ++m){

    Asub = &A.elements[A.stride * FOOTPRINT_SIZE * block_row + FOOTPRINT_SIZE * m];
    Bsub = &B.elements[B.stride * FOOTPRINT_SIZE * m + FOOTPRINT_SIZE * block_col];

    __shared__ float shared_A[FOOTPRINT_SIZE][FOOTPRINT_SIZE];
    __shared__ float shared_B[FOOTPRINT_SIZE][FOOTPRINT_SIZE];

    shared_A[thread_row][thread_col] = Asub[thread_row * A.stride + thread_col];
    shared_A[thread_row + blockDim.y][thread_col + blockDim.x] = Asub[(thread_row + blockDim.y )* A.stride + (thread_col + blockDim.x)];
    shared_A[thread_row + 2 * blockDim.y][thread_col + 2 * blockDim.x] = Asub[(thread_row + 2 * blockDim.y )* A.stride + (thread_col + 2 * blockDim.x)];
    shared_A[thread_row + 3 * blockDim.y][thread_col + 3 * blockDim.x] = Asub[(thread_row + 3 * blockDim.y )* A.stride + (thread_col + 3 * blockDim.x)];

    shared_B[thread_row][thread_col] = Bsub[thread_row * B.stride + thread_col];
    shared_B[thread_row + blockDim.y][thread_col + blockDim.x] = Bsub[(thread_row + blockDim.y )* B.stride + (thread_col + blockDim.x)];
    shared_B[thread_row + 2 * blockDim.y][thread_col + 2 * blockDim.x] = Bsub[(thread_row + 2 * blockDim.y )* B.stride + (thread_col + 2 * blockDim.x)];
    shared_B[thread_row + 3 * blockDim.y][thread_col + 3 * blockDim.x] = Bsub[(thread_row + 3 * blockDim.y )* B.stride + (thread_col + 3 * blockDim.x)];

    __syncthreads();

#pragma unroll
    for(int e=0; e<FOOTPRINT_SIZE; ++e)
       Cvalue0 += shared_A[thread_row][e] * shared_B[e][thread_col];

#pragma unroll
    for(int e=0; e<FOOTPRINT_SIZE; ++e)
       Cvalue1 += shared_A[thread_row + blockDim.y][e] * shared_B[e][thread_col + blockDim.x];

#pragma unroll
    for(int e=0; e<FOOTPRINT_SIZE; ++e)
      Cvalue2 += shared_A[thread_row + 2 * blockDim.y][e] * shared_B[e][thread_col + 2 * blockDim.x];

#pragma unroll
    for(int e=0; e<FOOTPRINT_SIZE; ++e)
      Cvalue3 += shared_A[thread_row + 3 * blockDim.y][e] * shared_B[e][thread_col + 3 * blockDim.x];

    __syncthreads();
  }

  Csub[thread_row * C.stride + thread_col] = Cvalue0;
  Csub[(thread_row + blockDim.y) * C.stride + (thread_col + blockDim.x)] = Cvalue1;
  Csub[(thread_row + 2 * blockDim.y) * C.stride + (thread_col + 2 * blockDim.x)] = Cvalue2;
  Csub[(thread_row + 3 * blockDim.y) * C.stride + (thread_col + 3 * blockDim.x)] = Cvalue3;
}
