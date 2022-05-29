#include <stdio.h>
#include <Windows.h>

int b[3][4] = {
			10,  15,  4,  1,
            23,  13,  8,  7,
            2,   4,   8,  11};

int _row = 0;
int _col = 0;
int max = b[_row][_col];

int main(){
	
	for(int i = 0; i < 3; ++i){
		for(int j = 0; j < 4; ++j){
			if(b[i][j] > max){
				max = b[i][j];
				_row = i;
				_col = j;
			}
		}
	}

	printf("最大值为: %d\n", max);
	printf("行数为: %d\n", _row + 1);
	printf("列数为: %d\n", _col + 1);

	system("pause");
	return 0;
}