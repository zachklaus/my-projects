#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string a = "Testing string case2"s;
  s=s+a;
  string st;
  try{
    s.get(st);
    if (st!=a){
	cerr << "Case118 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "118 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "118 Error msg not std::string" << '\n';
  }
  return 0;
}
