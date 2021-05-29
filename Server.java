public class Server {
	private String type;
	private int limit;
	private int bootTime;
	private float hourlyRate;
	private int coreCount;
	private int memory;
	private int disk;
	private int id;
	private String status;
	private int startTime;
	private int waitingJobs;
	private int runningJobs;

	public Server(String type, int limit, int bootTime, float hourlyRate, int coreCount, int memory, int disk) {
		this.type = type;
		this.limit = limit;
		this.bootTime = bootTime;
		this.hourlyRate = hourlyRate;
		this.coreCount = coreCount;
		this.memory = memory;
		this.disk = disk;
	}

	public Server(String type, int id, String state, int startTime, int coreCount, int memory, int disk, int waitingJobs, int runningJobs) {
		this.type = type;
		this.id = id;
		this.status = state;
		this.startTime = startTime;
		this.coreCount = coreCount;
		this.memory = memory;
		this.disk = disk;
		this.waitingJobs = waitingJobs;
		this.runningJobs = runningJobs;

	}

	//id : ID of server
	public int getID() {
		return this.id;
	}

	//type : category of job
	public String getType() {
		return this.type;
	}

	//limit : limit of servers of unique type
	public int getLimit() {
		return this.limit; // the number of servers of a particular type
	}

	//bootupTime : the amount of time taken to boot a server of particular type
	public int getBootupTime() {
		return this.bootTime;
	}

	//
	public Float getHourlyRate() {
		return this.hourlyRate;
	}

	//core : CPU cores
	public int getCores() {
		return this.coreCount;
	}

	//memory : RAM
	public int getMemory() {
		return this.memory;
	}

	//disk : disk space
	public int getDisk() {
		return this.disk;
	}

	// returns an int for wait time for server
	public int getWaitJob() {
		return this.waitingJobs;
	}

	//state : returns status of server 
	public String getState() {
		return this.status;
	}

	//startTime : startTime of server 
	public int getStartTime() {
		return this.startTime;
	}

	//runTime : runTime of server 
	public int getRunTime() {
		return this.runningJobs;
	}

	public void printData() {
		System.out.println(this.type + " " + this.limit + " " + this.bootTime + " " + this.hourlyRate + " " + this.coreCount + " " + this.memory + " " + this.disk);
	}

}
