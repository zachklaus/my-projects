# Parallel Mandelbrot

- This progam is a demonstration of how OpenMP can be used to dramatically speed up sequential computations.
- The progam mandelbrot.c computes and outputs a Mandelbrot fractal to a .PGM file.
- I was tasked with placing OpenMP compiler directives within the code to speed up the computions.
- I found that the computations within the triple-nested for-loop could be done independent of iteration and so could be made parallel.
- By placing one before the for-loop I was able to make it parallel and greatly reduce the amount of time required for the program to execute.
- All source code was provided to me and I placed compiler directives to make sections of the program parallel.