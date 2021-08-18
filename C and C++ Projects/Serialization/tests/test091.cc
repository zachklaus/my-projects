#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  long a = -456789L;
  s+=a;
  long l;
  try{
    s.get(l);
    if (l!=a){
	cerr << "Case91 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "91 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "91 Error msg not std::string" << '\n';
  }
  return 0;
}
