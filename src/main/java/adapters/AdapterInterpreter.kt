package adapters

import org.example.integration.Interpreter as IntegrationInterpreter
import org.example.interpreter.input.InputProvider as IntegrationInputProvider
import org.example.interpreter.output.OutputPrinter as IntegrationOutputPrinter
import org.example.common.ErrorHandler as IntegrationErrorHandler


import interpreter.PrintScriptInterpreter as TckInterpreter
import interpreter.PrintEmitter as TckPrintEmitter
import interpreter.ErrorHandler as TckErrorHandler
import interpreter.InputProvider as TckInputProvider

import java.io.InputStream

/**
 * Adapter que conecta las interfaces del TCK con el Interpreter.
 */
class AdapterInterpreter : TckInterpreter {

    override fun execute(
        src: InputStream,
        version: String,
        emitter: TckPrintEmitter,
        handler: TckErrorHandler,
        provider: TckInputProvider
    ) {
        val lines: Iterator<String> = LineIterator(src)

        val out: IntegrationOutputPrinter = OutputPrinterFromTck(emitter)
        val err: IntegrationErrorHandler = AdapterErrorHandler(handler)
        val inp: IntegrationInputProvider = InputProviderFromTck(provider)

        try {
            IntegrationInterpreter().execute(
                src = lines,
                version = version,
                emitter = out,
                handler = err,
                provider = inp
            )
        } catch (e: OutOfMemoryError) {
            handler.reportError("Java heap space")
        }
    }
}

private class OutputPrinterFromTck(
    private val tck: TckPrintEmitter
) : IntegrationOutputPrinter {
    override fun print(output: String) {
        tck.print(output)
    }
}

private class InputProviderFromTck(
    private val tck: TckInputProvider
) : IntegrationInputProvider {
    override fun readInput(): String {
        return tck.input("algo tengo que poner")
    }

}
