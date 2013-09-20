********************* Introduction *********************

Address Book:
It's a command line application developed by C programming language. 
You can manage address book by adding, searching and removing address entries.

********************* environment  *********************
Linux version 2.6.32
Gcc version 4.4.7 (Red Hat 4.4.7-3)
 
 
*********************   details    *********************
 ***************
 **  name     **
 **  age      **
 **  mobile   **
 **  address  **
 ***************

*********************************************************
1.compile the program,and generate a executable file:process
sh>make clean
sh>make
sh>./process
addressbook>

or

you can compile the program by this:
sh>gcc addressbook.c -o process

*********************************************************
2.'ls'command to list the items in current position.
addressbook>ls
addressbook>entry

*********************************************************
3.'cd'command to go to the entry like go to a directory
addressbook>cd entry
addressbook>ls
addressbook>Nothing!

*********************************************************
4.'add'command to add new address entry
addressbook>add
name:xiao ming
age:20
mobile:10000000000
address:xiao ming home
Address entry added.

addressbook>add
name:xiao gang
age:30
mobile:20000000000
address:xiao gang home
Address entry added.

*********************************************************
5.'display'command to display all the items
addressbook>display
record 1:
-----------------------------------------------------------------
name:xiao ming (age:20 mobile:10000000000 address:xiao ming home)
record 2:
-----------------------------------------------------------------
name:xiao gang (age:30 mobile:20000000000 address:xiao gang home)

*********************************************************
6.'cat'command to search the item data and display it
addressbook>cat
please choice (name|age|mobile|address): name
name:xiao ming
1 search result:
-----------------------------------------------------------------
name:xiao ming (age:20 mobile:10000000000 address:xiao ming home)
-----------------------------------------------------------------

*********************************************************
7.'remove'command to delete one or more address entries.
addressbook>remove
by (name|age|mobile|address): name
name:xiao ming
1 address entry deleted.

addressbook>ls
addressbook>xiao gang

*********************************************************
8.'save'command to save all the items.
addressbook>save
file path: /home/addressbook/out.txt
Now saving...
Successfully saved!

*********************************************************
9.'!help'command to get help

*********************************************************
10.'quit'command to quit from the application to shell.

**********************     END      *********************

