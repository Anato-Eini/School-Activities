#include <iostream>
#include "arraylist.h"
using namespace std;

int main() {
    List* list = new ArrayList();
    char ch;
    int num, pos, res;
    do {
        try {
            cout << "Op: ";
            cin >> ch;
            switch (ch) {
            case 'a':
                cin >> num;
                list->insert(num);
                break;
            case 'g':
                cin >> pos;
                res = list->get(pos) ;
                cout << "Elem at pos " << pos << ": " << res << endl;
                break;
            case 's':
                cout << "Size: " << list->size() << endl;
                break;
            case '?':
                cout << "Is Empty? " << list->isEmpty() << endl;
                break;
            case 'r':
                cin >> num;
                res = list->remove(num);
                if (res == 0) {
                    cout << "No such element " << num << endl;
                } else {
                    cout << "Removed " << num << " from pos " << res << endl;
                }
                break;
            case '-':
                cin >> pos;
                res = list->removeAt(pos);
                cout << "Removed " << res << " from pos " << pos << endl;
                break;
            case '@':
                cin >> num >> pos;
                list->addAt(num, pos);
                break;
            case 'p':
                list->print();
                break;
            case 'x':
                cout << "Exiting...";
                break;
            default:
                cout << "Invalid operation" << endl;
            }
        } catch (exception& e) {
            cout << e.what() << endl;
        }
    } while (ch != 'x');
    return 0;
}