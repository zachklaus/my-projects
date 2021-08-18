#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(true);
  if (s.size() != 1){
      cerr << "Case09 failed" << endl;
  }
  return 0;
}
