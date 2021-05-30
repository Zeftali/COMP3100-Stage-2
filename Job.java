public class Job {
    
    private int startTime;
    private int  jobID;
    private int  runTime;
    private int  coreReq;
    private int  memoryReq;
    private int  diskReq;


    public Job(int startTime, int jID, int runTime, int coreRq, int memReq, int dkReq){
        this.startTime = startTime;
        this.jobID = jID;
        this.runTime = runTime;
        this.coreReq = coreRq;
        this.memoryReq = memReq;
        this.diskReq = dkReq;
    }

    //submitTime : returns submit time 
    public int getStartTime(){
        return this.startTime;
    }
    //jobID : returns the jobID
    public int getJobID(){
        return this.jobID;
    }
    //ruTime : returns runTime of server
    public int getRunTime(){
        return this.runTime;
    }
    //coreReq : returns the cpu core required for job to be added to server 
    public int getCoreReq(){
        return this.coreReq;
    }
    //memoryReq : returns the memory required for job to be added to server 
    public int getMemoryReq(){
        return this.memoryReq;
    }
    //diskReq : returns the memory required for job to be added to server 
    public int getDiskReq(){
        return this.diskReq;
    }
}
