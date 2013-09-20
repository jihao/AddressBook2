/*******************************************************************************

* @Author     : Yanke

* @Creat time : 2013-09-20

* @Describe   : A command line application developed in C programming language.

*******************************************************************************/

#include "addressbook.h"

/*******************************************************************************

* @Function   : abook_command()

* @Parameter  : None

* @Describe   : This function is used to check command for address book.

*******************************************************************************/
int 
abook_command()
{
    int choice;
    char command[30];
    printf("addressbook>");  
    fgets(command, sizeof(command), stdin);  
    command[strlen(command) - 1] = '\0';
    
    while (strcmp(command, "ls") && strcmp(command, "cd entry") && strcmp(command, "display") && strcmp(command, "add")
		   && strcmp(command, "cat") && strcmp(command, "remove") && strcmp(command, "!help") && strcmp(command, "quit") && strcmp(command, "save"))
    {
        printf("%s: COMMAND NOT FOUND\n", command);
        printf("Please type '!help' to get more help.\n");
        printf("addressbook>");
        fgets(command, sizeof(command), stdin);  
        command[strlen(command) - 1] = '\0';
    }
	
    if (!strcmp(command, "ls")) choice = 1;
    if (!strcmp(command, "cd entry")) choice = 2;
    if (!strcmp(command, "add")) choice = 3;
    if (!strcmp(command, "display")) choice = 4;
    if (!strcmp(command, "cat")) choice = 5;
    if (!strcmp(command, "remove")) choice = 6;
    if (!strcmp(command, "save")) choice = 7;
    if (!strcmp(command, "!help")) choice = 8;
    if (!strcmp(command, "quit")) choice = 9;		
   
    return(choice);
}

/*******************************************************************************

* @Function   : abook_ls()

* @Parameter  : None

* @Describe   : This function is used to deal with "ls" command.

*******************************************************************************/
void 
abook_ls()
{
    printf("addressbook>");  
	if(ls_mark==0)
	{
		printf("%s\n",name);
	}
	else
	{
		struct personal *p;
		p = head;
		if(head != NULL)
		{
			do
			{
				printf("%s ", p->name);
				p = p->next;
			}while (p != NULL);
			printf("\n");
		}
		else  
			printf("Nothing!\n");
	}
		
}

/*******************************************************************************

* @Function   : abook_cd()

* @Parameter  : None

* @Describe   : This function is used to deal with "cd" command.

*******************************************************************************/
void 
abook_cd()
{
    ls_mark = 1;
}

/*******************************************************************************

* @Function   : inputs()

* @Parameter  : char *explain, char *valueAddr, int count

* @Describe   : This function is used to get string from STDIN.

*******************************************************************************/
void 
inputs(char *explain, char *valueAddr, int count)
{
    char valueStr[40];
    do
	{
        printf("%s", explain);
        fgets(valueStr, sizeof(valueStr), stdin);  
        valueStr[strlen(valueStr) - 1] = '\0';
        if (strlen(valueStr) >= count) printf("Too long!\n\n");
    }while (strlen(valueStr) >= count);
    strcpy(valueAddr, valueStr);
}

/*******************************************************************************

* @Function   : store_link()

* @Parameter  : struct personal *info

* @Describe   : This function is used to add a new node to link.

*******************************************************************************/
struct personal *
store_link(struct personal *info)
{
    if (last)
	{ 
        last->next = info;
    }
    info->next = NULL;
    last = info;
    return(info);
}

/*******************************************************************************

* @Function   : abook_add()

* @Parameter  : None

* @Describe   : This function is used to deal with "add" command.

*******************************************************************************/
void 
abook_add()
{
    struct personal *info;
    info = (struct personal *)malloc(sizeof(struct personal));
    if (info == NULL)
	{ 
        printf("\nOut of memory!\n");
        return;
    }
    inputs("name:", info->name, 30);
	inputs("age:",info->age,4);
    inputs("mobile:", info->mobile, 12);
    inputs("address:", info->address, 40);    
    info = store_link(info);
    if (addr_num == 0) head = info; 
    printf("Address entry added.\n\n");
    addr_num++;
}

