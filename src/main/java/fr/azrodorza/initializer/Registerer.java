package fr.azrodorza.initializer;

import static fr.azrodorza.commands.CommandInitializer.initCommands;
import static fr.azrodorza.events.EventInitializer.initEvents;

public class Registerer {
    public static void registerAll() {
        initCommands();
        initEvents();
    }
}
