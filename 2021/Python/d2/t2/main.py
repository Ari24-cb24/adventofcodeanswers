f=open("input.txt");n=f.readlines();f.close()
print(sum(int(e.split()[1])*sum(int(k.split()[1])*(1 if k.split()[0]=="down"else-1)for k in n[:i]if k.split()[0]in("up","down"))for i,e in enumerate(n)if e.split()[0]=="forward")*sum(int(l.split()[1])for l in n if l.split()[0]=="forward"))