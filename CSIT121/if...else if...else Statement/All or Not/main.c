#include <stdio.h>

int main(){
    
    int num1, num2, num3;
    
    printf("Enter first number: ");
    scanf("%d", &num1);
    printf("Enter second number: ");
    scanf("%d", &num2);
    printf("Enter third number: ");
    scanf("%d", &num3);
    
    if(num1 >= 0 && num2 >= 0 && num3 >= 0){
        printf("All numbers are positive.");
    }else if(num1 <= 0 && num2 <= 0 && num3 <= 0){
        printf("All numbers are negative.");
    }else if(num1 % 2 == 0 && num2 % 2 == 0 && num3 % 2 == 0){
        printf("All numbers are even.");
    }else if(num1 % 2 != 0 && num2 % 2 != 0 && num3 % 2 != 0){
        printf("All numbers are odd.");
    }else{
        printf("Numbers are different.");
    }
    
    return 0;
    
}
