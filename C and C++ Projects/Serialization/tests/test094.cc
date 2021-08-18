#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  char a = '	';
  s+=a;
  char c;
  try{
    s.get(c);
    if (c!=a){
	cerr << "Case94 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "94 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "94 Error msg not std::string" << '\n';
  }
  return 0;
}
