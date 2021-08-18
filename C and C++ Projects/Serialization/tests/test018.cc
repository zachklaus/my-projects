#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(39534);
  if (s.empty()!= false){
      cerr << "Case18 failed" << endl;
  }
  return 0;
}
