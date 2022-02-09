import java.io.File

object Day1
{
    private fun parse(): List<Int>
    {
        val parsed = mutableListOf<Int>()
        val content: List<String> = File("./src/main/resources/input1.txt").readText().split("\n").map { it.replace("\r", "") }
        content.forEach { parsed.add(it.toInt())}
        return parsed
    }
    object Part1
    {
        fun solve()
        {
            val input = parse()
            var counter = 0
            var currentMax = input.first()
            input.forEach {
                if(it > currentMax) counter++
                currentMax = it
            }
            println("ANSWER: $counter")
        }
    }

    object Part2
    {
        fun solve()
        {
            val input = parse()
            var counter = 0
            var currentMax = input.first()
            for(i in 0..(input.size - 4))
            {
                if(input[i] + input[i + 1] + input[i + 2] > currentMax)
                    counter++
                currentMax = input[i] + input[i + 1] + input[i + 2]
            }
            println("ANSWER: $counter")
        }
    }
}