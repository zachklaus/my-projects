#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const long a = 456789L;
  s=a + s;
  long l;
  try{
    s.get(l);
    if (l!=a){
	cerr << "Case134 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "134 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "134 Error msg not std::string" << '\n';
  }
  return 0;
}
