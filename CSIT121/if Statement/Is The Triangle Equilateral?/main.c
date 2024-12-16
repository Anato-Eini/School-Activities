#include <stdio.h>

int main(){
    
    int x,y,z;
    
    printf("Enter x: ");
    scanf("%d", &x);
    printf("Enter y: ");
    scanf("%d", &y);
    printf("Enter z: ");
    scanf("%d", &z);
    
    if( x == y && y== z){
        printf("Triangle is equilateral.");
    }
    return 0;
}
