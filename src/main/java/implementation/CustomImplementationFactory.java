package implementation;

import adapters.AdapterFormatter;
import adapters.AdapterInterpreter;
import adapters.AdapterLinter;
import interpreter.PrintScriptFormatter;
import interpreter.PrintScriptInterpreter;
import interpreter.PrintScriptLinter;


public class CustomImplementationFactory implements PrintScriptFactory {

    @Override
    public PrintScriptInterpreter interpreter() {
        // your PrintScript implementation should be returned here.
        // make sure to ADAPT your implementation to PrintScriptInterpreter interface.

        // Dummy impl: return (src, version, emitter, handler) -> { };
        return new AdapterInterpreter();
    }

    @Override
    public PrintScriptFormatter formatter() {
        // your PrintScript formatter should be returned here.
        // make sure to ADAPT your formatter to PrintScriptFormatter interface.

        // Dummy impl: return (src, version, config, writer) -> { };
        return new AdapterFormatter();
    }

    @Override
    public PrintScriptLinter linter() {
        // your PrintScript linter should be returned here.
        // make sure to ADAPT your linter to PrintScriptLinter interface.
        return new AdapterLinter();
    }
}