#include <stdio.h>

void printPass(){
    printf("Pass");
}
void printFail(){
    printf("Fail");
}

int main(){
    int num;
    printf("Enter the grade (between 0 and 100): ");
    scanf("%d", &num);
    
    if(num < 60){
        printFail();
    }else{
        printPass();
    }
}
