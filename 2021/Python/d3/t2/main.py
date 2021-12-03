f=open("input.txt");n=f.read().split();f.close()

"""
110101
101011
101100
001101
010100

10110
...
"""

c = n.copy()
oxygen = "0"
co2 = "0"

for idx in range(12):
    oxy_bit = int([item[idx] for item in n].count("1") > [item[idx] for item in n].count("0"))
    n = [item for item in n if item[idx] == str(oxy_bit)]

    if len(n) == 1:
       oxygen = n[0]
       break

for idx in range(12):
    co2_bit = 1 - int([item[idx] for item in c].count("1") > [item[idx] for item in c].count("0"))
    c = [item for item in c if item[idx] == str(co2_bit)]

    if len(n) == 1:
       co2 = c[0]
       break

print(int(co2, 2))
