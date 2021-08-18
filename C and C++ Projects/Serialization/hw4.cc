#include "hw1.h"
#include "hw2.h"
#include "hw3.h"

using namespace std;

void serialize(bool val, ostream & os) {

	if (val)
		os << "t";
	else
		os << "f";

}

void serialize(short val, ostream & os) {

		string vnum = generate_vnum((long) val);
		string new_str = "";

		for (unsigned int d = 0; d < vnum.length(); d++) {
			int is_space = isspace(vnum.at(d));
			if (is_space)
				continue;
			else {
				new_str = new_str + vnum.at(d);
			}
		}

		os << "s";

		for (unsigned int i = 0; i < new_str.length() - 1; i += 2) {

			string byte = new_str.substr(i, 2);

			if (byte.at(0) == '0')
				byte = byte.at(1);

			int num = 0;
			stringstream my_stream;
			my_stream << hex << byte ;
			my_stream >> num;

			char char_byte = char(num);
			os << char_byte;

		}

}

void serialize(int val, ostream & os) {

	string vnum = generate_vnum((long) val);
	string new_str = "";

	for (unsigned int d = 0; d < vnum.length(); d++) {
		int is_space = isspace(vnum.at(d));
		if (is_space)
			continue;
		else {
			new_str = new_str + vnum.at(d);
		}
	}

	os << "i";

	for (unsigned int i = 0; i < new_str.length() - 1; i += 2) {

		string byte = new_str.substr(i, 2);

		if (byte.at(0) == '0')
			byte = byte.at(1);

		int num = 0;
		stringstream my_stream;
		my_stream << hex << byte ;
		my_stream >> num;

		char char_byte = char(num);
		os << char_byte;

	}

}

void serialize(long val, ostream & os) {

	string vnum = generate_vnum((long) val);
	string new_str = "";

	for (unsigned int d = 0; d < vnum.length(); d++) {
		int is_space = isspace(vnum.at(d));
		if (is_space)
			continue;
		else {
			new_str = new_str + vnum.at(d);
		}
	}

	os << "l";

	for (unsigned int i = 0; i < new_str.length() - 1; i += 2) {

		string byte = new_str.substr(i, 2);

		if (byte.at(0) == '0')
			byte = byte.at(1);

		int num = 0;
		stringstream my_stream;
		my_stream << hex << byte ;
		my_stream >> num;

		char char_byte = char(num);
		os << char_byte;

	}

}

void serialize(char val, ostream & os) {

	os << "c";
	os << val;

}

void serialize(const string & val, ostream & os) {

	os << "S";

	string length_vnum = generate_vnum(val.length());
	string new_str = "";

	for (unsigned int d = 0; d < length_vnum.length(); d++) {
		int is_space = isspace(length_vnum.at(d));
		if (is_space)
			continue;
		else {
			new_str = new_str + length_vnum.at(d);
		}
	}

	for (unsigned int i = 0; i < new_str.length() - 1; i += 2) {

		string byte = new_str.substr(i, 2);

		if (byte.at(0) == '0')
			byte = byte.at(1);

		int num = 0;
		stringstream my_stream;
		my_stream << hex << byte ;
		my_stream >> num;

		char char_byte = char(num);
		os << char_byte;

	}

	for (unsigned int i = 0; i < val.length(); i++) {
		char c = val.at(i);
		os << c;
	}

}

void unserialize(istream & is, bool & val) {

	is.clear();

	if (is.peek() == 't')
		val = true;
	else if (is.peek() == 'f')
		val = false;
	else {
		string error = "error in hw4 - attempted to call unserialize(istream&, bool&) for value that is not of type bool";
		throw error;
	}

	char boolean;
	is >> noskipws >> boolean;

}

void unserialize(istream & is, short & val) {

	is.clear();

	if (is.peek() != 's') {
			string error = "error in hw4 - attempted to call unserialize(istream&, short&) for value that is not of type short";
			throw error;
	}

	char tag;
	is >> noskipws >> tag;

	char nibble;
	string nibble_str = "";
	stringstream my_stream;

	is >> noskipws >> nibble;
	my_stream << hex << (unsigned int) (unsigned char) nibble;

	if (my_stream.str().length() == 1)
		nibble_str += "0";

	nibble_str += my_stream.str();
	my_stream.str("");
	stringstream char_str;
	char_str << nibble_str.at(0);
	int nibble_num;
	char_str >> nibble_num;
	char byte;
	string hex_str = nibble_str;

	for (int x = 0; x < nibble_num; x++) {
		is >> noskipws >> byte;
		my_stream << hex << (unsigned int) (unsigned char) byte;
		if (my_stream.str().length() == 1)
			hex_str += "0";
		hex_str += my_stream.str();
		my_stream.str("");
	}

	hex_str = hex_str.substr(1, (hex_str.length() - 1));
	string binary = hex_to_binary(hex_str);

	if (binary.at(0) == '0') {
		auto converted = stoul(binary, nullptr, 2);
		val = converted;
	}

	else {
		binary.insert(0, "-");
		auto converted = stoul(binary, nullptr, 2);
		val = -converted;
	}

}

