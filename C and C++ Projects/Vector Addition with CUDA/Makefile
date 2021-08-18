OPTIONS   :=  -O3 --ptxas-options -v --gpu-architecture=sm_61 --compiler-bindir /usr/local/gcc-6.4.0/bin -std=c++11 #-I/s/chopin/l/grad/mrdragan/cuda-patches/include replaced with your path and uncomment 

EXECS :=  vecadd00 
all:$(EXECS)

clean:
	rm -f $(EXECS) *.o

timer.o : timer.cu timer.h
	nvcc $< -c -o $@ $(OPTIONS)


vecaddKernel00.o : vecaddKernel00.cu
	nvcc $< -c -o $@ $(OPTIONS)

vecadd00 : vecadd.cu vecaddKernel.h vecaddKernel00.o timer.o
	nvcc $< vecaddKernel00.o -o $@ $(LIB) timer.o $(OPTIONS)


