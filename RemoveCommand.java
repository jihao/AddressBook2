package ericsson.online.exam.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ericsson.online.exam.AddressBook;

/**
 * <b>Description:</b><br>
 * The class handles the task to remove a specified person from AddressBook.
 */
public class RemoveCommand {
  private String output = "";
	private String name;
	
	/**
	 * <b>Description:</b><br>
	 * Default constructor. Read the name of a person form IO.
	 */
	public RemoveCommand() {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(in);
		try {
			//read the person's name 
			System.out.println("Please give the key:");
			this.name = br.readLine();
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * <b>Description:</b><br>
	 * A overloaded version of constructor. Mainly used for junit test.
	 * 
	 * @param name - String
	 */
	public RemoveCommand(String name) {
		this.name = name;
	}
	
	/**
	 * <b>Description:</b><br>
	 * Execute remove command. Read the name of the person to be removed 
	 * and remove this person from entries of AddressBook. Finally return 
	 * the executed command.
	 * 
	 * @return RemoveCommand
	 */
	public RemoveCommand exec() {
		AddressBook addressBook = AddressBook.getInstance();
		if(addressBook.getCurrentDirectory().equals("root")) {
			this.output = "Wrong directory.";
		} else if(addressBook.existInCurrentDirectory(name)) {
			addressBook.removePerson(name);
			this.output = "Address entry removed.";
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
	public String getOutput() {
		if(this.output.equals("")) {
			return "remove command not executed.";
		} else {
			return this.output;
		}
	}
}
