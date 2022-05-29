#include <stdio.h>
#include <time.h>
#include <Windows.h>

int main_01(){

	int i = 0, a[10], min = 0, index = 0;

	// (1) start
	srand((unsigned)time(NULL));
	for(int _i = 0; _i < sizeof(a) / sizeof(int); ++_i){
		a[_i] = rand() % 20;
		printf("%d ", a[_i]);
	}
	printf("\n");
	// (1) end

	for(i = 0, /* (2) */min = a[0]; i < 10; ++i){
		if(min > a[i]){
			min = a[i];
			index = i;
		}
	}

	printf("MIN=%d,index=%d\n",min,index);

	system("pause");
	return 0;
}