from django.db import models

class Author(models.Model):
    name = models.CharField(max_length=100)

    def __str__(self):
        return self.name

class Book(models.Model):
    title = models.CharField(max_length=100)
    author = models.ForeignKey(Author, on_delete=models.CASCADE)
    borrowed_by = models.CharField(max_length=100, blank=True, null=True)
    due_date = models.DateField(blank=True, null=True)

    def __str__(self):
        return self.title


