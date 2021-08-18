#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(39534);
  if (s.size()!= 4){
      cerr << "Case17 failed" << endl;
  }
  return 0;
}
