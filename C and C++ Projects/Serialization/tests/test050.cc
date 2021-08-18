#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  char ch {'r'};
  s.put(ch);
  if (s.empty()!= false){
      cerr << "Case50 failed" << endl;
  }
  return 0;
}
