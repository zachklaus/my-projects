//Author: Zachary Klausner
//Class: CS370
//Assignment: HW4
//Email: zachklau@rams.colostate.edu

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
	
	//open file
	FILE *commands_raw = fopen(argv[1], "r");
	
	//check if file opening is successful
	if (commands_raw == NULL) {
	  printf("File opening has failed. Ending program.\n");
	  exit(0);
	}
	
	//get length of file contents
	fseek(commands_raw, 0, SEEK_END);
	int sentence_length = ftell(commands_raw) + 1;

	//MEMORY ALLOCATION!
	char* sentence = calloc(sentence_length, sizeof(char));

	fseek(commands_raw, 0, SEEK_SET);
	
	//read contents of file
	if (fgets(sentence, sentence_length, commands_raw) == NULL) {
		printf("No contents in file to read. Ending program.\n");
		exit(0);
	}
	
	//close file
	fclose(commands_raw);
	
	//get location of the write end of pipe from arguments
	int pipe_address = atoi(argv[2]);
	
	//write file contents to write end of pipe
	write(pipe_address, sentence, strlen(sentence) + 1);
	
	//close write end of pipe
	close(pipe_address);
	
	//free allocated memory
	free(sentence);
		
	return 0;

}