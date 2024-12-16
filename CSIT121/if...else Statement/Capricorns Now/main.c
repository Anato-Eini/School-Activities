#include <stdio.h>

int main(){
    
    int month;
    int day;
    
    printf("Enter your birth month: ");
    scanf("%d", &month);
    printf("Enter your birth day: ");
    scanf("%d", &day);
    
    if(month == 1 || month == 12 && day > 0 && day <= 22){
     printf("Your zodiac sign is Capricorn.");
    }else{
        printf("Your zodiac sign is not Capricorn.");
    }
    return 0;
}
