###
### CS475 Fall 17
### Makefile for CUDA PA5 matmult
### By Wim Bohm, and Waruna Ranasinghe
###

OPTIONS   :=  -O3 --ptxas-options -v --gpu-architecture=sm_61 --compiler-bindir /usr/local/gcc-6.4.0/bin  -std=c++11 -I /s/bach/g/under/zachklau/cuda-patches/include
EXECS :=  matmult00 matmult01
all:$(EXECS)

clean:
	rm -f $(EXECS) *.o

timer.o : timer.cu timer.h
	nvcc $< -c -o $@ $(OPTIONS)

matmultKernel00.o : matmultKernel00.cu matmultKernel.h
	nvcc $< -c -o $@ $(OPTIONS)

matmult00 : matmult.cu  matmultKernel.h matmultKernel00.o timer.o
	nvcc $< matmultKernel00.o -o $@ $(LIB) timer.o $(OPTIONS)

matmultKernel01.o : matmultKernel01.cu matmultKernel.h
	nvcc $< -c -o $@ $(OPTIONS)

matmult01 : matmult.cu  matmultKernel.h matmultKernel01.o timer.o
	nvcc $< matmultKernel01.o -o $@ $(LIB) timer.o $(OPTIONS) -DFOOTPRINT_SIZE=32

tar: matmultKernel00.cu matmultKernel01.cu matmultKernel.h matmult.cu Makefile 
	tar cf PA4.tar  matmultKernel00.cu matmultKernel01.cu matmultKernel.h matmult.cu Makefile
