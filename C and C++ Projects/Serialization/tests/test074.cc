#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;

  bool caught {false};
  try {
  char ch;
  s.get(ch);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (!caught){
      cerr << "Case74 failed" << endl;
  }

  return 0;
}
