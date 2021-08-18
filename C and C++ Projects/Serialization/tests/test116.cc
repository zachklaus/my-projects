#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const char a = 'G';
  s=s+a;
  char c;
  try{
    s.get(c);
    if (c!=a){
	cerr << "Case116 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "116 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "116 Error msg not std::string" << '\n';
  }
  return 0;
}
