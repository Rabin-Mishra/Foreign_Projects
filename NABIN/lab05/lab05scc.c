#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_VERTICES 100
#define MAX_EDGES 100
#define MAX_NAME_LENGTH 25
#define TABLE_LOAD_FACTOR 0.5

struct Vertex {
    char name[MAX_NAME_LENGTH];
    int number;
};

struct Edge {
    int tail;
    int head;
};

struct DoubleHashTable {
    int size;
    struct Vertex *vertices;
    int *h1;
    int *h2;
    int *probe_count;
};

int is_prime(int n) {
    if (n <= 1) return 0; // 0 and 1 are not prime
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) return 0; // Not prime
    }
    return 1; // Prime
}

int next_prime(int n) {
    while (!is_prime(n)) {
        n++;
    }
    return n;
}

int compute_hash1(char *name, int table_size) {
    int hash = 0;
    for (int i = 0; name[i] != '\0'; i++) {
        hash = (hash * 31 + name[i]) % table_size;
    }
    return hash;
}

int compute_hash2(char *name, int table_size) {
    int hash = 0;
    for (int i = 0; name[i] != '\0'; i++) {
        hash = (hash * 17 + name[i]) % table_size;
    }
    return (hash == 0) ? 1 : hash; // Ensure it's not 0
}

void insert_into_double_hash(struct DoubleHashTable *hash_table, struct Vertex vertex) {
    int index1 = compute_hash1(vertex.name, hash_table->size);
    int index2 = compute_hash2(vertex.name, hash_table->size);

    while (hash_table->h1[index1] != -1 && strcmp(hash_table->vertices[hash_table->h1[index1]].name, vertex.name) != 0) {
        index1 = (index1 + index2) % hash_table->size;
        hash_table->probe_count[hash_table->h1[index1]]++;
    }

    hash_table->h1[index1] = vertex.number;
}

int search_in_double_hash(struct DoubleHashTable *hash_table, char *name) {
    int index1 = compute_hash1(name, hash_table->size);
    int index2 = compute_hash2(name, hash_table->size);
    int probes = 1;

    while (hash_table->h1[index1] != -1 && strcmp(hash_table->vertices[hash_table->h1[index1]].name, name) != 0) {
        index1 = (index1 + index2) % hash_table->size;
        probes++;
    }

    hash_table->probe_count[hash_table->h1[index1]] += probes;

    return (hash_table->h1[index1] != -1) ? hash_table->h1[index1] : -1;
}

void print_double_hash_table(struct DoubleHashTable *hash_table) {
    for (int i = 0; i < hash_table->size; i++) {
        if (hash_table->h1[i] != -1) {
            printf("%d %s\n", i, hash_table->vertices[hash_table->h1[i]].name);
        }
    }
}

void kosaraju_scc(int n, int m, struct Edge *edges, struct DoubleHashTable *hash_table) {
    // TODO: Implement Kosaraju's SCC algorithm
}

