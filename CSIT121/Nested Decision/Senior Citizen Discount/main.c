#include <stdio.h>

int main(){
    int age;
    float income;
    
    printf("Enter your age: ");
    scanf("%d", &age);
    
    printf("Enter your income: $");
    scanf("%f", &income);
    
    if(age >= 60){
        if(income < 10000)
            printf("Eligible for senior citizen discount");
        else
            printf("Not eligible for senior citizen discount");
    }else{
        printf("Not eligible for senior citizen discount");
    }
    
    return 0;
}
