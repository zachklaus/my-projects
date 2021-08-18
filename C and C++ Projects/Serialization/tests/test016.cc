#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(39534);
  if (s.str()!= "\x69\x20\x9a\x6e"s){
      cerr << "Case16 failed" << endl;
  }
  return 0;
}
