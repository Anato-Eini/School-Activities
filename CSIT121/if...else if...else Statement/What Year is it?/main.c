#include <stdio.h>


int main(){
    
    int year;
    
    printf("Enter a year: ");
    scanf("%d", &year);
    
    if (year % 400 == 0){
        printf("It's a leap century year.");
    }else if( year % 100 == 0){
        printf("It's a century year.");
    }else if( year % 4 == 0){
        printf("It's a leap year.");
    }else{
        printf("It's neither a leap year nor a century year.");
    }
    
    return 0;
    
}
