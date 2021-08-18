#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s.put("Testing c-string");
  string st;
  try{
    s.get(st);
    if (st =="Testing c-string"s){
	cerr << "Case157 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "157 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "157 Error msg not std::string" << '\n';
  }
  return 0;
}
