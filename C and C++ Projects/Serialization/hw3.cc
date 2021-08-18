#include <iostream>
#include <vector>
#include <unistd.h>
#include <getopt.h>

using namespace std;

#include "hw1.h"
#include "hw2.h"

void serialize (vector<string> input, vector<char> tags);
vector<int> get_options(int argc, char *argv[]);
vector<string> read_serialized(int file_count, string files[]);
void unserialize(vector<string> input);
string hex_to_binary(string hex);
string remove_vnum(string hex_str);
string get_length_vnum(string hex_str);

void unserialize(vector<string> input) {

	for (string s: input) {

		unsigned int q;
		stringstream my_stream;
		my_stream << hex << s.substr(0, 2);
		my_stream >> q;

		if (s.at(0) == 'x') {
			cerr << "Cannot read file " << s.substr(1, s.length() - 1) << "\n";
			break;
		}
		else if (s.at(0) == 'z') {
			cerr << "Processing file " << s.substr(1, s.length() - 1);
		}
		else if (q == 'c') {
			unsigned int q2;
			stringstream my_stream2;
			my_stream2 << hex << s.substr(3, 2);
			my_stream2 >> q2;
			cout << "\'";
			cout << (char) q2;
			cout << "\'";
		}
		else if (q == 't') {
			cout << "true";
		}
		else if (q == 'f') {
			cout << "false";
		}

		else if (q == 'S') {

			if (s == "53 00") {
				cout << "\"\"" << "\n";
				continue;
			}

			cout << "\"";

			string new_str = "";

			for (unsigned int d = 0; d < s.length(); d++) {
				int is_space = isspace(s.at(d));
				if (is_space)
					continue;
				else {
					new_str = new_str + s.at(d);
				}
			}

			string new_str2 = remove_vnum(new_str);

			for (unsigned int h = 0; h < new_str2.length() - 1; h += 2) {

				unsigned int q3;
				stringstream my_stream3;
				my_stream3 << hex << new_str2.substr(h, 2);
				my_stream3 >> q3;
				cout << (char) q3;
			}

			cout << "\"";

		}

		else if (q == 's' || q == 'i' || q == 'l') {

			string value = s.substr(4, (s.length() - 4));

			string new_value = "";

			for (unsigned int d = 0; d < value.length(); d++) {
				int is_space = isspace(value.at(d));
				if (is_space)
					continue;
				else {
					new_value = new_value + value.at(d);
				}
			}

			string binary = hex_to_binary(new_value);

			if (binary.at(0) == '0') {
				auto converted = stoul(binary, nullptr, 2);
				cout << converted;
			}
			else {
				binary.insert(0, "-");
				auto converted = stoul(binary, nullptr, 2);
				cout << "-" << converted;
			}
		}

		cout << "\n";

	}

}

vector<string> read_serialized(int file_count, string files[]) {

	bool flag = false;
	ifstream in_file;
	vector<string> file_contents;

	//check if no files were specified
	if (file_count == 1) {
		cerr << "usage: ./hw2 {name of file1} {name of file2} ...\n";
		exit(0);
	}

	for(int i = 1; i < file_count; i++) {

		if (flag && files[i][0] == '-') {
			string file_name(files[i]);
			file_name = "x" + file_name;
			file_contents.push_back(file_name);
		}

		if (files[i][0] == '-')
			continue;

		flag = true;

		in_file.open(files[i]);

		if (!in_file) {
			string file_name(files[i]);
			file_name = "x" + file_name;
			file_contents.push_back(file_name);
		}


		string file_line = "";


		//read a whole line from file to parse into tokens
		while (getline(in_file, file_line)) {

			for (unsigned int m = 0; m < file_line.length(); m++) {

				int is_x_digit = isxdigit(file_line.at(m));
				int is_a_space = isspace(file_line.at(m));

				string serialized = "";

				if(is_a_space)
					continue;

				if (is_x_digit) {

					//check if integer value and parse if so
					while ((is_x_digit || file_line.at(m) == ' ') && m < file_line.length()) {


						if (m + 1 < file_line.length()) {

							int is_space = isspace(file_line.at(m));
							int is_next_space = isspace(file_line.at(m+1));

							if (is_next_space && is_space)
								break;

						}

						if (file_line.at(m) == '	') {
							break;
						}

						serialized = serialized + file_line.at(m);

						m++;
					}

				file_contents.push_back(serialized);

				}
				//exit program if can't parse data
				else {

					string error_string = "Invalid data format.";
					file_contents.push_back(error_string);
					break;

				}
				m--;
			}
		}

		in_file.close();
		in_file.clear();

	}

	return file_contents;

}

vector<int> get_options(int argc, char *argv[]) {

	vector<int> options;

	if (argc == 1 || (argc == 2 && argv[1][0] == '-')) {
		cerr << "usage: ./hw3 {options} {name of file1} {name of file2} ...\n";
		exit(0);
	}

	int k = 1;

	for (int p = 0; p < argc; p++) {

		if (argv[k][0] != '-')
			break;

		else if (strlen(argv[k]) > 2) {

			for (unsigned int i = 1; i < strlen(argv[k]); i++) {

				if (argv[k][i] == 's' || argv[k][i] == 'u' || argv[k][i] == 'v')
					options.push_back(argv[k][i]);

				else  {

					cerr << "Invalid option: \'" << argv[k][i] << "\'\n";
					exit(0);
				}

			}

		}

		 else if (argv[k][0] == '-' && strlen(argv[k]) == 2) {

			if (argv[k][1] == 's' || argv[k][1] == 'u' || argv[k][1] == 'v')
				options.push_back(argv[k][1]);

			else  {

					cerr << "Invalid option: \'" << argv[k][1] << "\'\n";
					exit(0);
				}

		}
		k++;
	}

	bool is_u = false;
	bool is_s = false;

	for (int opt: options) {

		if (opt == 'u')
			is_u = true;
		if (opt == 's')
			is_s = true;
		if (is_u && is_s) {
			cerr << "Cannot process with both -s and -u options.\n";
			exit(0);
		}
		if (opt == '?')
			exit(0);
	}

	return options;

}

