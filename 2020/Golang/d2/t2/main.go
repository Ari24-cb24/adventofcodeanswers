package main

import (
	"fmt"
	"io/ioutil"
	"strconv"
	"strings"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func validate(s string) bool {
	t := strings.Fields(s)
	//fmt.Println(t)
	m := strings.Split(t[0], "-")
	a, _ := strconv.Atoi(m[0])
	b, _ := strconv.Atoi(m[1])

	n := strings.TrimSuffix(t[1], ":")

	l := len(t[2])

	if a > l || b > l {
		fmt.Println("too long")
		return false
	}

	//fmt.Println(t[2])

	if t[2][a-1] == n[0] && t[2][b-1] != n[0] {
		//fmt.Println("true")
		return true
	}
	if t[2][a-1] != n[0] && t[2][b-1] == n[0] {
		//fmt.Println("true")
		return true
	}

	return false
}

func main() {

	dat, err := ioutil.ReadFile("d2/t2/input.txt")
	check(err)
	//fmt.Print(string(dat))

	s := strings.Split(string(dat), "\n")
	//fmt.Println(s)
	count := 0
	for _, i := range s {
		//fmt.Println(i)
		if validate(i) {
			count++
			//fmt.Println(i)
		}
	}
	fmt.Println(count)
}
