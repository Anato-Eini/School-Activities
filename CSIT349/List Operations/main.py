# Declare two variables a and b as lists.
    # Variable a contains the numbers from 0 to 4
    # Variable b contains the numbers from 5 to 9

a = [x for x in range(0, 5)]
b = [x for x in range(5, 10)]


# Declare a function "func" that takes two parameters list_a and list_b and does the following:
    # First, it appends list_b to list_a
    # Next, it extends list_b to list_a
    # Finally, it prints list_a

def func(list_a,list_b) -> None:
    list_a.append(list_b)
    list_a.extend(list_b)
    print(list_a)
    # pass
    # Comment out the "pass" in your final output


# Run your func1 with a as list_a and b as list_b

func(a, b)


# Declare a function "func" that takes two parameters list_a and list_b and does the following:
    # First, it appends list_b to list_a
    # Next, it extends list_b to list_a
    # Finally, it prints list_a

def func(list_a,list_b):
    # Your code here:
    pass
    # Comment out the "pass" in your final output


# Declare a function "func2" that takes three parameters: list_x, index, and element.
    # The function should insert the given element at the specified index in list_x.
    # Then, it should print the modified list_x.

def func2(list_x, index, element) -> None:
    # Your code here:
    list_x.insert(index, element)
    print(list_x)
    # pass
    # Comment out the "pass" in your final output


# Declare a function "func3" that takes two parameters: list_x and reverse_order.
    # list_x is the list to be sorted, and reverse_order is a boolean (True or false) that determines the sorting order.
    # The function should sort the list in ascending order by default, or in descending order if reverse_order is True.
    # Finally, print the sorted list.
    # Hint: The sort() function takes a parameter called "reverse"

def func3(list_to_sort, reverse_order=False) -> None:
    list_to_sort.sort(reverse=reverse_order)
    print(list_to_sort)
    # pass
    # Comment out the "pass" in your final output