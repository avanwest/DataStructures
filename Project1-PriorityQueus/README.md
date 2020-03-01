### Testing Priority Queues

#### Overview
In this project we analyze three different implementations of priority queues using a double linked list, a
dynamic array, and a binary heap. Priority queues are a convenient abstract data structure in that they allow us
to simulate the collection and processing of data much like we might find in the real world; by priority rather
than next in line. For example, the way a hospital emergency room works. A bullet wound in the stomach is
more life-threatening than a sore throat, and therefore the priority of whom the doctors see must be adjusted
accordingly. Because the doctors must treat people by the severity of the emergency rather than first come/first
serve, a priority queue would meet the requirements as an abstract data structure.
The idea for this project is to benchmark each implementation using the same interface and to test how quickly
each handles three different operations: inserting a task, processing and returning the task with the highest
priority, and then a combination of both.
Tasks for our analysis were randomly generated (with randomized priorities) and run at intervals of 10,000.
