#include <iostream>
#include "linkedlist.h"
#include <bits/stdc++.h>

LinkedList* mergeSort(LinkedList*);

// WARNING! Do not modify this main function!
// Doing so will nullify your score for this activity.
int main(void) {
    LinkedList* list = new LinkedList();

    int length;
    cout << "Input length: ";
    cin >> length;

    int input;
    for (int i = 0; i < length; i++) {
        cout << "Enter element " << i + 1 << ": ";
        cin >> input;
        list->add(input);
    }
    list->print();

    list = mergeSort(list);
    return 0;
};

node* getHalf(LinkedList *list){
    node* slow = list->head, *fast = list->head;

    while(fast && fast->next){
        slow = slow->next;
        fast = fast->next->next;
    }

    return !fast ? slow : slow->next;
}

LinkedList* mergeSort(LinkedList* list) {
    // BASE CASE: When the list only contains one element.
    if (list->size() <= 1) {
        return list;
    }

    // Step 1 - DIVIDE
    LinkedList* firsthalf = new LinkedList();
    LinkedList* secondhalf = new LinkedList();

    node* middleNode = getHalf(list), *curr = list->head;

    for(; curr != middleNode; curr = curr->next)
        firsthalf->add(curr->element);

    for(; curr; curr = curr->next)
        secondhalf->add(curr->element);

    // Given: Step 2 - RECURSIVE CALL
    cout << "My first list: ";
    firsthalf->print();
    firsthalf = mergeSort(firsthalf);

    cout << "My second list: ";
    secondhalf->print();
    secondhalf = mergeSort(secondhalf);

    // Step 3 - COMBINE
    LinkedList* sorted = new LinkedList();

    node* currFirst = firsthalf->head, *currSecond = secondhalf->head;

    while(currFirst && currSecond){
        if(currFirst->element < currSecond->element){
            sorted->add(currFirst->element);
            currFirst = currFirst->next;
        }else{
            sorted->add(currSecond->element);
            currSecond = currSecond->next;
        }
    }

    while(currFirst){
        sorted->add(currFirst->element);
        currFirst = currFirst->next;
    }

    while(currSecond){
        sorted->add(currSecond->element);
        currSecond = currSecond->next;
    }

    // print and return the sorted list
    cout << "SORTED: ";
    sorted->print();
    return sorted;
}