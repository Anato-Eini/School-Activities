#include "stdio.h"
#define PI 3.141592653589793


double calculateSquareArea(){
    double side;
    
    printf("Enter the side of the square: ");
    scanf("%lf", &side);
    
    return side * side;
}

double calculateCircleArea(){
    double radius;
    
    printf("Enter the radius of the circle: ");
    scanf("%lf", &radius);
    
    return  PI * radius * radius;
}

int main(){
    printf("Total area (square + circle): %.2lf", calculateSquareArea() + calculateCircleArea());
    return 0;
}
