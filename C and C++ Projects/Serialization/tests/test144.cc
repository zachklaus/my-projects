#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  s=true + s;
  bool b;
  try{
    //s.get(b);
    b <<= s;  
    if (b!=true){
	cerr << "Case144 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "144 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "144 Error msg not std::string" << '\n';
  }
  return 0;
}
