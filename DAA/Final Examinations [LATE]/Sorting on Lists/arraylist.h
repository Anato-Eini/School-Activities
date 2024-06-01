#include "list.h"
#include <cstdlib>
#include <iostream>
#include <cmath>

using namespace std;
/**
 * Your ArrayList must have an initial capacity of 4.
 * If the array is full, allocate an additional capacity of 50% its current size.
 * Example, 4 becomes 6, then 6 becomes 9, so on.
 * Maintain a minimum capacity of 4.
 * 
 * On the fields:
 * array - holds the pointer to the array itself
 * index - holds the current size, initially zero
 * capacity - holds the current capacity of the array - minimum of 4
 */
class ArrayList : public List {
    int* array;
    int index;
    int capacity;

public:
    // CONSTRUCTOR
    ArrayList() : capacity{4}, index{0}, array{new int[4]} {}

    int add(int num) {
        if(index == capacity) {
            capacity += ceil(capacity / 2.0);
            array = (int *) realloc(array, capacity * sizeof(int));
        }

        array[index++] = num;

        return index - 1;
    }

    int get(int pos){
        if(pos < 0 || pos >= index)
            return -1;
        return array[pos];
    }

    int size(){
        return index;
    }

    void swap(int pos1, int pos2){
        std::swap(array[pos1], array[pos2]);
    }

    // WARNING! Do not modify the print method.
    // Doing so will nullify your score for this activity.
    void print() {
        cout << "[";
        for (int i = 0; i < capacity; i++) {
            if (i < index) {
                cout << *(array + i);
            } else {
                cout << "?";
            }

            if (i != capacity - 1) {
                cout << ", ";
            }
        }
        cout << "]" << endl;
    }
};