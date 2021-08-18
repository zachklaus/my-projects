//Author: Zachary Klausner
//Class: CS370
//Assignment: HW5
//Email: zachklau@rams.colostate.edu

//header file for scheduling.c

#ifndef _SCHEDULING_H_
#define _SCHEDULING_H_

#include <stdio.h>
#include <stdlib.h>

//process struct to model processes and contain data
typedef struct process {
	int arrival_time;
	int burst_time;
	int id;
} Process;

//function declarations
void FCFS(Process processes[], int num_processes);
void SJF_non_pre(Process processes[], int num_processes);
int cmp_function(const void *p1, const void *p2);
int cmp_function2(const void *p1, const void *p2);

#endif
