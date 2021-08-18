//Author: Zachary Klausner
//Class: CS370
//Assignment: HW5
//Email: zachklau@rams.colostate.edu

#include "scheduling.h"


int main(int argc, char *argv[]) {

	//get arguments and place in variables
	int seed = atoi(argv[1]);
	int num_processes = atoi(argv[2]);
	int time_quantum = atoi(argv[3]);

	//seed random number generator
	srand(seed);

	//increment to avoid compiler warnings
	seed++;
	time_quantum++;

	//create arrays to store generated arrival and burst times
	int arrival_times[num_processes];
	int burst_times[num_processes];

	//generate process arrival and burst times
	for (int i = 0; i < num_processes; i++) {
		arrival_times[i] = rand() % 50;
		burst_times[i] = rand() %10 + 1;
	}

	//create an array of process structs
	Process processes[num_processes];

	//fill data fields in process structs with generated arrival and burst times
	for(int i = 0; i < num_processes; i++) {
		processes[i].arrival_time = arrival_times[i];
		processes[i].burst_time = burst_times[i];
	}

	//print out arrival and burst times of processes
	printf("Arrival Time | Burst Time\n");

	for (int i = 0; i < num_processes; i++) {
		printf("	%d |	%d\n", processes[i].arrival_time, processes[i].burst_time);
	}
	printf("\n");

	//sort the processes by arrival time
	qsort(processes, num_processes, sizeof(Process), cmp_function);

	int process_id = 1;

	//set processes id's
	for (int i = 0; i < num_processes; i++)
		processes[i].id = process_id++;

	//print out processes after being sorted by arrival times
	printf("After sorting by arrival times\n");

	printf("Arrival Time | Burst Time\n");

	int process_counter = 1;

	for (int i = 0; i < num_processes; i++) {
		printf("P%d	%d |	%d\n",process_counter++, processes[i].arrival_time, processes[i].burst_time);
	}

	//perform fcfs operations
	printf("\nIn First Come First Serve\n\n");
	printf("Grantt chart is:\n");
	FCFS(processes, num_processes);

	//perform sjf - non preemp operations
	printf("\nIn Shortest Job First - Non Preemptive\n\n");
	printf("Grantt chart is:\n");
	SJF_non_pre(processes, num_processes);

}

//performs shortest job first - non preemptive algorithm and analysis
void SJF_non_pre(Process processes[], int num_processes) {

	qsort(processes, num_processes, sizeof(Process), cmp_function2);

	double total_wait_time = 0;
	double total_turnaround_time = 0;
	int cpu_time = 0;

	for (int i = 0; i < num_processes; i++) {

		if (cpu_time >= processes[i].arrival_time) {
			printf("[%d]--P%d--[%d]\n", cpu_time, processes[i].id, cpu_time + processes[i].burst_time);
			total_wait_time += cpu_time - processes[i].arrival_time;
			total_turnaround_time += (cpu_time + processes[i].burst_time) - processes[i].arrival_time;
			cpu_time += processes[i].burst_time;
		}

		else if (cpu_time < processes[i].arrival_time) {

			printf("[%d]--IDLE--[%d]\n", cpu_time, processes[i].arrival_time);
			cpu_time = processes[i].arrival_time;
			printf("[%d]--P%d--[%d]\n", processes[i].arrival_time, processes[i].id, cpu_time + processes[i].burst_time);
			total_turnaround_time += (cpu_time + processes[i].burst_time) - processes[i].arrival_time;
			cpu_time += processes[i].burst_time;

		}

	}

	printf("\nAverage Turnaround time for Shortest Job First - Non Preemptive is %f\n", total_turnaround_time / num_processes);
	printf("Average Wait time for Shortest Job First - Non Preemptive is %f\n", total_wait_time / num_processes);
	printf("Average Throughput for Shortest Job First - Non Preemptive is %f\n", num_processes / (double) cpu_time);

	qsort(processes, num_processes, sizeof(Process), cmp_function);

}

//performs first come first serve algorithm and analysis
void FCFS(Process processes[], int num_processes) {

	double total_wait_time = 0;
	double total_turnaround_time = 0;
	int cpu_time = 0;

	for (int i = 0; i < num_processes; i++) {

		if (cpu_time >= processes[i].arrival_time) {
			printf("[%d]--P%d--[%d]\n", cpu_time, processes[i].id, cpu_time + processes[i].burst_time);
			total_wait_time += cpu_time - processes[i].arrival_time;
			total_turnaround_time += (cpu_time + processes[i].burst_time) - processes[i].arrival_time;
			cpu_time += processes[i].burst_time;
		}

		else if (cpu_time < processes[i].arrival_time) {

			printf("[%d]--IDLE--[%d]\n", cpu_time, processes[i].arrival_time);
			cpu_time = processes[i].arrival_time;
			printf("[%d]--P%d--[%d]\n", processes[i].arrival_time, processes[i].id, cpu_time + processes[i].burst_time);
			total_turnaround_time += (cpu_time + processes[i].burst_time) - processes[i].arrival_time;
			cpu_time += processes[i].burst_time;

		}

	}

	printf("\nAverage Turnaround time for First Come First Serve is %f\n", total_turnaround_time / num_processes);
	printf("Average Wait time for First Come First Serve is %f\n", total_wait_time / num_processes);
	printf("Average Throughput for First Come First Serve is %f\n", num_processes / (double) cpu_time);

}

//comparison helper function for qsort - sort by arrival times
int cmp_function(const void *p1, const void *p2) {

	if ((*(Process*)p1).arrival_time > (*(Process*)p2).arrival_time)
		return 1;
	else if ((*(Process*)p1).arrival_time < (*(Process*)p2).arrival_time)
		return -1;
	else
		return 0;

	return 0;
}

//comparison helper function for qsort - sort by arrival times and burst times
int cmp_function2(const void *p1, const void *p2) {

	if ((*(Process*)p1).arrival_time > (*(Process*)p2).arrival_time)
		return 1;
	else if ((*(Process*)p1).arrival_time < (*(Process*)p2).arrival_time)
		return -1;
	else if ((*(Process*)p1).arrival_time == (*(Process*)p2).arrival_time) {
		if ((*(Process*)p1).burst_time > (*(Process*)p2).burst_time)
				return 1;
			else if ((*(Process*)p1).burst_time < (*(Process*)p2).burst_time)
				return -1;
	}

	return 0;
}

