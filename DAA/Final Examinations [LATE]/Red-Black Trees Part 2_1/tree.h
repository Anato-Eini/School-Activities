#include "node.h"
#include <iostream>
#include <cstdlib>
using namespace std;
class BSTree {
    node* root;
    int size;

    node* create_node(int num, node* parent) {
        node* n = (node*) malloc( sizeof(node) );
        n->element = num;
        n->parent = parent;
        n->right = NULL;
        n->left = NULL;
        n->is_red = true;
        return n;
    }

    bool search(node* curr, int num) {
        if (curr == NULL) {
            return false;
        }
        if (num == curr->element) {
            return true;
        }

        if (num < curr->element) {
            return search(curr->left, num);
        }
        return search(curr->right, num);
    }

    node* search_node(node* curr, int num) {
        if (num == curr->element) {
            return curr;
        }

        if (num < curr->element) {
            if (curr->left != NULL) {
                return search_node(curr->left, num);
            }
            return curr;
        }
        if (curr->right != NULL) {
            return search_node(curr->right, num);
        }
        return curr;
    }

public:
    BSTree() {
        root = NULL;
        size = 0;
    }

    bool search(int num) {
        return search(root, num);
    }

    bool insert(int num) {
        if (root == NULL) {
            root = create_node(num, NULL);
            root->is_red = false;
            size++;
            return true;
        } else {
            node* parent = search_node(root, num);
            if (parent->element != num) {
                node* newest = create_node(num, parent);
                if (parent->element < num) {
                    parent->right = newest;
                } else {
                    parent->left = newest;
                }
                size++;
                node* par = newest->parent;
                // CHECK for double red violation
                while (newest->is_red && par->is_red) {
                    node* sibling = NULL;
                    if (par->parent->left == par) {
                        sibling = par->parent->right;
                    } else {
                        sibling = par->parent->left;
                    }
                    // CASE 1: Sibling s of y is BLACK
                    if (sibling == NULL || sibling->is_red == false) {
                        cout << "INSERTION Violation: Case 1" << endl;
                        node* b = restructure(newest, true);
                        b->is_red = false;
                        b->left->is_red = true;
                        b->right->is_red = true;
                        break;
                    } else if (sibling->is_red == true) {
                        cout << "INSERTION Violation: Case 2" << endl;
                        sibling->is_red = false;
                        par->is_red = false;
                        if (par->parent != root) {
                            par->parent->is_red = true;
                            newest = par->parent;
                            par = newest->parent;
                        }
                    }
                }
                return true;
            }
        }
        print();
        return false;
    }
    //Case 1 -> Print restructure;

