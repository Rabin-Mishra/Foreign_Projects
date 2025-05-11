/*
Name:Nabin Dhakal
ID:1002167771
Compilation Command:two_room_interval_scheduling.c -o interval_scheduling
interval_scheduling < lab3(a,b,c).dat
Using Command Prompt to run the program
*/
#include <stdio.h>

struct Interval {
    int start;
    int finish;
    int weight;
};

int max(int a, int b) {
    return (a > b) ? a : b;
}

void twoRoomScheduling(struct Interval intervals[], int n) {
    int DP[2][n + 1];
    int room1[n], room2[n];
    int maxWeight1 = 0, maxWeight2 = 0;

    for (int i = 0; i <= n; i++) {
        DP[0][i] = DP[1][i] = 0;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (intervals[i - 1].finish <= intervals[j - 1].start) {
                DP[0][i] = max(DP[0][i], DP[1][j] + intervals[i - 1].weight);
            }
            if (intervals[j - 1].finish <= intervals[i - 1].start) {
                DP[1][i] = max(DP[1][i], DP[0][j] + intervals[i - 1].weight);
            }
        }
    }

    int i = n, j = 0;
    while (i > 0) {
        if (DP[0][i] >= DP[1][i]) {
            room1[j] = i;
            maxWeight1 += intervals[i - 1].weight;
            j++;
        } else {
            room2[n - i] = i;
            maxWeight2 += intervals[i - 1].weight;
        }
        i--;
    }

    // Output in the specified format (with the placeholder line)
    printf("%d\n", j);
    for (int k = j - 1; k >= 0; k--) {
        int idx = room1[k] - 1;
        printf("%d %d %d\n", intervals[idx].start, intervals[idx].finish, intervals[idx].weight);
    }
    printf("<<< This line is to be replaced by your DP table(s) >>>\n");
    printf("%d\n", n - j);
    for (int k = 0; k < n - j; k++) {
        int idx = room2[k] - 1;
        printf("%d %d %d\n", intervals[idx].start, intervals[idx].finish, intervals[idx].weight);
    }
    printf("%d\n", max(maxWeight1, maxWeight2));
}

int main() {
    int n;
    scanf("%d", &n);
    struct Interval intervals[n];
    for (int i = 0; i < n; i++) {
        scanf("%d %d %d", &intervals[i].start, &intervals[i].finish, &intervals[i].weight);
    }
    twoRoomScheduling(intervals, n);
    return 0;
}
