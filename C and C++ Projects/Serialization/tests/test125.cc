#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const bool a = true;
  s=a + s;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case125 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "125 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "125 Error msg not std::string" << '\n';
  }
  return 0;
}
