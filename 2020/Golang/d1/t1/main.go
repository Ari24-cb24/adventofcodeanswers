package main

import (
	"fmt"
	"strconv"
	"strings"

	//"io"
	"io/ioutil"
	//"os"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {

	dat, err := ioutil.ReadFile("d1/t1/input.txt")
	check(err)
	//fmt.Print(string(dat))

	s := strings.Fields(string(dat))
	fmt.Println(s)

	//d := strconv.Atoi(s)

	for _, i := range s {
		for _, j := range s {
			a, err := strconv.Atoi(i)
			check(err)
			b, err := strconv.Atoi(j)
			check(err)
			//fmt.Printf("i + j = %d\n", a+b)
			if a+b == 2020 {
				fmt.Println(a * b)
			}
		}
	}
}
