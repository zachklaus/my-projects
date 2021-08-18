'''
Created on Nov 3, 2018

@author: Zachary Klausner
'''

import math

L = []

# Return the dynamic programming table for the longest common subsequence
def LCS(s1, s2):
    n = len(s1)
    m = len(s2)
    T = []
    for i in range(n+1):
        T.append([0]*(m+1))
    for i in range(1,n+1):
        for j in range(1,m+1):
            if s1[i-1] == s2[j-1]:
                T[i][j] = T[i-1][j-1] + 1
            else:
                T[i][j] = max(T[i-1][j], T[i][j-1])
    L  = LCSAux(s1, s2, T, n, m)
    return L

# Given the two strings, s1 and s2, the dynamic programing table for
# their longest common subsequence, and i and j, 0 <= i <= len(s1),
# 0 <= j <= len(s2), return a longest common sequence of the first i 
# characters of s1 and the first j characters of s2.  The recursive strategy
# is similar to that of print-LCS on page 395, except that the dynamic
# programming array has not been annotated with arrows.  Figure out
# how to solve the problem without the arrows.  Also, don't print the
# sequence out; assemble and return it as an array of characters in
# their left-to-right order in s1 and s2.

def LCSAux(s1, s2, T, i, j):
    # replace the code here with your solution
    
    global L
    
    
    if i == len(s1) and j == len(s2): 
        L = []
   
    if i == 0 or j == 0:
        return L
    
    if (s1[i-1] == s2[j-1]):
        LCSAux(s1, s2, T, i-1, j-1)
        L.append(s1[i-1])
    elif (T[i-1][j] >= T[i][j-1]):
        LCSAux(s1, s2, T, i-1, j)
    else:
        LCSAux(s1, s2, T, i, j-1)
        
    return L
   
#  Return the max sum of any subarray of A.   A is an array of
#  integers.
def maxSubarray(A):
    total, maxPrefix, maxSuffix, max = maxSubarrayAux(A, 0, len(A) - 1)
    return max

# Return the sum of A[i..k], the maximum sum of a prefix of A[i..k],
#  the maximum sum of a suffix of A[i..k], and the maximum sum in
#  a subarray of A[i..k].  

#  Preconditions:  A is an array of integers, 0 <= i <= k < len(A).
#  Postconditions:  A tuple (total, maxPrefix, maxSuffix, max) has
#    been returned, where total is the sum of A[i..k], maxPrefix is 
#    the maximum sum of a prefix, maxSuffix is the max sum of a suffix,
#    and max is the maximum sum of a subarray.
def maxSubarrayAux(A, i, k):
    if i == k:
        return A[i], A[i], A[i], A[i]
    else:  
        j = (i+k)//2
        Ltotal, LmaxPrefix, LmaxSuffix, Lmax = maxSubarrayAux(A,i,j)
        Rtotal, RmaxPrefix, RmaxSuffix, Rmax = maxSubarrayAux(A,j+1,k)
        total = Ltotal + Rtotal
        maxSuffix = max(LmaxSuffix + Rtotal, RmaxSuffix)
        maxPrefix = max(RmaxPrefix + Ltotal, LmaxPrefix)
        maxSubSum  = max(LmaxSuffix + RmaxPrefix, Lmax, Rmax)
        return total, maxPrefix, maxSuffix, maxSubSum

#  Return beginning index, the end endex and sum of a max subarray of A.   
#  Do this by modifying
#  Return beginning index, the end endex and sum of a max subarray of A.   
#  Do this by modifying
def maxSubarray2(A):
    #  Modify this code here.  You will need to write a new method
    #  maxSubarrayAux2, which should be a modification of maxSubarrayAux,
    #  above.  The method should be called here.
    total, maxPrefix, maxSuffix, maxSubSum, maxBegin, maxEnd, = maxSubarrayAux2(A, 0, len(A) - 1, -1, -1)
    return maxBegin, maxEnd, maxSubSum
        
def maxSubarrayAux2(A, low, high, maxBegin, maxEnd):
    
    if high == low:
        return A[low], A[low], A[low], A[low], low, high
    else:  
        mid = (low+high)//2
        Ltotal, LmaxPrefix, LmaxSuffix, Lmax, LmaxBegin, LmaxEnd = maxSubarrayAux2(A, low, mid, maxBegin, maxEnd)
        Rtotal, RmaxPrefix, RmaxSuffix, Rmax, RmaxBegin, RmaxEnd = maxSubarrayAux2(A, mid+1, high, maxBegin, maxEnd)
        total = Ltotal + Rtotal
        maxPrefix = max(RmaxPrefix + Ltotal, LmaxPrefix)
        maxSuffix = max(LmaxSuffix + Rtotal, RmaxSuffix)
        maxSubSum  = max(LmaxSuffix + RmaxPrefix, Lmax, Rmax)
    
    
        if maxSubSum == Lmax:
            maxBegin = low
            maxEnd = LmaxEnd
        else:
            if mid % 2 == 0:
                maxBegin = mid-1
            else:
                maxBegin = mid
            maxEnd = RmaxEnd - 2
            
            
    return total, maxPrefix, maxSuffix, maxSubSum, maxBegin, maxEnd
        

