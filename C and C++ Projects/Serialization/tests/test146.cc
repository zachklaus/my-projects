#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  short a = 4;
  s=a + s;
  short i;
  try{
    i <<= s;
    if (i!=4){
	cerr << "Case146 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "146 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "146 Error msg not std::string" << '\n';
  }
  return 0;
}
