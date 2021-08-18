#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s="Testing string case1"s + s;
  string st;
  try{
    s.get(st);
    if (st!="Testing string case1"s){
	cerr << "Case138 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "138 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "138 Error msg not std::string" << '\n';
  }
  return 0;
}
