#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const long a = 456789L;
  s+=a;
  long l;
  try{
    s.get(l);
    if (l!=a){
	cerr << "Case92 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "92 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "92 Error msg not std::string" << '\n';
  }
  return 0;
}
