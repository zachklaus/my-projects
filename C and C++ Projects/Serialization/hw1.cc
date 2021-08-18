
#include <iostream>
#include <bitset>
#include <iomanip>
#include <string>
#include <climits>

using namespace std;

#include "hw1.h"

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


string generate_vnum(long value) {

		bool is_n = is_negative(value);
		string binary = "";
		string vnum_hex = "";

		if(is_n) {
			binary = bitset<63>(-value).to_string();
			string neg_binary = bitset<63>(value).to_string();
			int position = get_position(binary);
			string new_binary = get_new_binary(neg_binary, position);
			string pad_binary = pad(new_binary,is_n);
			string nibble = get_nibble(pad_binary);
			unsigned long long vnum = create_vnum(nibble, pad_binary);
			vnum_hex = convert_hex(vnum);
		}
		else {
			binary = bitset<63>(value).to_string();
			int position = get_position(binary);
			string new_binary = get_new_binary(binary, position);
			string pad_binary = pad(new_binary,is_n);
			string nibble = get_nibble(pad_binary);
			unsigned long long vnum = create_vnum(nibble, pad_binary);
			vnum_hex = convert_hex(vnum);
		}

		return vnum_hex;

	}

//get position of starting bit
int get_position(string binary) {

	int position = 63;

	for (int i = (signed int) binary.length() - 1; i >= 0; i--) {

		if (binary.at(i) == '1')
				position = i;


	}

	position -= 1;
	return position;

}

//computes the number of relevant bits given a binary string
int number_bits(string binary) {

    int position = 63;

    for (int i = (signed int) binary.length() - 1; i >= 0; i--) {

        if (binary.at(i) == '1')
            position = i;

    }

    int num_bits = 0;

    if (position == 0)
        num_bits = 63;
    else if (position == 63)
        num_bits = 0;
    else
        num_bits = 63 - (position + 1);

	//return num_bits + 1 for sign bit

    return num_bits + 1;

}

//determine if value is negative for padding
bool is_negative(long value) {

	bool is_n = false;

	if (value < 0)
		is_n = true;
	else
		is_n = false;

	return is_n;

}

//get new binary string from original
string get_new_binary(string binary, int position) {

	string new_binary = "";

	if(position == -1)
		new_binary = bitset<64>(LONG_MAX).to_string();
	else if (position == 63)
		new_binary = "0";
	else
		new_binary = binary.substr(position,63);

	return new_binary;
}

//pad new_binary
string pad(string new_binary, bool is_n) {

	int padding = calculate_padding(new_binary.length());
	string pad = "";

	if(is_n) {

		for (int i = 0; i < padding; i++) {
			pad = pad + "1";
		}

	}
	else {

		for (int i = 0; i < padding; i++) {
			pad = pad + "0";
		}
	}

	new_binary = pad + new_binary;
	return new_binary;

}

//calculate nibble to be added
string get_nibble(string pad_binary) {

	int num_bits = pad_binary.length();
	num_bits += 4;
	int nibble = num_bits / 8;
	nibble -= 1;
	string nibble_str = bitset<4>(nibble).to_string();
	return nibble_str;

}

//create final vnum
unsigned long long create_vnum(string nibble, string pad_binary) {


	string vnum_str = nibble + pad_binary;
	unsigned long long vnum = 0;

	switch (vnum_str.length()) {

		case 8: vnum = bitset<8>(vnum_str).to_ullong(); break;
		case 16: vnum = bitset<16>(vnum_str).to_ullong(); break;
		case 24: vnum = bitset<24>(vnum_str).to_ullong(); break;
		case 32: vnum = bitset<32>(vnum_str).to_ullong(); break;
		case 40: vnum = bitset<40>(vnum_str).to_ullong(); break;
		case 48: vnum = bitset<48>(vnum_str).to_ullong(); break;
		case 56: vnum = bitset<56>(vnum_str).to_ullong(); break;
		case 64: vnum = bitset<64>(vnum_str).to_ullong(); break;

	}
	return vnum;
}

//convert int to hex string
string convert_hex(unsigned long long vnum) {

	stringstream string_str;
	string_str << hex << vnum;
	string vnum_hex(string_str.str());

	if(vnum_hex.length() == 1)
		vnum_hex = "0" + vnum_hex;

	if (vnum_hex.length() > 3) {

		for (unsigned int i = 2 ; i < vnum_hex.length(); i+=3) {

			vnum_hex.insert(i, " ");

			if (i + 3 > vnum_hex.length())
				break;

		}
	}
	return vnum_hex;
}

