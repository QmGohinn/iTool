#include <stdio.h>
#include <Windows.h>

int isaprime(int n) /*��2���д��isaprime����*/ 
{
	if(n <= 1){
		return 1;
	}

	for(int i = 2; i < n - 1; ++i){
		if(0 == n % i){
			return 1;
		}
	}
	return 0;
}

int isprime(int* x, int n)
{  
	int j, total = 0;
	/*Ƕ�׵���isaprime*/
	for(j = 0; j < n; j++){
		int _input = *(x + j);
		if(isaprime(_input) == 0){
			total++;
		}
	}
	return total;
}

int main_06()
{
	int x[10], i;
	for(i = 0; i < 10; i++){
		scanf("%d",&x[i]);
	}
	printf("�����к���%d������", isprime(x, 10));

	system("pause");
	return 0;
}
