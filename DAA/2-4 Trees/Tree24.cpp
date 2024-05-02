#include "Tree24.h"

Tree24::Tree24() : root(nullptr), size(0) {}

Node *Tree24::search(int num) {
    Node* curr = root;
    if(!curr)
        return nullptr;
    while(true){
        int i;
        for(i = 0; i < curr->size && num < curr->data[i]; i++){
            if(curr->data[i] == num)
                return curr;
        }
        Node* child = curr->children[i];
        if(!child)
            return curr;
        curr = child;
    }
}

Node *Tree24::insert(int num) {
    if(!root){
        root = new Node(num, nullptr);
        size++;
        return root;
    }
    Node* searchedNode = search(num);
    bool ok = searchedNode->addKey(num);
    if(!ok){
        Node* u;
        int pos;
        if(searchedNode == root){
            u = new Node(searchedNode->data[2], nullptr);
            root = u;
            pos = 0;
        }else{
            u = searchedNode->parent;
            pos = u->indexOf(searchedNode);
        }
        Node* wp = new Node(searchedNode->data[0], u);
        wp->addKey(searchedNode->data[1]);
        Node* w2p = new Node(searchedNode->data[3], u);
        u->setChildren(wp, w2p, pos);
    }
    return searchedNode;
}

void Tree24::print() {
    root->print();
}
