#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s1;
  string x {"hello"s};
  s1.put(x);

  Serial s2(s1);
  s2.str("t"s);

  string xx;
  try{
    s1.get(xx);
    if (xx!="hello"s){
	cerr << "Case65 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "65 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "65 Error msg not std::string" << '\n';
  }
  return 0;
}
