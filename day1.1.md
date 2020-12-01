First of all, copy all of these numbers (https://adventofcode.com/2020/day/1/input) into a multiline list and split it by a newline

```
for i in range(len(numbers)):
  for j in range(len(numbers)):
      if int(numbers[i]) + int(numbers[j]) == 2020:
        print("Found number:", int(numbers[i]) * int(numbers[j]))
        break
```
