package main

import (
	"fmt"
	"io/ioutil"
	"regexp"
	"strings"
)

type passport struct {
	byr string //(Birth Year)
	iyr string //(Issue Year)
	eyr string //(Expiration Year)
	ecl string //(Eye Color)
	pid string //(Passport ID)
	cid string //(Country ID)
	hgt string //(Height)
	hcl string //(Hair Color)
}

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

func stringToPassport(s string) passport {
	p := passport{byr: "",
		iyr: "",
		eyr: "",
		hgt: "",
		hcl: "",
		ecl: "",
		pid: "",
		cid: ""}

	byrReg, _ := regexp.Compile(`byr:(\S+)`)
	iyrReg, _ := regexp.Compile(`iyr:(\S+)`)
	eyrReg, _ := regexp.Compile(`eyr:(\S+)`)
	hgtReg, _ := regexp.Compile(`hgt:(\S+)`)
	hclReg, _ := regexp.Compile(`hcl:(\S+)`)
	eclReg, _ := regexp.Compile(`ecl:(\S+)`)
	pidReg, _ := regexp.Compile(`pid:(\S+)`)
	//cidReg, _ := regexp.Compile(`cid:(\S+)`)

	p.byr = byrReg.FindString(s)
	p.iyr = iyrReg.FindString(s)
	p.eyr = eyrReg.FindString(s)
	p.hgt = hgtReg.FindString(s)
	p.hcl = hclReg.FindString(s)
	p.ecl = eclReg.FindString(s)
	p.pid = pidReg.FindString(s)
	//p.cid = cidReg.FindString(s)

	//byr:%v iyr:%v eyr:%v {hgt:%vcm hgt:%vin} hcl:%v ecl:%v pid:%v cid:%v

	return p
}

func checkPassport(p passport) bool {
	if p.byr == "" {
		return false
	}
	if p.iyr == "" {
		return false
	}
	if p.eyr == "" {
		return false
	}
	if p.hgt == "" {
		return false
	}
	if p.hcl == "" {
		return false
	}
	//if strings.Contains(p.hcl, "z") {
	//	return false
	//}
	if p.ecl == "" {
		return false
	}
	if p.pid == "" {
		return false
	}
	return true
}

func main() {

	dat, err := ioutil.ReadFile("d4/t1/input.txt")
	check(err)

	//fmt.Println(string(dat))
	var valid []passport
	var invalid []passport
	var pass passport

	validCount := 0

	s := strings.Split(string(dat), "\n")
	s = append(s, "\n")
	s = append(s, "")
	k := ""
	for i := 0; i < len(s); i++ {
		for (s[i] != "") && (i < len(s)) {
			k += s[i]
			k += " "
			i++
		}
		//fmt.Println(k)
		pass = stringToPassport(k)
		//fmt.Println(pass)
		if checkPassport(pass) {
			validCount++
			valid = append(valid, pass)
		} else {
			invalid = append(invalid, pass)
		}
		k = ""
	}

	for _, p := range valid {
		fmt.Println("V", p)
	}
	fmt.Printf("\n\n\n")
	for _, p := range invalid {
		fmt.Println("I", p)
	}
	fmt.Printf("\n\n\n")
	fmt.Println(validCount)
	//fmt.Println(s)
}
