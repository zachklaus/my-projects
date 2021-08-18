#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const short a = -4;
  s=s+a;
  short i;
  try{
    s.get(i);
    if (i!=-4){
	cerr << "Case110 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "110 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "110 Error msg not std::string" << '\n';
  }
  return 0;
}
