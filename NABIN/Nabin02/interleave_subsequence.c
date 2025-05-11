/*
Name:Nabin Dhakal
ID:1002167771
Compilation Command: 
gcc -o test1 interleave_subsequence.c
test1 < lab2a.dat

Using Command Prompt to run the program
*/

#include <stdio.h>
#include <stdlib.h>

// Function to check if X is a subsequence of A with a given interleave factor i
int isSubsequence(int A[], int nA, int X[], int nX, int i)
{
    int iA = 0; // Indexing for sequence A

    for (int iX = 0; iX < nX; iX++)
    {
        int found = 0;
        while (iA < nA && A[iA] != X[iX])
        {
            iA++;
        }

        // Checking if element from X was found in A with enough repetitions
        for (int j = 0; j < i; j++)
        {
            if (iA + j >= nA || A[iA + j] != X[iX])
            {
                found = 0;
                break;
            }
            found = 1;
        }

        if (!found)
        {
            iA = 0; // Resetting the index for sequence A
            // Trying to find the element in A again from the beginning
            while (iA < nA && A[iA] != X[iX])
            {
                iA++;
            }
        }

        if (!found)
        {
            return 0; // X is not a subsequence of A with factor i
        }
    }
    return 1; // X is a subsequence of A with factor i
}

// Binary search to find the maximum interleave factor
int findMaxInterleave(int A[], int nA, int X[], int nX)
{
    int low = 0;
    int high = nA / nX; 
    int result = 0;

    while (low <= high)
    {
        int mid = (low + high) / 2;
        if (isSubsequence(A, nA, X, nX, mid))
        {
            result = mid;
            low = mid + 1;
            printf("low %d mid %d high %d passed\n", low, mid, high);
        }
        else
        {
            high = mid - 1;
            printf("low %d mid %d high %d failed\n", low, mid, high);
        }
    }
    return result;
}

int main()
{
    // Reading input from standard input
    int nA, nX;
    scanf("%d %d", &nA, &nX);

    int *A = (int *)malloc(nA * sizeof(int));
    int *X = (int *)malloc(nX * sizeof(int));

    // Reading sequences A and X
    for (int i = 0; i < nA; i++)
    {
        scanf("%d", &A[i]);
    }

    for (int i = 0; i < nX; i++)
    {
        scanf("%d", &X[i]);
    }

    // Finding  the maximum interleave factor
    int maxInterleave = findMaxInterleave(A, nA, X, nX);

    // Printing the maximum interleave factor
    printf("Maximum repeats is %d\n", maxInterleave);

    // Freeing dynamically allocated memory
    free(A);
    free(X);

    return 0;
}
