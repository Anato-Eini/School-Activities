#include "stdio.h"

int addNumbers(){
    int a = 5;
    int b = 10;
    return a + b;
}

int main(){
    printf("Sum of numbers: %d", addNumbers());
    return 0;
}
