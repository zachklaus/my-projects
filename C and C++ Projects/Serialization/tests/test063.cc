#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s1;
  string x {"hello"s};
  s1.put(x);

  const Serial s2(s1);

  Serial s3(s2);
  string xx;
  try{
    s3.get(xx);
    if (xx!="hello"s){
	cerr << "Case63 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "63 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "63 Error msg not std::string" << '\n';
  }
  return 0;
}
