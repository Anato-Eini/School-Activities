#include <iostream>
#include <string>
#include "arraystack.h"
using namespace std;

int main() {
    Stack* stack = new ArrayStack();
    string input;
    do {
        cout << "Enter: ";
        cin >> input;
        int num;
        string a, b;
        switch (input[0]) {
            // TODO add cases for operations here
            case '+':
                a = stack->pop();
                b = stack->pop();
                stack->push("+ " + b + " " + a);
                break;
            case '-':
                a = stack->pop();
                b = stack->pop();
                stack->push("- " + b + " " + a);
                break;
            case '*':
                a = stack->pop();
                b = stack->pop();
                stack->push("* " + b + " " + a);
                break;
            case '/':
                a = stack->pop();
                b = stack->pop();
                stack->push("/ " + b + " " + a);
                break;
            case 'x':
                cout << "Prefix: " << stack->pop() << endl;
                break;
            default: // number
                stack->push(input);
                break;
        }
    } while (input != "x");
    return 0;
}