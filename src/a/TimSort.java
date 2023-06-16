package a;

public class TimSort<T extends Comparable<T>> implements Sort<T> {
	private T[] array;

	
	// Inicia com o caulculo da minRun
	// quem é o tamanhos dos subVetores
	@Override
	public void sort(T[] vetor) {
        int n = vetor.length;
		int minRun = calculateMinRun(vetor.length);
        
        for (int start = 0; start < n; start += minRun) {
            int end = Math.min(start + minRun - 1, n - 1);
            insertionSort(vetor, start, end);
        }
        
        int size = minRun;
        while (size < n) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(n - 1, left + size - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                
                if (mid < right) {
                    merge(vetor, left, mid, right);
                }
            }
            
            size = 2 * size;
        }
    }
	
    public void insertionSort(T[] vetor, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int j = i;
            while (j > left && vetor[j].compareTo(vetor[j - 1]) < 0) {
                T temp = vetor[j];
                vetor[j] = vetor[j - 1];
                vetor[j - 1] = temp;
                j--;
            }
        }
    }
    
    public void merge(T[] vetor, int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;
        T[] left = (T[]) new Object[len1];
        T[] right = (T[]) new Object[len2];
        
        for (int i = 0; i < len1; i++) {
            left[i] = vetor[l + i];
        }
        
        for (int i = 0; i < len2; i++) {
            right[i] = vetor[m + 1 + i];
        }
        
        int i = 0, j = 0, k = l;
        
        while (i < len1 && j < len2) {
            if (left[i].compareTo(right[j]) <= 0) {
                vetor[k] = left[i];
                i++;
            } else {
                vetor[k] = right[j];
                j++;
            }
            k++;
        }
        
        while (i < len1) {
            vetor[k] = left[i];
            k++;
            i++;
        }
        
        while (j < len2) {
            vetor[k] = right[j];
            k++;
            j++;
        }
    }

	
	
	private int calculateMinRun(int length) {
		int r = 0;
		// 32 é a diferença entre 64 e 32
		while (length >= 32) {
			// r faz um OR bit a bit com o resultado da expressão "(n & 1)"
			r |= (length & 1);
			/*
			 * Essa linha de código atualiza o valor de "length" deslocando seus bits para a
			 * direita em uma posição, o que é equivalente a dividir o valor atual de
			 * "length" por 2. O operador ">>=" é um operador de atribuição composto que
			 * combina o operador de deslocamento bit a bit para a direita (">>") com o
			 * operador de atribuição ("="), realizando a operação e atribuindo o resultado
			 * de volta a "length".
			 */

			length >>= 1;
		}
		return length + r;
	}

}
