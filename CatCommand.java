package ericsson.online.exam.commands;

import ericsson.online.exam.AddressBook;

/**
 * <b>Description:</b><br>
 * The class handles the task to display a specified person's information.
 */
public class CatCommand extends Commands {
    private String name;
    private String output = "";
    public CatCommand(String name) {
    this.name = name;
}
	
    /**
     * <b>Description:</b>
     * Execute the cat command. Fill output with person's information. 
     * Finally return the executed command.
     * 
     * @return CatCommand
     */
    public CatCommand exec() {
        AddressBook addressBook = AddressBook.getInstance();
	if(addressBook.getCurrentDirectory().equals("root")) {
            this.output = "Wrong directory.";
	} else if(addressBook.existInCurrentDirectory(name)) {
	    this.output = AddressBook.getInstance().getPersonData(name);
	} else {
	    this.output = name + " does not exist.";
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
	    return "Cat command not executed.";
	} else {
	    return this.output;
	}
    }
}
