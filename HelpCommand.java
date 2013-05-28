package ericsson.online.exam.commands;

/**
 * <b>Description:</b><br>
 * The class handles the task to display the help information.
 */
public class HelpCommand {
  private String output = "";
	
	/**
	 * <b>Description:</b><br>
	 * Execute help command and fill output with help message.
	 * Finally return the executed command.
	 * 
	 * @return HelpCommand
	 */
	public HelpCommand exec() {
		this.output = "This is the help message.";
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
			return "Help command not executed.";
		} else {
			return this.output;
		}
	}
}
