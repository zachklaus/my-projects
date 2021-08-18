#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const bool a = true;
  s+=a;
  bool b;
  try{
    s.get(b);
    if (b!=true){
	cerr << "Case83 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "83 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "83 Error msg not std::string" << '\n';
  }
  return 0;
}
