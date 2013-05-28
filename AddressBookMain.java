package ericsson.online.exam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b>Description:</b><br>
 * This class is a demo which starts a main thread to manage commands 
 * input and execution.
 */
public class AddressBookMain {
  public static void mian(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new ManageCommands());
	}
}
