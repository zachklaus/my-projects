#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  string st {"You may assume that a string constant will not contain a double quote, backslash, or a newline."s};
  s.put(st);
  if (s.size()!= 98){
      cerr << "Case53 failed" << endl;
  }
  return 0;
}
