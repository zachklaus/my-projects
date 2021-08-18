# Vector Reduction with CUDA

- The objective of this project was to implement parallelized vector reduction using CUDA.
- The program divides a given vector betwen the threads of the GPU and calculates the local max for each thread in parallel. The overall max of the vector is then calculated over these local maxes.
- I edited the provided code of VecMaxKernel01.cu to implement this.
- VecMaxKernel01.cu is the CUDA kernel code that is run on the GPU.
- The majority of the other source code was provided to me and is the host CPU code.