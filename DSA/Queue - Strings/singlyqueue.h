#include "queue.h"
#include "linkedlist.h"
class SinglyQueue: public Queue{
    LinkedList* list;
public:
    SinglyQueue(){
        list = new LinkedList();
    }

    void enqueue(string string1) override {
        list->add(string1);
    }

    string dequeue() override {
        if(isEmpty()) return "(none)";
        return list->removeHead();
    }

    string first() override {
        if(isEmpty()) return "(none)";
        return list->getHead();
    }

    int size() override {
        return list->_size();
    }

    bool isEmpty() override {
        return size() == 0;
    }

    void print() override {
        list->print();
    }
};