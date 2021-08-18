#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s1;
  string x {"hello"s};
  s1.put(x);

  Serial s2;
  s2 = s1;

  if (s2.str()!= "\x53\x05\x68\x65\x6c\x6c\x6f"s){
      cerr << "Case68 failed" << endl;
  }

  return 0;
}
