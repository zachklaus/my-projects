#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  int i {39534};
  s.put(i);
  if (s.str()!= "\x69\x20\x9a\x6e"s){
      cerr << "Case40 failed" << endl;
  }
  return 0;
}
