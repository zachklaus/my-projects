#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s='$' + s;
  char c;
  try{
    c <<= s;
    if (c!='$'){
	cerr << "Case148 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "148 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "148 Error msg not std::string" << '\n';
  }
  return 0;
}
