package test.junittest;

import ericsson.online.exam.AddressBook;
import ericsson.online.exam.commands.AddCommand;
import ericsson.online.exam.commands.CatCommand;
import ericsson.online.exam.commands.CdCommand;
import ericsson.online.exam.commands.HelpCommand;
import ericsson.online.exam.commands.LsCommand;
import ericsson.online.exam.commands.RemoveCommand;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
* <b>Test Case:</b><br>
* Address Book JUnit Test <br>
* <b>Verify:</b><br>
* That all commands works well as follow.
* <p>
* CdCommand:<br>
* If there is no parameter, just return to the root directory. If there 
* is a directory name in command, check the name. Then enter the directory 
* if it exists in current position in address book. If the directory doesn't 
* exist here, output the error message.
* <p>
* CatCommand:<br>
* Output error message if current directory is not "entries" or the person 
* name does not exist. Out put the person's information if name exists in 
* current directory.
* <p>
* LsCommand:<br>
* List all items in current directory.
* <p>
* HelpCommand:<br>
* Output help message.
* <p>
* AddCommand:<br>
* If current directory is "root", output error message. In "entries", if 
* the person to be added already exists, output error message. If it does 
* not exist, add this person into "entries".
* <p>
* RemoveCommand:<br>
* If current directory is "root", output error message. In "entries", if 
* the person to be removed already exists, remove this person from "entries".
* If it does not exist, output error message.
* <p>
*/
public class AddressBookJunitTest extends TestCase {
   
    /**
     * <b>Discription</b><br>
     * Test the function of CdCommand.
     */
    public void testCdCommand() {
	AddressBook addressBook = AddressBook.getInstance();
	//check if it enters directory entries
	Assert.assertTrue(new CdCommand("entries").exec().getOutput().equals("Done"));
	Assert.assertTrue(addressBook.getCurrentDirectory().equals("entries"));
	//check if it returns to root
	Assert.assertTrue(new CdCommand(null).exec().getOutput().equals("Done"));
	Assert.assertTrue(addressBook.getCurrentDirectory().equals("root"));
	//check output when aaa directory does not exist
	Assert.assertTrue(new CdCommand("aaa").exec().getOutput().equals("aaa does not exist."));
	Assert.assertTrue(addressBook.getCurrentDirectory().equals("root"));
    }

    /**
     * <b>Discription</b><br>
     * Test the function of CatCommand.
     */
    public void testCatCommand() {
	//check error message in wrong position
	Assert.assertTrue(new CatCommand("lilei").exec().getOutput().equals("Wrong directory."));
	new CdCommand("entries").exec();
	//check right message 
	Assert.assertTrue(new CatCommand("lilei").exec().getOutput()
			.equals("\"lilei\" : { \"age\": 27, \"mobile\": \"13700000000\", \"address\": " +
					"\"Earth somewhere\"}"));
	//check output when person aaa  does not exist
	Assert.assertTrue(new CatCommand("aaa").exec().getOutput().equals("aaa does not exist."));
    }

    /**
     * <b>Discription</b><br>
     * Test the function of LsCommand.
     */
    public void testLsCommand() {
	new CdCommand(null).exec();
	Assert.assertTrue(new LsCommand().exec().getOutput().equals("entries "));
	new CdCommand("entries").exec();
	Assert.assertTrue(new LsCommand().exec().getOutput().equals("lilei hanmeimei "));
    }
	
    /**
     * <b>Discription</b><br>
     * Test the function of HelpCommand.
     */
    public void testHelpCommand() {
	new CdCommand(null).exec();
	Assert.assertTrue(new HelpCommand().exec().getOutput().equals("This is the help message."));
    }
    
    /**
     * <b>Discription</b><br>
     * Test the function of AddCommand.
     */	
    public void testAddCommand() {
	new CdCommand(null).exec();
	//check error message when execute in wrong directory
	Assert.assertTrue(new AddCommand("abc",1,"123","shanghai").exec()
			.getOutput().equals("Wrong directory."));
	new CdCommand("entries").exec();
	//check output message and items when execute in entries
	Assert.assertTrue(new AddCommand("abc",1,"123","shanghai").exec()
			.getOutput().equals("Address entry added."));
	Assert.assertTrue(new LsCommand().exec().getOutput().equals("lilei abc hanmeimei "));
	//check error message when adding a existed person
	Assert.assertTrue(new AddCommand("abc",1,"123","shanghai").exec()
			.getOutput().equals("abc has already existed."));		
    }

    /**
     * <b>Discription</b><br>
     * Test the function of RemoveCommand.
     */	
    public void testRemoveCommand() {
	new CdCommand(null).exec();
	//check error message when execute in wrong directory
	Assert.assertTrue(new RemoveCommand("abc").exec().getOutput().equals("Wrong directory."));
	new CdCommand("entries").exec();
	//check output message and items when execute in entries
	Assert.assertTrue(new RemoveCommand("abc").exec().getOutput().equals("Address entry removed."));
	Assert.assertTrue(new LsCommand().exec().getOutput().equals("lilei hanmeimei "));
	//check error message when removing a not-existed person
	Assert.assertTrue(new RemoveCommand("abc").exec().getOutput().equals("abc does not exist."));
    }	
}
