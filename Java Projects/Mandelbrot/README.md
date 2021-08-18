# Mandelbrot

- In this project I was tasked with implementing the parallel version of the Mandelbrot computation for fractals.
- The purpose was to gain experience with Java multi-threading.
- SequentialMandelbrot.java: Sequential version of the Mandelbrot computation.
- ParallelMandelbrot.java: Parallel version of the Mandelbrot computation. Contains an inner class called Mandelbrot which implements Runnable. This inner class is used to compute the Mandelbrot fractal more quickily than the sequential version by dividing work between threads running in parallel.