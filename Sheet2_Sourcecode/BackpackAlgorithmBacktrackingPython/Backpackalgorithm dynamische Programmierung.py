import time

def backPack(weight, values, n, capacity):
    counter = 0
    start = time.time()
    backpack = [[0 for x in range (capacity+1)] for x in range (n +1)]
    
    for rc in range(0,capacity+1):
        if rc < weight[n-1]:
            backpack[n][rc] = 0
        else:
            backpack[n][rc] = values[n-1]    
    
    for i in range (n, 0, -1):
        for rc in range(0, capacity + 1):
            if rc < weight[i-1]:
                backpack[i-1][rc] = backpack[i][rc]
            else:
                backpack[i-1][rc] = max(backpack[i][rc], backpack[i][rc - weight[i-1]] + values[i-1])   
                counter += 1         
    end = time.time()
    print("result: ", backpack[0][capacity])
    print("additions: ", counter)
    print("Runtime: ", (end - start) *1000 , "ms")             

n = 5
n2 = 20
capacity = 6
capacity2 = 20
weight = [2, 2, 6, 5, 4]
values = [6, 3, 5, 4 ,6]
weight2 = [2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4]
values2 = [6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6]

backPack(weight, values, n, capacity)
print()
backPack(weight2, values2, n2, capacity2)

            
