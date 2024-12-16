#include <stdio.h>

int main(){
    
    int score1, score2;
    
    printf("Enter the first score: ");
    scanf("%d", &score1);
    printf("Enter the second score: ");
    scanf("%d", &score2);
    
    if(score1 > score2){
        if(score1 < 80){
            printf("Good job!");
        }else{
            printf("Excellent!");
        }
    }
    
    if(score1 < score2){
        printf("Keep up the good work!");
    }
    
    return 0;
    
}
