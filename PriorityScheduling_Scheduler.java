import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

public class PriorityScheduling_Scheduler {
    private double throughput;
    private int cpuIdleTime;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public void schedule(List<Process> processes) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        int initialTime = processes.get(0).getArrivalTime();
        int currentTime = initialTime;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        cpuIdleTime = 0;

        // PriorityQueue to maintain ready processes sorted by priority
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator
            .comparingInt(Process::getPriority)
            .thenComparingInt(Process::getArrivalTime));

        int index = 0;
        
        while (index < processes.size() || !readyQueue.isEmpty()) {
            // Add all processes that have arrived up to the current time
            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                readyQueue.offer(processes.get(index));
                index++;
            }

            if (readyQueue.isEmpty()) {
                // If no process is ready, skip time to the next arriving process
                currentTime = processes.get(index).getArrivalTime();
            } else {
                Process currentProcess = readyQueue.poll();

                // Set waiting and turnaround time
                currentProcess.setWaitingTime(currentTime - currentProcess.getArrivalTime());
                currentProcess.setTurnaroundTime(currentProcess.getWaitingTime() + currentProcess.getBurstTime());

                totalWaitingTime += currentProcess.getWaitingTime();
                totalTurnaroundTime += currentProcess.getTurnaroundTime();

                // Execute the process
                currentTime += currentProcess.getBurstTime();
            }
        }

        averageWaitingTime = (double) totalWaitingTime / processes.size();
        averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();
        throughput = (double) processes.size() / (currentTime - initialTime); 
    }

    public double getThroughput() {
        return throughput;
    }

    public int getCpuIdleTime() {
        return cpuIdleTime;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }
}