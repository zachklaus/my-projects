OBJS = timer.o
H_FILE = timer.h

EXEC = jac jacOMP jacMPI

all: $(EXEC)

jac: jac.c $(OBJS) $(H_FILE)
	gcc -O3 -o $@ jac.c $(OBJS) $(MATHFLAG)

jacOMP: jacOMP.c $(OBJS) $(H_FILE)
	gcc -O3 -o $@ -fopenmp jacOMP.c $(OBJS) $(MATHFLAG)

jacMPI: jacMPI.c $(OBJS) $(H_FILE)
	mpicc -o $@ jacMPI.c $(OBJS) $(MATHFLAG) -O3

clean:
	rm -f $(EXEC) PA6.tar

tar:
	tar cvf PA6.tar Makefile jac.c jacOMP.c jacMPI.c report.pdf timer.c timer.h
