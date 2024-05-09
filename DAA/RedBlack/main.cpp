#include <iostream>
#include <exception>
#include "RedBlack.h"


using namespace std;
int main() {
    auto* tree = new RedBlack();
    char op;
    int num;
    do{
        cout << "Op: ";
        cin >> op;
        try{
            switch (op) {
                case 'i':
                    cin >> num;
                    tree->insert(num);
                    break;
                case 'p':
                    tree->print();
                    break;
                case 's':
                    cin >> num;
                    cout << num << (tree->search(num) ? "" : " doesn't") << " exists\n";
                    break;
                case 'd':
                    cin >> num;
                    break;
                case 'x':
                    cout << "Existing...";
            }
        }catch (const std::exception& e){
            cout << e.what();
        }
    }while(op != 'x');
    return 0;
}
