#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s+=true;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case81 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "81 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "81 Error msg not std::string" << '\n';
  }
  return 0;
}
