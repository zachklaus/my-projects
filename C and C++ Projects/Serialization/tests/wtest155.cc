#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s = s + "Testing c-string";
  string st;
  try{
    s.get(st);
    if (st =="Testing c-string"s){
	cerr << "Case155 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "155 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "155 Error msg not std::string" << '\n';
  }
  return 0;
}
