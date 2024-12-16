#include <stdio.h>

int main(void)
{
    char recipient[40], message[40], name[40],
    subject[40], code[40], location[40], company[40], website[40],
    phone[40], title[40], department[40];
    float version, discount;
    char status;
    int age;

    printf("Enter the recipient: ");
    scanf("%s", &recipient);

    printf("Enter the message: ");
    scanf("%s", &message);

    printf("Enter the name: ");
    scanf("%s", &name);

    printf("Enter the subject: ");
    scanf("%s", &subject);

    printf("Enter the version: ");
    scanf("%f", &version);

    printf("Enter the discount: ");
    scanf("%f", &discount);

    printf("Enter the status: ");
    scanf(" %c", &status);

    printf("Enter the code: ");
    scanf("%s", &code);

    printf("Enter the location: ");
    scanf("%s", &location);

    printf("Enter the age: ");
    scanf("%d", &age);

    printf("Enter the company name: ");
    scanf("%s", &company);

    printf("Enter the website: ");
    scanf("%s", &website);

    printf("Enter the phone: ");
    scanf("%s", &phone);

    printf("Enter the job title: ");
    scanf("%s", &title);

    printf("Enter the department: ");
    scanf("%s", &department);

    printf("Dear %s, I hope this email finds you well.\n", recipient);
    printf("%s\n", message);
    printf("Subject: %s\n", subject);
    printf("Sender: %s\n", name);
    printf("Version: %.1f\n", version);
    printf("Discount: %.2f%%\n", discount);
    printf("Status: %c\n", status);
    printf("Code: %s\n", code);
    printf("Location: %s\n", location);
    printf("Age: %d\n", age);
    printf("Company: %s\n", company);
    printf("Website: %s\n", website);
    printf("Phone: %s\n", phone);
    printf("Job Title: %s\n", title);
    printf("Department: %s", department);

    return 0;
}
