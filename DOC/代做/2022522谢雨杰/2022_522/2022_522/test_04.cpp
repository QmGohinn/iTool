#include <string.h>
#include <stdio.h>
#include <Windows.h>

char s1[20];
char s2[20];
char s3[40];

int main_04(){
	
	printf("ÇëÊäÈë×Ö·û´®s1\n");
	gets(s1);
	printf("ÇëÊäÈë×Ö·û´®s2\n");
	gets(s2);

	for(int i = 0; i < strlen(s1); ++i){
		s3[i] = s1[i];
	}
	for(int j = 0, _start = strlen(s1); j < strlen(s2); ++j){
		s3[_start + j] = s2[j];
	}
	printf("ÅÅĞòÇ°s3Îª: %s\n", s3);

	for(int _i = 0; _i < strlen(s3) - 1; ++_i){
		for(int _j = 0; _j < strlen(s3) - _i - 1; ++_j){
			if(s3[_j] >= s3[_j + 1]){
				s3[_j] = s3[_j] + s3[_j + 1];
				s3[_j + 1] = s3[_j] - s3[_j + 1];
				s3[_j] = s3[_j] - s3[_j + 1];
			}
		}
	}
	printf("ÅÅĞòºós3Îª: %s\n", s3);

	system("pause");
	return 0;
}
