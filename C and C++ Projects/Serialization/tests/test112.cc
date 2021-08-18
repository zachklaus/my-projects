#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  long a = -456789L;
  s=s+a;
  long l;
  try{
    s.get(l);
    if (l!=a){
	cerr << "Case112 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "112 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "112 Error msg not std::string" << '\n';
  }
  return 0;
}
