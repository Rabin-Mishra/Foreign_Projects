 
#include <stdio.h>
#include <stdlib.h>

int *interleave(int X[], int nX, int i) {
    int *result = (int *)malloc(nX * i * sizeof(int));
    int index = 0;
    for (int j = 0; j < nX; j++) {
        for (int k = 0; k < i; k++) {
            result[index++] = X[j];
        }
    }
    return result;
}

int isSubsequence(int A[], int nA, int Y[], int nY) {
    int j = 0;
    for (int k = 0; k < nA && j < nY; k++) {
        if (A[k] == Y[j]) {
            j++;
        }
    }
    return j == nY;
}

int binarySearch(int A[], int nA, int X[], int nX) {
    int low = 1, high = nA / nX, maxInterleave = -1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        int isSubseq = 0;
        int j = 0;
        for (int k = 0; k < nA; k++) {
            if (A[k] == X[j]) {
                j++;
                if (j == nX) {
                    isSubseq = 1;
                    break;
                }
            }
        }

        if (isSubseq) {
            maxInterleave = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return maxInterleave;
}

int main() {
    int nA, nX, separator;

    if (scanf("%d %d", &nA, &nX) != 2 || nA <= 0 || nX <= 0) {
        printf("Invalid input sizes!\n");
        return 1;
    }

    int *A = (int *)malloc(nA * sizeof(int));
    int *X = (int *)malloc(nX * sizeof(int));

    for (int i = 0; i < nA; i++) {
        if (scanf("%d", &A[i]) != 1) {
            printf("Invalid input for array A!\n");
            free(A);
            free(X);
            return 1;
        }
    }

    if (scanf("%d", &separator) != 1 || separator != -999999999) {
        printf("Incorrect separator!\n");
        free(A);
        free(X);
        return 1;
    }

    for (int i = 0; i < nX; i++) {
        if (scanf("%d", &X[i]) != 1) {
            printf("Invalid input for array X!\n");
            free(A);
            free(X);
            return 1;
        }
    }

    if (scanf("%d", &separator) != 1 || separator != -999999999) {
        printf("Incorrect separator!\n");
        free(A);
        free(X);
        return 1;
    }

    int maxInterleave = binarySearch(A, nA, X, nX);

    printf("Maximum repeats is %d\n", maxInterleave);

    free(A);
    free(X);

    return 0;
}
