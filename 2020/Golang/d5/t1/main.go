package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	dat, err := ioutil.ReadFile("d5/t1/input.txt")
	check(err)
	s := strings.Split(string(dat), "\n")

	var c, r []bool
	var ci, ri, sid int
	maxId := 0

	for i := 0; i < len(s); i++ {
		c, r = parse(s[i])
		ci = calcColumm(c)
		ri = calcRow(r)
		sid = seatID(ci, ri)
		maxId = max(maxId, sid)
	}

	fmt.Println(maxId)
}

/*
Sample n
FBBBBBFRRR

FBBBBBF = Row | F = low, B = high
RRR = columm  | L = low, R = high

*/

func parse(str string) ([]bool, []bool) {
	var columm []bool
	var row []bool

	for _, c := range []byte(str) {
		switch c {
		case 'F':
			columm = append(columm, false)
		case 'B':
			columm = append(columm, true)
		case 'L':
			row = append(row, false)
		case 'R':
			row = append(row, true)
		}
	}

	return columm, row
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func max(a int, b int) int {
	if a < b {
		return b
	} else {
		return a
	}
}

func calcColumm(b []bool) int {
	ci := 127
	d := 128

	for i := 0; i < len(b); i++ {
		if b[i] {
			ci = ci
		} else {
			ci = ci - (d / 2)
		}
		d = d / 2
	}

	return ci
}

func calcRow(b []bool) int {
	ri := 7
	d := 8

	for i := 0; i < len(b); i++ {
		if b[i] {
			ri = ri
		} else {
			ri = ri - (d / 2)
		}
		d = d / 2
	}

	return ri
}

func seatID(c int, r int) int {
	return (c * 8) + r
}