# See assignment sheet for an explanation of finding the silhouette of a
#  set of buildings...
def silhouette(Buildings):
    return silhouetteAux(Buildings, 0, len(Buildings)-1)

def silhouetteAux(Buildings, i, k):
    # Replace code here with your solution
    
    S = []

    if i == k:
        S.append((Buildings[i][0], Buildings[i][1]))
        S.append((Buildings[i][2], 0))
        return S
    else:
        j = (i+k)//2
        S1 = silhouetteAux(Buildings, i, j)
        S2 = silhouetteAux(Buildings, j+1, k)
        S = mergeSilhouettes(S1, S2)
        return S

def mergeSilhouettes(S1, S2):
    # replace code here with your solution
    S = []
    
    S1 = sorted(S1)
    S2 = sorted(S2)
    
    height1, height2 = 0, 0
    i, j = 0, 0
    
    while i < len(S1) and j < len(S2):
        if S1[i][0] < S2[j][0]:
            x1 = S1[i][0]
            height1 = S1[i][1]
            max_height = max(height1, height2)
            S = add_coordinate(S, (x1, max_height))
            i += 1
        else:
            x2 = S2[j][0]
            height2 = S2[j][1]
            max_height = max(height1, height2)
            S = add_coordinate(S, (x2, max_height))
            j += 1
    
    while i < len(S1):
        S.append(S1[i])
        i += 1
    
    while j < len(S2):
        S.append(S2[j])
        j += 1
    
    return S

def add_coordinate(S, new_coordinate):
    
    if S:
        old_coordinate = S[len(S) - 1]
           
        if (old_coordinate[1] == new_coordinate[1]):
            return S
        if (old_coordinate[0] == new_coordinate[0]):
            del S[len(S) - 1]
            new_coordinate[1] = max(old_coordinate[1], new_coordinate[1]) 
    
    S.append(new_coordinate)
    return S

#  See assignment sheet for an explanation of this problem about
#  typesetting text.  You may assume as a precondition that no
#  element of list 'W' is greater than 'pagesize'.
def typecost(Wordlengths, pagesize):
    # replace code here with your solution ...
    T, S = [], []
    return T, S

#  This is the method that needs to reconstruct the typesetting from
#  the word lengths and the annotations S[], where S[i] gives the
#  index of the first word of the second line in an optimum typesetting
#  of Wordlengths[i, ..., len(Wordlengths[i]-1)]
def typeset(Wordlengths, S):
    # Replace code here with your solution
    Lines = []
    return Lines

#  This reads in a file of buildings for the silhouette problem.  The
#  file should have a triple of integers on on each line, separated by spaces.
#  Each line represents a building, as described in the homework sheet.
def readBuildings(filename):
    buildingList = []
    fp = open(filename, 'r')
    for line in fp:
        left, top, right = [int(x) for x in line.split(' ')]
        buildingList.append((left, top, right))
    fp.close()
    return buildingList



if __name__ == '__main__':
    s1 = 'ABCBDAB'
    s2 = 'BDCABA'
    L = LCS(s1, s2)
    print ('\nLongest common subsequence: ', L)
    
    
    print ('\n-------------------------')

    A = [-2,1,-3,4,-1,2,1,-5,4]
    
    maxSubSum = maxSubarray(A)
    print('For this array: ', A)
    print ('\nMaximum subarray sum is ', maxSubSum)

    maxBegin, maxEnd, maxSubSum  = maxSubarray2(A)
    print ('\nMax subarray start, max subarray end, max subarray sum: ', maxBegin, maxEnd, maxSubSum)
    
 
    
    print ('\n-------------------------')
    
    Buildings = [(1, 11, 5), (2, 6, 7), (3, 13, 9), (12, 7, 16),
                 (14, 3, 25), (19, 18, 22), (23, 13, 29), (24, 4, 28)]

    Silhouette = silhouette(Buildings)
    print ('\nFor these buildings: ', Buildings)
    print('\nThe silhouette is:  ', Silhouette)
    