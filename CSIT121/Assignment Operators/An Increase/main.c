#include <stdio.h>

int main(void){
    
    float price;
    float result;
    
    printf("Enter the price: ");
    scanf("%f", &price);
    
    
    result = (price*1.1);
    printf("The new price is: %.2f", result);
    
    return 0;
    
}
