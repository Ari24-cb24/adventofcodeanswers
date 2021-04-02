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

	c := 0
	for _, i := range t[2] {
		if i == rune(n[0]) {
			c++
		}
	}

	//fmt.Printf("%v mal %v in %v %v - %v dovon sind erwünscht, dehalb %v\n", c, n, t[2], a, b, c >= a && c <= b)

	if c >= a && c <= b {
		return true
	}

	return false
}

func main() {

	dat, err := ioutil.ReadFile("d2/t1/input.txt")
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
