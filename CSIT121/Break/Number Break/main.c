#include <stdio.h>

int main(){
    // continuously ask the user for a number, if number is equal 
    // zer0, break 
    // start
    // input user number
    int number;

    while(number >= 0){
        printf("Enter a number: ");
        scanf("%d", &number);
        if(number == 0){
            break;
        }
        printf("Number: %d\n", number);
    }
    return 0;
}
