#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  const int a = 45678;
  s=a + s;
  int i;
  try{
    s.get(i);
    if (i!=45678){
	cerr << "Case128 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "128 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "128 Error msg not std::string" << '\n';
  }
  return 0;
}
