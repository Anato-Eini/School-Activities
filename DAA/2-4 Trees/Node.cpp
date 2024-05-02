#include "Node.h"
Node::Node(int num, Node* parent) : data(new int[4]), size(1), children(new Node*[5]), parent(parent) {
    data[0] = num;
    for(int i = 0; i < 5; i++)
        children[i] = nullptr;
}

void Node::print() {
    print("", this);
}

void Node::print(string prefix, Node *node) {
    if(!node)
        return;
    cout << prefix;
    for(int i = 0; i < node->size; i++){
        cout << node->data[i] << ' ';
    }
    cout <<'\n';
    for(int i = 0; i < node->size + 1; i++){
        print(prefix + "--" + to_string(i + 1) + ": ", node->children[i]);
    }
}

bool Node::addKey(int num) {
    if(size == 4)
        return false;
    int i;
    for(i = size - 1; i >= 0 && num < data[i]; i--){
        data[i + 1] = data[i];
    }
    data[++i] = num;
    return ++size != 4;
}

void Node::setChildren(Node *wp, Node* w2p, int pos) {
    for(int i = size; i > pos; i--)
        children[i + 1] = children[i];
    children[pos] = wp;
    children[pos + 1] = w2p;
}

int Node::indexOf(Node *child) {
    for(int i = 0; i <= size; i++)
        if(child == children[i])
            return i;
    return -1;
}


