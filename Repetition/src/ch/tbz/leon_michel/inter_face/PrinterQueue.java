package ch.tbz.leon_michel.inter_face;

public class PrinterQueue{
    private MyPrintJob[] jobs;
    int maxJobs = 2;

    public PrinterQueue(){
        this.jobs = new MyPrintJob[maxJobs];
        for (int i = 0; maxJobs > i; i++){
            this.jobs[i] = null;
        }
    }
    public void addJob(){
        int indexOfNextJob = 0;
        while (jobs[indexOfNextJob] != null && indexOfNextJob != 1){
            indexOfNextJob++;
        }
        if(indexOfNextJob < maxJobs){
            jobs[indexOfNextJob] = new MyPrintJob();
        }
    }
    public MyPrintJob nextJob(){
        int lastIndex = 0;
        if(jobs[0] == null){
            return null;
        }
        MyPrintJob job = jobs[0];
        for(int i = 0; maxJobs - 1 > i; i++){
            jobs[i] = jobs[i+1];
        }
        jobs[maxJobs - 1] = null;
        return job;
    }
}
