# import the union-find data type from your UF.py file ..
from UF import UF
# import your priority queue data type from your PQ.py file
from PQ import PQ

'''
This is an implementation of the adjacency-list representation of a graph
with integer edge weights, where the vertices are numbered from 0 through n-1.

The class has integer variables recording the number n of vertices
and number m of edges.  It has a list ._verts of length n of lists of integers.
The list that appears in position i of this list is the list of neighbors
of vertex i.  That is, ._verts[i][j] is the jth vertex in the adjacency
list for i.

It also has a list ._weights of length n of lists of integers, where
._weights[i][j] is the weight of the edge to the jth vertex in the adjacency
list for i
'''


class WGraph:
  
    # Return number of vertices in the graph
    def getn(self): 
        return len(self._verts)

    # Return number of edges in the graph
    def getm(self): 
        return self._m

    # Return a list of ordered triples, one for each edge.  The first element
    #  of the triple gives the starting vertex, the second gives the ending
    #  vertex and the third gives the weight of the edge
    def getEdges(self): 
        edgeList = []
        for i in range(self.getn()): 
            for j, w in zip(self._verts[i], self._weights[i]): 
                edgeList.append((i, j, w))
        return edgeList
        
    
    ''' constructor.  
        numVerts tells how many vertices it should have.  
        edgeList is a list of ordered triples, one for each undirected edge,
        where the first two elements of a triple
        give the endpoints of an edge and the third gives its weight
        Example: numVerts = 3 and edgeList = [(0,1,5), (1,2,3), (0,2,8), (2,0,7)]
        This graph can be represented and assigned to G by the following:
        G = WGraph(3, edgeList)
    '''

    def __init__ (self, numVerts, edgeList): 
        self._m = len(edgeList)
        self._verts = [[] for i in range(numVerts)]
        self._weights = [[] for i in range(numVerts)]
        for u, v, w in edgeList: 
            self._verts[u].append(v)
            self._weights[u].append(w)

    # Add a directed edge of weight 'weight' from 'tail' to 'head'
    def addEdge(self, tail, head, weight): 
        self._verts[tail].append(head)
        self._weights[tail].append(weight)
        self._m += 1
  
    # String that can be printed out to display the graph represented by 'self'
    def __str__(self):
        return 'n = ' + str(self.getn()) + ' m = ' + str(self.getm()) + '\n' + ''.join([(str(i) + ': ').rjust(6) + str(list(zip(self._verts[i], self._weights[i]))) + '\n' for i in range(self.getn())])

    # Return a list of edges of an MST of the graph using Kruskal's algorithm.
    # Each edge is a triple consisting of the integer ID of its two endpoints
    # and its weight.
    def Kruskal(self): 
        # replace this
        
        mst = []
        unionFind = UF(self.getn())
         
        sortedEdges  = sorted(self.getEdges(),key=lambda x: x[2])
        
        for edge in sortedEdges:
            if unionFind.find(edge[0]) != unionFind.find(edge[1]):
                mst.append(edge)
                unionFind.union(edge[0], edge[1])
                
        return mst

    # Return the edges of an MST of the graph using Prim's algorithm
    def Prim(self):
        P = PQ()
        Blackened = [False] * self.getn()
        Items = [None] * self.getn()
        CurKeys = [float('inf')] * self.getn()

        CurKeys[0] = 0
        Blackened[0] = True
        for i in range(self.getn()):
            Items[i] = P.insert(CurKeys[i], (i, None))

        MSTList = []
        
        while P:
            current = P.extractMin()
            neighbors = self._verts[current[1][0]]
            keys = self._weights[current[1][0]]
        
            smallestIndex = keys.index(min(keys))
        
            while True:
                print(MSTList)
                print("currentVertex: ", current[1][0])
                print("neighbores: ", neighbors)
                print("smallestIndex: ", smallestIndex)
                print("blackened: ", Blackened)
                print("mst: ", MSTList)
                
                if (not Blackened[neighbors[smallestIndex]]):
                    edge = (current[1][0], neighbors[smallestIndex], keys[smallestIndex])
                    if not edge in MSTList:
                        MSTList.append(edge)
                        Blackened[neighbors[smallestIndex]] = True
                        break
            
                del neighbors[smallestIndex]
                del keys[smallestIndex]
                smallestIndex = keys.index(min(keys))
                
           
        
        
        
        #print("current: ", current)
        #print("neighbors: ", neighbors)
        #print("keys: ", keys)
        #print("go to: ", neighbors[smallestIndex])
            
        return MSTList


# Read a graph from a file of triples, one per line.  The first element
#  of the triple is the starting vertex, the second is the ending vertex,
#  and the third is the weight.
def readGraph(filename): 
    edgeList = []
    fp = open(filename, 'r')
    n = int(fp.readline())
    for line in fp:
        u, v, w = [int(x) for x in line.split(',')]
        edgeList.append((u, v, w))
    return WGraph(n, edgeList)

    
''' This is what executes when you run the command python -i hw5.py '''

if __name__ == '__main__':
    G = readGraph('winput.txt')
    print(G)
    print ('Kruskal: ', G.Kruskal())
    print('Prim: ', G.Prim())
