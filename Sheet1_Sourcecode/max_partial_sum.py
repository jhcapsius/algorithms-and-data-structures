import time
import sys

#function to end the programm
def endProgram():
    endString = input("Press enter to end...")
    sys.exit

#iniatizes and fills the array with values
myValues = []
file = input("Dataname: ")
try:
    with open(file, mode="r", encoding="utf8") as file: # open file
        for line in file:
            myValues.append(line.rstrip())
except(IOError):
    print ("Data not found.")
    endProgram()


n = len(myValues)
max_sum = -sys.maxsize
addition = 0

//algorithm
try:
    start = time.time()
    for i in range(0,n):
        for j in range(i,n):
            sum = 0
            for k in range (i,j+1):
                sum += int(myValues[k])
                addition += 1
            if sum > max_sum:
                max_sum = sum
                _from = i-1
                _to = j
                       
    end = time.time()
except(IOError):
    print("Data not compatible")
    endProgram()    

print("Max. partial sum: " + str(max_sum))
print("First index: " + str(_from))
print("Last index: " + str(_to))
print("Number of additions: " + str(addition))
print("Programmlaufzeit:", end - start, "s")
endProgram()
