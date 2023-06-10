package a;

public class TimSort<T extends Comparable<T>> implements Sort<T> {
    private T[] array;
	@Override
	public void sort(T[] vetor) {
		this.array= vetor;
		
		int minRun = calculateMinRun(vetor.length);
		
		 for (int i = 0; i < vetor.length; i += minRun) {
	            int end = Math.min(i + minRun - 1, vetor.length - 1);
	            insertionSort(i, end);
	        }

	        for (int size = minRun; size < vetor.length; size = 2 * size) {
	            for (int left = 0; left < vetor.length; left += 2 * size) {
	                int mid = left + size - 1;
	                int right = Math.min(left + 2 * size - 1, vetor.length - 1);
	                merge(left, mid, right);
	            }
	        }
		
	}
	
    private void merge(int left, int mid, int right) {
        int length1 = mid - left + 1;
        int length2 = right - mid;

        T[] leftArray = (T[]) new Object[length1];
        T[] rightArray = (T[]) new Object[length2];

        int i = 0, j = 0, k = left;

        while (i < length1 && j < length2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < length1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < length2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
	
    private void insertionSort(int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= start && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
	

    private int calculateMinRun(int length) {
        int r = 0;
        while (length >= 32) {
            r |= (length & 1);
            length >>= 1;
        }
        return length + r;
    }

}
