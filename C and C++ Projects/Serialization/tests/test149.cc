#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s="Testing string case1"s + s;
  string st;
  try{
    st <<= s;
    if (st!="Testing string case1"s){
	cerr << "Case149 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "149 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "149 Error msg not std::string" << '\n';
  }
  return 0;
}