    void deleteFix(node* n){
        node* currNode = n;
        while(currNode->parent && !currNode->is_red){
            node* sibling;
            if(currNode->parent->left == currNode && currNode->parent->right){
                sibling = currNode->parent->right;
                if(sibling->is_red){
                    cout << "DELETION Violation: Case 3\n";
                    currNode->parent->is_red = true;
                    sibling->is_red = false;
                    restructure(sibling, false);
                    sibling = currNode->parent->right;
                }

                if(
                        (!sibling->right && !sibling->left) ||
                        (sibling->right && sibling->left && !sibling->right->is_red && !sibling->left->is_red))
                {
                    cout << "DELETION Violation: Case 2\n";
                    sibling->is_red = true;
                    currNode = currNode->parent;
                }else{
                    cout << "DELETION Violation: Case 1\n";
                    if(sibling->right) {
                        sibling->is_red = currNode->parent->is_red;
                        sibling->right->is_red = currNode->parent->is_red = false;
                        restructure(sibling->right, true);
                    }else if(sibling->left && sibling->left->is_red){
                       sibling->left->is_red = true;
                       currNode->parent->is_red = sibling->is_red = false;
                       restructure(sibling->left, true);
                       if(sibling->left)
                            sibling->left->is_red = currNode->parent->is_red;
                       sibling->is_red = false;
                    }else{
                        sibling->is_red = currNode->parent->is_red;
                        currNode->parent->is_red = false;
                        restructure(sibling, true);
                    }
                    currNode = root;
                }
            }else if(currNode->parent->left){
                sibling = currNode->parent->left;
                if(sibling->is_red){
                    cout << "DELETION Violation: Case 3\n";
                    currNode->parent->is_red = true;
                    sibling->is_red = false;
                    restructure(sibling, false);
                    sibling = currNode->parent->left;
                }
                if(
                        (!sibling->right && !sibling->left) ||
                        (sibling->right && sibling->left && !sibling->right->is_red && !sibling->left->is_red))
                {
                    cout << "DELETION Violation: Case 2\n";
                    sibling->is_red = true;
                    currNode = currNode->parent;
                }else{
                    cout << "DELETION Violation: Case 1\n";
                    if(sibling->left){
                        sibling->is_red = currNode->parent->is_red;
                        sibling->left->is_red = currNode->parent->is_red = false;
                        restructure(sibling->left, true);
                    }else if(sibling->right && sibling->right->is_red){
                        sibling->right->is_red = true;
                        currNode->parent->is_red = sibling->is_red = false;
                        restructure(sibling->right, true);
                        if(sibling->right)
                            sibling->right->is_red = currNode->parent->is_red;
                        sibling->is_red = false;
                    }else {
                        sibling->is_red = currNode->parent->is_red;
                        currNode->parent->is_red = false;
                        restructure(sibling, true);
                    }
                    currNode = root;
                }
            }else
                currNode = currNode->parent;
        }
        currNode->is_red = false;
    }

    bool remove(int num) {
        if (isEmpty()) {
            return false;
        }
        node* rem_node = search_node(root, num);
        if (rem_node->element != num) {
            return false;
        }

        // FIND the number of children.
        int children = 0;
        // 0 - no children
        // -1 - left child only
        // 1 - right child only
        // 2 - both children
        if (rem_node->right) {
            children = 1;
        }
        if (rem_node->left) {
            if (children == 1) {
                children = 2;
            } else {
                children = -1;
            }
        }

        if (children == 0) { // NO CHILDREN
            node* parent = rem_node->parent;

            if(!rem_node->is_red){
                deleteFix(rem_node);
            }

            if (!parent) {
                root = NULL;
            } else {
                if (rem_node == parent->left) {
                    parent->left = NULL;
                } else {
                    parent->right = NULL;
                }
            }


            free(rem_node);
            size--;
        } else if (children == -1 || children == 1) { // ONE CHILD
            node* parent = rem_node->parent;
            node* child;
            if (children == -1) {
                child = rem_node->left;
            } else {
                child = rem_node->right;
            }

            child->parent = parent;

            if (!parent) {
                root = child;
            } else {
                if (parent->left == rem_node) {
                    parent->left = child;
                } else {
                    parent->right = child;
                }
            }

            deleteFix(child);

            free(rem_node);
            size--;
        } else { // TWO CHILDREN
            node* restructureNode = rem_node;

            node* right_st = rem_node->right;
            while (right_st->left != NULL) {
                right_st = right_st->left;
            }

            if(right_st != rem_node->right)
                restructureNode = right_st;

            if(!restructureNode->is_red)
                deleteFix(restructureNode);


            int temp = right_st->element;
            remove(temp);
            rem_node->element = temp;
        }

        return true;
    }


    void zigleft(node* curr) {
        node* t2 = curr->left;
        node* par = curr->parent;
        if (!par) {
            return;
        }

        node* gp = par->parent;
        if (gp) {
            if (gp->right == par) {
                gp->right = curr;
            } else {
                gp->left = curr;
            }
            curr->parent = gp;
        }
        curr->left = par;
        par->parent = curr;
        par->right = t2;
        if (t2) {
            t2->parent = par;
        }
        if (!gp) {
            root = curr;
            curr->parent = NULL;
        }
    }

    void nodeTransplant(node* u, node* v){
        if(u == root)
            root = v;
        else if(u->parent->left == u)
            u->parent->left = v;
        else
            u->parent->right = v;

        if(v)
            v->parent = u->parent;
    }

