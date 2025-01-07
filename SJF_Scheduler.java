import java.util.List;

public class SJF_Scheduler {
    private double throughput;
    private int cpuIdleTime;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public void schedule(List<Process> processes) {
        int currentTime = 0;
        int initialTime = processes.get(0).getArrivalTime();
        int completed = 0;
        int n = processes.size();
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        cpuIdleTime = 0;
        boolean[] isCompleted = new boolean[n];

        
        while (completed < n) {
            
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                Process process = processes.get(i);
                if (!isCompleted[i] && process.getArrivalTime() <= currentTime) {
                    if (process.getBurstTime() < minBurstTime) {
                        minBurstTime = process.getBurstTime();
                        idx = i;
                    } else if (process.getBurstTime() == minBurstTime
                            && process.getArrivalTime() < processes.get(idx).getArrivalTime()) {
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                cpuIdleTime++;
                currentTime++;
            } else {
                Process process = processes.get(idx);
                process.setWaitingTime(currentTime - process.getArrivalTime());
                process.setTurnaroundTime(process.getWaitingTime() + process.getBurstTime());

                totalWaitingTime += process.getWaitingTime();
                totalTurnaroundTime += process.getTurnaroundTime();

                isCompleted[idx] = true;
                completed++;

                currentTime += process.getBurstTime();
            }
        }

        averageWaitingTime = (double) totalWaitingTime / n;
        averageTurnaroundTime = (double) totalTurnaroundTime / n;
        throughput = (double) n / (currentTime - initialTime);
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