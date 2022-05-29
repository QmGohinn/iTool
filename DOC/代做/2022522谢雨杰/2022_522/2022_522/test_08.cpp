#include "stdio.h"
#include <Windows.h>

int x = 10; //全局变量x，初值为10

int f(int y) //定义返回值为整形的函数f，含有一个形参y
{
	static int x = 0; //局部静态变量x，初始化值为0
	x += y;
	printf("f:x=%d\n", x); 
	return x; 
}

int main_08()
{ 
	int x = 6;
	{  
		int x = 5;//局部变量x，作用域只在花括号内
		f(x);  //调用函数，x为实参，值为5
		printf("part:x=%d\n", x); 
	}

	x = f(x); //调用函数f(x)，实参为x，值为6，并将该函数调用的结果赋给变量x
	printf("main:x=%d\n", x);

	system("pause");
	return 0;
}
