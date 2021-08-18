#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  const string a = "Testing serial case3";
  Serial s;
  s+=a;
  const Serial s2(s);
  Serial s3;
  s3 = s3 + s2;
  string st;
  try{
    s3.get(st);
    if (st!=a){
	cerr << "Case122 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "122 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "122 Error msg not std::string" << '\n';
  }
  return 0;
}
