#include <stdio.h>

int main(void){
    float kmph, distance;
    int hour;
    float miles = 0.621371;
    
    
    
    printf("Enter kilometers per hour: ");
    scanf("%f", &kmph);
    printf("Enter number of hours traveled: ");
    scanf("%d", &hour);
    
    distance = kmph*hour;
    distance *= miles;
    
    
    printf("Distance in miles: %.3f", distance);
    return 0; 
}
