#include <iostream>
#include <string>
#include "arraystack.h"
using namespace std;

int main() {
    // Step 1: Create the stack similar to postfix_main
    Stack* stack = new ArrayStack();
    int number, a, b, num;
    // Step 2: Ask the user for number of items to be inputted.
    cout << "Enter number of inputs: ";
    cin >> number;
    string input[number];
    // Step 3: Initialize a string array and place all items there.
    cout << "Enter expression: ";
    for(int z = 0; z < number; z++)
        cin >> input[z];
    // Step 4: Loop the array from last to first
    for(int z = number - 1; z >= 0; z--){
        if(input[z] == "+"){
            a = stack->pop();
            b = stack->pop();
            stack->push(a + b);
        } else if (input[z] == "-"){
            a = stack->pop();
            b = stack->pop();
            stack->push(a - b);
        } else if (input[z] == "*"){
            a = stack->pop();
            b = stack->pop();
            stack->push(a * b);
        } else if (input[z] == "/"){
            a = stack->pop();
            b = stack->pop();
            stack->push(a / b);
        }else stack->push(stoi(input[z]));
    }
    cout << "Answer is " << stack->pop();
    // Step 5: Use the same switch-case below. Add more cases for other operations
    // Step 6: When loop is done, output the answer the exact way in case 'x' of the switch statement.
}

int postfix_main() {
    Stack* stack = new ArrayStack();
    string input;
    do {
        cout << "Enter: ";
        cin >> input;
        int num;
        int a, b;
        switch (input[0]) {
            case '+':
                a = stack->pop();
                b = stack->pop();
                stack->push(a + b);
                break;
                // add more cases for operations here
            case 'x':
                cout << "Answer is " << stack->pop() << endl;
                break;
            default: // number
                num = stoi(input);
                stack->push(num);
                break;
        }
    } while (input != "x");
    return 0;
}