#include <string>
using namespace std;

struct node {
    string elem;
    node* left;
    node* right;
    node* parent;

    // TODO evaluate method
    int evaluate() {
        if(elem == "+"){
            return left->evaluate() + right->evaluate();
        }else if(elem == "-"){
            return left->evaluate() - right->evaluate();
        }else if(elem == "*"){
            return left->evaluate() * right->evaluate();
        }else if(elem == "/"){
            return left->evaluate() / right->evaluate();
        }else{
            return stoi(elem);
        }
    }
};