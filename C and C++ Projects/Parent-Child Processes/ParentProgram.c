//Author: Zachary Klausner
//Class: CS370
//Assignment: HW4
//Email: zachklau@rams.colostate.ed

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>

#define READ 0
#define WRITE 1

char* read_file(char* filename);
char** parse_commands(char* sentece);
int get_num_commands(char* sentaence);
void execute_commands(char** commands, char* sentence);
void cleanup(char** commands, char* sentence);

int main(int argc, char **argv)
{

	char* filename = "textfile.txt";

	if (argc > 1) {
		filename = argv[1];
	}

	char* sentence = read_file(filename);
	char** commands = parse_commands(sentence);
	execute_commands(commands, sentence);

	printf("ParentProgram: Process Complete.\n");

	cleanup(commands, sentence);

}

// executes the given commands passed as a 2D array using Executor
void execute_commands(char** commands, char* sentence) {

	int num_commands = get_num_commands(sentence);
	int memory_segment = 0;
	void *pointer;

	for (int i = 0; i < num_commands; i++) {

		int pid = fork();
		
		//parent process
		if (pid > 0) {
			
			int waitstatus_v = 0;
			
			printf("ParentProgram: forked process with ID %d.\n", pid);
			printf("ParentProgram: waiting for process [%d].\n", pid);
			
			//create shared memory and pointer to it
			memory_segment = shm_open("SHARED", O_CREAT | O_RDWR, 0666);
			ftruncate(memory_segment, 256);
			pointer = mmap(0, 256, PROT_READ | PROT_WRITE, MAP_SHARED, memory_segment, 0);
			
			//check if map creation failed
			if (pointer == MAP_FAILED) {
				printf("Map creation failed. Exiting Program\n");
				exit(0);
			}
			
			printf("ParentProgram: FD for shared memory for Executor is %d\n", memory_segment);
			
			if (wait(&waitstatus_v) >= 0) {

				if (WEXITSTATUS(waitstatus_v) == 0) {
					
					//get status from shared memory
					int status = -1;
					status = *(int*) pointer;
					
					//print out status from shared memory
					printf("ParentProgram: Child process %d returned %d\n", pid, status);
					
					//unlink shared memory
					if (shm_unlink("SHARED") == -1) {
						
						printf("unlinking failed. exiting\n");
						exit(0);
						
					}
				}
			}
		}
		
		//child process
		else if (pid == 0) {
			execlp("./Executor","Executor", commands[i] , NULL);
		}
		
		//error in creating child process
		else {
			printf("ParentProgram: Child Process Creation failed. Exiting.\n");
			exit(0);
		}

	}

}

//gets the number of commands contained within the sentence that was read from the file
int get_num_commands(char* sentence) {

	int num_commands = 0;

	for (int i = 0; i < strlen(sentence); i++) {
		if (sentence[i] == ',') {
			num_commands++;
		}
	}
	num_commands++;

	return num_commands;
}

//converts the sentence from the file into a 2D array of commands
char** parse_commands(char* sentence) {


	int num_commands = get_num_commands(sentence);

	int string_lengths[1000000];
	int command_length = 0;
	int j = 0;

	for (int i = 0; i < strlen(sentence); i++) {

		if (sentence[i] == ',' || i == strlen(sentence) - 1) {
			string_lengths[j] = command_length;
			j++;
			command_length = 0;
			continue;
		}
		command_length++;
	}
	string_lengths[j-1]++;

	// MEMORY ALLOCATION!
	char** commands = calloc(num_commands, sizeof(char *));

	for (int i = 0; i < num_commands; i++) {

		// MEMORY ALLOCATIONS!
		commands[i] = calloc(string_lengths[i] + 1, sizeof(char));

	}

	int x = 0;
	int y = 0;

	for (int i = 0; i < strlen(sentence); i++) {

		if (sentence[i] == ',' || i == strlen(sentence)) {

			commands[x][y] = '\0';
			x++;
			y = 0;
			continue;

		}
		commands[x][y] = sentence[i];
		y++;
	}

	return commands;

}

//reads the sentence from the given file into a string using FileOpener
char* read_file(char* filename) {

	//create array for pipe and allocate memory for file contents
  int read_pipe[2] = {0, 0};
	char* file_contents = calloc(100, sizeof(char));
	
	//create pipe
  int pipe_status = pipe(read_pipe);
	
	//check if pipe creation failed
	if (pipe_status == -1) {
		printf("Pipe creation failed. Exiting Program.\n");
		exit(0);
	}
	
	//place pipe location into string
	char ploc[100];
  sprintf(ploc, "%d", read_pipe[WRITE]);
	
	int pid = fork();
	
	//parent process
	if (pid > 0) {

		printf("ParentProgram: forked process with ID %d.\n", pid);
		printf("ParentProgram: waiting for process [%d].\n", pid);

		int waitstatus_v = 0;

		if (wait(&waitstatus_v) >= 0) {

			if (WEXITSTATUS(waitstatus_v) == 0) {
				
				//get contents of file that were placed in pipe by FileOpener
				read(read_pipe[READ], file_contents, 100);
				close(read_pipe[READ]);
				
				printf("Sentence is %s\n", file_contents);
				printf("ParentProgram: Child process %d returned %d.\n", pid, WEXITSTATUS(waitstatus_v));
					
			}

		}
		
	}
	
	//child process
	else if (pid == 0) {
		close(read_pipe[READ]);
		execlp("./FileOpener", "FileOpener", filename, ploc, NULL);
	}
	
	//error in creating child process
	else {
		printf("ParentProgram: Child Process Creation failed. Exiting.\n");
		exit(0);
	}
		
	return file_contents;

}

//frees allocated memory that was used during the program
void cleanup(char** commands, char* sentence) {

	int num_commands = get_num_commands(sentence);

	free(sentence);

	for(int i = 0; i < num_commands; i++) {
		free(commands[i]);
	}

	free(commands);

}
