#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  char ch {'r'};
  s.put(ch);
  if (s.size()!= 2){
      cerr << "Case49 failed" << endl;
  }
  return 0;
}