void serialize (vector<string> input, vector<char> tags) {

	for (unsigned int i = 0; i < input.size(); i++) {

		if (input[i] == "Invalid data format.") {

			cerr << "Invalid data format.\n";
			exit(0);

		}

		else if (tags[i] == 'X') {

			cerr << "Cannot read file " << input[i].substr(1, input[i].length() - 1) << "\n";
			exit(0);

		}

		else if (tags[i] == 'Z') {

			cerr << "Processing file " << input[i].substr(1, input[i].length() - 1) << "\n";

		}

		else if (is_char(input[i])) {

			int char_tag = tags[i];
			int char_int = input[i].at(1);

			cout << hex << char_tag << " ";

			if (char_int <= 15)
				cout << "0";

			cout << hex << char_int << "\n";

		}

		else if (is_number(input[i])) {

			string vnum = generate_vnum(stol(input[i]));
			int char_tag = tags[i];

			cout << hex << char_tag << " " << vnum << "\n";

		}

		else if (is_string(input[i])) {
			int char_tag = tags[i];
			string length_vnum = generate_vnum(input[i].length() - 2);
			cout << hex << char_tag << " " << length_vnum << " ";


			for (unsigned int j = 1; j < input[i].length() - 1; j++) {
				char s_to_c = input[i].at(j);
				int c_to_i = s_to_c;

				if (c_to_i <= 15)
					cout << "0";

				cout << hex << c_to_i;

				if (j+1 < input[i].length() - 1)
					cout << " ";

			}
			cout << "\n";

		}

		else if (input[i].at(0) == 't')
			cout << hex << (int) tags[i] << "\n";

		else if (input[i].at(0) == 'f')
			cout << hex << (int) tags[i] << "\n";

	}

}

string hex_to_binary(string hex) {

	string binary = "";

	for (unsigned int i = 0; i < hex.length(); i++) {

		switch(hex.at(i)) {

	  	case '0': binary.append ("0000"); break;
			case '1': binary.append ("0001"); break;
			case '2': binary.append ("0010"); break;
			case '3': binary.append ("0011"); break;
			case '4': binary.append ("0100"); break;
			case '5': binary.append ("0101"); break;
			case '6': binary.append ("0110"); break;
			case '7': binary.append ("0111"); break;
			case '8': binary.append ("1000"); break;
			case '9': binary.append ("1001"); break;
			case 'a': binary.append ("1010"); break;
			case 'b': binary.append ("1011"); break;
			case 'c': binary.append ("1100"); break;
			case 'd': binary.append ("1101"); break;
			case 'e': binary.append ("1110"); break;
			case 'f': binary.append ("1111"); break;

		}

	}

	int pad_amount = 63 - binary.length();
	string padding = "";

	char sign_bit = binary.at(0);

	if (binary.length() < 64 && sign_bit == '0') {
		for (int u = 0; u < pad_amount; u++) {
			padding.append("0");
		}
	}
	else if (binary.length() < 64 && sign_bit == '1') {
		for (int u = 0; u < pad_amount; u++) {
			padding.append("1");
		}
	}

	string sign;
	sign.push_back(sign_bit);

	binary.insert(1, padding);
	binary.insert(1, sign);

	return binary;

}

string remove_vnum(string hex_str) {

	unsigned int nibble;
	stringstream my_stream;
	my_stream << hex << hex_str.substr(0, 1);
	my_stream >> nibble;

	int num_hex = 0;

	switch (nibble) {

		case 0: num_hex = 1; break;
		case 1: num_hex = 3; break;
		case 2: num_hex = 5; break;
		case 3: num_hex = 7; break;
		case 4: num_hex = 9; break;
		case 5: num_hex = 11; break;
		case 6: num_hex = 13; break;
		case 7: num_hex = 15; break;
		case 8: num_hex = 17; break;
		case 9: num_hex = 19; break;
		case 10: num_hex = 21; break;
		case 11: num_hex = 23; break;
		case 12: num_hex = 25; break;
		case 13: num_hex = 27; break;
		case 14: num_hex = 29; break;
		case 15: num_hex = 31; break;

	}

	num_hex += 1;
	hex_str = hex_str.substr(num_hex, (hex_str.length() - num_hex)) ;

	return hex_str;

}

string get_length_vnum(string hex_str) {

	unsigned int nibble;
	stringstream my_stream;
	my_stream << hex << hex_str.substr(0, 1);
	my_stream >> nibble;

	int num_hex = 0;

	switch (nibble) {

		case 0: num_hex = 1; break;
		case 1: num_hex = 3; break;
		case 2: num_hex = 5; break;
		case 3: num_hex = 7; break;
		case 4: num_hex = 9; break;
		case 5: num_hex = 11; break;
		case 6: num_hex = 13; break;
		case 7: num_hex = 15; break;
		case 8: num_hex = 17; break;
		case 9: num_hex = 19; break;
		case 10: num_hex = 21; break;
		case 11: num_hex = 23; break;
		case 12: num_hex = 25; break;
		case 13: num_hex = 27; break;
		case 14: num_hex = 29; break;
		case 15: num_hex = 31; break;

	}

	num_hex += 1;
	hex_str = hex_str.substr(0, num_hex) ;

	return hex_str;

}
