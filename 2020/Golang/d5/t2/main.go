package main

import (
	"fmt"
	"io/ioutil"
	"sort"
	"strings"
)

func main() {
	dat, err := ioutil.ReadFile("d5/t2/input.txt")
	check(err)
	s := strings.Split(string(dat), "\n")

	var c, r []bool
	var ci, ri, sid int
	var ids []int
	maxId := 0

	for i := 0; i < len(s); i++ {
		c, r = parse(s[i])
		ci = calcColumm(c)
		ri = calcRow(r)
		sid = seatID(ci, ri)
		maxId = max(maxId, sid)
		ids = append(ids, sid)
		//fmt.Printf("%v\n%v-%v\n%v-%v\n%v\n\n", s[i], c, r, ci, ri, sid)
	}

	sort.Ints(ids)
	fmt.Println(ids)

	for myid := 0; myid < maxId; myid++ {
		if (!contains(ids, myid)) && (contains(ids, (myid - 1))) {
			fmt.Println(myid)
		}
	}

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

func contains(i []int, e int) bool {
	for _, a := range i {
		if a == e {
			return true
		}
	}
	return false
}

func checkSeat(ids []int, i int) bool {
	return !(contains(ids, (i-1)) && contains(ids, (i+1)))
}
