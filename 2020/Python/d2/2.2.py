with open("data.txt", "r") as fh:
    data = fh.readlines()
    
for i in range(len(data)):
    elem = data[i]
    key, val = elem.split(": ")
    positions, letter = key.split()
    positions = positions.split("-")
    
    data[i] = (positions, letter, val)
    

right = 0
for i in range(len(data)):
    letter = data[i][1]
    positions = data[i][0]
    val = data[i][2]
    count = 0
    
    for pos in positions:
        p = int(pos)
        if val[p-1] == letter:
            count += 1
    
    if count == len(positions)-1:
        right += 1
        
print(right)