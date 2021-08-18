#include "hw1.h"
#include "hw2.h"
#include "hw3.h"
#include "hw4.h"
#include "FSerial.h"

using namespace std;


	Serial::Serial(): contents("") {}

	Serial::Serial(const Serial &serial2) {

		this->contents = serial2.str();

	}

	Serial& Serial::operator=(const Serial& rhs) {
		contents = rhs.str();
		return *this;
	}

	void Serial::put(bool b) {

		stringstream ss;
		serialize(b, ss);
		contents.append(ss.str());

	}


	void Serial::put(short s) {

		stringstream ss;
		serialize(s, ss);
		contents.append(ss.str());

	}

	void Serial::put(int i) {

		stringstream ss;
		serialize(i, ss);
		contents.append(ss.str());

	}

	void Serial::put(long l) {

		stringstream ss;
		serialize(l, ss);
		contents.append(ss.str());

	}

	void Serial::put(char c) {

		stringstream ss;
		serialize(c, ss);
		contents.append(ss.str());

	}

	void Serial::put(const string s) {

		stringstream ss;
		serialize(s, ss);
		contents.append(ss.str());

	}

	void Serial::get(bool& b) {

		stringstream ss(contents);
		try {
			unserialize(ss, b);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c;
		while (ss >> noskipws >> c) {
			contents += c;
		}
	}

	void Serial::get(short& s) {

		stringstream ss(contents);
		try {
			unserialize(ss, s);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c;
		while (ss >> noskipws >> c) {
			contents += c;
		}
	}

	void Serial::get(int& i) {

		stringstream ss(contents);
		try {
			unserialize(ss, i);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c;
		while (ss >> noskipws >> c) {
			contents += c;
		}
	}

	void Serial::get(long& l) {

		stringstream ss(contents);
		try {
			unserialize(ss, l);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c;
		while (ss >> noskipws >> c) {
			contents += c;
		}

	}

	void Serial::get(char& c) {

		stringstream ss(contents);
		try {
			unserialize(ss, c);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c2;
		while (ss >> noskipws >> c2) {
			contents += c2;
		}

	}


	void Serial::get(string &s) {

		stringstream ss(contents);
		s = "";
		try {
			unserialize(ss, s);
		}
		catch (string &msg) {
			throw;
		}
		contents = "";
		char c;
		while (ss >> noskipws >> c) {
			contents += c;
		}
	}

	string Serial::str() const {
		return contents;
	}

	void Serial::str(string s) {
		contents = s;
	}

	int Serial::size() const {
		return contents.length();
	}

	bool Serial::empty() const {
		return contents.empty();
	}


ostream& operator<<(ostream& os, const Serial& serial) {
	os << serial.str();
	return os;
}
