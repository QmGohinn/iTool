#include "stdio.h"
#include <Windows.h>

int x = 10; //ȫ�ֱ���x����ֵΪ10

int f(int y) //���巵��ֵΪ���εĺ���f������һ���β�y
{
	static int x = 0; //�ֲ���̬����x����ʼ��ֵΪ0
	x += y;
	printf("f:x=%d\n", x); 
	return x; 
}

int main_08()
{ 
	int x = 6;
	{  
		int x = 5;//�ֲ�����x��������ֻ�ڻ�������
		f(x);  //���ú�����xΪʵ�Σ�ֵΪ5
		printf("part:x=%d\n", x); 
	}

	x = f(x); //���ú���f(x)��ʵ��Ϊx��ֵΪ6�������ú������õĽ����������x
	printf("main:x=%d\n", x);

	system("pause");
	return 0;
}
