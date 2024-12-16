#include <stdio.h>

int main(void)
{
    char book[40], author[40], genre[40], library[40], memberID[40], date[40];
    int publication;

    printf("Enter the book title: ");
    scanf("%s", &book);

    printf("Enter the author: ");
    scanf("%s", &author);

    printf("Enter the year of publication: ");
    scanf("%d", &publication);

    printf("Enter the genre: ");
    scanf("%s", &genre);

    printf("Enter the library: ");
    scanf("%s", &library);

    printf("Enter your member ID: ");
    scanf("%s", &memberID);

    printf("Enter the return date: ");
    scanf("%s", &date);

    printf("You have successfully reserved the book \'%s\' by %s.", book, author );
    printf("\nYear of Publication: %d", publication);
    printf("\nGenre: %s", genre);
    printf("\nLibrary: %s", library);
    printf("\nMember ID: %s", memberID);
    printf("\nReturn Date: %s", date);

return 0;
}
