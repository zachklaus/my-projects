#include <string>
#include "Serial.h"
#include <iostream>

using namespace std;

int main() {
  Serial s;

  s.put(3);
  s.put('*');
  int x;
  try{
    s.get(x);
    if (x != 3){
	cerr << "Case76 failed" << '\n';
    }
    s.put("testing"s);
    s.put(true);
    
    char ch;
    s.get(ch);
    if (ch != '*'){
	cerr << "Case76 failed" << '\n';
    }
    
    string ss;
    s.get(ss);
    if (ss != "testing"s){
	cerr << "Case76 failed" << '\n';
    }

  }
  catch (string msg) {
    cerr << "76 Error msg: " << msg << '\n';
  }
  catch (...) {
    cerr << "76 Error msg not std::string" << '\n';
  }
  return 0;
}
