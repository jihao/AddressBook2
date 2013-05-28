package ericsson.online.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ericsson.online.exam.commands.AddCommand;
import ericsson.online.exam.commands.CatCommand;
import ericsson.online.exam.commands.CdCommand;
import ericsson.online.exam.commands.HelpCommand;
import ericsson.online.exam.commands.LsCommand;
import ericsson.online.exam.commands.RemoveCommand;

/**
 * <b>Description:</b>
 * Class ManageCommands handles tasks of main thread including parsing
 * input, executing command and printing output messages.
 */
public class ManageCommands implements Runnable {
  
    /**
     * <b>Description:</b><br>
     * Local method. Parse the input command into command type and parameter,
     * restore them in a HasMap. If command has no parameters, just restore the 
     * command type.
     * 
     * @param input - String - input command - Examples : "cd entries"
     * @return HashMap<String, String> - parsed command 
     */
    private HashMap<String,String> parseCommand(String input) {
	HashMap<String,String> result = new HashMap<String,String>();
	Matcher cdMatcher = Pattern.compile("(cd\\s)\\w+").matcher(input);
	Matcher catMatcher = Pattern.compile("(cat\\s)\\w+").matcher(input);
	if(cdMatcher.lookingAt()) {
	    result.put("type","cd");
	    result.put("parameter",input.substring(3));
	} else if(catMatcher.lookingAt()) {
	    result.put("type","cat");
	    result.put("parameter",input.substring(4));
	} else if(input.equals("cd")) {
	    result.put("type","cd");
	    result.put("parameter",null);
	} else if(input.equals("ls")) {
	    result.put("type","ls");
	    result.put("parameter",null);
	} else if(input.equals("add")) {
	    result.put("type","add");
	    result.put("parameter",null);
	} else if(input.equals("remove")) {
	    result.put("type","remove");
	    result.put("parameter",null);
	} else if(input.equals("!quit")) {
	    result.put("type","quit");
	    result.put("parameter",null);
	} else if(input.equals("!help")) {
	    result.put("type","help");
	    result.put("parameter",null);
	} else if(input.equals("")) {
	    result.put("type","empty");
	    result.put("parameter",null);
	} else {
	    result.put("type","wrong");
	    result.put("parameter",null);
	}
	    return result;
	} 
	
	/**
	 * <b>Description:</b><br>
	 * Implementation of run() function of ManageCommands. Read 
	 * commands repeatedly from standard IO. Then parse the input 
	 * commands and start a corresponding thread. 
	 */
	public void run() {
	    System.out.println("running");
	    InputStreamReader in = new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
	    boolean loop = true;
	    try {
	        while(loop) {
		    //read
		    String input = br.readLine();
		    //parse command into type and parameter
		    HashMap<String,String> command = parseCommand(input);
		    //construct and start the corresponding thread
		    if(command.get("type").equals("cd")) {
		        System.out.println(new CdCommand(command.get("parameter")).exec().getOutput());
		    } else if(command.get("type").equals("cat")) {
			CatCommand catCommand = new CatCommand(command.get("parameter"));
			catCommand.exec();
			System.out.println(catCommand.getOutput());
		    } else if(command.get("type").equals("ls")) {
			LsCommand lsCommand = new LsCommand();
			lsCommand.exec();
			System.out.println(lsCommand.getOutput());
		    } else if(command.get("type").equals("add")) {
			AddCommand addCommand = new AddCommand();
			addCommand.exec();
			System.out.println(addCommand.getOutput());
		    } else if(command.get("type").equals("remove")) {
			RemoveCommand removeCommand = new RemoveCommand();
			removeCommand.exec();
			System.out.println(removeCommand.getOutput());
		    } else if(command.get("type").equals("help") || command.get("type").equals("wrong")) {
			HelpCommand helpCommand = new HelpCommand();
			helpCommand.exec();
			System.out.println(helpCommand.getOutput());
		    } else if(command.get("type").equals("empty")) {
			continue;
		    } else if(command.get("type").equals("quit")) {
			loop = false;
		    } 
		}
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
}

