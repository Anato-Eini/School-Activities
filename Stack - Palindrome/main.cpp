#include <iostream>
#include <string>
#include "arraystack.h"
using namespace std;

// TODO implement this method.
// Use the ArrayStack to perform the stack operations needed.
bool is_palindrome(string str){
    auto* arrayStack = new ArrayStack();
    for(char c: str){
        arrayStack->push(c);
    }
    for(char c: str){
        if(c != arrayStack->pop())
            return false;
    }
    return true;
}

int main(){
    string str;
    cout << "Enter a string: ";
    cin >> str;
    if(is_palindrome(str)) {
        cout << "The string is a palindrome!" << endl;
    } else {
        cout << "The string is not a palindrome!" << endl;
    }
    return 0;
}