    // implementation of rotate operation of x where
    //   |
    //   y
    //  /
    // x <- curr
    void zigright(node* curr) {
        node *right = curr->right, *parent = curr->parent;
        nodeTransplant(parent, curr);
        curr->right = parent;
        parent->parent = curr;
        parent->left = right;
        if(right)
            right->parent = parent;
    }

    // Given the child, find its parent and grandparent. Assume that both are present.
    // Return the parent node (or b) after the restructure.
    node* restructure(node* child, bool print) {
        node* par = child->parent;
        // This is an indicator of the placement of parent to child (ptoc)

        bool ptoc_right = false;
        if (par->right == child) {
            ptoc_right = true;
        }

        node* gp = par->parent;
        // This is an indicator of the placement of grandparent to parent (gtop)
        bool gtop_right = false;
        if (gp && gp->right == par) {
            gtop_right = true;
        }

        // FOR THE FOLLOWING: Write in each of the if statements a console output
        // on its corresponding operation (ZIGLEFT, ZIGRIGHT, ZIGZAGLEFT, or ZIGZAGRIGHT)

        // z
        //  \
        //   y
        //    \
        //     x
        if (gp && gtop_right && ptoc_right) {

            if(print)
                cout << "ZIGLEFT\n";
            zigleft(par);
            return par;
        }

            // z
            //   \
            //     y
            //    /
            //   x
        else if (gp && gtop_right) {
            if(print)
                cout << "ZIGZAGLEFT\n";
            zigright(child);
            zigleft(child);
            return child;
        }

            //     z
            //    /
            //   y
            //  /
            // x
        else if (gp && !ptoc_right) {
            if(print)
                cout << "ZIGRIGHT\n";
            zigright(par);
            return par;
        }

            //      z
            //    /
            //  y
            //   \
            //    x
        else if(gp){
            if(print)
                cout << "ZIGZAGRIGHT\n";
            zigleft(child);
            zigright(child);
            return child;
        }else if(ptoc_right){
            if(print)
                cout << "ZIGLEFT\n";
            zigleft(child);
            return child;
        }else {
            if(print)
                cout << "ZIGRIGHT\n";
            zigright(child);
            return child;
        }
    }

    // WARNING. Do not modify these methods below.
    // Doing so will nullify your score for this activity.
    void print() {
        if (isEmpty()) {
            cout << "EMPTY" << endl;
            return;
        }
        cout << "PRE-ORDER: ";
        print_preorder(root);
        cout << endl << "IN-ORDER: ";
        print_inorder(root);
        cout << endl << "POST-ORDER: ";
        print_postorder(root);
        cout << endl << "STATUS: " << check_health(root, NULL) << endl;
    }

    bool isEmpty() {
        return size == 0;
    }

    void print_preorder(node* curr) {
        cout << curr->element;
        if (curr->is_red) {
            cout << "(R) ";
        } else {
            cout << "(B) ";
        }
        if (curr->left != NULL) {
            print_preorder(curr->left);
        }
        if (curr->right != NULL) {
            print_preorder(curr->right);
        }
    }

    void print_inorder(node* curr) {
        if (curr->left != NULL) {
            print_inorder(curr->left);
        }
        cout << curr->element;
        if (curr->is_red) {
            cout << "(R) ";
        } else {
            cout << "(B) ";
        }
        if (curr->right != NULL) {
            print_inorder(curr->right);
        }
    }

    void print_postorder(node* curr) {
        if (curr->left != NULL) {
            print_postorder(curr->left);
        }
        if (curr->right != NULL) {
            print_postorder(curr->right);
        }
        cout << curr->element;
        if (curr->is_red) {
            cout << "(R) ";
        } else {
            cout << "(B) ";
        }
    }

    // WARNING. Do not modify this method.
    // Doing so will nullify your score for this activity.
    bool check_health(node* curr, node* parent) {
        bool health = curr->parent == parent;
        if (curr->left != NULL) {
            health &= check_health(curr->left, curr);
        }
        if (curr->right != NULL) {
            health &= check_health(curr->right, curr);
        }
        return health;
    }
};