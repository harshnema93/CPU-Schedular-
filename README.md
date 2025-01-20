CPU-Process-Scheduling
This Java project implements several popular CPU scheduling algorithms to simulate and compare the performance of different scheduling strategies. The algorithms included are First-Come, First-Served (FCFS), Shortest Job First (SJF), Shortest Remaining Time First (SRTF), Round Robin (RR), and Non preemptive Priority Scheduling. Each algorithm handles the scheduling of processes based on different principles, providing insight into their behavior and efficiency.

The application features an intuitive Graphical User Interface (GUI) built with Java Swing, enabling seamless input and interaction with CPU scheduling processes. Users can input essential details for each process. The interface displays a detailed table containing the Waiting Time and Turnaround Time for each process, allowing for easy comparison across processes. Additionally, it calculates and presents key performance metrics for each scheduling algorithm, including Throughput, CPU Idle Time, Average Waiting Time, and Average Turnaround Time.

Algorithms Implemented
First-Come, First-Served (FCFS): Processes are executed in the order they arrive, with no preemption.
Shortest Job First (SJF): The process with the shortest burst time is selected for execution next, minimizing waiting time.
Shortest Remaining Time First (SRTF): A preemptive version of SJF, where the process with the shortest remaining burst time is executed next.
Round Robin (RR): Each process is assigned a fixed time slice or quantum and is executed in a cyclic order.
Priority Scheduling: Processes are executed based on priority. The process with the highest priority is selected for execution first. If two processes have the same priority, the one that arrived first is executed first.
How to Use
Clone the repository:
git clone https://github.com/harshnema93/CPU-Schedular.git
Navigate to the project directory::
cd CPU-Process-Scheduling/src
 
Compile the Java files::
javac *.java
Run the program:
java CPU_Scheduler
