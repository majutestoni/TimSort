
# TimSort

- O TimSort nasceu em 2002 por Tim Peters, que por tal motivo recebeu o nome de Tim. 

- Esta forma de ordenação vem de outras duas muito famosas:
```bash
  mergeSort
```
```bash
  insertSort
```
## minRun
O ponto de partidade da ordenação é o calculo da minRun, que pega como base N(tamanho do vetor) e segue alguns principios:
1. O valor de minrun não deve ser tão grande → minrun < 256;
2. O valor de minrun não deve ser tão pequeno → minrun > 8; 
3. Seria bom se N / minrun fosse uma potência de 2 (ou perto disso).
4. Após testes diários, verificou-se que varios casos utilizando a diferença entre 32 e 64 (ou seja, igual a 32)
5. O(N log(N))
   
```java
	private int calculateMinRun(int length) {
		int r = 0;
		while (length >= 32) {
			r |= (length & 1);
			length >>= 1;
		}
		return length + r;
	}
```

## InserSort
 
 Após a minRun definida, o vetor mãe passa a ser separado e varios outros com o tamanho do calculo e os mesmos são organizados utilizando InsertSort.
 
## MergeSort

E por ultimo os subvetores (ou minRuns) passar a ser unido utilizando a ordenação MergeSort.

## Utilizado em:

 Hoje em dia o TimSort é o principal metodo de ordenação utilizado em Python e em Arrays.sort() do Java(font [freeCode camp](https://www.freecodecamp.org/portuguese/news/algoritmos-de-ordenacao-explicados-com-exemplos-em-python-java-e-c/)) por ser extremamente eficaz e rapido.


## Referencias

- [skerritt](https://skerritt.blog/timsort/)
- [ronanlopes](https://ronanlopes.me/implementacao-em-python-e-analise-dos-algoritmos-de-ordenacao-insertionsort-mergesort-e-timsort/)
- [video de simulação](https://www.youtube.com/watch?v=NVIjHj-lrT4&ab_channel=TimoBingmann)

