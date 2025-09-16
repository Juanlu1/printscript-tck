package adapters

import interpreter.ErrorHandler as TckErrorHandler
import org.example.common.ErrorHandler as IntegrationErrorHandler


class AdapterErrorHandler(
    private val tck: TckErrorHandler
) : IntegrationErrorHandler {
    override fun handleError(message: String) {
        println(message)
        tck.reportError(message)
    }
}