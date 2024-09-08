print("------------------------------------------\nDICTIONARY\n")
# (3 pts)Task 1: Create a dictionary named 'student' with the following key-value pairs:
# 'name' -> 'John Doe', 'age' -> 22, 'courses' -> ['Math', 'Science']
# Then, add a new key-value pair 'grade' -> 'A' to the dictionary.
# Finally, print the dictionary.

# Your code here:
student = {
    'name'    : 'John Doe',
    'age'     : 22,
    'courses' : ['Math', 'Science']
}

student['grade'] = 'A'

print(f"Student dictionary: {student}")

# (6 pts)Task 2: Given the dictionary 'student' from Task 1, perform the following operations:
# 1. Access and print the value of 'name' key.
# 2. Modify the 'age' key to 23.
# 3. Print the updated dictionary.

# Your code here:

name = student['name']
print(f"\n'Name' key value in student dictionary: {name}")
student['age'] = 23
print(f"Updated student dictionary: {student}")

# (6 pts)Task 3: Using the 'student' dictionary, demonstrate the use of the following methods:
# 1. Use '.get()' to access the 'courses' key and print its value. Handle the case if the key does not exist.
# 2. Print all the keys of the dictionary using '.keys()'.
# 3. Print all the values of the dictionary using '.values()'.

# Your code here:

print(f"\n'courses' value in student dictionary: {student.get('courses', 'No courses')}")
print("Student dictionary keys: ", end="")

for index, keys in enumerate(student.keys()):
    print(f"{keys}", end=", " if index != len(student.keys()) - 1 else "")

print("\nStudent dictionary values: ", end="")
for index, values in enumerate(student.values()):
    print(f"{values}", end=", " if index != len(student.keys()) - 1 else "")

# (6 pts) Task 4: Create a nested dictionary 'classroom' where each key is a student's name and the value is another
# dictionary with keys 'age' and 'grades' pointing to their age and a list of grades, respectively.
# Example:
# classroom = {
#     'Alice': {'age': 22, 'grades': ['A', 'B+', 'A-']},
#     'Bob': {'age': 23, 'grades': ['B', 'B-', 'C+']}
# }
# Add a new student named 'Charlie' with age 24 and grades ['A+', 'A', 'A-'] to the 'classroom' dictionary.
# Print the updated 'classroom' dictionary.

# Your code here:

classroom = {
    'Alice' : {
        'age' : 22,
        'grades' : ['A', 'B+', 'A-']
    },
    'Bob' : {
        'age' : 23,
        'grades' : ['B', 'B-', 'C+']
    }
}

print(f"\n\nClassroom dictionary:\n{classroom}")

classroom['Charlie'] = {
    'age' : 24,
    'grades' : ['A+', 'A', 'A-']
}

print(f"Updated classroom dictionary:\n{classroom}")

# (6 pts) Task 5: Using the 'classroom' dictionary from Task 4, create a new dictionary 'age_map' using
# dictionary comprehension
# that maps each student's name to their age. Then, print 'age_map'.
# 'age_map' should look something like this:
#     {'Alice':22, 'Bob':23,...}

# Your code here:

age_map = {key: value['age'] for key, value in classroom.items()}

print(f"\nage_map = {age_map}")

print("------------------------------------------\nSETS\n")

# (2 pts) Task 6: Create two sets, set1 and set2, with the following elements:
# set1 = {1, 2, 3, 4, 5}
# set2 = {4, 5, 6, 7, 8}
# Then, print set1 and set2


set1 = {1, 2, 3, 4, 5}
set2 = {4, 5, 6, 7, 8}

print(f"Set 1: {set1}\n"
      f"Set 2: {set2}")

# Task 7: Perform the following operations in the next few code blocks:
# 7.1. Find the union of set1 and set2 and print it.
# 7.2. Find the intersection of set1 and set2 and print it.
# 7.3. Find the difference between set1 and set2 and print it.
# 7.4. Find the symmetric difference between set1 and set2 and print it.

# (3 pts) Your code for 7.1 (Union) here:

union = set1.union(set2)
print(f"Union set: {union}")

# (3 pts) Your code for 7.2 (intersection) here:

intersection = set1.intersection(set2)
print(f"Intersection set: {intersection}")

# (3 pts) Your code for 7.3 (difference) here:

difference = set1.difference(set2)
print(f"Difference set: {difference}")

# (3 pts) Your code for 7.4 (symmetric difference) here:

symmetric_difference = set1.symmetric_difference(set2)
print(f"Symmetric difference set: {symmetric_difference}")

# (3 pts) Task 8: Create a set 'squared_set' containing the squares of numbers from 1 to 10 using set comprehension.
# Print 'squared_set'.

# Your code here:

squared_set = {x ** 2 for x in range(1, 11)}
print(f"\nSquared set: {squared_set}")

# (6 pts) Task 9: Given the dictionary 'student_scores' below where each key is a student's name and the value
# is their score, filter the dictionary to only include students with a score above 75.
# Then, sort this filtered dictionary by score in descending order.
# Hint: You might find lambda functions useful for sorting.
# Example input: {'Alice': 80, 'Bob': 65, 'Charlie': 90, 'Dana': 76}
# Expected output: {'Charlie': 90, 'Alice': 80, 'Dana': 76}

# Your code here
# Given input dictionary
student_scores = {'Alice': 80, 'Bob': 65, 'Charlie': 90, 'Dana': 76}
sorted_filtered_dictionary = dict(sorted(
    dict({key : value for key, value in student_scores.items() if value > 75}).items(),
    key=lambda item: item[1], reverse=True)
)

print(f"\nSorted filtered dictionary: {sorted_filtered_dictionary}")