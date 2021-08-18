#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s = 456789L + s;
  long i;
  try{
    i <<= s;
    if (i!=456789L){
	cerr << "Case147 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "147 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "147 Error msg not std::string" << '\n';
  }
  return 0;
}
