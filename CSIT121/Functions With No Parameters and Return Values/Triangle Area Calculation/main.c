#include<stdio.h>
void getCalculation(){
    float base, height;
    
    printf("Enter the base of the triangle: ");
    scanf("%f", &base);
    
    printf("Enter the height of the triangle: ");
    scanf("%f", &height);
    float area = (base*height)/2;
    printf("Result: %.2f", area);
    
}
int main(){
    
    getCalculation();
}
