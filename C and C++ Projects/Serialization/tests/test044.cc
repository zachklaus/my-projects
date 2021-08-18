#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  long l {9832759025L};
  s.put(l);
  if (s.str()!= "\x6c\x42\x4a\x13\xfe\xf1"s){
      cerr << "Case44 failed" << endl;
  }
  return 0;
}
