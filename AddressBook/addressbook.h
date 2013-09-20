/******************************************************************************
*

* @Author     : Yanke

* @Creat time : 2013-09-20

* @Describe   : A command line application developed in C++ programming language.

*******************************************************************************
/
/***************************** Include Files *********************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/************************** Global variable definition *****************************/

/* address entry data structure */
struct personal{
    char name[30];                  /* name of the person */  
	char age[4];                    /* age of the person */  
    char mobile[12];                /* mobile number of the person */
    char address[40];               /* home address of the person */
    struct personal *next;          /* pointer to next entry */
 };

struct personal *head, *last;       /* head & tail pointer of the address linklist */
char *name = "entry";
int addr_num = 0;                   /* number of address entries */
struct personal *addr_info[100];    /* search result */
char file_name[100];                /* file name record */
int ls_mark=0;                      /* mark for ls comand */

