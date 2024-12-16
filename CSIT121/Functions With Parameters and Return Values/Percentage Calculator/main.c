#include "stdio.h"
#include "math.h"

double calculatePercentage(double value, double total){
    return (value / total) * 100;
}


int main(){
    double value, total;
    
    printf("Enter the value: ");
    scanf("%lf", &value);
    printf("Enter the total: ");
    scanf("%lf", &total);
    
    printf("%.2lf%% of %.2lf is %.2lf", calculatePercentage(value, total), total, value);
    
    return 0;
}
