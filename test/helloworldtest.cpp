#include "./src/cpp/helloworld.cpp"
#include <string>
#include <assert.h>

int main() {
	std::string expected = "Hello World!";
	std::string result = helloWorld();
	
	assert(expected == result);
}