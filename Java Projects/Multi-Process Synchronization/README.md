# Multi-Process Synchronization

- This project demonstrates the producer-consumer problem of multiple processes in an Operating System. It implements a circular buffer to synchronize the producer and consumer processes.
- App.java [Contains the main class from which the program begins]
- Producer.java [Class for modeling producer processes]
- Consumer.java [Class for modeling consumer processes]
- CircularBuffer.java [Class for modeling a circular buffer that will be shared between the consumer and producer processes]
- output.txt [Sample output from one run of the program]
- Notes:
    - Command line arguments must be given as: [size of circular buffer] [maximum wait time in nanoseconds] [number of items to be produced/consumed] [number of producers] [number of consumers]
    - Execution begins from App.java
    - It is important to note that the maximum wait time must be given in nanoseconds