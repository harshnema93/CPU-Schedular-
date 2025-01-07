import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class RR_Scheduler {
    private double throughput;
    private int cpuIdleTime;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public void schedule(List<Process> processes, int quantum) {
        int currentTime = 0;
        int initialTime = processes.get(0).getArrivalTime();
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        cpuIdleTime = 0;

        Queue<Process> queue = new LinkedList<>();
        int[] remainingBurstTime = new int[processes.size()];
        boolean[] isInQueue = new boolean[processes.size()];

        for (int i = 0; i < processes.size(); i++) {
            remainingBurstTime[i] = processes.get(i).getBurstTime();
            isInQueue[i] = false;
        }

        int index = 0;
        while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
            queue.add(processes.get(index));
            isInQueue[index] = true;
            index++;
        }

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            int i = processes.indexOf(currentProcess);

            if (currentTime == currentProcess.getArrivalTime()) {
                currentProcess.setWaitingTime(0);
            }

            int executeTime = Math.min(remainingBurstTime[i], quantum);
            currentTime += executeTime;
            remainingBurstTime[i] -= executeTime;

            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                if (!isInQueue[index]) {
                    queue.add(processes.get(index));  
                    isInQueue[index] = true;
                }
                index++;
            }

            if (remainingBurstTime[i] == 0) {
                currentProcess.setFinishTime(currentTime);

                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

                totalWaitingTime += currentProcess.getWaitingTime();
                totalTurnaroundTime += currentProcess.getTurnaroundTime();
            } else {
                queue.add(currentProcess);
            }

            if (queue.isEmpty() && index < processes.size()) {
                cpuIdleTime += processes.get(index).getArrivalTime() - currentTime;
                currentTime = processes.get(index).getArrivalTime();
                queue.add(processes.get(index));
                isInQueue[index] = true;
                index++;
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



