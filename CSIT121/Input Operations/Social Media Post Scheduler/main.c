#include <stdio.h>

int main(void)
{
    char date[40], time[40];
    double reach;
    double engagement;
    float duration;
    char letter;

    printf("Enter Post Date: ");
    scanf("%s", &date);

    printf("Enter Post Time: ");
    scanf("%s", &time);

    printf("Enter Post Reach: ");
    scanf("%lf", &reach);

    printf("Enter Post Engagement: ");
    scanf("%lf", &engagement);

    printf("Enter Post Duration: ");
    scanf("%f", &duration);

    printf("Enter Post Category: ");
    scanf(" %c", &letter);

    printf("\nPost Scheduled:");
    printf("\nDate: %s", date);
    printf("\nTime: %s", time);
    printf("\nReach: %.2lf", reach);
    printf("\nEngagement: %.2lf", engagement);
    printf("\nDuration: %.2f", duration);
    printf("\nCategory: %c", letter);


    return 0;


}
