#include <stdio.h>

int main(){
    int x,y; 
    
    printf("Enter x: ");
    scanf("%d", &x);
    printf("Enter y: ");
    scanf("%d", &y);
    
    if(x == y){
        printf("Numbers are equal.");
    }
    
    return 0;
}
