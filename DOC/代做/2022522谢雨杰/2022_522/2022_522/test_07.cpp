#include <stdio.h>
#include <Windows.h>

void change(int i)  //����void�޷���ֵ���͵ĺ���change���ú�������һ���β�i
{  
	if(i < 0){
		i = -i;
		putchar('-');
	}

	/* �ݹ����*/
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
		change(i);//����change����
		printf("\nDo you want to continue? Y or N?");
		ch=getchar();
	}while(ch=='y'||ch=='Y');  //do��whileѭ�����
}
