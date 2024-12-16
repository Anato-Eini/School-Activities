#include "stdio.h"

double calculateRectanglePerimeter(){
    float length, width;
    
    printf("Enter length of the rectangle: ");
    scanf("%f", &length);
    printf("Enter width of the rectangle: ");
    scanf("%f", &width);
    
    return 2 * (length + width);
}

int main(){
    printf("Perimeter of the rectangle: %.2lf", calculateRectanglePerimeter());
    return 0;
}
