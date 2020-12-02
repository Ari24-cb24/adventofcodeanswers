with open("data.txt", "r") as fh:
    data = fh.readlines()

for i in range(len(data)):
    elem = data[i]
    key, val = elem.split(": ")
    range_, letter = key.split()
    range_ = range_.split("-")
    
    data[i] = (range_, letter, val)

right = 0
for i in range(len(data)):
    count = 0
    letter = data[i][1]
    min_, max_ = data[i][0]
    
    for char in data[i][2]:
        if char == letter:
            count += 1
    
    if int(min_) <= count <= int(max_):
        right += 1
        
print(right)
