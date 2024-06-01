#include "arraylist.h"
#include <cstdlib>
#include <iostream>
using namespace std;

// WARNING! Do not modify this struct!
// Doing so will nullify your score for this activity.
struct node {
    int element;
    node* next;
};

/**
 * This implementation is of a Singly-Linked List.
 * Feel free to add helper methods. You might want the create_node helper method.
 */
class LinkedList : public List {
    node* head;
    node* tail;
    int index;

    static node* create_node(int num) {
        return new node{num, nullptr};
    }

public:
    LinkedList() : index{0} {
        head = tail = nullptr;
    }

    int add(int num) {
        if(!head)
            head = tail = create_node(num);
        else
            tail = tail->next = create_node(num);

        index++;
        return index;
    }

    int get(int pos) {
        node* curr = head;
        for(int i = 0; i < pos; i++)
            curr = curr->next;

        return curr->element;
    }

    int size() {
        return index;
    }

    void swap(int pos1, int pos2) {
        node* curr1 = head, *curr2 = head;

        for(int i = 0; i < pos1; i++)
            curr1 = curr1->next;

        for(int i = 0; i < pos2; i++)
            curr2 = curr2->next;

        std::swap(curr1->element, curr2->element);

    }

    // WARNING! Do not modify this method below!
    // Doing so will nullify your score for this activity.
    void print() {
        node* currnode = head;
        if (head == NULL && tail == NULL) {
            cout << "(empty)";
            return;
        }
        while (true) {
            cout << currnode->element;
            if (currnode == tail) {
                cout << endl;
                return;
            }
            cout << " -> ";
            currnode = currnode->next;
        }
    }
};