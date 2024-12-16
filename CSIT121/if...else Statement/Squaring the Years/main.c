#include <stdio.h>
#include <math.h>

int main(){
    
    int birthYear, currentYear;
    
    printf("Enter your birth year: ");
    scanf("%d", &birthYear);
    printf("Enter the current year: ");
    scanf("%d", &currentYear);
    
    int age = currentYear - birthYear;
    int root = sqrt(age);
    
    if(root*root == age){
        printf("Your age is a perfect square.");
    }else{
        printf("Your age is not a perfect square.");
    }
    
    return 0;
}
