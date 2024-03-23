#include <iostream>
#include "arraylist.h"
#include "stack.h"
using namespace std;

class ArrayStack : public Stack {
    ArrayList* list = new ArrayList();
public:
    void push(char num) {
        list->add(num);
    }

    char pop() {
        if (isEmpty()) {
            return ' ';
        }
        return list->removeLast();
    }

    char top() {
        if (isEmpty()) {
            return ' ';
        }
        return list->get(size());
    }

    int size() {
        return list->_size();
    }

    bool isEmpty() {
        return list->_size() == 0;
    }

    void print() {
        list->print();
    }
};