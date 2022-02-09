import java.io.File

object Day2
{
    private open class Submarine(var horizontal: Int = 0, var depth: Int = 0)
    {
        open fun move(destination: String)
        {
            if(destination.isEmpty()) return

            val number = Character.getNumericValue(destination.last())
            when(destination.replace("[0-9 ]".toRegex(), ""))
            {
                "forward" -> horizontal += number
                "down" -> depth += number
                "up" -> depth -= number
                else -> return
            }
            printInfo()
        }
        fun printInfo() = println("HORIZONTAL: $horizontal \tDEPTH: $depth")
        fun executeInstructions(text: List<String>) = text.forEach { move(it) }
    }

    private fun parse(): List<String> = File("./src/main/resources/input2.txt").readText().split('\n')

    object Part1
    {
        fun solve()
        {
            val submarine = Submarine()
            val instructions = parse()
            submarine.executeInstructions(instructions)
            println()
            print("LOCATION ==> ")
            submarine.printInfo()
            println("ANSWER: ${submarine.horizontal * submarine.depth}")
        }
    }

    object Part2
    {
        private class Submarine2 constructor(var aim: Int = 0) : Submarine()
        {
            override fun move(destination: String)
            {
                if(destination.isEmpty()) return

                val number = Character.getNumericValue(destination.last())
                when(destination.replace("[0-9 ]".toRegex(), ""))
                {
                    "forward" -> {
                        horizontal += number
                        depth += aim * number
                    }
                    "down" -> aim += number
                    "up" -> aim -= number
                    else -> return
                }
                printInfo()
            }
        }

        fun solve()
        {
            val submarine = Submarine2()
            val instructions = parse()
            submarine.executeInstructions(instructions)
            println()
            print("LOCATION ==> ")
            submarine.printInfo()
            println("ANSWER: ${submarine.horizontal * submarine.depth}")
        }

    }
}