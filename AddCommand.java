package ericsson.online.exam.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ericsson.online.exam.AddressBook;

/**
 * <b>Description:</b><br>
 * The class handles the task to add a specified person into the current 
 * position in AddressBook.
 */
public class AddCommand extends Commands{
  
    private String output = "";
    private String name;
    private int age;
    private String mobile;
    private String address;
	
    /**
     * <b>Description:</b><br>
     * Default constructor. Read the informations of a person form IO 
     * and parse input informations.
     */
    public AddCommand() {
	InputStreamReader in = new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(in);
	try {
	    //read the person's name 
	    System.out.println("key:");
	    this.name = br.readLine();
	    //read the person's detail information
	    System.out.println("value:");
	    String content = br.readLine();
	    //use regular expression to get age, mobile and address
	    Matcher ageMatcher = Pattern.compile("\"age\":\\s(\\d+),").matcher(content);
	    Matcher mobileMatcher = Pattern.compile("\"mobile\":\\s\"(\\d+)\",").matcher(content);
	    Matcher addressMatcher = Pattern.compile("\"address\":\\s\"([\\w\\s]+)\"}").matcher(content);
	    if(ageMatcher.find()) {
	        this.age = Integer.parseInt(ageMatcher.group(1));
	    }
	    if(mobileMatcher.find()) {
	        this.mobile = mobileMatcher.group(1);
	    }
	    if(addressMatcher.find()) {
		this.address = addressMatcher.group(1);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * <b>Description:</b><br>
     * A overloaded version of constructor. Mainly used for junit test.
     * 
     * @param name - String
     * @param age - int
     * @param mobile - String
     * @param address - String
     */
    public AddCommand(String name, int age, String mobile, String address) {
        this.name = name;
	this.age = age;
	this.mobile = mobile;
	this.address = address;
    }
	
    /**
     * <b>Description:</b><br>
     * Execute the Add command. Read the informations of a person form IO 
     * and add this person into entries of AddressBook. Finally return the
     * executed command.
     * 
     * @return AddCommand
     */
    public AddCommand exec() {
	AddressBook addressBook = AddressBook.getInstance();
	if(addressBook.getCurrentDirectory().equals("root")) {
            this.output = "Wrong directory.";
	} else if(addressBook.existInCurrentDirectory(name)) {
	    this.output = this.name + " has already existed.";
	} else {
	    //add person
	    addressBook.addPerson(name, age, mobile, address);
	    this.output = "Address entry added.";
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
	    return "Add command not executed.";
	} else {
	    return this.output;
	}
    }	
}
