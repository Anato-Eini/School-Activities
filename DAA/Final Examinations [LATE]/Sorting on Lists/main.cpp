#include <iostream>
#include "linkedlist.h"

/**
 * Override rules: Position -> index
 */

void bubbleSort(List*);
void selectionSort(List*);
void insertionSort(List*);

/**
 * This activity is focused on using Arrays and Linked Lists as two different
 * implementations of List. It follows that you are only to use the methods of
 * List and not of the specific array or linkedlist.
 */

int main(void) {
    // WARNING! Do not modify this line up until line 22!
    // Doing so will nullify your score for this activity.
    // Also, do not create more instances of ArrayList or LinkedList
    // aside from those in lines 24 and 26.
    char li;
    cout << "Enter type of list: ";
    cin >> li;

    List* list;
    if (li == 'A') {
        list = new ArrayList();
    } else {
        list = new LinkedList();
    }

    int length;
    cout << "Enter number of elements: ";
    cin >> length;

    int input;
    for (int i = 0; i < length; i++) {
        cout << "Enter element " << i+1 << ": ";
        cin >> input;
        list->add(input);
    }

    char sym;
    cout << "Enter sorting method: ";
    cin >> sym;
    list->print();
    switch (sym) {
        case 'B':
            bubbleSort(list);
            break;
        case 'I':
            insertionSort(list);
            break;
        case 'S':
            selectionSort(list);
            break;
    }
    return 0;
};

void bubbleSort(List* list) {
    int size = list->size();
    for(int i = 0; i < size - 1; i++)
        for(int j = 1; j < size - i; j++)
            if(list->get(j) < list->get(j - 1)) {
                list->swap(j, j - 1);
                list->print();
            }
};

void selectionSort(List* list) {
    int size = list->size();
    for(int i = 0; i < size - 1; i++){
        int minimum = i;
        for(int j = i + 1; j < size; j++)
            if(list->get(minimum) > list->get(j))
                minimum = j;
        if(minimum != i) {
            list->swap(minimum, i);
            list->print();
        }
    }
};

void insertionSort(List* list) {
    int size = list->size();
    for(int i = 1; i < size; i++){
        if(list->get(i) < list->get(i - 1))
            for(int j = i - 1; j >= 0 && list->get(j) > list->get(j + 1); j--){
                list->swap(j, j + 1);
                list->print();
            }
    }
};