#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(short(100));

  bool caught;
  try {
    char x;
    s.get(x);
  } catch (string st) {
    if (st.size() > 0) caught = true;
  }

  if (!caught){
	cerr << "Case80 failed" << '\n';
    }
  return 0;
}
