#ifndef _HW2_H_
#define _HW2_H_

#include <iostream>
#include <bitset>
#include <iomanip>
#include <string>
#include <vector>
#include <fstream>
#include <cstring>
#include <iterator>
#include <limits>
#include <climits>

using namespace std;

vector<string> read_files(int file_count, char *files[], bool v);
vector<char> generate_tags(vector<string> input);
bool is_number(string s);
bool is_char(string s);
bool is_string(string s);
char get_num_tag(string s);

#endif
