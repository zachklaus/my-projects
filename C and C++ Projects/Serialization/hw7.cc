#include "hw1.h"
#include "hw2.h"
#include "hw3.h"
#include "hw4.h"
#include "FSerial.h"

using namespace std;

IFSerial::IFSerial(string filename) {

	ifstream temp(filename);

	if (!temp)
		failbit = true;
	else
		failbit = false;

	if (temp.eof())
		eofbit = true;
	else
		eofbit = false;

	stringstream file_contents;
	vector<string> file_contents_vec;
	string filenamearr[1];
	filenamearr[0] = filename;
	file_contents_vec = read_serialized(2, filenamearr);
	copy(file_contents_vec.begin(), file_contents_vec.end(),ostream_iterator<string>(file_contents));
	contents.str(file_contents.str());

}


IFSerial& operator>>(IFSerial& is, bool& b) {

	try {
		is.contents.get(b);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
		is.eofbit = true;

	return is;
}

IFSerial& operator>>(IFSerial& is, short& s){

	try {
		is.contents.get(s);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
		is.eofbit = true;

	return is;

}

IFSerial& operator>>(IFSerial& is, int& i) {

	try {
		is.contents.get(i);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
		is.eofbit = true;

	return is;

}

IFSerial& operator>>(IFSerial& is, long& l) {

	try {
		is.contents.get(l);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
		is.eofbit = true;

	return is;

}

IFSerial& operator>>(IFSerial& is, char& c) {

	try {
		is.contents.get(c);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
		is.eofbit = true;

	return is;

}

IFSerial& operator>>(IFSerial& is, string& s) {

	try {
		is.contents.get(s);
	} catch (string& msg) {
		is.failbit = true;
	}

	if (is.contents.empty())
			is.eofbit = true;

	return is;

}

bool IFSerial::eof() const {

	return eofbit;

}

bool IFSerial::fail() const {

	return failbit;

}


OFSerial::OFSerial(string filename) {

	outfile.open(filename);

	if (!outfile)
		failbit = true;
	else
		failbit = false;
}

OFSerial::~OFSerial() {

	outfile.close();

}

OFSerial& operator<<(OFSerial& os, bool b) {

	os.contents.str("");
	os.contents.put(b);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}

OFSerial& operator<<(OFSerial& os, short s) {

	os.contents.str("");
	os.contents.put(s);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}

OFSerial& operator<<(OFSerial& os, int i) {

	os.contents.str("");
	os.contents.put(i);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}

OFSerial& operator<<(OFSerial& os, long l) {

	os.contents.str("");
	os.contents.put(l);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}

OFSerial& operator<<(OFSerial& os, char c) {

	os.contents.str("");
	os.contents.put(c);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}

OFSerial& operator<<(OFSerial& os, const string s) {

	os.contents.str("");
	os.contents.put(s);
	os.outfile << os.contents.str() << "\n";
	os.contents.str("");
	return os;

}


bool OFSerial::fail() const {
	return failbit;
}

