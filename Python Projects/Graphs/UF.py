# Unlike in Java, not all Python classes were originally descendants of a
# single 'object' class.  To make inheritance work well, it is convenient
# to impose this.  The following line declares UF to be a class, and
# a child of the 'object' class:
class UF(object):

    ''' The elements to be unioned together are numbered 0 through n-1.  Each
        identifier for a union-find class is also an integer from 0 through
        n-1.

        _id[i] tells I.D. number of union-find class i is in
        _l[j] tells the list of elements for class with identifier j, or None 
        if there is currently no class with that identifier.
      
        When two classes are merged, the new class assumes
        the identifier of the larger of the old classes.  
      
        Suppose two classes of the same size are merged through a call to
        union(i1,i2).  Then the new class takes on the I.D. number
        of the old class that contained the first parameter, i1.
        (This rule is of no consequence for the correctness, but
        it makes it easier to grade if everybody adopts it.)
    '''


    # Get number of elements in the collection of union-find classes
    def getn(self):
        return len(self._l)

    # Initialize a union-find structure with elements {0, 1, ... n-1}, each
    #  initially in its own union-find class
    def __init__(self, n):
        self._id = list(range(n))
        self._l = [[i] for i in range(n)]

    # String representing current state of union-find structure.  Use is similar
    #  to that of toString() in Java.
    def __str__(self):
        return ''.join(['id:     ', str(self._id), '\nl:      ',  str(self._l)]) 

    # Find I.D. number of union-find class that currently contains element i
    def find(self, i):
        # replace this:
        for e in range(0, len(self._l)):
            if self._l[e] == None:
                continue
            elif i in self._l[e]:
                return e

        return None

    # Return a reference to the list of members of the class containing i.
    def classOf (self, i):
        return self._l[self.find(i)]

    # Check whether i and j are currently in the same union-find class.  If
    #  so, return False.  Otherwise, merge the union-find classes containing
    #  i and j and return True.
    def union(self, i, j):
        # replace this
        
        i_id = self.find(i)
        j_id = self.find(j)        
        i_class = self.classOf(i_id)
        j_class = self.classOf(j_id)
        
        if i_id == j_id:
            return False
        
        if len(j_class) > len(i_class):
            for e in i_class:
                self._id[e] = j_id
            j_class.extend(i_class)
            self._l[i_id] = None
        else:
            for e in j_class:
                self._id[e] = i_id
            i_class.extend(j_class)
            self._l[j_id] = None
        return True       
            
            
                



def main():
    C = UF(9)
    
    C.union(6,7)
    print(C)
    
    C.union(2,8)
    print(C)
    
    """
    print(C)
    print(C.getn())
    print(C.find(4))
    print(C.find(6))
    print(C.union(4,6))
    print(C)
    print(C.union(3,6))
    print(C)
    print(C.union(3,4))
    print(C.union(1,9))
    print(C)
    print(C.union(1,6))
    print(C)
    print(C.find(1))
    print(C.find(3))
    print(C.find(2))
    print(C.union(2,0))
    print(C)
    print(C.find(2))
    print(C.find(0))
    print(C.classOf(3))
    """

if __name__ == "__main__":
    main()