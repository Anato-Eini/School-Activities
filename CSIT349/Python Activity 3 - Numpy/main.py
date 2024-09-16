import numpy as np

# complete the code and output the array
print(np.array([2, 4, 6, 8]))

# complete the code and output the array
print(np.zeros(4))

# complete the code and output the array

#create an array with values from 0 to 4
print(np.arange(5))

#create an array with values from 1 to 8 with a step of 2
print(np.arange(1, 9, 2))

# complete the code and output the array
print(np.random.rand(5))

# complete the code and output the array
print(np.empty(4))

# The elements inside the array are not completely empty but rather it contains non-existing numbers

# create an array of integers
array = np.array([-3, -1, 0, 1])

# print the data type of the array

print(array.dtype)

array = np.array([1,3,7], dtype = "int32")

# print the array and its data type
print(array.dtype)

array = np.array([1, 3, 5, 7, 9])
# use this syntax to perform type conversion arr.astype('float')

array = array.astype('float')

# print the converted array and its new data type

print(array)
print(array.dtype)
