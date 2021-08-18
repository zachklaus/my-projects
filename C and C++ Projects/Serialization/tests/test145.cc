#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s = 45678 + s;
  int i;
  try{
    i <<= s;
    if (i!=45678){
	cerr << "Case145 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "145 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "145 Error msg not std::string" << '\n';
  }
  return 0;
}
