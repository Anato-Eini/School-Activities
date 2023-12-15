#include <cstdlib>

struct node {
    node **children;
    int num_child;
    node *parent;
    int elem;

    int depth() {
        // TODO implementation [+10 pts]
        if(!parent && !children){
            return NULL;
        }
        return 1 + parent->depth();
    }


    int height() {
        // TODO implementation [+15 pts]
        return NULL;
    }
};