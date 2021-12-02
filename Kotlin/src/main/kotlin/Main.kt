import java.io.File
import java.util.*

fun main() {
    println("Hello World!")

    Day2("src/main/resources/day2.txt").print()
}

class Day2(private val path : String){

    fun print(){
        println("Day2 :")

        print("Task 1 : ")
        println(task1(readInput()))

        print("Task 2 : ")
        println(task2(readInput()))
    }

    private fun readInput() : Vector<Pair<String, Int>> {
        val text : Vector<String> = Vector(File(path).readLines())
        val result : Vector<Pair<String, Int>> = Vector<Pair<String, Int>>()

        for (line in text) {
            val entry = line.split(" ")
            if(entry.isNotEmpty()) {
                result.addElement(Pair(entry.first(), entry.last().toInt()))
            }
        }

        return result
    }

    private fun task1(input : Vector<Pair<String, Int>>) : Int{
        var depth = 0
        var horizontal = 0

        for (entry in input){
            when(entry.first){
                "forward" -> horizontal += entry.second
                "down" -> depth += entry.second
                "up" -> depth -= entry.second
            }
        }

        //val coords = Pair(horizontal, depth)

        return horizontal * depth
    }

    private fun task2(input: Vector<Pair<String, Int>>) : Int {
        var depth = 0
        var horizontal = 0
        var aim = 0

        for (entry in input){
            when(entry.first){
                "forward" -> {
                    horizontal += entry.second
                    depth += aim * entry.second
                }
                "down" -> aim += entry.second
                "up" -> aim -= entry.second
            }
        }


        return horizontal * depth
    }
}