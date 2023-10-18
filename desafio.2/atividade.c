#include <stdio.h>
#include <stdlib.h>
int main()
{
            char vetorA[10];
            int i, j, troca;
            for (i=0;i<10;i++){
                        printf("Digite o %d elemento:", i);
                        scanf(" %c", &vetorA[i]);
                        fflush(stdin);
            }
            for (i=0; i<9; i++)           {
                        for (j=i+1; j<10;j++) {
                                    if (vetorA[i] > vetorA[j])  {
                                               troca = vetorA[i];
                                               vetorA[i] = vetorA[j];
                                               vetorA[j] = troca;
                                    }
                        }
            }
            printf ("\n VETOR ORDENADO: \n");
            for (i=0;i<10;i++)           {
                        printf(" %c - ", vetorA[i]);
            }
            return (0);
}