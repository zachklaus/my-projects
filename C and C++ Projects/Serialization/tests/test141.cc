#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s+="Testing serial case1"s;
  Serial s2;
  s2 = s + s2;
  string st;
  try{
    s2.get(st);
    if (st!="Testing serial case1"s){
	cerr << "Case141 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "141 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "141 Error msg not std::string" << '\n';
  }
  return 0;
}
