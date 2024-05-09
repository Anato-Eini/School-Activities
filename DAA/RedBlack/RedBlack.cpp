#include "RedBlack.h"

RedBlack::RedBlack() : root(nullptr), size(0), null(new Node{0, false, nullptr, nullptr, nullptr}){}

Node *RedBlack::createNode(int num, Node *parent) {
    return new Node{num, true, parent, null, null};
}

void RedBlack::rotateLeft(Node *node) {
    Node* right = node->right, *rightLeft = right->left;
    nodeTransplant(node, right);
    node->parent = right;
    right->left = node;
    node->right = rightLeft;
    if(rightLeft != null)
        rightLeft->parent = node;
}

void RedBlack::rotateRight(Node *node) {
    Node* left = node->left, *leftRight = left->right;
    nodeTransplant(node, left);
    node->parent = left;
    left->right = node;
    node->left = leftRight;
    if(leftRight != null)
        leftRight->parent = node;
}

void RedBlack::nodeTransplant(Node *v, Node *u) {
    if(v == root)
        root = u;
    else if(v->parent->left == v)
        v->parent->left = u;
    else
        v->parent->right = u;
    u->parent = v->parent;
}


void RedBlack::insertFix(Node *node) {
    for(Node* curr = node; curr->parent && curr->parent->isRed;){
        Node* gp = curr->parent->parent, *uncle;
        if(gp && curr->parent == gp->left){
            uncle = gp->right;
            if(uncle->isRed){
                uncle->isRed = false;
                curr->parent->isRed = false;
                gp->isRed = true;
                curr = gp;
                continue;
            }
            gp->isRed = true;
            if(curr->parent->right == curr) {
                curr->parent->isRed = true;
                rotateLeft(curr->parent);
            }else
                curr->parent->isRed = false;
            rotateRight(gp);
        }else if(gp) {
            uncle = gp->left;
            if (uncle->isRed) {
                uncle->isRed = false;
                curr->parent->isRed = false;
                gp->isRed = true;
                curr = gp;
                continue;
            }
            gp->isRed = true;
            if(curr->parent->left == curr) {
                curr->parent->isRed = true;
                rotateRight(curr->parent);
            }else
                curr->parent->isRed = false;
            rotateLeft(gp);
        }else
            break;
    }
    root->isRed = false;
}

void RedBlack::insertHelper(int num, Node *node) {
    if(num < node->elem){
        if(node->left != null)
            insertHelper(num, node->left);
        else {
            node->left = createNode(num, node);
            insertFix(node->left);
        }
    }else{
        if(node->right != null)
            insertHelper(num, node->right);
        else {
            node->right = createNode(num, node);
            insertFix(node->right);
        }
    }
}

void RedBlack::insert(int num) {
    if(!root)
        root = createNode(num, nullptr);
    else
        insertHelper(num, root);
    size++;
}

void RedBlack::print() {
    cout << "Size: " << size << '\n';
    print("", 'Q', root);
}

void RedBlack::print(const std::string& pre, char loc, Node *node) {
    if(node != null){
        cout << pre << "|--" << loc << ": " << node->elem << (node->isRed ? "(R)" : "(B)") << '\n';
        print(pre + "    ", 'L', node->left);
        print(pre + "    ", 'R', node->right);
    }
}

void RedBlack::deleteNode(int num) {
    Node* node = findNode(num);
    if(!node)
        throw std::logic_error(to_string(num) + " doesn't exists\n");


}

Node *RedBlack::findNode(int num) {
    return findNodeHelper(root, num);
}

Node *RedBlack::findNodeHelper(Node *node, int num) {
    if(!node || node->elem == num)
        return node;
    else if(node->elem > num)
        return findNodeHelper(node->left, num);
    else
        return findNodeHelper(node->right, num);
}

bool RedBlack::search(int num) {
    return findNode(num);
}
