/*/////////////////////////////////////////////////////////////////////////////
//
// File name : sieve.c
// Author    : Nissa Osheim
// Date      : 2010/19/10
// Desc      : Finds the primes up to N
//
// updated Wim Bohm
/////////////////////////////////////////////////////////////////////////////*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "timer.h"

int main(int argc, char **argv) {

  long N = 100;
  
  if (argc > 1)
  {
    N = atoi(argv[1]);
  }

  long blockSize;
   
  if (argc == 3) 
  { 
    blockSize = atoi(argv[2]);
  }
  else 
  {
    blockSize = 100l;
  }
  
  //printf("blockSize: %ld\n",blockSize);

  int nPrimes[1000000];
  int block [blockSize];

  for (int i = 0; i < (int) sqrt(N); i++) 
  {
    nPrimes[i] = 0;
  }

  for (int i = 0; i < blockSize; i++) 
  {
    block[i] = 0;
  }

  int primes[1000000];
  long count;

  long size = (N+1)*sizeof(char);
  char *mark = (char *)malloc(size);

  /* Time */

  double time;

  /* Start Timer */

  initialize_timer ();
  start_timer();
   
  long rootNum = (int) sqrt(N + .0);
  for (long i=2; i<= rootNum; ++i)
  {
    if (!nPrimes[i])
    {
      primes[count++] = i;
      if (i* 1ll * i <= rootNum)
      {
        for (long j=i*i; j<=rootNum; j+=i)
        {
          nPrimes[j] = 1;
        }
      }
    }
  }

  long primesCount = 0;
  long markIndex = 0;
  for (long k=0, kMax = N/blockSize; k<= kMax; ++k) 
  {
    memset(block, 0, sizeof(block));
    long begin = k * blockSize;
    for (long i=0; i<count; ++i) 
    {
      long startIndex = (begin + primes[i] - 1) / primes[i];
      long j = fmax(startIndex,2) * primes[i] - begin;
      for (; j < blockSize; j+=primes[i])
      {
        block[j] = 1;
      }
    }
    if (k == 0) 
    {
      block[0] = block[1] = 1;
    }
    for (long i=0; i < blockSize && begin+i <= N; ++i) 
    {
      mark[markIndex] = block[i];
      markIndex++;
      if (!block[i]) 
      {
        mark[i] = 0;
        ++primesCount;
      }
    }
  }
  
  //printf("primesCount: %ld\n",primesCount);

  /* stop timer */
  stop_timer();
  time=elapsed_time ();
   
  long i,j,n;

  /*number of primes*/
  count = 1;
  for(i = 3; i <=N; i+=2){
    if(mark[i] == 0) {
      //  printf("\t prime %ld  \n",i );
      ++count;
    }

  }
  printf("There are %ld primes less than or equal to %ld\n", primesCount, N);
  /* print results */
  printf("First three primes:");
  j = 1;
  printf("%d ", 2);
  for (i=3 ; i <= N && j < 3; i+=2 ) {
    if (mark[i]==0){
          printf("%ld ", i);
          ++j;
    }
  }
  printf("\n");

  printf("Last three primes:");
  j = 0;
  n=(N%2?N:N-1);
  for (i = n; i > 1 && j < 3; i-=2){
    if (mark[i]==0){
      printf("%ld ", i);
      j++;
    }
  }
  printf("\n");


  printf("elapsed time = %lf (sec)\n", time);

  free(mark);
  
  return 0;
  
  }

  