void unserialize(istream & is, int & val) {

	is.clear();

	if (is.peek() != 'i') {
			string error = "error in hw4 - attempted to call unserialize(istream&, int&) for value that is not of type int";
			throw error;
	}

	char tag;
	is >> noskipws >> tag;

	char nibble;
	string nibble_str = "";
	stringstream my_stream;

	is >> noskipws >> nibble;
	my_stream << hex << (unsigned int) (unsigned char) nibble;

	if (my_stream.str().length() == 1)
		nibble_str += "0";

	nibble_str += my_stream.str();
	my_stream.str("");
	stringstream char_str;
	char_str << nibble_str.at(0);
	int nibble_num = 0;
	char_str >> nibble_num;
	char byte;
	string hex_str = nibble_str;

	for (int x = 0; x < nibble_num; x++) {
		is >> noskipws >> byte;
		my_stream << hex << (unsigned int) (unsigned char) byte;
		if (my_stream.str().length() == 1)
			hex_str += "0";
		hex_str += my_stream.str();
		my_stream.str("");
	}

	hex_str = hex_str.substr(1, (hex_str.length() - 1));
	string binary = hex_to_binary(hex_str);

	if (binary.at(0) == '0') {
		auto converted = stoul(binary, nullptr, 2);
		val = converted;
	}

	else {
		binary.insert(0, "-");
		auto converted = stoul(binary, nullptr, 2);
		val = -converted;
	}
}

void unserialize(istream & is, long & val) {

	is.clear();

	if (is.peek() != 'l') {
			string error = "error in hw4 - attempted to call unserialize(istream&, long&) for value that is not of type long";
			throw error;
	}

	char tag;
	is >> noskipws >> tag;

	char nibble;
	string nibble_str = "";
	stringstream my_stream;

	is >> noskipws >> nibble;
	my_stream << hex << (unsigned int) (unsigned char) nibble;

	if (my_stream.str().length() == 1)
		nibble_str += "0";

	nibble_str += my_stream.str();
	my_stream.str("");
	stringstream char_str;
	char_str << nibble_str.at(0);
	int nibble_num = 0;
	char_str >> nibble_num;
	char byte;
	string hex_str = nibble_str;

	for (int x = 0; x < nibble_num; x++) {
		is >> noskipws >> byte;
		my_stream << hex << (unsigned int) (unsigned char) byte;
		if (my_stream.str().length() == 1)
			hex_str += "0";
		hex_str += my_stream.str();
		my_stream.str("");
	}

	hex_str = hex_str.substr(1, (hex_str.length() - 1));
	string binary = hex_to_binary(hex_str);

	if (binary.at(0) == '0') {
		auto converted = stoul(binary, nullptr, 2);
		val = converted;
	}

	else {
		binary.insert(0, "-");
		auto converted = stoul(binary, nullptr, 2);
		val = -converted;
	}

}

void unserialize(istream & is, char & val) {

		is.clear();

		if (is.peek() != 'c') {
				string error = "error in hw4 - attempted to call unserialize(istream&, char&) for value that is not of type char";
				throw error;
		}

		char tag;
		is >> noskipws >> tag;

		char byte;
		is >> noskipws >> byte;

		val = byte;

}

void unserialize(istream & is, string & val) {

	is.clear();

	if (is.peek() != 'S') {
			string error = "error in hw4 - attempted to call unserialize(istream&, string&) for value that is not of type string";
			throw error;
	}

	char tag;
	is >> noskipws >> tag;

	char nibble;
	string nibble_str = "";
	stringstream my_stream;

	is >> noskipws >> nibble;
	my_stream << hex << (unsigned int) (unsigned char) nibble;

	if (my_stream.str().length() == 1)
		nibble_str += "0";

	nibble_str += my_stream.str();
	my_stream.str("");
	stringstream char_str;

	char_str << nibble_str.at(0);

	int nibble_num = 0;

	char_str >> nibble_num;

	char byte;
	string hex_str = "";
	stringstream my_stream3;
	string length_vnum = "";

	for(int g = 0; g < nibble_num; g++) {
		is >> noskipws >> byte;
		my_stream3 << hex << (unsigned int) (unsigned char) byte;
		if (my_stream3.str().length() == 1)
			length_vnum += "0";
		length_vnum += my_stream3.str();
		my_stream3.str("");
	}

	if (hex_str == "00") {
		string empty = "";
		val = empty;
		return;
	}

	stringstream ss;
	int string_length = 0;
	string length_vnum_char = "";
	unsigned int temp = 0;
	stringstream ms;

	length_vnum.insert(0, nibble_str);

	for (unsigned int l = 0; l < length_vnum.length(); l += 2) {
		ms.clear();
		string y = length_vnum.substr(l, 2);
		ms << hex << y;
		ms >> temp;
		length_vnum_char += (char) temp;

	}

	length_vnum_char.insert(0, "i");

	ss.str(length_vnum_char);
	unserialize(ss, string_length);

	char byte2;

	for (int h = 0; h < string_length; h ++) {

		is >> noskipws >> byte2;
		val += byte2;

	}

}
