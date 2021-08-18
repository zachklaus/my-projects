#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const char a = 'G';
  s+=a;
  char c;
  try{
    s.get(c);
    if (c!=a){
	cerr << "Case95 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "95 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "95 Error msg not std::string" << '\n';
  }
  return 0;
}
