#include "list.h"
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
    node* create_node(int num) {
        node* n = (node*) malloc( sizeof(node) );
        n->element = num;
        n->next = NULL;
        return n;
    }

public:
    node* head;
    node* tail;
    int index;

    LinkedList() {
        index = 0;
        head = NULL;
        tail = NULL;
    }

    void addHead(int num) {
        node* newest = create_node(num);
        newest->next = head;
        head = newest;
        if (tail == NULL) {
            tail = newest;
        }
        index++;
    }

    void addTail(int num) {
        node* newest = create_node(num);
        if (tail != NULL) {
            tail->next = newest;
        }
        tail = newest;
        if (head == NULL) {
            head = newest;
        }
        index++;
    }

    int removeHead() {
        int elem = 0;
        if (head != NULL) {
            elem = head->element;
            node* rem = head;
            head = head->next;
            if (head == NULL) {
                tail = NULL;
            }
            free(rem);
            index--;
        }
        return elem;
    }

    int add(int num) {
        addTail(num);
        return index;
    }

    int get(int pos) {
        node* currnode = head;
        int count = 0;
        while (currnode != NULL) {
            count++;
            if (pos == count) {
                return currnode->element;
            } else {
                currnode = currnode->next;
            }
        }
        return -1;
    }

    int size() {
        return index;
    }

    // WARNING! Do not modify this method below!
    // Doing so will nullify your score for this activity.
    void print() {
        node* currnode = head;
        if (head == NULL && tail == NULL) {
            cout << "(empty)" << endl;
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