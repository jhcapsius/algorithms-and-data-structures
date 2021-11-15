import numpy as np



def checker(bo, n):
    for row in range (0,n):
        if sum(bo[row])>1:
            return False

    for col in range (0,n):
        if sum(bo[col])>1:
            return False

    diago = [bo[::-1,:].diagonal(i) for i in range(-n+1, n)]
    diago.extend(bo.diagonal(i) for i in range(n-1, -n, -1))
    for x in diago:
        if len(x)>1:
            if sum (x)>1:
                return False
    return True

def solve(bo, col, n):
    if checker(bo, n):
        if bo.sum() == n:
            return True
            
        for row in range (0,n):
            bo[row, col] = 1
            if checker (bo,n):
                if solve(bo, col+1, n):
                    return True
                bo[row,col] = 0
            else:
                bo[row,col] = 0
    return False


schachbrett = np.zeros((4,4))
if solve(schachbrett, 0, 4):
    print(schachbrett)