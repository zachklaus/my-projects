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

  if (s3.str()!= "\x53\x05\x68\x65\x6c\x6c\x6f"s){
      cerr << "Case64 failed" << endl;
  }
  return 0;
}
