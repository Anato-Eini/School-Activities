// DO NOT modify this file.
// Only handle main.cpp
#include <string>
using namespace std;
class Stack {
public:
    virtual void push(string) = 0;
    virtual string pop() = 0;
    virtual string top() = 0;
    virtual int size() = 0;
    virtual bool isEmpty() = 0;
    virtual void print() = 0;
};