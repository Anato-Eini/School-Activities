#include <stdio.h>

void convertTemperature(){
    float celc;
    
    printf("Enter the temperature in Celsius: ");
    scanf("%f", &celc);
    float fahrenheit = (celc*9/5)+32;
    printf("Temperature in Fahrenheit: %.2f", fahrenheit);
    
}

int main(){
    convertTemperature();
}
