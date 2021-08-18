#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  short a = 4;
  s+=a;
  short i;
  try{
    s.get(i);
    if (i!=4){
	cerr << "Case88 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "88 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "88 Error msg not std::string" << '\n';
  }
  return 0;
}
