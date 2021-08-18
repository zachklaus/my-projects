#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put("You may assume that a string constant will not contain a double quote, backslash, or a newline."s);
  if (s.empty()!= false){
      cerr << "Case30 failed" << endl;
  }
  return 0;
}
