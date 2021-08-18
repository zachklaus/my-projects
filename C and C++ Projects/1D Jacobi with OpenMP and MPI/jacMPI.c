// Author: Zachary Klausner
// Email: zachklau@rams.colostate.edu
// Class: CS475
// Assignment: PA6

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include "timer.h"

int main(int argc, char **argv) {

   int id;               /* Process rank */
   int p;                /* Number of processes */
   int iterations_counter = 0;
   int BLOCK_SIZE, begin, end, contents;

   int     n;
   int     t;
   int     m = 2000;
   double  *prev, *cur;
   double *rec_buff;

   // Timer
   double  time;

   // temporary variables
   int i,j;
   int vp = -1; //verbose
   int k = 100;
   double  *temp;

   MPI_Init (&argc, &argv);
   MPI_Barrier(MPI_COMM_WORLD);
   MPI_Comm_rank (MPI_COMM_WORLD, &id);
   MPI_Comm_size (MPI_COMM_WORLD, &p);

   // Check commandline args.
   if ( argc > 1 ) {
      n = atoi(argv[1]);
   } else {
      printf("Usage : %s [N]\n", argv[0]);
      exit(1);
   }
   if ( argc > 2 ) {
      m = atoi(argv[2]);
   }
   if ( argc > 3 ) {
      k = atoi(argv[3]);
   }
   if (argc > 4) {
     vp = atoi(argv[4]);
   }

   BLOCK_SIZE = n/p;

   // Memory allocation for data array.
   prev  = (double *) malloc( sizeof(double) * BLOCK_SIZE);
   cur   = (double *) malloc( sizeof(double) * BLOCK_SIZE);
   rec_buff = (double *) malloc(n*sizeof(double));

   if ( prev == NULL || cur == NULL ) {
      printf("[ERROR] : Fail to allocate memory.\n");
      exit(1);
   }

   // Initialization
   contents = id*BLOCK_SIZE;

   for ( i=0 ; i < BLOCK_SIZE ; i++ ) {
         prev[i] = contents;
         contents++;
      }

   begin = id * BLOCK_SIZE;
   end = (begin + BLOCK_SIZE) - 1;

   cur[0]  = begin;
   cur[BLOCK_SIZE-1]  = end;

   initialize_timer();
   start_timer();

   // Computation

   for (t = 0; t < m; t++) {
      for ( i=1 ; i < BLOCK_SIZE-1 ; i++ ) {
            cur[i] = (prev[i-1]+prev[i]+prev[i+1])/3;
       }
      temp = prev;
      prev = cur;
      cur  = temp;

      if (id == 0) {
        iterations_counter++;
      }
   }

   MPI_Barrier(MPI_COMM_WORLD);
   MPI_Gather(prev, BLOCK_SIZE, MPI_DOUBLE, rec_buff, BLOCK_SIZE, MPI_DOUBLE, 0, MPI_COMM_WORLD);

   stop_timer();
   time = elapsed_time();

   if(vp == id){
     for(i=0;i<BLOCK_SIZE;i++) printf("%f ",prev[i]);
     printf("\n");
   }
   else {
     if (id == 0) {
       printf("first, mid, last: %f %f %f\n",rec_buff[0], rec_buff[n/2-1], rec_buff[n-1]);
     }
   }

   if (id == 0) {
     printf("Data size : %d  , #iterations : %d , time : %lf sec\n", n, t, time);
   }

   MPI_Barrier(MPI_COMM_WORLD);

EXIT:
   MPI_Finalize();

   return 0;

}
