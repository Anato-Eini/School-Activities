#include <stdio.h>

int main(void)
{
    char subject[] = "Greetings";
    char sender[] = "Jane";
    double version = 1.2;
    float discount = 10.50;
    char status = 'A';
    int age = 30;

    printf("Dear John, I hope this email finds you well.\n");
    printf("I wanted to reach out and say hello.\n");
    printf("I hope you are doing well and enjoying your day.\n");
    printf("It's been a while since we last spoke, and I wanted to catch up with you.\n");
    printf("Let's plan to meet up soon and have a great time together!\n");
    printf("Subject: %s\n", subject);
    printf("Sender: %s\n", sender);
    printf("Version: %.1lf\n", version);
    printf("Discount: %.2f%%\n", discount);
    printf("Status: %c\n", status);
    printf("Code: ABCD123\n");
    printf("Location: City XYZ\n");
    printf("Age: %d\n", age);
    printf("Company: ABC Corporation\n");
    printf("Website: www.example.com\n");
    printf("Phone: +1 123-456-7890\n");
    printf("Job Title: Software Engineer\n");
    printf("Department: Engineering");

    return 0;
}
