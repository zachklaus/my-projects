# 1D Jacobi with OpenMP and MPI

- The goal of this project was to parallelize the sequential version of the 1D Jacobi problem using both OpenMP and MPI.
- Parallelization using OpenMP makes it so that the program is parallel across the cores of a multi-core CPU.
- Parallelization using MPI makie it so that the program is parallel across multiple CPUs.
- The objective was to then compare the performance of these two methods of parallelization.
- I was given jac.c (the sequential version of the progam) and implemented jacMPI.c and jacOMP.c. 
- jacMPI.c is the parallel version of jac.c using MPI.
- jacOMP.c is the parallel verison of jac.c using OpenMP.