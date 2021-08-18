#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s=s+true;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case102 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "102 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "102 Error msg not std::string" << '\n';
  }
  return 0;
}
