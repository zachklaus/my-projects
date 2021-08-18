#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s=true + s;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case123 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "123 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "123 Error msg not std::string" << '\n';
  }
  return 0;
}
