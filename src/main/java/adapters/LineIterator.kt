package adapters

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.NoSuchElementException

class LineIterator(inputStream: InputStream) : Iterator<String> {
    private val reader = BufferedReader(InputStreamReader(inputStream))
    private var nextLine: String? = reader.readLine()

    override fun hasNext(): Boolean = nextLine != null

    override fun next(): String {
        val line = nextLine ?: throw NoSuchElementException()
        nextLine = reader.readLine()
        return line
    }
}
