#include <stdio.h>

int main(void)
{
    char name[] = "John Doe";
    double reach = 2500.50;
    float engagement = 0.75;
    char category = 'N';


    printf("Post Scheduled:\n");
    printf("Content: Exciting news! Our new product is launching soon.\n");
    printf("Date: 2023-06-30\n");
    printf("Time: 10:00 AM\n");
    printf("Reach: %.2lf\n", reach);
    printf("Engagement: %.2f\n", engagement);
    printf("Duration: 1.50\n");
    printf("Cost: 50.25\n");
    printf("Category: %c\n", category);
    printf("Status: S\n");
    printf("Author Name: %s\n", name);
    printf("Author Email: johndoe@example.com\n");
    printf("Platform: Facebook\n");
    printf("Audience: Targeted\n");
    printf("Budget: 1000.00\n");

    return 0;

}