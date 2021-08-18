#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s = 45678 + s;
  int i;
  try{
    s.get(i);
    if (i!=45678){
	cerr << "Case126 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "126 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "126 Error msg not std::string" << '\n';
  }
  return 0;
}
