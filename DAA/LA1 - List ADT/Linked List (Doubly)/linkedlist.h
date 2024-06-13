#ifndef LINKEDLIST_H
#define LINKEDLIST_H
// TODO entire implementation and add more header file/s as needed
#include<iostream>
#include<stdexcept>
#include "node.h"
#include "list.h"
using namespace std;
class LinkedList: public List{
Node* head, *tail;
int totalSize;
public:
    LinkedList(): totalSize(0), head(nullptr), tail(nullptr){}
    void insert(int num){
        if(!head){
            head = tail = new Node{num, nullptr, nullptr};
        }else{
            tail->next = new Node{num, nullptr, tail};
            tail = tail->next;
        }
        totalSize++;
    }
    int get(int pos){
        if(pos > totalSize || pos < 1)
            throw logic_error("Invalid position");
        Node* curr = head;
        int a = 1;
        while(a < pos){
            curr = curr->next;
            a++;
        }
        return curr->value;
    }
    int remove(int num){
        Node* curr = head;
        int a = 1;
        while(curr){
            if(curr->value == num){
                if(totalSize == 1){
                    head = tail = nullptr;
                    totalSize = 0;
                    return 1;
                }else if(curr == head){
                    head = head->next;
                    head->prev = nullptr;
                }else if(curr == tail){
                    tail = tail->prev;
                    tail->next = nullptr;
                }else{
                    curr->prev->next = curr->next;
                    curr->next->prev = curr->prev;
                }
                totalSize--;
                delete curr;
                return a;
            }
            curr = curr->next;
            a++;
        }
        return 0;
    }
    void print(){
        cout << "FROM HEAD: ";
        Node* curr = head;
        if(totalSize != 0) {
            while(curr){
            if(curr->next){
                cout << curr->value << " -> ";
            }else{
                cout << curr->value << '\n';
            }
            curr = curr->next;
            }
        }else{
            cout << "(none)\n";
        }
        curr = tail;
        cout << "FROM TAIL: ";
        if(totalSize != 0) {while(curr){
            if(curr->prev){
                cout << curr->value << " <- ";
            }else{
                cout << curr->value << '\n';
            }
            curr = curr->prev;
        }
        } else cout << "(none)\n";
    }
    int size(){
        return totalSize;
    }
    bool isEmpty(){
        return totalSize == 0;
    }
    void addAt(int num, int pos){
        if(pos > totalSize + 1 || pos < 1)
            throw logic_error("Invalid position");
        Node *curr = head;

        int a = 1;
        while(a < pos){
            curr = curr->next;
            a++;
        }
        if(totalSize == 0){
            Node* newNode = new Node;
            newNode->value = num;
            newNode->prev = nullptr;
            newNode->next = nullptr;
            head = tail = newNode;
        }else if(curr == head){
            Node* newNode = new Node;
            newNode->value = num;
            newNode->prev = nullptr;
            newNode->next = head;
            head->prev = newNode;
            head = newNode;
        }else if(curr == tail){
            Node* newNode = new Node;
            newNode->value = num;
            newNode->next = tail;
            newNode->prev = tail->prev;

            tail->prev->next = newNode;
            tail->prev = newNode;
        } else if(!curr){
            Node* newNode = new Node;
            newNode->value = num;
            newNode->next = nullptr;
            newNode->prev = tail;
            tail->next = newNode;
            tail = newNode;
        }
        else{
            Node* newNode = new Node;
            newNode->value = num;
            newNode->next = curr;
            newNode->prev = curr->prev;
            curr->prev->next = newNode;
            curr->prev = newNode;

        }
        totalSize++;
    }
    int removeAt(int pos){
        if(pos > totalSize || pos < 1)
            throw logic_error("Invalid position");
        Node* curr = head;
        int a = 1;
        while(a < pos){
            curr = curr->next;
            a++;
        }

        if(totalSize == 1){
            head = tail = nullptr;
        }else if(curr == head){
            head = head->next;
            head->prev = nullptr;
        }else if(curr == tail){
            tail = tail->prev;
            tail->next = nullptr;
        }else{
            curr->prev->next = curr->next;
            curr->next->prev = curr->prev;
        }
        int returner = curr->value;
        delete curr;
        totalSize--;
        return returner;
    }
};
#endif //LINKEDLIST_H
