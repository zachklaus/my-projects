CFLAGS=  -O3 --ptxas-options -v --gpu-architecture=sm_61 --compiler-bindir /usr/local/gcc-6.4.0/bin  -std=c++11 -I /s/bach/g/under/zachklau/cuda-patches/include
LIBRARIES=-lm
CC=nvcc
AR=xiar
OBJS = MMScan.o

all: plain debug verb int rand verb-rand check DNC DNC-int

plain: $(OBJS)
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA $(OBJS) $(CFLAGS) $(LIBRARIES)

# The assignment description only stated that the output file was was MMScanCUDA So,
# the other types of output files similar to those in PA2 were not supported

debug: $(OBJS)
	$(CC) MMScan-wrapper.cu -w -g -o MMScanCUDA.debug $(OBJS) $(CFLAGS) $(LIBRARIES)

verb:
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA.verb $(OBJS) $(CFLAGS) $(LIBRARIES) -DVERBOSE

int: $(OBJS)
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA.int $(OBJS) $(CFLAGS) $(LIBRARIES) -DINTERACTIVE

rand:
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA.rand $(OBJS) $(CFLAGS) $(LIBRARIES) -DRANDOM

verb-rand:
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA.verb-rand $(OBJS) $(CFLAGS) $(LIBRARIES) -DVERBOSE -DRANDOM

check:
	$(CC) MMScan-wrapper.cu -w -o MMScanCUDA.check $(OBJS) $(CFLAGS) $(LIBRARIES) -DCHECKING

MMScan.o: MMScan.cu
	$(CC) MMScan.cu -w -o MMScan.o $(CFLAGS) $(LIBRARIES) -c

# MMScanDNC.o: MMScan.cu
# 	$(CC) MMScan.cu -w -o MMScanDNC.o $(CFLAGS) $(LIBRARIES) -c -DDNC -DOTHER_FLAGS # (as needed)
#
# # Additional lines for DNC (& DNC.int for testing)
#
# DNC: MMScanDNC.o
# 	$(CC) MMScan-wrapper.cu -o MMScanDNC MMScanDNC.o $(CFLAGS) $(LIBRARIES) -DNC
#
# DNC-int:
# 	$(CC) MMScan-wrapper.cu -o MMScanDNC.int MMScanDNC.o $(CFLAGS) $(LIBRARIES) -DINTERACTIVE


## Add additional line for different versions like verb, rand, etc. (for debugging/testing)

## Then add simolar sets of lines for other executables

tar: Makefile MMScan.cu MMScan-wrapper.cu report.pdf
	tar cf PA5.tar Makefile MMScan.cu MMScan-wrapper.cu report.pdf

clean:
	rm -f *.o MMScanCUDA MMScanCUDA.verb MMScanCUDA.int MMScanCUDA.rand MMScanCUDA.verb-rand MMScanCUDA.check MMScanCUDA.debug
	##MMScanDNC MMScanDNC.int
