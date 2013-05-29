package ericsson.online.exam.commands;

/**
 * <b>Description:</b><br>
 * The class is the father of other commands.
 */
public abstract class Commands {
    
    /**
     * The output message
     */
    private String output = "";
    
    /**
     * <b>Description:</b>
     * Execute the cat command. 
     * 
     * @return Commands
     */
    abstract public Commands exec();
	
    /**
     * <b>Description:</b>
     * Get the output message. 
     * 
     * @return String
     */
    public String getOutput() {
 	return this.output;
    }
}