/*******************************************************************************

* @Function   : abook_display()

* @Parameter  : struct personal *head

* @Describe   : This function is used to display all tems..

*******************************************************************************/
void 
abook_display(struct personal *head)
{
    struct personal *p;
    int count = 1;
    p = head;
    if(head != NULL)
	{
        do
		{
            printf("record %d:\n",count++);
            printf("-----------------------------------------------------------------\n");
			printf("name:%s (age:%s mobile:%s address:%s)\n",p->name,p->age,p->mobile,p->address);
            p = p->next;
        }while (p != NULL);
    }
    else  
        printf("No data!\n\n");
}

/*******************************************************************************

* @Function   : abook_display_search()

* @Parameter  : struct personal **info, int result

* @Describe   : This function is used to display all tems for searching.

*******************************************************************************/
void 
abook_display_search(struct personal **info, int result)
{
    int i;
    if (result == 1)
    	printf("1 search result:\n");
    else
        printf("%d search results:\n", result);
	printf("-----------------------------------------------------------------\n");
    for (i = 0; i < result; i++)
	{
		
		printf("name:%s (age:%s mobile:%s address:%s)\n",(*info)->name,(*info)->age,(*info)->mobile,(*info)->address);
		printf("-----------------------------------------------------------------\n");
        info++;
    }
}

/*******************************************************************************

* @Function   : abook_strcmp()

* @Parameter  : char *dest, char *src

* @Describe   : This function is compare two strings.

*******************************************************************************/
int 
abook_strcmp(char *dest, char *src)
{
    while (*dest != '\0')
	{
        if (*dest == *src)
		{
            dest++;
            src++;
        }
        else 
            return 1;	
    }
    return 0;    
}

/*******************************************************************************

* @Function   : abook_find()

* @Parameter  : int choice

* @Describe   : This function is used to find tems based on "choice".

*******************************************************************************/
int abook_find(int choice)
{
    struct personal *info;
    info = head;
    char entry[30]; 
    size_t n;
    int result = 0;
    switch (choice)
	{
    case 1:  printf("name:");
    	     fgets(entry, sizeof(entry), stdin);  
             entry[strlen(entry) - 1] = '\0';
      	     n = strlen(entry);
             if ((entry[n - 2] == '.') && (entry[n - 1] == '*'))
			 {          
                 entry[n - 2] = '\0';
                 while (info)
				 {
                     if (!abook_strcmp(entry, info->name))
					 {
                         addr_info[result++] = info;
                         info = info->next;
                     }
                     else info = info->next;
                 }
                 break;	
             }
      	     while (info)
			 {
                 if (!strcmp(entry, info->name))
				 {
                     addr_info[result++] = info;
                     info = info->next;
                 }
                 else info = info->next;
             } break;
	 case 2:  printf("age:");
			  fgets(entry, sizeof(entry), stdin);  
			  entry[strlen(entry) - 1] = '\0';
			  n = strlen(entry);
			  if ((entry[n - 2] == '.') && (entry[n - 1] == '*'))
			  { 		  
				  entry[n - 2] = '\0';
				  while (info)
				  {
					  if (!abook_strcmp(entry, info->age))
					  {
						  addr_info[result++] = info;
						  info = info->next;
					  }
					  else info = info->next;
				  }
				  break; 
			  }
			  while (info)
			  {
				  if (!strcmp(entry, info->age))
				  {
					  addr_info[result++] = info;
					  info = info->next;
				  }
				  else info = info->next;
			  } break;
    case 3:  printf("mobile:");
      	     fgets(entry, sizeof(entry), stdin);  
             entry[strlen(entry) - 1] = '\0';
      	     n = strlen(entry);
             if ((entry[n - 2] == '.') && (entry[n - 1] == '*'))
			 {            
                 entry[n - 2] = '\0';
                 while (info)
				 {
                     if (!abook_strcmp(entry, info->mobile))
					 {
                         addr_info[result++] = info;
                         info = info->next;
                     }
                     else info = info->next;
                 }
                 break;	
             }    
      	     while (info)
			 {
                 if (!strcmp(entry, info->mobile))
				 { 
                     addr_info[result++] = info;
                     info = info->next;
                 }
                 else info = info->next;
             } break;
    case 4:  printf("address:");
      	     fgets(entry, sizeof(entry), stdin);  
             entry[strlen(entry) - 1] = '\0';
      	     n = strlen(entry);
             if ((entry[n - 2] == '.') && (entry[n - 1] == '*'))
			 {             
                 entry[n - 2] = '\0';
                 while (info)
				 {
                     if (!abook_strcmp(entry, info->address))
					 {
                         addr_info[result++] = info;
                         info = info->next;
                     }
                     else info = info->next;
                 }
                 break;	
      	     }
      	     while (info)
			 {
                 if (!strcmp(entry,info->address))
				 {
                     addr_info[result++] = info;
                     info = info->next;
                 }
                 else info = info->next;
             } break;
    }       
    return(result);
}

