#include <iostream>
#include <string>
#include "Serial.h"

using namespace std;

int main() {
  Serial s;
  if (s.size() != 0){
   cerr << "Case01 failed" << '\n';   
  }
  return 0;
}
