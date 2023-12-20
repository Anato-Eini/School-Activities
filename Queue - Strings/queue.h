#include <iostream>
using namespace std;
class Queue {
public:
    virtual void enqueue(string) = 0;
    virtual string dequeue() = 0;
    virtual string first() = 0;
    virtual int size() = 0;
    virtual bool isEmpty() = 0;
    virtual void print() = 0;
};