/*******************************************************************************

* @Function   : abook_cat()

* @Parameter  : None

* @Describe   : This function is used to deal with "cat" command.

*******************************************************************************/
void abook_cat()
{
    char entry[30];
    int result;
    int choice;
    printf("please choice (name|age|mobile|address): ");
    fgets(entry, sizeof(entry), stdin);  
    entry[strlen(entry) - 1] = '\0';
    while (strcmp(entry, "name") && strcmp(entry, "age") && strcmp(entry, "mobile") && strcmp(entry, "address"))
	{
        printf("Invalid input! Please reenter your choice.\n");
        printf("by (name|age|mobile|address): ");
        fgets(entry, sizeof(entry), stdin);  
        entry[strlen(entry) - 1] = '\0';
    }
   
    if (!strcmp(entry, "name")) choice = 1;
    if (!strcmp(entry, "age")) choice = 2;
    if (!strcmp(entry, "mobile")) choice = 3;
    if (!strcmp(entry, "address")) choice = 4;
   
    if (!(result = abook_find(choice)))
	{
        printf("Address entry not found!\n\n");
    }
    else
        abook_display_search(addr_info, result);
}

/*******************************************************************************

* @Function   : abook_remove()

* @Parameter  : None

* @Describe   : This function is used to deal with "remove" command.

*******************************************************************************/
void 
abook_remove()
{
    char entry[30];
    int result, choice, n;
    struct personal *p1, *p2;
    struct personal **info_ptr = addr_info;
    
    printf("by (name|age|mobile|address): ");
    fgets(entry, sizeof(entry), stdin);  
    entry[strlen(entry) - 1] = '\0';
    
    while (strcmp(entry, "name") && strcmp(entry, "age") && strcmp(entry, "mobile") && strcmp(entry, "address"))
	{
        printf("Invalid input! Please reenter your choice.\n");
        printf("by (name|age|mobile|address): ");
        fgets(entry, sizeof(entry), stdin);  
        entry[strlen(entry) - 1] = '\0';
    }
    if (!strcmp(entry, "name")) choice = 1;
    if (!strcmp(entry, "age")) choice = 2;
    if (!strcmp(entry, "mobile")) choice = 3;
    if (!strcmp(entry, "address")) choice = 4;

    if (result = n = abook_find(choice))
	{ 
    	while (result--)
		{
            if (addr_num == 1)
			{                                                      /* if only 1 node */
                head = last = NULL;
                free(*info_ptr);
                *info_ptr = NULL;
            }
            else
			{
                if (*info_ptr == last)
				{                                                  /* to delete the tail node */
                    p2 = head;
                    p1 = head->next;
                    while(p1->next != NULL)
					{ 
                        p2 = p1;
                        p1 = p1->next;
                    }
                    p2->next = NULL;
                    last = p2;
                    free(*info_ptr);
                    *info_ptr = NULL;
                    info_ptr++;
                }
                else if (head == *info_ptr)
				{                                                   /* to delete the head node */
                    head = (*info_ptr)->next;
                    free(*info_ptr); 
                    *info_ptr = NULL;
                    info_ptr++;
                }
                else if ((*info_ptr != head) && (*info_ptr != last))
				{                                                  /* to delete the intermediate node */
                    p2 = head; 
                    p1 = head->next; 
                    while (*info_ptr != p1)
					{
                        p2 = p1;
                        p1 = p1->next;
                    }
                    p2->next = p1->next;
                    free(*info_ptr);
                    *info_ptr = NULL;
                    info_ptr++;
                
                }
            }
            addr_num--;
        }
        if (n == 1)
            printf("1 address entry deleted.\n");  
        else
        	printf("%d address entries deleted.\n", n);   
    }
    else
	{ 
        printf("Address entry not found!\n");
    }
    printf("\n");
}

