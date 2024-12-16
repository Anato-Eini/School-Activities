#include <stdio.h>

int main(void)
{
    char name[] = "John Doe";
    int num = 12345;
    float amount = 99.99;
    printf("Invoice for Customer: %s\n", name);
    printf("Order ID: %d\n", num);
    printf("Total Amount: $%.2f\n", amount);

    return 0;
}
