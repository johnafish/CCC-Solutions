# CCC 2015 Senior 4: Convex Hull
#
# This is by Calvin Liu
#
# This approach uses the SPFA: (Shortest Path Faster Algorithm)
#
# This produces the correct answers and
# is quite close to the 5 sec limit on even the larger test cases.


file = open("s4.15.in","r")

def main():                            #Put everything into a function speeds it up a little bit
    k,n,m=map(int,file.readline().split())
    graph=[[] for i in xrange(n+1)]    #Make adjacency list
    for i in xrange(m):
        a,b,c,d=map(int,file.readline().split())
        graph[a].append([b,c,d])       #Insert undirected edges
        graph[b].append([a,c,d])       #Insert undirected edges
    s,t=map(int,file.readline().split())
    dp=[[1555555555 for j in xrange(k+1)]for i in xrange(n+1)]       #A 2D array called dp (the idea is dp-ish and the name dp is short) to store the answers
    for i in xrange(k+1):
        dp[s][i]=0                     #The distance from the source to the source is definitely 0
    flag=[False for i in xrange(n+1)]  #A flag to prevent pushing the same node onto the queue when the node is already in the queue
    q=[s]                              #A queue simulated by a list
    while q:                           #Standard SPFA implementation
        u=q.pop(0)
        flag[u]=False
        for v in graph[u]:
            for i in xrange(k-v[2]+1):
                if dp[u][i]+v[1]<dp[v[0]][i+v[2]]:
                    dp[v[0]][i+v[2]]=dp[u][i]+v[1]
                    if not flag[v[0]]:
                        q.append(v[0])
                        flag[v[0]]=True
    if dp[t][k-1]>1e9:
        print -1
    else:
        print dp[t][k-1]
        
main()
