#include <stdio.h>

int main(){
  int a[] = {1,1,1,1};
  int b[] = {1,-1,-1,1};
  int c[] = {1,1,-1,-1};
  int d[] = {1,-1,1,-1};
  int i=0,j=0;
  //信号
  int z[] = {16,-2,4,-6};
  int ans[4]={};
  printf("信号を入力\n");
  // for(i=0;i<4;i++){
  //   scanf("%d", &z[i]);
  // }

  int count=0;
  for(i=0;i<4;i++){
    for(j=0;j<4;j++){
      if(i == 0){
        ans[i] += z[j] * a[j];
        printf("%d += %d * %d\n", ans[i], z[j], a[j]);
      } else if(i == 1){
        ans[i] += z[j] * b[j];
        printf("%d += %d * %d\n", ans[i], z[j], b[j]);
      } else if(i == 2){
        ans[i] += z[j] * c[j];
        printf("%d += %d * %d\n", ans[i], z[j], c[j]);
      } else if(i == 3){
        ans[i] += z[j] * d[j];
        printf("%d += %d * %d\n", ans[i], z[j], d[j]);
      }

    }
  }
  for(i=0;i<4;i++){
    ans[i] = ans[i] * 1/4;
  }

  for(i=0;i<4;i++){
    printf("%d\n", ans[i]);
  }
  return 0;
}
