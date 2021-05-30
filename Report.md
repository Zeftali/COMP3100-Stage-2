# Cost-Efficient Resource Allocator for Distributed Systems 
Zoheb Eftali (45983569)
## Introduction (1/2 Page)
This stage of the Cost-Efficient Resource Allocator for Distributed Systems focuses on dispatching/scheduling jobs on the basis of a number of performance metrics/objectives, mainly turnaround time, resource utilisation and costs of execution. The goal of this stage is to design and implement a new scheduling algorithm that differs from the previous allToLargest algorithm so that it can perform better than the preset algorithms worst-fit, best-fit and first-fit. This new algorithm should also be optimised in one or more objectives: 
* Minimisation of average turnaround time 
* Maximisation of average resource utilisation 
* Minimisation of total server rental cost 

## Problem Definition 
The baseline algorithms (WF, BF and FF) have not been optimised correctly and so an alternative algorithm is required to be made. This alternative algorithm will have conflicting optimisation as the optimisation on one objective could lead to sacrifising another. E.g. Minimising average turnaround time by minimising waiting time might end up with more resources and hence resulting in higher cost. For this, I have decided to optimise for rental cost as it can be somewhat forgiving on turnaround time and not the other way around. In essense, the newScheduler algorithm will optimise for total rental cost at the expense of turnaround time. 

## Algorithm Description 
The purpose of the newScheduler algorithm is to complete all jobs as fast as possible with the least amount of wait time for each available job. This is achieved by querying through which jobs are best capable of being held by a server and if none are found then to continue onto the next job and repeat the process. We know that the rental cost of a server is determined by: 
* ((ENDTIME - STARTTIME)/3600) * WAITINGTIME 
The code structure is similar to that of turnaround time with ENDTIME - STARTTIME and so initially the algorithm was a modified version of First Fit that was optimised for turnaround time. This was changed to be better optimised for rental cost as the algorithm would reduce the ENDTIME and WAITINGTIME by checking if the server has the neccesary amount of resources available. For jobs that did not fit that criteria, the algorithm would not schedule that job, further reducing overall rental cost. 
### Psuedocode 
1. Empty string is made to hold server information 
2. For loop with server 
3. Nested for loop with job 
4. Finds server for job based on server disk space, core count, amount of memory and job's startTime and updates serverInfo with the server's type and ID. Finally schedules job with the job's ID and serverInfo
5. Else
6. Jobs that are not able to be sent to a getsCapable list are deferred to the first server from that list
7. Returns the first job's ID along with the serverInfo

## Implementation Details 
The newScheduler algorithm required modifications to the Client class and Server class as well as the introduction of a new Job class, which determines the job's startTime and runTime. The main data structure used was an ArrayList, which allowed the storing of job information and began scheduling on the basis of the criteria given. The newScheduler algorithm utilises two parameters that are of type ArrayList; ArrayList<Server> and ArrayList<Job>. ArrayList<Server> and ArrayList<Job> are used to cycle through each server and assign each job to a capable server based on the criteria written in the algorithm (server disk space, core count, amount of memory and the job's startTime). 
The Job class contains six fields of type integer: 
* startTime - Job's startTime
* jobID - ID of job
* runTime - Time for job to be completed
* coreReq - No. of CPU cores required 
* memoryReq - No. of memory required
* diskReq - No. of disk space required
The Server class introduces a new constructor which is used to create a new server object to be used as a temporary arraylist for all capable servers. This new constructor contains: 
* String type - Category of job 
* int id - ID of sErver 
* int coreCount - No. CPU cores in server
* int memory - No. of memory in server
* int disk - No. of disk space in server
  
## Evaluation 
The configuration files found in ds-sim’s ‘config’ folder and further in the ‘other’ folder (‘config/other’) along with the test script ‘PRACTICE test script’ as provided by the convenor of COMP3100, were used to test and determine the results of the new scheduling algorithm. To set up this test with these configuration files, the user must: 
1.	Move all necessary files including ds-system, ds-jobs, ds-server, ds-client and ds-system into the same directory. Ensure the ‘configs’ folder and ‘PRACTICE test script’ are also in the same directory as the other files.
2.	Open a terminal in that directory and run the test case via “./test_resultsv3 “java Client” -o co -c configs/other
Individual files could also be tested for further analysis by:
1.	Open a terminal in that directory and run ds-server via “./ds-server -c [config-file] -v all” 
2.	Open another terminal in that directory and run the client via “javac Client.java” to compile followed by “java Client” to run.
*/IMAGES OF RESULTS*/
From the results, you can see that the total rental cost for 9/18 or half of the files given have been reduced to the point where it is lower than all three of the baseline algorithms (BF, FF and WF). Furthermore, the overall average of the new algorithm is somewhat better than the baseline algorithms. Whilst it performs better than the pre-set algorithms, it does not perform better than the allToLargest (ATL) algorithm for any of the files or the overall average. 
The resource utilisation portion differs from the other two objectives as there is a combination of both great (green), mild (yellow) and bad (red) optimisation. The algorithm does not take into account any resource utilisation and so this is a result of the configuration made to rental cost. It is worth noting that the total average between the baseline algorithms puts the newScheduler algorithm as the second most optimised. 
Due to the algorithm’s optimisation in rental cost, this has caused turnaround time to be significantly worse than the baseline algorithms. However, the overall average makes the newScheduler come in as the third most optimised, beating worst-fit. Despite this, the turnaround time performs significantly better than ATL with a 98% improvement. 

 Pros	
1.	Reduces rental costs
2.	Beats ATL at turnaround time (requirement of the assessment) 
3.	Overall better performance in rental costs 
 Cons
1.	Slower turnaround time 
2.	It is not optimised for more than one objective
3.	Does not beat all configuration files even at total rental costs 

## Conclusion 
The newScheduler algorithm successfully does its job in increasing the optimisation of at least one objective. The total rental cost had been reduced in comparison to the three baseline algorithms. However, this has caused the turnaround time to perform significantly worse than the pre-set algorithms. Despite this, it beats the ATL significantly as a requirement of the assessment. More work could be done to improve the overall functionality of the algorithm and for it to increase the optimisation of more than one objective and/or to be able to allow for all files to be better than the baseline algorithms. In conclusion, the newScheduler algorithm performs slower than two out of the three pre-set algorithms but allows for cheaper rental costs and utilises an even amount of resource utilisation. Furthermore, the newly made algorithm beats ATL at turnaround time, making it an overall better performing algorithm in terms of total rental cost and somewhat in resource utilisation.
  
## References 
Ds-sim - https://github.com/distsys-MQ/ds-sim.git
Personal Git - https://github.com/Zeftali/COMP3100-Stage-2.git
