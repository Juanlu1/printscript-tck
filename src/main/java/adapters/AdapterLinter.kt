package adapters

import org.example.integration.Linter as IntegrationLinter
import org.example.common.ErrorHandler as IntegrationErrorHandler

import interpreter.PrintScriptLinter as TckLinter
import interpreter.ErrorHandler as TckErrorHandler

import java.io.InputStream
/**
 * Adapter que conecta las interfaces del TCK con el Linter.
 */
class AdapterLinter : TckLinter {
    override fun lint(
        src: InputStream,
        version: String,
        config: InputStream,
        handler: TckErrorHandler
    ) {
        val lines: Iterator<String> = LineIterator(src)

        val err: IntegrationErrorHandler = AdapterErrorHandler(handler)

        IntegrationLinter().lint(
            src = lines,
            version = version,
            config = config,
            handler = err
        )
    }
}