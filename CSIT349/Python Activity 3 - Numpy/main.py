import numpy as np

# the numpy library is denoted as np so that it will be readable
# and easier to call the functions inside the library

# complete the code and output the array
a = np.array([2, 4, 6, 8])
print(a)

# complete the code and output the array
a = np.zeros(4)
print(a)

# complete the code and output the array

#create an array with values from 0 to 4

a = np.arange(5)
print(a)

#create an array with values from 1 to 8 with a step of 2
a = np.arange(1, 9, 2)
print(a)

# complete the code and output the array
a = np.random.rand(5)
print(a)

# complete the code and output the array
a = np.empty(4)

# The elements inside the array are not completely empty but rather it contains non-existing numbers
print(a)

# create an array of integers
a = np.array([-3, -1, 0, 1])

# print the data type of the array
print(a.dtype)

a = np.array([1,3,7], dtype = "int32")

# print the array and its data type
print(a)
print(a.dtype)

a = np.array([1, 3, 5, 7, 9])
# use this syntax to perform type conversion arr.astype('float')
a = a.astype('float')
# print the converted array and its new data type
print(a)
print(a.dtype)

a = np.arange(9)

# print the dimension of the array
print(np.ndim(a))
# print the array shape
print(np.shape(a))
# print the array size
print(np.size(a))


a = np.array([1, 3, 5, 7, 9, 11])
# access the first element
element1 = a[0]
# access the third element
element2 = a[2]
# access the fifth element
element3 = a[4]

print(element1, element2, element3)

a = np.array([1, 3, 5, 7, 9, 11])
# access the last element
element1 = a[-1]
# access the second-to-the-last element
element2 = a[-2]
# access the fourth-to-the-last element
element3 = a[-4]

print(element1, element2, element3)


a = np.array([1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27])
# get the subset of [5, 7, 9, 11]
subset1 = a[2:6]
# get the subset of [9, 15, 21, 27]
subset2 = a[4::3]
# get the subset of [13, 17]
subset3 = a[6:9:2]

print(subset1, subset2, subset3)

a = np.array([1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27])
# get the subset of [23, 25, 27]
subset1 = a[-3:]
# get the subset of [9, 13, 17, 21]
subset2 = a[-10:-3:2]
# get the subset of [27, 17, 7]
subset3 = a[::-5]

print(subset1, subset2, subset3)

a = np.array([2, 4, 6, 8, 9, 10, 11, 12])
# reshaped the array into a 2D array with 2 rows and 4 columns

a = np.reshape(a, (2, 4), order='C')

print(a)

a = np.array([1, 2, 3])
b = np.array([3, 2, 1])

# perform the lesser than comparison using the less() function
lesser = np.less(a, b)
print(lesser)
# perform the greater than comparison using the greater() function
greater = np.greater(a, b)
print(greater)
# perform the not equal comparison using the not_equal() function
not_equal = np.not_equal(a, b)
print(not_equal)

a = np.array([True, False, True])
b = np.array([False, False, True])

# perform the AND operator using logical_and() function
logical_and = np.logical_and(a, b)
print(logical_and)


a = np.array([1, 3, 5, 7, 9, 11])
b = np.array([2, 4, 6, 8, 10, 12])

# get the sum of two arrays using the + operator and add() function
print(np.add(a, b))
print(a + b)
#get the product of two arrays using the * operator and product() function
print(a * b)
print(np.multiply(a, b))

# array of angles in radians
a = np.array([0, 1, 2])
# compute the cosine of the angles
print(np.cos(a))
# compute the inverse tangent of the angles
print(np.tan(a))

# angle in radians
angle = 1.57079633
# convert the angle to degrees
print(np.degrees(angle))
# convert the angle back to radians
print(np.radians(angle))

a = np.array([1.23456, 2.34567, 3.45678, 4.56789])
# call the floor() function and observe its output
print(np.floor(a))
# call the ceil() function and observe its output
print(np.ceil(a))

a = np.array([76, 78, 81, 66, 85])

# compute the mean of the marks
print(np.mean(a))
# compute the median of marks
print(np.median(a))
# find the maximum and minimum marks
print(np.max(a))
print(np.min(a))

a = np.array([[1, 2, 3, 4, 5],
          [6, 7, 8, 9, 10]])
#print the array including its dimension and shape
print(a, a.ndim, a.shape)

a = np.array([[[1, 2, 3, 4],
           [5, 6, 7, 8],
           [9, 10, 11, 12]],

          [[13, 14, 15, 16],
           [17, 18, 19, 20],
           [21, 22, 23, 24]]])
# print the array including its dimension and shape
print(a, a.ndim, a.shape)

# create a 2D array with 2 rows and 3 columns filled with zeros
print(np.zeros((2, 3)))

# create a 3D array with dimensions 2x3x4 filled with zeros
print(np.zeros((2, 3, 4)))

# create a 2D array with elements initialized to 5
a = np.full((2, 2), 5)

# print the array
print(a)

