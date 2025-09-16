package adapters

import java.io.InputStream
import java.io.Writer
import org.example.integration.Formatter as IntegrationFormatter


import interpreter.PrintScriptFormatter as TckFormatter

class AdapterFormatter : TckFormatter {
    override fun format(
        src: InputStream,
        version: String,
        config: InputStream,
        writer: Writer
    ) {
        val lines: Iterator<String> = LineIterator(src)

        IntegrationFormatter().format(
            src = lines,
            version = version,
            config = config,
            writer = writer
        )
    }
}