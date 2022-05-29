#include "stdio.h"
int  fun(int x,int y)                                /*1*/
{
	int z;                                         /*2*/
	z=10*x+y;                                    /*3*/
	return z;                                      /*4*/
}
void main_05()                                     /*5*/
{
	int a,b,result;                                  /*6*/
	printf("\nPlease input 2 numbers a, b\n");           /*7*/
	scanf("%d,%d",&a,&b);                        /*8*/
	result = fun(a,b);                              /*9*/
	printf("\nThe result is=%d\n", result);             /*10*/ 
}
