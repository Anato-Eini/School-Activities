#include <stdio.h>


int main(){
    int a,b,c,d,e;
    int numpcount = 0;
    int numncount = 0;
    int numzcount = 0;
    
    printf("Enter first integer: ");
    scanf("%d", &a);
    printf("Enter second integer: ");
    scanf("%d", &b);
    printf("Enter third integer: ");
    scanf("%d", &c);
    printf("Enter fourth integer: ");
    scanf("%d", &d);
    printf("Enter fifth integer: ");
    scanf("%d", &e);
    
    if(a > 0){
        numpcount++;
    }else if(a < 0){
        numncount++;
    }else{
        numzcount++;
    }
    
    if(b > 0){
        numpcount++;
    }else if(b < 0){
        numncount++;
    }else{
        numzcount++;
    }
    
    if(c > 0){
        numpcount++;
    }else if(c < 0){
        numncount++;
    }else{
        numzcount++;
    }
    
    if(d > 0){
        numpcount++;
    }else if(d < 0){
        numncount++;
    }else{
        numzcount++;
    }
    
    if(e > 0){
        numpcount++;
    }else if(e < 0){
        numncount++;
    }else{
        numzcount++;
    }
    
    printf("Positive count: %d", numpcount);
    printf("\nNegative count: %d", numncount);
    printf("\nZero count: %d", numzcount);
    
    return 0;
    
}
