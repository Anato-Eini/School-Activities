#include "stdio.h"
#include "math.h"

double calculateAverageRating(double quality, double price, double service){
    return (quality + price + service) / 3;
}


void analyzeProductRating(char *name, char *category, 
                double quality, double price, double service){
    printf("Product Name: %s\n", name);
    printf("Category: %s\n", category);
    printf("Quality Rating: %.2lf\n", quality);
    printf("Price Rating: %.2lf\n", price);
    printf("Service Rating: %.2lf\n", service);
    printf("Overall Average Rating: %.2lf", calculateAverageRating(quality, price, service));
}

int main(){
    char category[50], name[50];
    double quality, price, service;
    
    printf("Enter Product Name: ");
    scanf("%s", &name);
    printf("Enter Category: ");
    scanf("%s", &category);
    printf("Enter Quality Rating: ");
    scanf("%lf", &quality);
    printf("Enter Price Rating: ");
    scanf("%lf", &price);
    printf("Enter Service Rating: " );
    scanf("%lf", &service);
    
    analyzeProductRating(name, category, quality, price, service);
    
    return 0;
}
