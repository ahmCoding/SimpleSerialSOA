package org.example.dataStructures.command;

/**
 * Abstrakte Klasse für die Implementierung von Befehlen
 */
public abstract class Command {
    protected String[] arguments;

    public Command(String[] argument) {
        this.arguments = argument;
    }

    /**
     * Führt den Befehl aus
     *
     * @return Rückgabe des Befehls
     */
    public abstract String execute();
}
