#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  char ch {'r'};
  s.put(ch);
  if (s.str()!= "\x63\x72"s){
      cerr << "Case48 failed" << endl;
  }
  return 0;
}
