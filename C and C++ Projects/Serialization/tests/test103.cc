#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  bool a = true;
  s=s+a;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case103 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "103 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "103  Error msg not std::string" << '\n';
  }
  return 0;
}
