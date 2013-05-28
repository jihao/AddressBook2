package ericsson.online.exam.commands;

import java.util.Set;

import ericsson.online.exam.AddressBook;

/**
 * <b>Description:</b><br>
 * The class handles the task to List all items in current position.
 */
public class LsCommand {
    private String output = "";
	
    /**
     * <b>Description:</b><br>
     * Execute the cat command. Fill output with items' names. Finally
     * return the executed command.
     * 
     * @return LsCommand
     */
    public LsCommand exec() {
	Set<String> items = AddressBook.getInstance().getCurrentItems();
	for(String item : items) {
	    this.output = item + " " + this.output;
	}
	return this;
    }
	
    /**
     * <b>Description:</b><br>
     * Get the output of this command;
     * 
     * @return String - output information
     */
    public String getOutput() {
	if(this.output.equals("")) {
	    return "Ls command not executed.";
	} else {
	    return this.output;
	}
    }
}
