with open("data.txt", "r") as fh:
    data = fh.readlines()
    
for i in range(len(data)):
    for j in range(i, len(data)):
        for k in range(j, len(data)):
            if int(data[i]) + int(data[j]) + int(data[k]) == 2020:
                print("Found one,", int(data[i]) * int(data[j]) * int(data[k]))