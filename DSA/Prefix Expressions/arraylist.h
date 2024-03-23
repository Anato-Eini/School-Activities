// DO NOT modify this file.
// Only handle main.cpp
#include <iostream>
#include <cstdlib>
#include <math.h>
#include "list.h"
using namespace std;

class ArrayList : public List {
    int* array;
    int size;
    int capacity = 5;

    void dynamic_add() {
        int new_size = ceil(capacity * 1.5);
        array = (int*) realloc(array, sizeof(int) * new_size);
//		int array = new_array;
        capacity = new_size;
    }

    void dynamic_deduce() {
        int new_size = ceil(capacity * 0.75);
        int* new_array = (int*) realloc(array, sizeof(int) * new_size);
        array = new_array;
        capacity = new_size;
    }

public:
    // constructor
    ArrayList() {
        array = (int*) calloc( capacity, sizeof(int) );
        size = 0;
    }

    void add(int num) {
        if (size == capacity) {
            dynamic_add();
        }
        array[size++] = num;
    }

    int _size() {
        return size;
    }

    int removeLast() {
        return array[--size];
    }

    int removeFirst() {
        int num = array[0];
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

    int remove(int num) {
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

    int get(int pos) {
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