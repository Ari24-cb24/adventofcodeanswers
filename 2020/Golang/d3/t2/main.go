package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func replaceAtIndex(in string, r rune, i int) string {
	out := []rune(in)
	out[i] = r
	return string(out)
}

func checkTrees(dat []string, x int, y int) int {
	count := 0
	j := 0
	for k, t := range dat {
		if k%y == 0 {
			if t[j] == '#' {
				count++
				fmt.Println(replaceAtIndex(t, 'X', j))
			} else {
				fmt.Println(replaceAtIndex(t, '0', j))
			}
			j = (j + x) % (len(t) - 1)
			//fmt.Println(k)
		} else {
			fmt.Println(t)
		}
		//t := strings.Repeat(s, m)

		//j = (j + x) % len(t)
	}
	//fmt.Println(count)
	return count
}

func main() {

	dat, err := ioutil.ReadFile("d3/t2/input.txt")
	check(err)

	//fmt.Println(string(dat))

	s := strings.Split(string(dat), "\n")
	result := 1

	result = checkTrees(s, 1, 1)
	fmt.Println(checkTrees(s, 1, 1))
	result = result * checkTrees(s, 3, 1)
	fmt.Println(checkTrees(s, 3, 1))
	result = result * checkTrees(s, 5, 1)
	fmt.Println(checkTrees(s, 5, 1))
	result = result * checkTrees(s, 7, 1)
	fmt.Println(checkTrees(s, 7, 1))
	result = result * checkTrees(s, 1, 2)
	fmt.Println(checkTrees(s, 1, 2), 1, 2)

	fmt.Println(result)
}
