#include <stdio.h>

int main(){
    
    int num1;
    int num2;
    
    printf("Enter first number: ");
    scanf("%d", &num1);
    printf("Enter second number: ");
    scanf("%d", &num2);
    
    if(num1 > 0 && num2 > 0){
        printf("Numbers have the same sign.");
    }
    if(num1 < 0 && num2 < 0){
        printf("Numbers have the same sign.");
    }
    
    return 0;
}
