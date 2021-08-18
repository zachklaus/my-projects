#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string st {"You may assume that a string constant will not contain a double quote, backslash, or a newline."s};
  s.put(st);
  string x;
  try{
    s.get(x);
    if (x!= "You may assume that a string constant will not contain a double quote, backslash, or a newline."s){
	cerr << "Case51 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "51 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "51 Error msg not std::string" << '\n';
  }
  return 0;
}
