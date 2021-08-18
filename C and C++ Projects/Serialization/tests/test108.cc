#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s=s+(-45678);
  int i;
  try{
    s.get(i);
    if (i!=-45678){
	cerr << "Case108 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "108 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "108 Error msg not std::string" << '\n';
  }
  return 0;
}
