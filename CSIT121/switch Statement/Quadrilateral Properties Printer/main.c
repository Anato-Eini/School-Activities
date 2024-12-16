#include <stdio.h>

int main(){
    char qType;
    
    printf("Enter the Quadrilateral type (S for Square, R for Rectangle, P for Parallelogram, T for Trapezoid): ");
    scanf(" %c", &qType);
    
    switch(qType){
        
        case 'S': case 's':
            printf("Properties of a Square: \n");
            printf(" - All sides are equal\n");
            printf(" - All angles are 90 degrees");
            break;
            
        case 'R': case 'r':
            printf("Properties of a Rectangle: \n");
            printf(" - Opposite sides are equal\n");
            printf(" - All angles are 90 degrees");
            break;
            
        case 'P': case 'p':
            printf("Properties of a Parallelogram: \n");
            printf(" - Opposite sides are parallel\n");
            printf(" - Opposite angles are equal");
            break;
            
        case 'T': case 't':
            printf("Properties of a Trapezoid: \n");
            printf(" - One pair of opposite sides are parallel\n");
            printf(" - Adjacent angles are supplementary");
            break;
            
        default:
            printf("Invalid Quadrilateral type.");
            break;
    }
    return 0;
}
