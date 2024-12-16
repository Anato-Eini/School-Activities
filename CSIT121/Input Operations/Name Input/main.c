#include <stdio.h>

int main(void)
{
    char name[1];
    printf("Enter your name: ");
    scanf("%s", &name);
    printf("Welcome, %s!", name);

    return 0;
}
