###
### CS475 Fall 17
### Makefile for CUDA PA5 vecmax
### By Wim Bohm, and Waruna Ranasinghe
###

OPTIONS   :=  -O3 --ptxas-options -v --gpu-architecture=sm_61 --compiler-bindir /usr/local/gcc-6.4.0/bin  -std=c++11 -I /s/bach/g/under/zachklau/cuda-patches/include
EXECS :=  vecMax00 vecMax01
all:$(EXECS)

clean:
	rm -f $(EXECS) *.o

timer.o : timer.cu timer.h
	nvcc $< -c -o $@ $(OPTIONS)

vecMaxKernel00.o : vecMaxKernel00.cu vecMaxKernel.h
	nvcc $< -c -o $@ $(OPTIONS)

vecMax00 : vecMax.cu  vecMaxKernel.h vecMaxKernel00.o timer.o
	nvcc $< vecMaxKernel00.o -o $@ $(LIB) timer.o $(OPTIONS)

vecMaxKernel01.o : vecMaxKernel01.cu vecMaxKernel.h
	nvcc $< -c -o $@ $(OPTIONS)

vecMax01 : vecMax.cu  vecMaxKernel.h vecMaxKernel01.o timer.o
	nvcc $< vecMaxKernel01.o -o $@ $(LIB) timer.o $(OPTIONS)

tar: Makefile vecMax.cu vecMaxKernel00.cu vecMaxKernel01.cu vecMaxKernel.h 
	tar cf PA4.tar vecMax.cu vecMaxKernel00.cu vecMaxKernel01.cu vecMaxKernel.h
