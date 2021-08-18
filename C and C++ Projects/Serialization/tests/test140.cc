#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const string a = "Testing string case3";
  s=a + s;
  string st;
  try{
    s.get(st);
    if (st!=a){
	cerr << "Case140 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "140 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "140 Error msg not std::string" << '\n';
  }
  return 0;
}
