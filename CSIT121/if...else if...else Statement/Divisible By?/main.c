#include <stdio.h>

int main(){
    
    int num;
    
    printf("Enter an integer: ");
    scanf("%d", &num);
    
    if(num % 4 == 0){
        printf("The number is divisible by 4.");
    }else if(num % 4 != 0 && num % 3 == 0){
        printf("The number is divisible by 3.");
    }else if(num % 4 != 0 && num % 3 != 0 && num % 2 == 0){
        printf("The number is divisible by 2.");
    }else{
        printf("The number is not divisible by 2, 3, or 4.");
    }
    
    return 0;
}
