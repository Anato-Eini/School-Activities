#include <stdio.h>

int main(){
    
    int num1, num2, num3;
    
    printf("Enter first number: ");
    scanf("%d", &num1);
    printf("Enter second number: ");
    scanf("%d", &num2);
    printf("Enter third number: ");
    scanf("%d", &num3);
    
    if(num1 == num2 && num1 == num3 && num2 == num3){
       printf("All numbers are equal.");
    }else{
        printf("Not all numbers are equal."); 
    }
    
    return 0;
}
