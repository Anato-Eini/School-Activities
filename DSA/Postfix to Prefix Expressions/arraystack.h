// DO NOT modify this file.
// Only handle main.cpp
#include <iostream>
#include <string>
#include "stack.h"
#include "arraylist.h"
using namespace std;

class ArrayStack : public Stack {
    ArrayList* list = new ArrayList();
public:
    void push(string num) {
        list->add(num);
    }

    string pop() {
        if (isEmpty()) {
            cout << "WAY SULOD OI" << endl;
            return "";
        }
        return list->removeLast();
    }

    string top() {
        if (isEmpty()) {
            cout << "WAY SULOD OI" << endl;
            return "";
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