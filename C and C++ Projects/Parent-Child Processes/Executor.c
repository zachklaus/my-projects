//Author: Zachary Klausner
//Class: CS370
//Assignment: HW4
//Email: zachklau@rams.colostate.ed

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>

int main(int argc, char **argv)
{
    
	int memory_segment = 0;
	void *pointer = 0;
	
	//access shared memory segment
	memory_segment = shm_open("SHARED", O_RDWR, 0666);
	
	//check if there is a failure in accessing shared memory segment
	if (memory_segment == -1) {
		
		printf("shared memory failed\n");
		exit(-1);
		
	}	
	
	//make pointer point to shared memory segment
	pointer = mmap(0, 256, PROT_WRITE, MAP_SHARED, memory_segment, 0);
	
	//check if map creation failed
	if (pointer == MAP_FAILED) {
		
		perror("Map failed");
		exit(0);
		
	}
	
	//execute command and get status
	int system_status = system(argv[1]);

	printf("\nExecutor: Given Command is: \'%s\' with process id %d\n", argv[1], getpid());
	
	//place status in shared memory
	*(int*) pointer = system_status;
	
}
