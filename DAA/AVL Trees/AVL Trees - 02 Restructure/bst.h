#include "mybinarytree.h"
class BST {
    BinaryTree* tree = new MyBinaryTree();

public:
    bool search(int num) {
        return search_node(tree->getRoot(), num);
    }

    bool search_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            return true;
        }
        if (num > n->elem) {
            // proceed to right
            return search_node(n->right, num);
        } else {
            return search_node(n->left, num);
        }
    }

    node* insert(int num) {
        node* n = tree->getRoot();
        if (n == NULL) {
            return tree->addRoot(num);
        }
        return insert_node(n, num);
    }

    node* insert_node(node* n, int num) {
        if (n == NULL) {
            return NULL;
        }
        if (n->elem == num) {
            return NULL;
        }
        if (num > n->elem) {
            if (!n->right) {
                return tree->addRight(n, num);
            } else {
                return insert_node(n->right, num);
            }
        } else {
            if (!n->left) {
                return tree->addLeft(n, num);
            } else {
                return insert_node(n->left, num);
            }
        }
    }

    bool remove(int num) {
        return remove_node(tree->getRoot(), num);
    }

    bool remove_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            if (n->left && n->right) {
                node* r = n->right;
                while (r->left) {
                    r = r->left;
                }
                int rem = tree->remove(r);
                n->elem = rem;
            } else {
                tree->remove(n);
            }
            return true;
        }
        if (num > n->elem) {
            return remove_node(n->right, num);
        } else {
            return remove_node(n->left, num);
        }
    }

    // GIVEN the grandparent (or z), find the parent (or y), and the child (or x).
    bool restructure(node* gp) {
        int gpLeftHeight = (gp->left ? gp->left->height() : 0), gpRightHeight = (gp->right ? gp->right->height() : 0);
        node* par = (gpLeftHeight - gpRightHeight < 0 ? gp->right : gp->left); // parent
        // TODO find parent

        // This is an indicator of the placement of grandparent to parent (gtop)
        bool gtop_right = false;
        if (gp->right == par) {
            gtop_right = true;
        }

        int parLeftHeight = (par->left ? par->left->height() : 0)
                , parRightHeight = (par->right ? par->right->height() : 0);
        int parBalanceFactor = parLeftHeight - parRightHeight;
        node* child;
        // TODO find child

        if(parBalanceFactor == 0){
            child = (gtop_right ? par->right : par->left);
        }else
            child = (parBalanceFactor < 0 ? par->right : par->left);

        // This is an indicator of the placement of parent to child (ptoc)
        bool ptoc_right = false;
        if (par->right == child) {
            ptoc_right = true;
        }

        // FOR THE FOLLOWING: Write in each of the if statements a console output
        // on its corresponding operation (ZIGLEFT, ZIGRIGHT, ZIGZAGLEFT, or ZIGZAGRIGHT)

        // z
        //  \
        //   y
        //    \
        //     x
        if (gtop_right && ptoc_right) {
            cout << "ZIGLEFT\n";
            tree->zigleft(par);
        }

            // z
            //   \
            //     y
            //    /
            //   x
        else if (gtop_right) {
            cout << "ZIGZAGLEFT\n";
            tree->zigright(child);
            tree->zigleft(child);
        }

            //     z
            //    /
            //   y
            //  /
            // x
        else if (!ptoc_right) {
            cout << "ZIGRIGHT\n";
            tree->zigright(par);
        }

            //      z
            //    /
            //  y
            //   \
            //    x
        else {
            cout << "ZIGZAGRIGHT\n";
            tree->zigleft(child);
            tree->zigright(child);
        }
        return true;
    }

    void zigleft(node* curr) {
        tree->zigleft(curr);
    }

    void zigright(node* curr) {
        tree->zigright(curr);
    }

    void print() {
        tree->print();
    }
};