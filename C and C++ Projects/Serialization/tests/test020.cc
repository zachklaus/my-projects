#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(9832759025L);
  if (s.str()!= "\x6c\x42\x4a\x13\xfe\xf1"s){
      cerr << "Case20 failed" << endl;
  }
  return 0;
}
