#include "stdio.h"

double calculateCylinderVolume(){
    double radius, height;
    printf("Enter the radius of the cylinder: ");
    scanf("%lf", &radius);
    printf("Enter the height of the cylinder: ");
    scanf("%lf", &height);
    
    return 3.141592653589793 * radius * radius * height * 4;
}

int main(){
    printf("Result: %.2lf", calculateCylinderVolume());
    return 0;
}
