#include "stdio.h"
#include "math.h"

void calculateBill(float units, float rate){
    printf("Bill Amount: %.2f\n", units * rate);
    printf("Thank you for using our services!");
}

void displayBillDetails(char * name, float units, float rate){
    printf("Customer Name: %s\n", name);
    printf("Units Consumed: %.2f\n", units);
    printf("Rate Per Unit: %.2f\n", rate);
    
    calculateBill(units, rate);
}

int main(){
    char name[50];
    float units, rate;
    
    printf("Enter Customer Name: ");
    scanf("%s", &name);
    printf("Enter Units Consumed: ");
    scanf("%f", &units);
    printf("Enter Rate Per Unit: ");
    scanf("%f", &rate);
    
    displayBillDetails(name, units, rate);
    
    return 0;
}
