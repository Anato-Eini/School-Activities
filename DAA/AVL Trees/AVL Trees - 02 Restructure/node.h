#include <algorithm>
struct node {
    node* parent;
    node* right;
    node* left;
    int elem;

    // TODO paste your height method here
    int height() {
        if(!left && !right)
            return 1;
        else if(!right)
            return left->height() + 1;
        else if(!left)
            return right->height() + 1;
        else
           return std::max(left->height(), right->height()) + 1;
    }
};