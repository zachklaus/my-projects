#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  bool a = true;
  s=a + s;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case124 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "124 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "124  Error msg not std::string" << '\n';
  }
  return 0;
}
