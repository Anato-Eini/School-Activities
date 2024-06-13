#ifndef ARRAYLIST_H
#define ARRAYLIST_H
// TODO entire implementation
#include<iostream>
#include "list.h"
#include<math.h>
#include<stdexcept>
using namespace std;
class ArrayList: public List{
int *array;
int capacity;
int totalSize;
    void dynamicAdd(){
        if(totalSize >= capacity){
            capacity = ceil(capacity * 1.5);
            array = (int*)realloc(array, capacity * sizeof(int));
        }
    }
    void dynamicReduce(){
        if(totalSize <= capacity * (2 / 3.0)){
            capacity = ceil(capacity * 0.75);
            if(capacity < 5)
                capacity = 5;
            array = (int*) realloc(array, capacity * sizeof(int));
        }
    }

    public:
    ArrayList(): totalSize(0), array(new int[5]), capacity(5){}

    void insert(int num){
        dynamicAdd();
        array[totalSize++] = num;
    }

    int get(int pos){
        if(pos > totalSize)
            throw logic_error("Invalid position");
        return array[pos - 1];
    }

    int remove(int num){
        for(int i = 0; i < totalSize; i++){
            if(array[i] == num){
                int returner = i + 1;

                for(++i; i < totalSize; i++)
                    array[i - 1] = array[i];

                totalSize--;
                dynamicReduce();
                return returner;
            }
        }

        return 0;
    }

    void print(){
        for(int i = 0; i < capacity; i++){
            if(i < totalSize)
                cout << array[i] << ' ';
            else cout << "? ";
        }
        cout << '\n';
    }

    int size(){
        return totalSize;
    }

    bool isEmpty(){
        return totalSize == 0;
    }

    void addAt(int num, int pos){
        if(pos > totalSize + 1)
            throw logic_error("Invalid position");
        dynamicAdd();
        for(int i = totalSize - 1; i >= pos - 1; i--){
            array[i + 1] = array[i];
        }
        array[pos - 1] = num;
        totalSize++;
    }

    int removeAt(int pos){
        if(pos > totalSize || pos <= 0)
            throw logic_error("Invalid position");
        int returner = array[pos - 1];
        for(int i = pos; i < totalSize; i++){
            array[i - 1] = array[i];
        }
        totalSize--;
        dynamicReduce();
        return returner;
    }

};
#endif //ARRAYLIST_H
