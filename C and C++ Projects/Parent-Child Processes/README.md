# Parent-Child Processes

- This project demonstrates the concept of parent and child processes in an Operating System using C.
- ParentProgram.c: This is the driver program which is used to open and read the file and also to invoke the child processes.
- Executor.c:  Executes a UNIX command passed as an argument to it. Should be invoked by the child processes only.
- FileOpener.c: Used by ParentProgram.c to open and read the contents of a file into a string.
- Makefile: For compiling, cleaning and taring.
