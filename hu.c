#include <stdio.h>

int main(){
  int bar[][4] = {{1,1,1,1},{1,-1,-1,1},{1,1,-1,-1},{1,-1,1,-1}};
  int in[] = {16,-2,4,-6};
  int i,j;
  // foo[4] = {1,2,3,4}　取りたい要素-1
  int foo[4] = {0};

  for(i=0;i<4;i++){
    for(j=0;j<4;j++){
      foo[i] += in[j] * bar[i][j];
    }
  }

  for(i=0;i<4;i++){
    foo[i] = foo[i] * 1/4;
    printf("%d\n", foo[i]);
  }

  //復元
  int rfoo[4] = {0};
  for(i=0;i<4;i++){
    for(j=0;j<4;j++){
      rfoo[i] += foo[j] * bar[j][i];
    }
  }

  for(i=0;i<4;i++){
    printf("%d\n", rfoo[i]);
  }

    return 0;
}
