#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;

  bool caught {false};
  try {
  string ss;
  s.get(ss);
  } catch (string st) {
    if (!st.empty()) caught = true;
  }
  if (!caught){
      cerr << "Case75 failed" << endl;
  }

  return 0;
}