/*******************************************************************************

* @Function   : abook_save()

* @Parameter  : None

* @Describe   : This function is used to deal with "save" command.

*******************************************************************************/
int 
abook_save()
{
    FILE *cf_ptr; 
    struct personal *node_ptr;
    printf("file path: ");
    fgets(file_name, sizeof(file_name), stdin);  
    file_name[strlen(file_name) - 1] = '\0';
    cf_ptr = fopen(file_name, "w+");
    if (cf_ptr == NULL)
	{
        printf("Could not open file! \n");
        printf("The file which you input may be NOT exist.\n");
        return 1;
    }
    printf("Now saving...\n");
    fwrite(&addr_num, sizeof(int), 1, cf_ptr);
    for (node_ptr = head; node_ptr != NULL; node_ptr = node_ptr->next)
	{
        fwrite(node_ptr, sizeof(struct personal), 1, cf_ptr);
    }
    printf("Successfully saved!\n");
    fclose(cf_ptr);
    return 0;
}

/*******************************************************************************

* @Function   : abook_help()

* @Parameter  : None

* @Describe   : This function is used to deal with "!help" command.

*******************************************************************************/
void 
abook_help()
{
	printf("---------Help information--------\n");
    printf("------ Address book operation -----\n");
    printf(" Type 'ls'       command to list the items in current position.\n");
    printf(" Type 'cd'       command to go to the entry like go to a directory.\n");
    printf(" Type 'display'  command to display all the items.\n");
    printf(" Type 'cat'      command to search the item data and display it.\n");
    printf(" Type 'add'      command to add new address entry to JSON.\n");
    printf(" Type 'remove'   command to delete one or more address entries.\n");
    printf(" Type 'save'     command to save all the items.\n");
    printf(" Type 'quit'     command to quit from the application.\n");
    printf(" Type '!help'    command to get help.\n");
}   
	
int 
main(int argc, char **argv)
{
	FILE *cf_ptr;
    head = last = NULL;
    printf("-------------------------------------------------\n");
	printf("@ Function: Browse and modify a JSON data structure\n");
	printf("@ Version:  2013-09-20  1.0\n");
    printf("@ By:       Yanke\n");
    printf("-------------------------------------------------\n");
    
    while (1)
	{
        switch (abook_command())
		{
	        case 1:    abook_ls(); 
	                   break;
		    case 2:	   abook_cd(); 
					   break;
		    case 3:	   abook_add(); 
					   break;
		    case 4:	   abook_display(head); 
			           break;
			case 5:	   abook_cat(); 
					   break;
			case 6:	   abook_remove(); 
					   break;
			case 7:	   abook_save(); 
					   break;
			case 8:	   abook_help(); 
					   break;
	        case 9:    return(0);
         
        }
   }
}

