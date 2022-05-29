#include <stdio.h>
#include <Windows.h>

void change(int i)  //定义void无返回值类型的函数change，该函数含有一个形参i
{  
	if(i < 0){
		i = -i;
		putchar('-');
	}

	/* 递归调用*/
	if (i / 2 >= 1){
		change(min(i / 2, i - i / 2));
	}
	printf("%d", i % 2);
}

void main_07()
{
	int i; char ch;
	do{
		printf("\nPlease input an interger!\n");
		scanf("%d",&i);
		getchar();
		change(i);//调用change函数
		printf("\nDo you want to continue? Y or N?");
		ch=getchar();
	}while(ch=='y'||ch=='Y');  //do、while循环语句
}
