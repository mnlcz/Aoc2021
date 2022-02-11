// THIS EXERCISE HAS A MODIFIED input.txt. The original has the draw nums at line 1. I extracted those numbers to make parsing a little easier

import java.io.File

object Day4
{
    // FOR TESTING
//    private val sampleNums = listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
//    private fun parse(): List<Int> = File("./src/main/resources/sample4.txt").readText().replace("\n", " ").replace("\r", "").replace("  ", ",").replace(" ", ",").replace(",,", ",").split(',').map { it.toInt() }

    private val sampleNums = listOf(74, 79, 46, 2, 19, 27, 31, 90, 21, 83, 94, 77, 0, 29, 38, 72, 42, 23, 6, 62, 45, 95, 41, 55, 93, 69, 39, 17, 12, 1, 20, 53, 49, 71, 61, 13, 88, 25, 87, 26, 50, 58, 28, 51, 89, 64, 3, 80, 36, 65, 57, 92, 52, 86, 98, 78, 9, 33, 44, 63, 16, 34, 97, 60, 40, 66, 75, 4, 7, 84, 22, 43, 11, 85, 91, 32, 48, 14, 18, 76, 8, 47, 24, 81, 35, 30, 82, 67, 37, 70, 15, 5, 73, 59, 54, 68, 56, 96, 99, 10)

    private fun parse(): List<Int> = File("./src/main/resources/input4.txt").readText().replace("\n", " ").replace("\r", "").replace("  ", ",").replace(" ", ",").replace(",,", ",").split(',').map { it.toInt() }

    private data class Values(val values: List<Int>)
    {
        var hasWinner: Boolean = false
        var winnerBoard: Int = -1
        var winnerValue: Int = -1
    }

    private open class Board(val board: IntArray, val boardNumber: Int)
    {
        fun printBoard()
        {
            for(i in 0..24)
            {
                if (i == 5 || i == 10 || i == 15 || i == 20) println()
                print("${board[i]}\t")
            }
            println("\n")
        }

        fun play(num: Int)
        {
            for(i in 0..24)
                board[i] = if(board[i] == num) -1 else board[i]
        }

        fun hasWon(): Boolean
        {
            var aux = 0
            for(i in 0 until 5)
            {
                if(board[i] + board[i + 5] + board[i + 10] + board[i + 15] + board[i + 20] == -5) // checks columns
                    return true

                if(board[i + aux] + board[i + 1 + aux] + board[i + 2 + aux] + board[i + 3 + aux] + board[i + 4 + aux] == -5) // checks rows
                    return true

                aux += 4

                /*          -----> EXPLANATION <-----
                                AUX = 0 && I = 0
                BOARD[0] + BOARD[1] + BOARD[2] + BOARD[3] + BOARD[4]

                                AUX = 4 && I = 1
                BOARD[5] + BOARD[6] + BOARD[7] + BOARD[8] + BOARD[9]

                                AUX = 8 && I = 2
                BOARD[10] ...
                 */
            }
            return false
        }

        fun points(): Int
        {
            var sum = 0
            for(i in 0..24)
                if(board[i] != -1) sum += board[i]
            return sum
        }
    }

    private class Board2(brd: IntArray, brdNum: Int, var firstWin: Int = -1) : Board(brd, brdNum)

    object Part1
    {
        fun solve()
        {
            val text = parse()
            val boards = mutableListOf<Board>()
            var aux = 0
            var counter = 1
            while(aux < text.size)
            {
                val input = IntArray(25)
                for((j, i) in (aux..(24 + aux)).withIndex())
                {
                    input[j] = text[i]
                }
                val board = Board(input, counter)
                boards.add(board)
                aux += 25
                counter++
            }
            boards.forEach { it.printBoard() }
            val values = Values(sampleNums)
            for(i in 0 until values.values.size)
            {
                if(values.hasWinner)
                {
                    val indexWinner = values.winnerBoard - 1
                    values.winnerValue = values.values[i - 1]
                    println("WINNER BOARD ${values.winnerBoard} WITH VALUE ${values.winnerValue}")
                    boards[indexWinner].printBoard()
                    println("SUM VALUES: ${boards[indexWinner].points()}")
                    println("SCORE: ${boards[indexWinner].points() * values.winnerValue}")
                    break
                }
                boards.forEach end@{ board ->
                    board.play(values.values[i])
                    if(board.hasWon())
                    {
                        values.hasWinner = true
                        values.winnerBoard = board.boardNumber
                        return@end
                    }
                }
            }
        }
    }

    object Part2
    {
        fun solve()
        {
            val text = parse()
            val boards = mutableListOf<Board2>()
            var aux = 0
            var counter = 1
            while(aux < text.size)
            {
                val input = IntArray(25)
                for((j, i) in (aux..(24 + aux)).withIndex())
                {
                    input[j] = text[i]
                }
                val board = Board2(input, counter)
                boards.add(board)
                aux += 25
                counter++
            }
            boards.forEach { it.printBoard() }
            val values = Values(sampleNums)
            var sumValues = 0
            for(v in values.values)
            {
                for(board in boards)
                {
                    board.play(v)
                    if(board.hasWon() && board.firstWin == -1)
                    {
                        values.hasWinner = true
                        values.winnerValue = v
                        values.winnerBoard = board.boardNumber
                        sumValues = board.points()
                        board.firstWin = v
                    }
                }
            }

            println("LAST WINNER: BOARD ${values.winnerBoard}")
            println("WON WITH VALUE: ${values.winnerValue}")
            println("SUM OF VALUES: $sumValues")
            println("SCORE: ${sumValues * values.winnerValue}")
        }
    }
}