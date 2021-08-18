#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  long l {9832759025L};
  s.put(l);
  if (s.empty()!= false){
      cerr << "Case46 failed" << endl;
  }
  return 0;
}
