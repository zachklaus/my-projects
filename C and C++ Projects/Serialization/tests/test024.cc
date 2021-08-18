#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put('r');
  if (s.str()!= "\x63\x72"s){
      cerr << "Case24 failed" << endl;
  }
  return 0;
}
