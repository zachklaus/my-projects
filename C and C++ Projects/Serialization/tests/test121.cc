#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string a = "Testing serial case2"s;
  s+=a;
  Serial s2;
  s2 = s2 + s;
  string st;
  try{
    s2.get(st);
    if (st!=a){
	cerr << "Case121 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "121 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "121 Error msg not std::string" << '\n';
  }
  return 0;
}
