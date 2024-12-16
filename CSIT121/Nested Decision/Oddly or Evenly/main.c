#include <stdio.h>

int main(){
    
    int number;
    
    printf("Enter an integer: ");
    scanf("%d", &number);
    
    if(number % 2 != 0){
        if(number % 3 == 0){
            printf("Oddly divisible by 3");
        }else{
            printf("Odd number");
        }
    }
    
    if(number % 2 == 0){
        if(number % 4 == 0){
            printf("Evenly divisible by 4");
        }else{
            printf("Even number");
        }
    }
    
    return 0;
}
