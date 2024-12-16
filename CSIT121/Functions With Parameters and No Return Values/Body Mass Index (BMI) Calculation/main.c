#include <stdio.h>
#include "functions.h"

int main() {
    double height;
    double weight;

    printf("Enter your height (in meters): ");
    scanf("%lf", &height);
    printf("Enter your weight (in kilograms): ");
    scanf("%lf", &weight);

    displayBodyMetrics(height, weight);

    return 0;
}
