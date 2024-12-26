package org.example.dataStructures.command;

/**
 * Klasse zur Implementierung des Befehls "Shutdown"
 */
public class ShutdownCommand extends Command {
    public ShutdownCommand(String[] argument) {
        super(argument);
    }

    @Override
    public String execute() {
        return "Server Shutdown!";
    }
}
