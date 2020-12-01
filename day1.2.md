```
numbers = ...

for i in range(len(numbers)):
  for j in range(i, len(numbers)):
    for k in range(i, len(numbers)):
      if numbers[i] + numbers[j] + numbers[k] == 2020:
        print("Found it", numbers[i] * numbers[j] * numbers[k])
```
