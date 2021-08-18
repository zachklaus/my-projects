#ifndef _HW3_H_
#define _HW3_H_

#include <iostream>
#include <vector>
#include <unistd.h>
#include <getopt.h>

#include "hw1.h"
#include "hw2.h"

using namespace std;

void serialize (vector<string> input, vector<char> tags);
vector<int> get_options(int argc, char *argv[]);
vector<string> read_serialized(int file_count, string files[]);
void unserialize(vector<string> input);
string hex_to_binary(string hex);
string remove_vnum(string hex_str);
string get_length_vnum(string hex_str);

#endif
