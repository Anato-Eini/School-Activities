#include <cstdlib>
#include <iostream>
#include <utility>
#include "list.h"
#include "node.h"
using namespace std;

class LinkedList : public List {
	node* head;
	node* tail;
	int size;

	public:
	string removeHead() {
		string elem = head->elem;
		head = head->next;
		size--;
		return elem;
	}

	void addTail(string num) {
        add(std::move(num));
    }

    void addHead(string num) {
		node* n = (node*) calloc( 1, sizeof(node) );
		n->elem = num;
		if (size == 0) {
			head = n;
			tail = n;
		} else {
			n->next = head;
			head = n;
		}
		size++;
    }

    int _size() {
    	return size;
	}

	void add(string num) {
		node* n = (node*) calloc( 1, sizeof(node) );
		n->elem = num;
		if (size == 0) {
			head = n;
			tail = n;
		} else {
			tail->next = n;
			tail = n;
		}
		size++;
	}

	string getHead() {
		return head->elem;
	}

    int get(int pos) {
    	return 0;
    }

    string remove(string num) {
    	node* curr = head;
    	node* prev;
    	while (curr) {
    		if (curr->elem == num) {
    			prev->next = curr->next;
    			size--;
    			return "";
			}
			prev = curr;
			curr = curr->next;
		}
    	return "";
	}

    void print() {
    	node* curr = head;
    	if (size == 0) {
    		cout << "Empty" << endl;
		}
		else {
	    	do {
	    		cout << curr->elem;
	    		if (curr->next) {
	    			cout << " -> ";
				} else {
					cout << endl;
				}
	    		curr = curr->next;
			} while (curr);
		}
    }
};