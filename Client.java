import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.*;

public class Client {
	//Initialize socket and input output streams
	private static Socket socket = null;
	private BufferedReader input = null;
	private DataOutputStream out = null;
	private BufferedReader in = null;
	
	//Constructor to put ip address and port
	public Client(String address, int port) throws IOException {
		connect(address, port);
		input = new BufferedReader(new InputStreamReader(System.in));
		out = new DataOutputStream(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	private void start () {
		boolean connected = false;
	
		sendMessage("HELO");
		readMessage();

		sendMessage("AUTH zoheb");
		readMessage();

		//Reads ds-system and populates it into array Server 't'
		ArrayList<Server> t = new ArrayList<Server>();

		//Array that holds job info from GETS_CAPABLE
		ArrayList<Job> jobs = new ArrayList<Job>();

		connected = true;

		sendMessage("REDY");

		String msg = readMessage();
		
		//Modified from Stage 1
		//Job scheduler 
		//When no servers are available that meet criteria from getsCapable method then send jobs with least number of waiting jobs.
		while (connected){
			if (msg.contains("JCPL")){ 
				sendMessage("REDY");
				msg = readMessage(); 
			} else if (msg.contains("NONE")){ 
				connected = false;			
				sendMessage("QUIT");
			} else {
				if (msg.contains("OK")){ 
					sendMessage("REDY");
					msg = readMessage(); 
				}

				//Create job based on incoming job 
				if (msg.contains("JOBN")){
					jobs.add(newJob(msg));

					//getCapable is called to determine available servers that are capable of scheduling job
					sendMessage(getsCapable(jobs.get(0))); 
					msg = readMessage();
					sendMessage("OK");

					//Capable servers are added to temporary arrayList 
					msg = readMessage();
					t = createServer(msg);
					sendMessage("OK");
					msg = readMessage();

					//Scheduling algorithm is called
					sendMessage(newScheduler(t, jobs));
					msg = readMessage();

					//Removes first element so that it will always be current job - decreases turnaround time 
					jobs.remove(0);
				} 
			} 
		}

		//Closes input, output and socket connection once QUIT message has been received; terminates connection
		try {
			if (readMessage().contains("QUIT")){
				input.close();
				out.close();
				socket.close();
			}
		} catch (IOException i) {
			System.out.println(i);
		}
		System.exit(1);
	}
	
	//New scheduling algorithm with respect to faster turnaround time 
	public String newScheduler(ArrayList<Server> servers, ArrayList<Job> j){
		String serverInfo = ""; 

		for (Server s: servers){
			if (s.getDisk() >= j.get(0).getDiskReq() && s.getCores() >= j.get(0).getCoreReq() && s.getMemory() >= j.get(0).getMemeoryReq()){
			 	serverInfo = s.getType() + " " + s.getID();
				return "SCHD " + j.get(0).getJobID() + " " + serverInfo;
			} else { //If no servers are optimal in GETS CAPABLE list then defer that job to first server
				serverInfo = servers.get(0).getType() + " " + servers.get(0).getID(); 
			}
		}
		return "SCHD " + j.get(0).getJobID() + " " + serverInfo;
	}

	//Grabs server input and creates an array list of servers capable of new scheduling algorithm 
	public ArrayList<Server> createServer(String server){
		//Removal of trailing space as this will cause an error if it is not removed 
		server = server.trim();

		//Temporary arraylist to be returned
		ArrayList<Server> newServers = new ArrayList<Server>();

		//For newline
		String[] lines = server.split("\\r?\\n");
 		
		for (String line : lines) {
			String[] splitString = line.split("\\s+");

			//Creates new server based on serverType, serverID, state, currentStartTime, coreCount, memory, disk, waitingJobs and runningJobs repsectively 
			Server s = new Server(splitString[0], Integer.parseInt(splitString[1]), splitString[2], Integer.parseInt(splitString[3]), Integer.parseInt(splitString[4]), 
			Integer.parseInt(splitString[5]), Integer.parseInt(splitString[6]), Integer.parseInt(splitString[7]), Integer.parseInt(splitString[8]));
			newServers.add(s);
        }

		return newServers;
	}



	//Grabs available servers 
	public String getsCapable(Job j){
		//Available servers based on core req, memory req and disk req respectively 
		return("GETS Capable " + j.getCoreReq() + " " + j.getMemeoryReq() + " " + j.getDiskReq());
	}

	
	//New job object
	public Job newJob(String job){
		//Removal of trailing space as this will cause an error if it is not removed
		job = job.trim();
		String[] splitString = job.split("\\s+");

		//Creates new job based on submit time, jobID, runtime, core req, memory req and disk req respectively 
		Job j = new Job(Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]), Integer.parseInt(splitString[3]),  Integer.parseInt(splitString[4]) ,Integer.parseInt(splitString[5]), Integer.parseInt(splitString[6]));

		//Returns job to fill arraylist
		return j;
	}

	//Send message to server
	private void sendMessage (String outStr) {
		byte[] byteMsg = outStr.getBytes();
		try {
			out.write(byteMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	//Read message from server
	private String readMessage () {
		String inStr = "";
		char[] cbuf = new char[65535]; 
		try {
			in.read(cbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		inStr = new String(cbuf, 0, cbuf.length);
		return inStr;
	}

	//Establishes connection to initiate handshake
	private static void connect(String address, int port) {
		double secondsToWait = 1;
		int tryNum = 1;
		while (true) {
			try {
				System.out.println("Connecting to server at: " + address + ":" + port);
				socket = new Socket(address, port);
				System.out.println("Connected");
				break; 
			} catch (IOException e) {
				secondsToWait = Math.min(30, Math.pow(2, tryNum));
				tryNum++;
				System.out.println("Connection timed out, retrying in  " + (int) secondsToWait + " seconds ...");
				try {
					TimeUnit.SECONDS.sleep((long) secondsToWait);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// Specify Server IP address and Port
		Client client = new Client("127.0.0.1", 50000);
		client.start();
	}
	
	public static ArrayList<Server> readXML(String fileName){
        ArrayList<Server> serverList = new ArrayList<Server>();
		
		try {
			//Read XML file
			File systemXML = new File(fileName);

			//Document Parser
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(systemXML);

			doc.getDocumentElement().normalize();
			NodeList servers = doc.getElementsByTagName("server");
			for (int i = 0; i < servers.getLength(); i++) {
				Element server = (Element) servers.item(i);


				String type = server.getAttribute("type");
				int limit = Integer.parseInt(server.getAttribute("limit"));
				int bootupTime = Integer.parseInt(server.getAttribute("bootupTime"));
				float hourlyRate = Float.parseFloat(server.getAttribute("hourlyRate"));
				int coreCount = Integer.parseInt(server.getAttribute("coreCount"));
				int memory = Integer.parseInt(server.getAttribute("memory"));
				int disk = Integer.parseInt(server.getAttribute("disk"));
				
				Server s = new Server(type,limit,bootupTime,hourlyRate,coreCount,memory,disk);
				serverList.add(s);
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return serverList;
    }
	
}
