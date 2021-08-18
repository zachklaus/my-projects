#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(true);
  if (s.str() != "t"s){
      cerr << "Case08 failed" << endl;
  }
  return 0;
}