# create a 2D array of 3 rows and 3 columns of random numbers
a = np.random.rand(3, 3)
print(a)
# create a 3D array of shape (3, 3, 3) of random numbers
a = np.random.rand(3, 3, 3)
print(a)

# create an empty 2D array with 2 rows and 2 columns
print(np.empty((2, 3)))
# create an empty 3D array of shape (2, 2, 2)
print(np.empty((3, 3, 3)))

a = np.array([[1, 3, 5, 7],
          [2, 4, 6, 8],
          [9, 11, 13, 15]])

# access the element at the third row and second column
print(a[-1, 1])
# access the element at the second row and third column
print(a[1, 2])
# print the last row
print(a[-1, :])
# print the last column
print(a[:, -1])

a = np.array([[[1, 2, 3, 4],
           [5, 6, 7, 8],
           [9, 10, 11, 12]],

          [[13, 14, 15, 16],
           [17, 18, 19, 20],
           [21, 22, 23, 24]]])

# print this element arr[1, 2, 1]
print(a[1, 2, 1])

a = np.array([[1, 3, 5, 7],
          [2, 4, 6, 8],
          [9, 11, 13, 15]])

# slice the array to get the first two rows and columns
print(a[:2, :])
# slice the array to get the last two rows and columns
print(a[-2:, :])
# output the second row of the array [2, 4, 6, 8]
print(a[1, :])
# output the fourth column of the array [7, 8, 15]
print(a[:, -1])

# flatten a 2D array to 1D
a = np.array([[1, 3],
          [6, 8],
          [11, 13]])

# np.reshape(arr, -1)
print(np.reshape(a, -1))

# flatten a 3D array to 1D
a = np.array([[[1, 2],
           [6, 8],
           [9, 12]],

          [[13, 14],
           [17, 20],
           [21, 24]]])

# np.reshape(arr, -1)
print(np.reshape(a, -1))

a = np.array([[1, 3, 5],
          [2, 4, 8]])

# transpose the 2D array
print(np.transpose(a))

a = np.array([[2, 4, 6],
          [8, 10, 12],
          [14, 16, 18]])

# computes the median along the horizontal axis
print(np.median(a , axis = 0))

a = np.array([[2, 4, 6],
          [8, 10, 12],
          [14, 16, 18]])

# computes the median along the vertical axis
print(np.median(a, axis = 1))


# complete the code
path = 'air-quality-data.csv'
a = np.genfromtxt(path, delimiter=',', skip_header=True, usecols=range(1, 13))

# print the loaded data
print(a)

# print data dimension
print(a.ndim)

# print data shape
print(np.shape(a))

# print array data type
print(a.dtype)

# compute the mean and median for each data column
# print the results
print(np.mean(a, axis=0))
print(np.median(a, axis=0))

# find the maximum and minimum values for each data column
# print results
print(np.max(a, axis=0))
print(np.min(a, axis=0))

# compute the standard deviation in the seventh column of the array
# print results
print(np.std(a[:, 6]))

# only compute the standard deviation of the first 50 elements in the seventh column of the array
# print results
print(np.std(a[:50, 6]))

# output data values greater than 28 in the third column of the array
# the printed output should be a numerical value
print(a[a[ : , 2] > 28, 2])

# output the first five row and column elements
print(a[:5, :5])

# output the first 20 row elements and all data columns with step 4
print(a[:20, ::4])

# get the last row
# reshape the array to a 2D array with a shape of (4, 3)
print( np.reshape(a[-1, :], (4, 3), order='C'))

# get the tenth-to-the-last row
# reshape the array to a 2D array with a shape of (2, 6)
# then transpose the array

print(a[-10, :].reshape((4, 3), order='C').transpose())

import matplotlib.pyplot as plt


# plot the first column of the array
pm25_column = a[::, 0]

plt.plot(pm25_column)
plt.xlabel('Index')
plt.ylabel('Values')
plt.title('PM2.5 Variation')
plt.show()

# plot the eight column of the array
plt.plot(a[:, 7])
plt.xlabel('Index')
plt.ylabel('Values')
plt.title("Eight column")
plt.show()
# only plot the first 50 elements in the eight column of the array

plt.plot(a[:50, 7])
plt.xlabel('Index')
plt.ylabel('Values')
plt.title("First 50 elements in the eight column")
plt.show()

# plot the second and third column elements of the array
# make sure that the two elements do not have the same color
plt.plot(a[:, 1 ], label="First column", color='red')
plt.plot(a[:, 2], label="Second column", color='blue')
plt.xlabel('Index')
plt.ylabel('Values')
plt.title("Two arrays different color")
plt.legend()
plt.show()

# only plot the first 10 elements in the second and third column of the array
# make sure that the two elements do not have the same color
plt.plot(a[:10, 1], label="Second column", color='red')
plt.plot(a[:10, 2], label="third column", color='blue')
plt.xlabel('Index')
plt.ylabel('Values')
plt.title("Two arrays different color 2")
plt.legend()
plt.show()


#:)
#:(
