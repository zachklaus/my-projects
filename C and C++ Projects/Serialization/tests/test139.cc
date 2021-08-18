#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string a = "Testing string case2"s;
  s=a + s;
  string st;
  try{
    s.get(st);
    if (st!=a){
	cerr << "Case139 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "139 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "139 Error msg not std::string" << '\n';
  }
  return 0;
}
