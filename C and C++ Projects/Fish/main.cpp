#include <iostream>
#include <vector>

using namespace std;

class Fish {


	private:

	string color;
	double fin_length;
	double swim_speed;


	public:

	Fish(string color, double fin_length, double swim_speed)
		: color(color), fin_length(fin_length), swim_speed(swim_speed){}

	string get_color(){
		return color;
	}

	double get_fin_length(){
		return fin_length;
	}

	double get_swim_speed(){
		return swim_speed;
	}

	void print() {

		cout << "The " << color << " fish has a fin length of " << fin_length << " cm and a swim_speed of " << swim_speed << " m/s.\n";

	}


};


int main() {

	Fish* f = new Fish("red", 0.7, 0.2);
	Fish* d = new Fish("blue", 0.8, 0.3);
	Fish* c = new Fish("purple", 1.3, 0.5);

	vector<Fish*> school;

	school.push_back(f);
	school.push_back(d);
	school.push_back(c);

	for (Fish* fish: school)
		fish -> print();

	delete(f);
	delete(d);
	delete(c);

}


