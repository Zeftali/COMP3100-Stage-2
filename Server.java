public class Server {
	private String type;
	private int limit;
	private int bootTime;
	private float hourlyRate;
	private int coreCount;
	private int memory;
	private int disk;
	private int id;


	public Server(String type, int limit, int bootTime, float hourlyRate, int coreCount, int memory, int disk) {
		this.type = type;
		this.limit = limit;
		this.bootTime = bootTime;
		this.hourlyRate = hourlyRate;
		this.coreCount = coreCount;
		this.memory = memory;
		this.disk = disk;
	}

	public Server(String type, int id, int coreCount, int memory, int disk) {
		this.type = type;
		this.id = id;
		this.coreCount = coreCount;
		this.memory = memory;
		this.disk = disk;
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
		return this.limit; 
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

	public void printData() {
		System.out.println(this.type + " " + this.limit + " " + this.bootTime + " " + this.hourlyRate + " " + this.coreCount + " " + this.memory + " " + this.disk);
	}

}
