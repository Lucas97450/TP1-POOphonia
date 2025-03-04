package ui;

import services.MusicLibrary;
import services.CommandProcessor;

/**
 * Main entry point for the POOphonia application.
 *
 * This class initializes the music library, processes user commands,
 * and ensures the library is saved before exiting.
 *
 * The application follows a simple console-based interface
 * where commands are executed from an input file.
 *
 * @author Francois Major
 * @version 1.0
 * @since 2025-01-31
 *
 * © 2025 POOphonia. All rights reserved.
 */
public class POOphonia {
<<<<<<< HEAD
=======

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    /**
     * Main method that starts the POOphonia application.
     *
     * @param args Command-line arguments (not used in this version).
     */
    public static void main( String[] args ) {

        // Display welcome message
<<<<<<< HEAD
//        Message.send( "***** POOphonia: Welcome! *****" );
=======
        Message.send( "***** POOphonia: Welcome! *****" );
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)

        // Initialize the music library
        MusicLibrary library = new MusicLibrary();

<<<<<<< HEAD
        // Initialize the processor
        CommandProcessor processor = new CommandProcessor(library);


        // Process commands from the default data/commands.txt file
        CommandProcessor.processCommand("SOURCE ");

        // Save the library to the default file data/POOphonia.csv file
//        library.save( "" );

        // Display exit message
//        Message.send( "***** POOphonia: Goodbye! *****" );
    }
}
=======
        // Process commands from the default data/commands.txt file
        CommandProcessor.processCommands( library );

        // Save the library to the default file data/POOphonia.csv file
        library.save( "" );

        // Display exit message
        Message.send( "***** POOphonia: Goodbye! *****" );
    }
}
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
