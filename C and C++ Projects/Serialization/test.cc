#include <iostream>
#include <string>
#include <cassert>

#include "FSerial.h"

using namespace std;

int main() {

	cout << "Test begin.\n";

	string filename = "C:\\Users\\Zachary\\eclipse-workspace\\cs253 HW7\\data"s;

	{

		OFSerial os(filename);
		os << true;

	}

	IFSerial is(filename);

	bool b0;
	is >> b0;
	assert(b0);

	cout << "Test end.\n";

    return 0;

}
