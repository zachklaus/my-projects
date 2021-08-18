#ifndef _HW1_H_
#define _HW1_H_

#include <string>
#include <bitset>
#include <iomanip>

using namespace std;

int number_bits(string binary);
int get_position(string binary);
int calculate_padding(int num_bits);
bool is_negative(long value);
string get_new_binary(string binary, int position);
string pad(string binary, bool is_n);
string get_nibble(string pad_binary);
unsigned long long create_vnum(string nibble, string pad_binary);
string convert_hex(unsigned long long vnum);
long get_value();
string generate_vnum(long value);

#endif