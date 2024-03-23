#include <iostream>
#include <cstdlib>
#include <cmath>
#include "list.h"
using namespace std;

class ArrayList : public List {
    char* array;
    int size;
    int capacity = 5;

    void dynamic_add() {
        int new_size = ceil(capacity * 1.5);
        char* new_array = (char*) realloc(array, sizeof(char) * new_size);
        array = new_array;
        capacity = new_size;
    }

    void dynamic_deduce() {
        int new_size = ceil(capacity * 0.75);
        char* new_array = (char*) realloc(array, sizeof(char) * new_size);
        array = new_array;
        capacity = new_size;
    }

public:
    // constructor
    ArrayList() {
        array = (char*) calloc( capacity, sizeof(char) );
        size = 0;
    }

    void add(char num) {
        if (size == capacity) {
            dynamic_add();
        }
        array[size++] = num;
    }

    int _size() {
        return size;
    }

    char removeLast() {
        return array[--size];
    }


    char removeFirst() {
        char num = array[0];
        // Step 2: MOVE the elements to left
        for (int j = 0; j < size-1; j++) {
            array[j] = array[j+1];
        }
        // Step 3: Set the size
        array[size-1] = 0;
        size = size-1;
        // Step 4: Check to reduce capacity
        if (size <= 2.0/3 * capacity) {
            dynamic_deduce();
        }
        // Step 5: Return
        return num;
    }

    int remove(char num) {
        // Step 1: FIND the num
        for (int i = 0; i < size; i++) {
            if (array[i] == num) {
                // Step 2: MOVE the elements to left
                for (int j = i; j < size-1; j++) {
                    array[j] = array[j+1];
                }
                // Step 3: Set the size
                array[size-1] = 0;
                size = size-1;
                // Step 4: Check to reduce capacity
                if (size <= 2.0/3 * capacity) {
                    dynamic_deduce();
                }
                // Step 5: Return
                return i+1;
            }
        }
        return -1;
    }

    char get(int pos) {
        return array[pos-1];
    }

    void print() {
        int i;
        for (i = 0; i < size; i++) {
            cout << array[i] << " ";
        }
        for (; i < capacity; i++) {
            cout << "? ";
        }
        cout << endl;
    }
};