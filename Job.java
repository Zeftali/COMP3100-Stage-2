public class Job {
    
    private int submitTime;
    private int  jobID;
    private int  estRunTime;
    private int  coreReq;
    private int  memoryReq;
    private int  diskReq;


    public Job(int subTime, int jID, int runT, int coreRq, int memReq, int dkReq){
        this.submitTime = subTime;
        this.jobID = jID;
        this.estRunTime = runT;
        this.coreReq = coreRq;
        this.memoryReq = memReq;
        this.diskReq = dkReq;
    }

    //submitTime : returns submit time 
    public int getSubmitTime(){
        return this.submitTime;
    }
    //jobID : returns the jobID
    public int getJobID(){
        return this.jobID;
    }
    //ruTime : returns runTime of server
    public int getRunTime(){
        return this.estRunTime;
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
