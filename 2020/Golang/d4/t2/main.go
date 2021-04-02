package main

import (
	"fmt"
	"io/ioutil"
	"regexp"
	"strconv"
	"strings"
)

type passport struct {
	cid    string //(Country ID)
	byr    int    //(Birth Year)
	iyr    int    //(Issue Year)
	eyr    int    //(Expiration Year)
	ecl    string //(Eye Color)
	pid    string //(Passport ID)
	hcl    string //(Hair Color)
	hgt_in bool   //(Height in - cm = true, in = false)
	hgt    int    //(Height)
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

func replaceAtIndex(in string, r rune, i int) string {
	out := []rune(in)
	out[i] = r
	return string(out)
}

func stringToPassport(s string) passport {
	p := passport{byr: 0,
		iyr:    0,
		eyr:    0,
		hgt:    0,
		hgt_in: false,
		hcl:    "",
		ecl:    "",
		pid:    "",
		cid:    ""}

	byrReg, _ := regexp.Compile(`byr:(\S+)`)
	iyrReg, _ := regexp.Compile(`iyr:(\S+)`)
	eyrReg, _ := regexp.Compile(`eyr:(\S+)`)
	hgtReg, _ := regexp.Compile(`hgt:(\S+)`)
	hclReg, _ := regexp.Compile(`hcl:(#[0-9a-f]{6})`)
	eclReg, _ := regexp.Compile(`ecl:(\S+)`)
	pidReg, _ := regexp.Compile(`pid:([0-9]{9})[\s\$]`)
	//cidReg, _ := regexp.Compile(`cid:(\S+)`)

	digitReg, _ := regexp.Compile(`\d+`)

	p.byr, _ = strconv.Atoi(digitReg.FindString(byrReg.FindString(s)))
	p.iyr, _ = strconv.Atoi(digitReg.FindString(iyrReg.FindString(s)))
	p.eyr, _ = strconv.Atoi(digitReg.FindString(eyrReg.FindString(s)))
	p.hgt, _ = strconv.Atoi(digitReg.FindString(hgtReg.FindString(s)))

	if pidReg.FindString(s) != "" {
		p.pid = pidReg.FindStringSubmatch(s)[1]
	}
	if hclReg.FindString(s) != "" {
		p.hcl = hclReg.FindStringSubmatch(s)[1]
	}
	if eclReg.FindString(s) != "" {
		p.ecl = eclReg.FindStringSubmatch(s)[1]
	}
	p.hgt_in = strings.Contains(hgtReg.FindString(s), "in")

	//p.cid = cidReg.FindString(s)

	//byr:%v iyr:%v eyr:%v {hgt:%vcm hgt:%vin} hcl:%v ecl:%v pid:%v cid:%v

	return p
}

func checkPassport(p *passport) bool {
	validEcl := []string{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}
	valid := true

	if p.byr < 1920 || p.byr > 2002 {
		p.byr = -1
		valid = false
	}
	if p.iyr < 2010 || p.iyr > 2020 {
		p.iyr = -1
		valid = false
	}
	if p.eyr < 2020 || p.eyr > 2030 {
		p.eyr = -1
		valid = false
	}
	if p.hgt_in {
		if p.hgt < 59 || p.hgt > 76 {
			p.hgt = -1
			valid = false
		}
	} else {
		if p.hgt < 150 || p.hgt > 193 {
			p.hgt = -1
			valid = false
		}
	}
	if p.hcl == "" {
		p.hcl = "-"
		valid = false
	}
	//if strings.Contains(p.hcl, "z") {
	//	valid = false
	//}
	if !contains(validEcl, p.ecl) {
		p.ecl = "-"
		valid = false
	}
	if p.pid == "" {
		p.pid = "-"
		valid = false
	}
	return valid
}

func main() {

	dat, err := ioutil.ReadFile("d4/t2/input.txt")
	check(err)

	//fmt.Println(string(dat))
	var valid []passport
	var invalid []passport
	var pass passport

	validCount := 0

	s := strings.Split(string(dat), "\n")
	s = append(s, "\n")
	k := ""
	for i := 0; i < len(s); i++ {
		for (s[i] != "") && (i+1 < len(s)) {
			k += s[i]
			k += " "
			i++
		}
		//fmt.Println(k)
		pass = stringToPassport(k)
		//fmt.Println(pass)
		if checkPassport(&pass) {
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
