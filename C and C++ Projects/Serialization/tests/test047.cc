#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  char ch {'r'};
  s.put(ch);
  char x;
  try{
    s.get(x);
    if (x!='r'){
	cerr << "Case47 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "47 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "47 Error msg not std::string" << '\n';
  }
  return 0;
}
