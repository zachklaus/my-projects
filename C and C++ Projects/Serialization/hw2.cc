
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

#include "hw1.h"

vector<string> read_files(int file_count, char *files[], bool v);
vector<char> generate_tags(vector<string> input);
bool is_number(string s);
bool is_char(string s);
bool is_string(string s);
char get_num_tag(string s);


char get_num_tag(string s) {

	long num = stol(s);

	if (num >= SHRT_MIN && num <= SHRT_MAX)
		return 's';
	else if (num >= INT_MIN && num <= INT_MAX)
		return 'i';
	else if (num >= LONG_MIN && num <= LONG_MAX)
		return 'l';
	return 'z';
}

bool is_string(string s) {

	if (s.at(0) == '\"')
		return true;
	else
		return false;

}

bool is_char(string s) {

	if (s.at(0) == '\'')
		return true;
	else
		return false;

}

bool is_number(string s) {

	for (unsigned int i = 0; i < s.length(); i++) {

		if (!isdigit(s.at(i)) && s.at(0) != '-')
			return false;
	}

	return true;

}

vector<char> generate_tags(vector<string> input) {

	vector<char> tags;

	for(string s: input) {

		if (is_char(s))
			tags.push_back('c');
		else if (is_string(s))
			tags.push_back('S');
		else if (is_number(s)) {
			char num = get_num_tag(s);
			tags.push_back(num);
		}
		else if (s.at(0) == 't')
			tags.push_back('t');
		else if (s.at(0) == 'f') {
			tags.push_back('f');
		}
		else if (s.at(0) == 'z') {
			tags.push_back('Z');
		}
		else if (s.at(0) == 'x') {
			tags.push_back('X');
		}
	}
	return tags;
}

vector<string> read_files(int file_count, char *files[], bool v) {

	bool flag = false;
	ifstream in_file;
	vector<string> file_contents;

	//check if no files were specified
	if (file_count == 1) {
		cerr << "usage: ./hw3 {options} {name of file1} {name of file2} ...\n";
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

		if (v) {

			string file_name(files[i]);
			file_name = "z" + file_name;
			file_contents.push_back(file_name);
		}

		string file_line = "";
		vector<char> token;

		//read a whole line from file to parse into tokens
		while (getline(in_file, file_line)) {

			for (unsigned int m = 0; m < file_line.length(); m++) {

				int is_space = isspace(file_line.at(m));
				int is_digit = isdigit(file_line.at(m));

				//check if space character
				if (is_space)
					continue;

				//check if integer value and parse if so
				else if (is_digit || file_line.at(m) == '-') {

					if ((m != 0) && file_line.at(m-1) == '\'') {
						string error_string = "Invalid data format.";
						file_contents.push_back(error_string);
						break;
					}

					if (file_line.at(m) == '-') {
						token.push_back('-');
						m++;
					}
					is_digit = isdigit(file_line.at(m));
					while (is_digit) {
						token.push_back(file_line.at(m));
						if (m + 1 >= file_line.length()) {
							break;
						}
						is_digit = isdigit(file_line.at(++m));
					}
					if (m + 1 < file_line.length())
						m--;
					string ready_token(token.begin(), token.end());
					file_contents.push_back(ready_token);
					token.clear();
					continue;
				}

				//check if char and parse if so
				else if (file_line.at(m) == '\'' && (m + 2 < file_line.length())) {

					if (file_line.at(m + 2) == '\'') {
						file_contents.push_back(file_line.substr(m, 3));
						m += 3;
					}
				}

				//check if string and parse if so
				else if (file_line.at(m) == '\"') {
					int end_pos = file_line.find('\"', m + 1);
					if (file_line.find('\"', m + 1) != std::string::npos) {
						file_contents.push_back(file_line.substr(m, (end_pos + 1) - m));
						m = end_pos + 1;
					}
				}

				//check if boolean true value and parse if so
				else if (file_line.at(m) == 't' && (m + 3 < file_line.length())) {

					string bool_string = file_line.substr(m, 4);
					if (bool_string == "true") {
						file_contents.push_back(bool_string);
						m += 3;
					}

				}

				//check if boolean false value and parse if so
				else if (file_line.at(m) == 'f' && (m + 4 < file_line.length())) {

					string bool_string = file_line.substr(m, 5);
					if (bool_string == "false") {
						file_contents.push_back(bool_string);
						m += 4;
					}

				}

				else if (file_line.at(m) == 'f' && (m + 4 < file_line.length())) {

					string bool_string = file_line.substr(m, 4);
					if (bool_string == "true")
						file_contents.push_back(bool_string);

				}

				//exit program if can't parse data
				else {

					string error_string = "Invalid data format.";
					file_contents.push_back(error_string);
					break;

				}
			}
		}

		in_file.close();
		in_file.clear();

	}

	return file_contents;

}
