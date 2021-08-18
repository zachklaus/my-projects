#include "hw1.h"
#include "hw2.h"
#include "hw3.h"
#include "hw4.h"
#include "FSerial.h"

using namespace std;

void Serial::operator+=(bool b) {
	this -> put(b);
}

void Serial::operator+=(short s) {
	this -> put(s);
}
void Serial::operator+=(int i) {
	this -> put(i);
}

void Serial::operator+=(long l) {
	this -> put(l);
}

void Serial::operator+=(char c) {
	this -> put(c);
}

void Serial::operator+=(const string s) {
	this -> put(s);
}

void Serial::operator+=(const Serial& rhs) {

	string cont = rhs.str();
	this -> put(cont);
}

Serial Serial::operator+(bool b) const {

	Serial retval(*this);
	retval.put(b);
	return retval;

}

Serial Serial::operator+(short s) const {

	Serial retval(*this);
	retval.put(s);
	return retval;

}

Serial Serial::Serial::operator+(int i) const {

	Serial retval(*this);
	retval.put(i);
	return retval;

}

Serial Serial::operator+(long l) const {

	Serial retval(*this);
	retval.put(l);
	return retval;

}

Serial Serial::operator+(char c) const {

	Serial retval(*this);
	retval.put(c);
	return retval;

}

Serial Serial::operator+(const string s) const {

	Serial retval(*this);
	retval.put(s);
	return retval;

}

void operator<<=(bool& b, Serial& rhs) {

	try {
		rhs.get(b);
	} catch (string& msg) {
		throw msg;
	}

}

void operator<<=(short& s, Serial& rhs) {

	try {
		rhs.get(s);
	} catch (string& msg) {
		throw msg;
	}

}

void operator<<=(int& i, Serial& rhs) {

	try {
		rhs.get(i);
	} catch (string& msg) {
		throw msg;
	}

}

void operator<<=(long& l, Serial& rhs) {

	try {
		rhs.get(l);
	} catch (string& msg) {
		throw msg;
	}

}

void operator<<=(char& c, Serial& rhs) {

	try {
		rhs.get(c);
	} catch (string& msg) {
		throw msg;
	}

}

void operator<<=(string& s, Serial& rhs) {

	try {
		rhs.get(s);
	} catch (string& msg) {
		throw msg;
	}

}

Serial operator+(bool b, const Serial& ser) {

	stringstream ss;
	serialize(b, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}

Serial operator+(short s, const Serial& ser) {

	stringstream ss;
	serialize(s, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}

Serial operator+(int i, const Serial& ser) {

	stringstream ss;
	serialize(i, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}


Serial operator+(long l, const Serial& ser) {

	stringstream ss;
	serialize(l, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}

Serial operator+(char c, const Serial& ser) {

	stringstream ss;
	serialize(c, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}

Serial operator+(const string s, const Serial& ser) {

	stringstream ss;
	serialize(s, ss);
	string cont = ser.str();
	cont.insert(0, ss.str());
	Serial ret;
	ret.str(cont);
	return ret;

}

Serial operator+(const Serial& lhs, const Serial& rhs) {

	Serial ret(lhs);
	string temp = ret.str();
	temp.append(rhs.str());
	ret.str(temp);
	return ret;

}

bool Serial::operator==(const Serial & rhs) const {

	return contents == rhs.contents;

}

bool Serial::operator!=(const Serial & rhs) const {

	return contents != rhs.contents;

}

