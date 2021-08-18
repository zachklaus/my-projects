#ifndef _FSERIAL_H_
#define _FSERIAL_H_

#include <fstream>

using namespace std;

class Serial {

	private:

		string contents;

	public:

		Serial();
		Serial(const Serial &serial2);
		Serial& operator=(const Serial& rhs);

		void put(bool b);
		void put(short s);
		void put(int i);
		void put(long l);
		void put(char c);
		void put(const string s);

		void get(bool &b) ;
		void get(short &s);
		void get(int &i);
		void get(long &l);
		void get(char &c);
		void get(string &s);

		string str() const;
		void str(string s);
		int size() const;
		bool empty() const;

		void operator+=(bool b);
		void operator+=(short s);
		void operator+=(int i);
		void operator+=(long l);
		void operator+=(char c);
		void operator+=(const string s);
		void operator+=(const Serial& rhs);

		Serial operator+(bool b) const;
		Serial operator+(short s) const;
		Serial operator+(int i) const;
		Serial operator+(long l) const;
		Serial operator+(char c) const;
		Serial operator+(const string s) const;

	    bool operator==(const Serial &) const;
	    bool operator!=(const Serial &) const;

};

void operator<<=(bool& b, Serial& rhs);
void operator<<=(short& s, Serial& rhs);
void operator<<=(int& i, Serial& rhs);
void operator<<=(long& l, Serial& rhs);
void operator<<=(char& c, Serial& rhs);
void operator<<=(string& s, Serial& rhs);

Serial operator+(bool b, const Serial& rhs);
Serial operator+(short s, const Serial& rhs);
Serial operator+(int i, const Serial& rhs);
Serial operator+(long l, const Serial& rhs);
Serial operator+(char c, const Serial& rhs);
Serial operator+(const string s, const Serial&rhs);
Serial operator+(const Serial& lhs, const Serial& rhs);

ostream& operator<<(ostream& os, const Serial& serial);















class IFSerial {

	public:

		Serial contents;
		bool failbit;
		bool eofbit;

		IFSerial(string filename);
		bool eof() const;
		bool fail() const;
		explicit operator bool() const {return !fail();}
};

class OFSerial {

	public:

		Serial contents;
		ofstream outfile;
		bool failbit;

		OFSerial(string filename);
		~OFSerial();
		bool fail() const;
		explicit operator bool() const {return !fail();}
};

IFSerial& operator>>(IFSerial& is, bool& b);
IFSerial& operator>>(IFSerial& is, short& s);
IFSerial& operator>>(IFSerial& is, int& i);
IFSerial& operator>>(IFSerial& is, long& l);
IFSerial& operator>>(IFSerial& is, char& c);
IFSerial& operator>>(IFSerial& is, string& s);

OFSerial& operator<<(OFSerial& os, bool b);
OFSerial& operator<<(OFSerial& os, short s);
OFSerial& operator<<(OFSerial& os, int i);
OFSerial& operator<<(OFSerial& os, long l);
OFSerial& operator<<(OFSerial& os, char c);
OFSerial& operator<<(OFSerial& os, const string s);

#endif
