class Person:
    def __init__(self, name, age, hobby):
        self.name = name
        self._age = age
        self.__hobby = hobby

    def set_name(self, name):
        self.name = name

    def get_name(self):
        return self.name

    def set_age(self, age):
        self._age = age

    def get_age(self):
        return self._age

    def set_hobby(self, hobby):
        self.__hobby = hobby

    def get_hobby(self):
        return self.__hobby

class Employee(Person):

    def __init__(self, name, age, hobby, salary):
        Person.__init__(self, name, age, hobby)
        self.salary = salary

    def set_salary(self, salary):
        self.salary = salary

    def get_salary(self):
        return self.salary

    def print_salary(self):
        print(self.salary)

    def print_age(self):
        print(self._age)

    def print_name(self):
        print(self.name)

    def print_hobby(self):
        print(Person.get_hobby(self))