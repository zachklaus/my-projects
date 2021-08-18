#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  string a = "Testing serial case2"s;
  s+=a;
  const Serial s2(s);
  //s2 = s + s2;
  string st;
  try{
    s.get(st);
    if ( s == s2){
	cerr << "Case153 failed" << '\n';
    }
  }
  catch (string msg) {
    cerr << "153 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "153 Error msg not std::string" << '\n';
  }
  return 0;
}
