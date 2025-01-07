import java.util.List;

public class SRTF_Scheduler {
    private double throughput;
    private int cpuIdleTime;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public void schedule(List<Process> processes) {
        int n = processes.size();
        int[] remainingTime = new int[n]; 
        int complete = 0, time = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finishTime;
        boolean foundProcess = false;
        int initialTime = processes.get(0).getArrivalTime();
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        cpuIdleTime = 0;

        for (int i = 0; i < n; i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
        }

        while (complete != n) {
        
            for (int i = 0; i < n; i++) {
                if ((processes.get(i).getArrivalTime() <= time) &&
                    (remainingTime[i] < minm) && remainingTime[i] > 0) {
                    minm = remainingTime[i];
                    shortest = i;
                    foundProcess = true;
                }
            }

            if (!foundProcess) {
                cpuIdleTime++; 
                time++; 
                continue;
            }

            remainingTime[shortest]--;
            minm = remainingTime[shortest];

            if (minm == 0) {
                minm = Integer.MAX_VALUE;
            }

            if (remainingTime[shortest] == 0) {
                complete++;
                foundProcess = false;

                finishTime = time + 1;
                processes.get(shortest).setFinishTime(finishTime);

                int waitingTime = finishTime - processes.get(shortest).getBurstTime() -
                                  processes.get(shortest).getArrivalTime();
                if (waitingTime < 0) {
                    waitingTime = 0;
                }
                processes.get(shortest).setWaitingTime(waitingTime);

                int turnaroundTime = processes.get(shortest).getBurstTime() + waitingTime;
                processes.get(shortest).setTurnaroundTime(turnaroundTime);

                totalWaitingTime += waitingTime;
                totalTurnaroundTime += turnaroundTime;
            }
            time++;
        }

        averageWaitingTime = (double) totalWaitingTime / n;
        averageTurnaroundTime = (double) totalTurnaroundTime / n;
        throughput = (double) n / (time - initialTime);
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