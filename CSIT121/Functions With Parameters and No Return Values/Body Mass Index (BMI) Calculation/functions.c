#include "functions.h"
#include <stdio.h>

// ------ DO NOT MODIFY CODE ABOVE -----

void displayBodyMetrics(double height, double weight) {
    printf("BMI: %.2lf", weight / (height * height));
}
