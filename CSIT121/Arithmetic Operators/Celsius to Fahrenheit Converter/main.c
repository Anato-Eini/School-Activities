#include <stdio.h>

int main(void)
{
    float temperatureC;
    float temperatureF;
    
    printf("Enter temperature in Celsius: ");
    scanf("%f", &temperatureC);
    
    temperatureF = (temperatureC*9/5)+32;
    printf("Temperature in Fahrenheit: %.2f", temperatureF);
    return 0;
}
