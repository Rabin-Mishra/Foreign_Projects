/*
Name: Nabin Dhakal
ID: 1002167771
Compilation Command: gcc fifo_queue.c -o simu1
simu1 < lab4(a,b).dat
Using Command Prompt to run the program
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// Node structure for the stack
typedef struct Node
{
    int data;
    struct Node *next;
} Node;

// Stack structure
typedef struct
{
    Node *top;
    Node *recycledNodes;
} Stack;

// Function prototypes
void initializeStack(Stack *stack);
void push(Stack *stack, int data);
int pop(Stack *stack);
int isStackEmpty(Stack *stack);

// Function to count nodes in the stack
int countNodes(Node **recycledNodes);

// FIFO Queue structure
typedef struct
{
    Stack inStack;
    Stack outStack;
    int totalMessageLength;
    int totalMessages;
    int minMessageLength;
    int maxMessageLength;
} Queue;

// Function prototypes for the queue
void initializeQueue(Queue *queue);
void enqueue(Queue *queue, int length);
int dequeue(Queue *queue);
void computeAverage(Queue *queue);
void updateMinMax(Queue *queue, int length);

int main()
{
    Queue myQueue;
    initializeQueue(&myQueue);

    int command, value;
    while (1)
    {
        scanf("%d", &command);

        switch (command)
        {
        case 0: // Exit
            printf("In Stack available nodes %d Out Stack available nodes %d\n",
                   countNodes(&myQueue.inStack.recycledNodes),
                   countNodes(&myQueue.outStack.recycledNodes));
            exit(0);
        case 1: // Enqueue
            scanf("%d", &value);
            enqueue(&myQueue, value);
            break;
        case 2: // Dequeue
            value = dequeue(&myQueue);
            if (value != -1)
            {
                printf("Dequeued %d\n", value);
            }
            break;
        case 3: // Compute average
            computeAverage(&myQueue);
            break;
        case 4: // Determine and print the minimum message length
            printf("minimum length %d\n", myQueue.minMessageLength);
            break;
        case 5: // Determine and print the maximum message length
            printf("maximum length %d\n", myQueue.maxMessageLength);
            break;
        default:
            break;
        }
    }

    return 0;
}

// Initialize a stack
void initializeStack(Stack *stack)
{
    stack->top = NULL;
    stack->recycledNodes = NULL;
}

// Push a value onto the stack
void push(Stack *stack, int data)
{
    Node *newNode;

    if (stack->recycledNodes != NULL)
    {
        newNode = stack->recycledNodes;
        stack->recycledNodes = stack->recycledNodes->next;
    }
    else
    {
        newNode = (Node *)malloc(sizeof(Node));
    }

    if (newNode == NULL)
    {
        fprintf(stderr, "Memory allocation error\n");
        exit(1);
    }

    newNode->data = data;
    newNode->next = stack->top;
    stack->top = newNode;
}

// Pop a value from the stack
int pop(Stack *stack)
{
    if (isStackEmpty(stack))
    {
        fprintf(stderr, "Error: Stack is empty\n");
        exit(1);
    }

    Node *temp = stack->top;
    int data = temp->data;
    stack->top = temp->next;

    // Recycle the node
    temp->next = stack->recycledNodes;
    stack->recycledNodes = temp;

    return data;
}

// Check if the stack is empty
int isStackEmpty(Stack *stack)
{
    return stack->top == NULL;
}

// Function to count nodes in the stack
int countNodes(Node **recycledNodes)
{
    int count = 0;
    Node *current = *recycledNodes;
    while (current != NULL)
    {
        count++;
        current = current->next;
    }
    return count;
}

// Initialize the queue
void initializeQueue(Queue *queue)
{
    initializeStack(&queue->inStack);
    initializeStack(&queue->outStack);
    queue->totalMessageLength = 0;
    queue->totalMessages = 0;
    queue->minMessageLength = INT_MAX;
    queue->maxMessageLength = INT_MIN;
}

// Enqueue a value into the queue
void enqueue(Queue *queue, int length)
{
    push(&queue->inStack, length);
    queue->totalMessageLength += length;
    queue->totalMessages++;
    updateMinMax(queue, length);
}

// Dequeue a value from the queue
int dequeue(Queue *queue)
{
    if (isStackEmpty(&queue->outStack))
    {
        // Transfer messages from inStack to outStack
        while (!isStackEmpty(&queue->inStack))
        {
            int value = pop(&queue->inStack);
            push(&queue->outStack, value);
        }
    }

    if (!isStackEmpty(&queue->outStack))
    {
        int value = pop(&queue->outStack);
        queue->totalMessageLength -= value;
        queue->totalMessages--;

        if (queue->totalMessages > 0)
        {
            // Update min and max only if the queue is not empty
            if (value == queue->minMessageLength || value == queue->maxMessageLength)
            {
                // Recalculate min and max if dequeued value is min or max
                queue->minMessageLength = INT_MAX;
                queue->maxMessageLength = INT_MIN;

                // Iterate through messages to find new min and max
                Stack tempStack;
                initializeStack(&tempStack);

                while (!isStackEmpty(&queue->outStack))
                {
                    int tempValue = pop(&queue->outStack);
                    updateMinMax(queue, tempValue);
                    push(&tempStack, tempValue);
                }

                while (!isStackEmpty(&tempStack))
                {
                    int tempValue = pop(&tempStack);
                    push(&queue->outStack, tempValue);
                }
            }
        }

        return value;
    }
    else
    {
        fprintf(stderr, "Error: Queue is empty\n");
        return -1;
    }
}

// Compute and print the average message length
void computeAverage(Queue *queue)
{
    if (queue->totalMessages > 0)
    {
        float average = (float)queue->totalMessageLength / queue->totalMessages;
        printf("average length %.6f\n", average);
    }
    else
    {
        fprintf(stderr, "Can't compute average for an empty queue\n");
    }
}

// Update min and max message lengths
void updateMinMax(Queue *queue, int length)
{
    if (length < queue->minMessageLength)
    {
        queue->minMessageLength = length;
    }
    if (length > queue->maxMessageLength)
    {
        queue->maxMessageLength = length;
    }
}
