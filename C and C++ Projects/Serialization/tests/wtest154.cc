#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s = "Testing c-string" + s;
  string st;
  try{
    s.get(st);
    if (st =="Testing c-string"s){
	cerr << "Case154 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "154 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "154 Error msg not std::string" << '\n';
  }
  return 0;
}
