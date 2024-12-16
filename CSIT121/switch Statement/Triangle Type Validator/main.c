#include <stdio.h>

int main(){
    
    char triangle;
    float side1, side2, side3;
    
    printf("Enter the triangle type identifier (E for Equilateral, I for Isosceles, S for Scalene): ");
    scanf(" %c", &triangle);
    
    printf("Enter the length of side 1: ");
    scanf("%f", &side1);
    printf("Enter the length of side 2: ");
    scanf("%f", &side2);
    printf("Enter the length of side 3: ");
    scanf("%f", &side3);
    
    switch(triangle){
        case 'E': case 'e':
            if(side1 == side2 && side1 == side3){
                printf("The given sides form an Equilateral triangle.");
            }else{
                printf("The given sides do not form an Equilateral triangle.");
            }
            break;
        case 'I': case 'i':
            if(side1 == side2 || side1 == side3 || side2 == side3){
                printf("The given sides form an Isosceles triangle.");
            }else{
                printf("The given sides do not form an Isosceles triangle.");
            }
            break;
        case 'S': case 's':
            if(side1 != side2 && side2 != side3 && side1 != side3){
            printf("The given sides form a Scalene triangle.");
            }else{
                printf("The given sides do not form a Scalene triangle.");
            }
            break;
    }
    
    return 0;
}
