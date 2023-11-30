#include "mybinarytree.h"

class BST {
	BinaryTree* tree = new MyBinaryTree();

	public:
	bool search(int num) {
        node* curr = tree->getRoot();
        node* par = NULL;
        if(!tree->getRoot()){
            tree->addRoot(num);
            return true;
        }
        while(curr){
            par = curr;
            if(curr->elem > num){
                curr = curr->left;
            } else if(curr->elem < num){
                curr = curr->right;
            }else if (curr->elem == num){
                return true;
            }
        }
        return false;
	}

	bool insert(int num) {
        node* curr = tree->getRoot();
        node* par = NULL;
        if(!tree->getRoot()){
            tree->addRoot(num);
            return true;
        }
        while(curr){
            par = curr;
            if(curr->elem > num){
                curr = curr->left;
            } else if(curr->elem < num){
                curr = curr->right;
            }else{
                return false;
            }
        }
        if(par->elem > num){
            tree->addLeft(par, num);
        } else{
            tree->addRight(par, num);
        }
        return true;
	}

	void print() {
		tree->print();
	}
};