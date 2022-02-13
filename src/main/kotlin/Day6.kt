import java.io.File
import java.util.Collections

object Day6
{
    // FOR TESTING
//    private fun parse(): List<Int> = File("./src/main/resources/sample6.txt").readText().split(',').map { it.toInt() }

    private fun parse(): List<Int> = File("./src/main/resources/input6.txt").readText().split(',').map { it.toInt() }

    private fun simulate(days: Int)
    {
        val input = parse()
        val fish = MutableList<Long>(9) {0}       // [0, 0, ... n Times] n = 9
        for(i in input)                           // Counts appearances and stores them in the same index as the value. (Ex: Value 1 appearences in Index 1)
            fish[i]++
        for(i in 1..days)
        {
            Collections.rotate(fish, -1)          // "Shifts" the array 1 spot to the left. The value at index 0 is now at the end.
            fish[6] += fish[8]
        }
        println("FISH: ${fish.sum()}")
    }

    object Part1 { fun solve(): Unit = simulate(80) }
    object Part2 { fun solve(): Unit = simulate(256) }
}
