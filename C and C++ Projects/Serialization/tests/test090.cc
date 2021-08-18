#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s+=456789L;
  long i;
  try{
    s.get(i);
    if (i!=456789L){
	cerr << "Case90 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "90 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "90 Error msg not std::string" << '\n';
  }
  return 0;
}