//get value from input
long get_value() {

	long value;

	cin >> value;

	if (cin.fail() || cin.peek() == '.' || cin.peek() == ' ' ||
		cin.peek() == '`' || cin.peek() == '~' || cin.peek() == '@' || cin.peek() == '#' ||
		cin.peek() == '$' || cin.peek() == '%' || cin.peek() == '^' || cin.peek() == '&' || cin.peek() == '*' || cin.peek() == '(' ||
		cin.peek() == ')' || cin.peek() == '_' || cin.peek() == '-' || cin.peek() == '+' || cin.peek() == '=' || cin.peek() == '[' || cin.peek() == ']' ||
		cin.peek() == '{' || cin.peek() == '}' || cin.peek() == '\'' || cin.peek() == '|' || cin.peek() == '\\' || cin.peek() == ';' || cin.peek() == ',' ||
		cin.peek() == '"' || cin.peek() == ':' || cin.peek() == '/' || cin.peek() == '?' || cin.peek()== '!' || cin.peek() == '<' || cin.peek() == '>'){

			cerr << "Invalid input!\n";
			exit(0);

		}

	return value;
}

//calculate how many 0's or 1's to pad
int calculate_padding(int num_bits) {

	int padding = 0;

	switch(num_bits) {

		case 1: padding = 3; break;
		case 2: padding = 2; break;
		case 3: padding = 1; break;
		case 4: padding = 0; break;
		case 5: padding = 12 - 5; break;
		case 6: padding = 12 - 6; break;
		case 7: padding = 12 - 7; break;
		case 8: padding = 12 - 8; break;
		case 9: padding = 12 - 9; break;
		case 10: padding = 12 - 10; break;
		case 11: padding = 12 - 11; break;
		case 12: padding = 0; break;
		case 13: padding = 20 - 13; break;
		case 14: padding = 20 - 14; break;
		case 15: padding = 20 - 15; break;
		case 16: padding = 20 - 16; break;
		case 17: padding = 20 - 17; break;
		case 18: padding = 20 - 18; break;
		case 19: padding = 20 - 19; break;
		case 20: padding = 0; break;
		case 21: padding = 28 - 21; break;
		case 22: padding = 28 - 22; break;
		case 23: padding = 28 - 23; break;
		case 24: padding = 28 - 24; break;
		case 25: padding = 28 - 25; break;
		case 26: padding = 28 - 26; break;
		case 27: padding = 28 - 27; break;
		case 28: padding = 0; break;
		case 29: padding = 36-29; break;
		case 30: padding = 36-30; break;
		case 31: padding = 36-31; break;
		case 32: padding = 36-32; break;
		case 33: padding = 36-33; break;
		case 34: padding = 36-34; break;
		case 35: padding = 36-35; break;
		case 36: padding = 0; break;
		case 37: padding = 44-37; break;
		case 38: padding = 44-38; break;
		case 39: padding = 44-39; break;
		case 40: padding = 44-40; break;
		case 41: padding = 44-41; break;
		case 42: padding = 44-42; break;
		case 43: padding = 44-43; break;
		case 44: padding = 0; break;
		case 45: padding = 52-45; break;
		case 46: padding = 52-46; break;
		case 47: padding = 52-47; break;
		case 48: padding = 52-48; break;
		case 49: padding = 52-49; break;
		case 50: padding = 52-50; break;
		case 51: padding = 52-51; break;
		case 52: padding = 0; break;
		case 53: padding = 60-53; break;
		case 54: padding = 60-54; break;
		case 55: padding = 60-55; break;
		case 56: padding = 60-56; break;
		case 57: padding = 60-57; break;
		case 58: padding = 60-58; break;
		case 59: padding = 60-59; break;
		case 60: padding = 0; break;
		case 61: padding = 68-61; break;
		case 62: padding = 68-62; break;
		case 63: padding = 68-63; break;
		case 64: padding = 68-64; break;
		case 65: padding = 68-65; break;
		case 66: padding = 68-66; break;
		case 67: padding = 68-67; break;
		case 68: padding = 0; break;
	}

	return padding;

}