int main() {
    // Process lab5a.dat
    int n_a, m_a;
    scanf("%d %d", &n_a, &m_a);

    struct Edge edges_a[MAX_EDGES];
    struct DoubleHashTable hash_table_a;
    hash_table_a.size = next_prime((int)(n_a / TABLE_LOAD_FACTOR));
    hash_table_a.vertices = (struct Vertex *)malloc(MAX_VERTICES * sizeof(struct Vertex));
    hash_table_a.h1 = (int *)malloc(hash_table_a.size * sizeof(int));
    hash_table_a.h2 = (int *)malloc(hash_table_a.size * sizeof(int));
    hash_table_a.probe_count = (int *)malloc(MAX_VERTICES * sizeof(int));

    for (int i = 0; i < hash_table_a.size; i++) {
        hash_table_a.h1[i] = -1;
    }

    for (int i = 0; i < m_a; i++) {
        char tail[MAX_NAME_LENGTH], head[MAX_NAME_LENGTH];
        scanf("%s %s", tail, head);
        struct Vertex vertex_tail, vertex_head;
        strcpy(vertex_tail.name, tail);
        strcpy(vertex_head.name, head);
        vertex_tail.number = i * 2; // Even numbers for tail vertices
        vertex_head.number = i * 2 + 1; // Odd numbers for head vertices

        if (search_in_double_hash(&hash_table_a, tail) == -1) {
            insert_into_double_hash(&hash_table_a, vertex_tail);
        }

        if (search_in_double_hash(&hash_table_a, head) == -1) {
            insert_into_double_hash(&hash_table_a, vertex_head);
        }

        edges_a[i].tail = search_in_double_hash(&hash_table_a, tail);
        edges_a[i].head = search_in_double_hash(&hash_table_a, head);
    }

    // Perform Kosaraju's SCC algorithm for lab5a
    kosaraju_scc(n_a, m_a, edges_a, &hash_table_a);

    // Free allocated memory for lab5a
    free(hash_table_a.vertices);
    free(hash_table_a.h1);
    free(hash_table_a.h2);
    free(hash_table_a.probe_count);

    // Process lab5b.dat
    int n_b, m_b;
    scanf("%d %d", &n_b, &m_b);

    struct Edge edges_b[MAX_EDGES];
    struct DoubleHashTable hash_table_b;
    hash_table_b.size = next_prime((int)(n_b / TABLE_LOAD_FACTOR));
    hash_table_b.vertices = (struct Vertex *)malloc(MAX_VERTICES * sizeof(struct Vertex));
    hash_table_b.h1 = (int *)malloc(hash_table_b.size * sizeof(int));
    hash_table_b.h2 = (int *)malloc(hash_table_b.size * sizeof(int));
    hash_table_b.probe_count = (int *)malloc(MAX_VERTICES * sizeof(int));

    for (int i = 0; i < hash_table_b.size; i++) {
        hash_table_b.h1[i] = -1;
    }

    for (int i = 0; i < m_b; i++) {
        char tail[MAX_NAME_LENGTH], head[MAX_NAME_LENGTH];
        scanf("%s %s", tail, head);
        struct Vertex vertex_tail, vertex_head;
        strcpy(vertex_tail.name, tail);
        strcpy(vertex_head.name, head);
        vertex_tail.number = i * 2; // Even numbers for tail vertices
        vertex_head.number = i * 2 + 1; // Odd numbers for head vertices

        if (search_in_double_hash(&hash_table_b, tail) == -1) {
            insert_into_double_hash(&hash_table_b, vertex_tail);
        }

        if (search_in_double_hash(&hash_table_b, head) == -1) {
            insert_into_double_hash(&hash_table_b, vertex_head);
        }

        edges_b[i].tail = search_in_double_hash(&hash_table_b, tail);
        edges_b[i].head = search_in_double_hash(&hash_table_b, head);
    }

    // Perform Kosaraju's SCC algorithm for lab5b
    kosaraju_scc(n_b, m_b, edges_b, &hash_table_b);

    // Free allocated memory for lab5b
    free(hash_table_b.vertices);
    free(hash_table_b.h1);
    free(hash_table_b.h2);
    free(hash_table_b.probe_count);

    // Process lab5c.dat
    int n_c, m_c;
    scanf("%d %d", &n_c, &m_c);

    struct Edge edges_c[MAX_EDGES];
    struct DoubleHashTable hash_table_c;
    hash_table_c.size = next_prime((int)(n_c / TABLE_LOAD_FACTOR));
    hash_table_c.vertices = (struct Vertex *)malloc(MAX_VERTICES * sizeof(struct Vertex));
    hash_table_c.h1 = (int *)malloc(hash_table_c.size * sizeof(int));
    hash_table_c.h2 = (int *)malloc(hash_table_c.size * sizeof(int));
    hash_table_c.probe_count = (int *)malloc(MAX_VERTICES * sizeof(int));

    for (int i = 0; i < hash_table_c.size; i++) {
        hash_table_c.h1[i] = -1;
    }

    for (int i = 0; i < m_c; i++) {
        char tail[MAX_NAME_LENGTH], head[MAX_NAME_LENGTH];
        scanf("%s %s", tail, head);
        struct Vertex vertex_tail, vertex_head;
        strcpy(vertex_tail.name, tail);
        strcpy(vertex_head.name, head);
        vertex_tail.number = i * 2; // Even numbers for tail vertices
        vertex_head.number = i * 2 + 1; // Odd numbers for head vertices

        if (search_in_double_hash(&hash_table_c, tail) == -1) {
            insert_into_double_hash(&hash_table_c, vertex_tail);
        }

        if (search_in_double_hash(&hash_table_c, head) == -1) {
            insert_into_double_hash(&hash_table_c, vertex_head);
        }

        edges_c[i].tail = search_in_double_hash(&hash_table_c, tail);
        edges_c[i].head = search_in_double_hash(&hash_table_c, head);
    }

    // Perform Kosaraju's SCC algorithm for lab5c
    kosaraju_scc(n_c, m_c, edges_c, &hash_table_c);

    // Free allocated memory for lab5c
    free(hash_table_c.vertices);
    free(hash_table_c.h1);
    free(hash_table_c.h2);
    free(hash_table_c.probe_count);

    return 0;
}
