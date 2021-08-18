// Author: Zachary Klausner
// Email: zachklau@rams.colostate.edu
// Class: CS475
// Assignment: PA6

#include <stdio.h>
#include <stdlib.h>
#include "timer.h"

int main(int argc, char **argv) {

   int     n;
   int     t;
   int iterations_counter = 0;
   int     m = 2000;
   double  *prev, *cur;

   // Timer
   double  time;

   // temporary variables
   int i,j;
   int v = 0; //verbose
   double  *temp;

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
      v = 1;
   }

   // Memory allocation for data array.
   prev  = (double *) malloc( sizeof(double) * n);
   cur   = (double *) malloc( sizeof(double) * n);
   if ( prev == NULL || cur == NULL ) {
      printf("[ERROR] : Fail to allocate memory.\n");
      exit(1);
   }

   // Initialization
   #pragma omp parallel
   {

   #pragma omp for schedule(static)
   for ( i=0 ; i < n ; i++ ) {
         prev[i] = i;
      }

   cur[0]  = 0;
   cur[n-1]  = n-1;

   initialize_timer();
   start_timer();

   // Computation

   #pragma omp for schedule(static)
   for (t = 0; t < m; t++) {
      for ( i=1 ; i < n-1 ; i++ ) {
            cur[i] = (prev[i-1]+prev[i]+prev[i+1])/3;
       }
      temp = prev;
      prev = cur;
      cur  = temp;

      #pragma omp critical
      iterations_counter++;
  }

  }

   stop_timer();
   time = elapsed_time();

   if(v){
     for(i=0;i<n;i++) printf("%f ",prev[i]);
     printf("\n");
   }
   else
     printf("first, mid, last: %f %f %f\n",prev[0], prev[n/2-1], prev[n-1]);

   printf("Data size : %d  , #iterations : %d , time : %lf sec\n", n, iterations_counter, time);
}
