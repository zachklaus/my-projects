#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;
  s.put(5L);

  bool caught;
  try {
    int x;
    s.get(x);
  } catch (string st) {
    if (st.size() > 0) caught = true;
  }

  if (!caught){
	cerr << "Case79 failed" << '\n';
    }
  return 0;
}
