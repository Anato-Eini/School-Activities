#include <cstdlib>
#include<vector>
using namespace std;
struct node {
    node **children;
    int num_child;
    node *parent;
    int elem;
    int depth() {
        if(parent) return 1 + parent->depth();
    }
    int height() {
        if(num_child == 0){
            return 0;
        }
        int array[num_child];
        for(int a = 0; a < num_child; a++) array[a] = 0;
        for(int a = 0; a < num_child; a++){
            array[a] = children[a]->height();
        }
        int max = array[0];
        for(int i: array){
            if(i > max)  max = i;
        }
        return max + 1;
    }
};