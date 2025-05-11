
/*
Name:Nabin Dhakal
ID:1002167771
Compilation Command: gcc binarySearch.c -o binary_search
binary_search < lab1(a,b,c,d).dat
Using Command Prompt to run the program
*/
#include <stdio.h>
#include <stdlib.h>

// Function to perform the binary search and to  find the rank
int binarySearch(int *a, int *b, int m, int n, int rank)
{
    // low and high are used to maintain the search range in the sequence
    int low = 0;
    int high = m;

    while (low <= high)
    {
        int i = (low + high) / 2;
        int j = rank - i;

        // Checking if the corresponding element is found
        if ((j == 0 || (j > 0 && b[j] < a[i])) && (j == n || a[i] <= b[j]))
        {
            // For Debugging output if corresponding element is found based on i and j
            printf("low=%d high=%d i=%d j=%d\n", low, high, i, j);
            return a[i];
        }
        else if (j < 0)
        {
            // Adjusting the search range for the first sequence
            high = i - 1;
        }
        else
        {
            // Adjusting the search range for the first sequence
            low = i + 1;
        }
    }
    // If the element not found
    return -1;
}

int main()
{
    // for reading the values m,n and p from the standard input
    int m, n, p;
    scanf("%d %d %d", &m, &n, &p);

    // Allocating memory for sequences a and b dynamically with additional space for sentinel values
    int *a = (int *)malloc((m + 2) * sizeof(int));
    int *b = (int *)malloc((n + 2) * sizeof(int));

    // Reading sequence a and initializing ranks for testing
    for (int i = 1; i <= m; i++)
    {
        scanf("%d", &a[i]);
    }

    // Reading sequence b
    for (int j = 1; j <= n; j++)
    {
        scanf("%d", &b[j]);
    }

    // Performing binary searches for each rank
    for (int r = 1; r <= p; r++)
    {
        int rank;
        scanf("%d", &rank);

        int element = binarySearch(a, b, m, n, rank);

        // Output the result
        printf("Rank %d: Element %d\n", rank, element);
    }

    // Freeing allocated memory
    free(a);
    free(b);

    return 0;
}
