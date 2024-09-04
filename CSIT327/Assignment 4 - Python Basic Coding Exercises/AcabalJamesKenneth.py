def maxNum(a, b):
    return a if a > b else b

print(f"Max between 3 and 2: {maxNum(3, 2)}\n")

class Student:
    def __init__(self, name, age, grade):
        self.name = name
        self.age = age
        self.grade = grade

    def __str__(self):
        return (f"Name: {self.name}\n"
                f"Age: {self.age}\n"
                f"Grade: {self.grade}\n")

    def print_name(self):
        """Prints name"""
        print(self.name)

    def print_age(self):
        """Prints age"""
        print(self.age)

    def print_grade(self):
        """Prints grade"""
        print(self.grade)

student = Student("James", 23, 2.4)
print(f"Student info: \n{student}")

_set = {key: key - 1 for key in range(1, 3)}

def fibo_dp(num, _set):
    """Memoization technique for fibonacci"""
    if num in _set:
        return _set[num]

    _set[num] = fibo_dp(num - 1, _set) + fibo_dp(num - 2, _set)
    return _set[num]

print("First 10 fibonacci numbers: ")
for i in range(1, 11):
    print(fibo_dp(i, _set), end=" ")

def reverse_string(string):
    """Return a reversed string"""
    return string[::-1]

print(f"\n\nReverse of 'Hello World': {reverse_string('Hello World')}\n")

class Book:
    def __init__(self, title, author, year):
        self.title = title
        self.author = author
        self.year = year

    def __str__(self):
        return f"Title: {self.title}\nAuthor: {self.author}\nYear: {self.year}"

    def print_title(self):
        """Prints title"""
        print(self.title)

    def print_author(self):
        """Prints author"""
        print(self.author)

    def print_year(self):
        """Prints year"""
        print(self.year)

book = Book("Python", "Louise", 1999)

print(f"Book info:\n{book}\n")

iteration = 0
number = 1

print(f"5 Even numbers from 1:", end=" ")
while iteration < 5:
    if number % 2 == 0:
        print(number, end=" ")
        iteration += 1
    number += 1

def isOdd(num):
    return True if num % 2 != 0 else False

print(f"\n\nIs 2 odd? {isOdd(2)}\n"
      f"What about 3? {isOdd(3)}\n")

class Car:
    def __init__(self, make, model, year):
        self.make = make
        self.model = model
        self.year = year

    def __str__(self):
        return f"Make: {self.make}\nModel: {self.model}\nYear: {self.year}"

car = Car("China", "Idunno", 1982)
print(f"Car's info:\n{car}\n")

print(f"Prints number from 1 to 10 using for loop:", end=" ")
for i in range(1, 11):
    print(i, end=" ")