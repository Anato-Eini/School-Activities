#include <stdio.h>

int main(){

    int input;

    printf("Enter an integer: "); // ngayo ko integer plz!!
    scanf("%d", &input); // 51 example

    int storage = 0;
    int remainder1;

    while(input > 0){ // until ma zero
        remainder1 = input % 10; // 51 % 10 = r.1 // 5%10 = r.5
                                 // use modulo to "get" the last digit.
        storage = storage + remainder1;
        // since i initialize my variable storage to zero
        // akong gamit sa storage diha nako ibutang akong
        // remainder, so the value of storage becomes
        // 1

        // storage = 1 + 5 = 6.
        input = input/10;
        // 51 / 10 = 5 
        // 5 / 10 = 0
    }
    printf("Sum of digits: %d", storage);

    return 0;
}
