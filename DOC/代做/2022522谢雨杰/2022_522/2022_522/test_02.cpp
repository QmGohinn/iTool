#include <stdio.h>
#include <Windows.h>

int a[4][4] = {
			10,  15,  4,  1,
            23,  13,  8,  7,
            2,   4,   8,  1,
            22,  9,   6,  5 };

int i = 0;
int j = 0;
int _res1 = 0, _res2 = 0, _res3 = 0;

int main_02(){

	for(i = 0; i < 4; ++i){
		for(j = 0; j < 4; ++j){
			if(i == j){
				_res1 += a[i][j];
				_res3 += a[i][j];
			}

			if(i == 0 || i == 3 || j == 0 || j == 3){
				_res2 += a[i][j];
			}

			if(i + j == 3){
				_res1 += a[i][j];
			}
		}
	}

	printf("对角线和为: %d\n", _res1);
	printf("外圈数值和为: %d\n", _res2);
	printf("主对角线和为: %d\n", _res3);

	system("pause");
	return 0;
}