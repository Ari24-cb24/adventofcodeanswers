f=open("input.txt");n=f.readlines();f.close();print(sum(map(lambda c:int(c[1])>int(c[0]),zip(n,n[1:]))))
