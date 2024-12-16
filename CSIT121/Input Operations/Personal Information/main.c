#include <stdio.h>

int main(void)
{
    char name[40], city[40];
    int age;

    printf("Enter your name: ");
    scanf("%s", &name);

    printf("Enter your age: ");
    scanf("%d", &age);

    printf("Enter your city: ");
    scanf("%s", &city);

    printf("Name: %s", name);
    printf("\nAge: %d", age);
    printf("\nCity: %s", city);

    return 0;
}
