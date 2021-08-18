# MMScan with CUDA

- This progam implements the MMScan algorithm to calculate the the "prefix-op" or "scan" of a sequence of matrices.
- The basics of the algorithm are that is uses the fact that matrix multiplication is associative and so because of this the work of the matrix multiplication can be divided between GPU threads and made parallel.
- I developed the majority of the code in this project.
- MMScan.cu is the CPU host code and uses the GPU kernel code found in MMScan-wrapper.cu to implement the MMScan algorithm using CUDA.