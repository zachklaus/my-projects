#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put("You may assume that a string constant will not contain a double quote, backslash, or a newline."s);
  string x;
  try{
    s.get(x);
    if (x!="You may assume that a string constant will not contain a double quote, backslash, or a newline."s){
	cerr << "Case27 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "27 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "27 Error msg not std::string" << '\n';
  }
  return 0;
}
