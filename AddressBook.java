package ericsson.online.exam;

import java.util.Hashtable;
import java.util.Set;

/**
 * <b>Description:</b><br>
 * The class stores and manages the addresses of different persons. 
 * Functions display data, add person and remove person are also included.
 */
public class AddressBook{
    
    /**
     * The HashTable used to restore data of persons.
     */
     private Hashtable<String,Hashtable<String,Person>> data = new Hashtable<String,Hashtable<String,Person>>();
	
    /**
     * The string representing the current position in data.
     */
     private String currentDirectory = null;

    /**
     * Using singleton mode to get a instance of AddressBook.
     */
     private static AddressBook instance = null;

    /**
     * <b>Description:</b><br>
     * The default constructor of class AddressBook.
     */
     private AddressBook(){
         Person lilei = new Person("lilei", 27, "13700000000", "Earth somewhere");
	 Person hanmeimei = new Person("hanmeimei", 26, "13700000001", "Earth somewhere else");
 	 Hashtable<String,Person> entries = new Hashtable<String, Person>();
	 entries.put("lilei", lilei);
	 entries.put("hanmeimei", hanmeimei);
	 this.data.put("entries", entries);
	 this.currentDirectory = "root";
     }
	
    /**
     * <b>Description:</b><br>
     * Get the single instance of AddressBook class.
     * 
     * @return AddressBook - the single instance 
     */
     public static AddressBook getInstance(){
	 if(instance == null){
	     synchronized (AddressBook.class){
	         if(instance == null){
		     instance = new AddressBook();
                 }
	     }
	 }
	 return instance;
     }
	
    /**
     * <b>Description:</b><br>
     * Get the current position in data.
     * 
     * @return String - the current position - Example: "entries" 
     */
     public String getCurrentDirectory() {
	 return this.currentDirectory;
     }
	
    /**
     * <b>Description:</b><br>
     * Change the current position to objective position.
     * 
     * @param directory - String - objective position - Example: "entries" 
     */
     public void changeDirectory(String directory) {
	 this.currentDirectory = directory;
     }
	
    /**
     * <b>Description:</b><br>
     * Change the current position to root.
     */
     public void changeDirectoryToRoot() {
         this.currentDirectory = "root";
     }
	
    /**
     * <b>Description:</b><br>
     * Get all items in current directory
     * 
     * return Set<String> - set of all items' names
     */
     public Set<String> getCurrentItems(){
	 if(this.currentDirectory == "root") {
	     return this.data.keySet();
	 } else {
	     return this.data.get(this.currentDirectory).keySet();
	 }
     }

    /**
     * <b>Description:</b><br>
     * Get a specified person's detail information in String form.
     * 
     * @param name - String - the name of a specified person - Example: "lilei"
     * 
     * @return String 
     * /
     public String getPersonData(String name) {
		return this.data.get("entries").get(name).getInformation();
	}
	
	/**
	 * <b>Description:</b><br>
	 * Add a specified person into entries.
	 * 
	 * @param name - String - the name of a specified person - Example: "lilei"
	 * @param age - Integer - the age of a person - Example: 26
	 * @param mobile - String - the mobile phone number - Example: "13800000000"
	 * @param address - String - the address of a person - Example: "Shanghai"
	 */
	public void addPerson(String name, int age, String mobile, String address) {
		this.data.get(this.currentDirectory).put(name, 
				new Person(name, age, mobile, address));
	}
	
	/**
	 * <b>Description:</b><br>
	 * Return if a person exists in the current directory.
	 * 
	 * @param name - String - the name of a specified person - Example: "lilei"
	 * 
	 * @return boolean 
	 */
	public boolean existInCurrentDirectory(String name) {
		if(currentDirectory.equals("root")) {
			return this.data.containsKey(name);
		} else {
			return this.data.get(currentDirectory).containsKey(name);
		}
	}
	
	/**
	 * <b>Description:</b><br>
	 * Remove a specified person from entries.
	 * 
	 * @param name - String - the name of the person being removed - Example: "lilei"
	 */
	public void removePerson(String name) {
		this.data.get(this.currentDirectory).remove(name);
	}
}

/**
 * <b>Description:</b><br>
 * The class can be used to restore and display the information of a person. 
 */
class Person {
	private String name;
	private int age;
	private String mobile;
	private String address;
	
	/**
	 * <b>Description:</b><br>
	 * The constructor of class Person.
	 * 
	 * @param name - String
	 * @param age - int
	 * @param mobile - String
	 * @param address - String
	 */
	public Person(String name, int age, String mobile, String address) {
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.address = address; 
	}
	
	/**
	 * <b>Description:</b><br>
	 * Display the informations of this person.
	 */
	public String getInformation() {
		return "\"" + this.name + "\" : { \"age\": " + this.age 
				+", \"mobile\": \"" + this.mobile + "\", \"address\": \"" 
				+ this.address + "\"}";
	}
}
