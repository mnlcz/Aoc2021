import java.io.File

object Day3
{
    // FOR TESTING
//    private fun parse(): List<String> = File("./src/main/resources/sample3.txt").readText().split('\n').map { it.replace("\r", "") }
//    private const val SIZE: Int = 5

    private fun parse(): List<String> = File("./src/main/resources/input3.txt").readText().split('\n').map { it.replace("\r", "") }
    private const val SIZE: Int = 12

    object Part1
    {
        fun solve()
        {
            val input = parse()
            var temp: String
            var finalMaxBit = ""
            var finalMinBit = ""
            var one: Int
            var zero: Int

            for(i in 0 until SIZE)
            {
                one = 0
                zero = 0
                temp = ""
                for(j in input.indices)
                {
                    val aux: String = input[j]
                    temp += aux[i]
                }
                zero = temp.count { it == '0' }
                one = temp.count { it == '1' }
                finalMaxBit += if(one > zero) '1' else '0'
                finalMinBit += if(one < zero) '1' else '0'
            }
            val gamma = Integer.parseInt(finalMaxBit, 2)
            val epsilon = Integer.parseInt(finalMinBit, 2)
            println("GAMMA: $gamma")
            println("EPSILON: $epsilon")
            println("FINAL CONSUMPTION: ${gamma * epsilon}")
        }

    }

    object Part2
    {
        fun solve()
        {
            TODO()
        }
    }
}