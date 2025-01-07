public class Process {
    private int processID;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int turnaroundTime;
    private int finishTime;  
    private int priority;
    private int responseTime = -1;  
    private boolean isStarted;



    public Process(int processID, int arrivalTime, int burstTime, int priority) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.isStarted = false;


    }

    public int getProcessID() { return processID; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public int getFinishTime() { return finishTime; }  
    public int getPriority() {
        return priority;
    }
    public int getResponseTime() { return responseTime; } 
    public boolean isStarted() { return isStarted; }




    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
    public void setFinishTime(int finishTime) { this.finishTime = finishTime; }  
    public void setResponseTime(int responseTime) { this.responseTime = responseTime; } 
    public void setStarted(boolean started) { isStarted = started; }



    
 
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return "Process [ID=" + processID + ", Arrival Time=" + arrivalTime + ", Burst Time=" + burstTime +
                ", Waiting Time=" + waitingTime + ", Turnaround Time=" + turnaroundTime + "]";
    }
}

