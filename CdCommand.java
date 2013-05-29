package ericsson.online.exam.commands;

import ericsson.online.exam.AddressBook;

/**
 * <b>Description:</b>
 * <p>
 * The class handles the task to change the current 
 * position in AddressBook.
 * </p>
 */
public class CdCommand extends Commands {
    private String directory;
    private String output = "";
    public CdCommand(String directory) {
        this.directory = directory;
    }
	
    /**
     * <b>Description:</b>
     * Execute the cd command and fill output with result. Finally 
     * return the executed command.
     * 
     * @return CdCommand
     */
    public CdCommand exec() {
	AddressBook addressBook = AddressBook.getInstance();
	if(directory == null) {
	    //cd command with no parameter, return to root
	    addressBook.changeDirectoryToRoot();
	    this.output = "Done";
	} else if(addressBook.existInCurrentDirectory(directory)) {
	    //cd command with parameter, change to directory
	    addressBook.changeDirectory(directory);
	    this.output = "Done";
	} else {
	    this.output = directory + " does not exist.";
	}
	return this;
    }
	
    /**
     * <b>Description:</b><br>
     * Get the output of this command;
     * 
     * @return String - output information
     */
    @Override
    public String getOutput() {
	if(this.output.equals("")) {
            return "Cd command not executed.";
	} else {
	    return this.output;
	}
    }
}
