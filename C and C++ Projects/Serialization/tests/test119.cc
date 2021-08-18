#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const string a = "Testing string case3";
  s=s+a;
  string st;
  try{
    s.get(st);
    if (st!=a){
	cerr << "Case119 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "119 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "119 Error msg not std::string" << '\n';
  }
  return 0;
}
