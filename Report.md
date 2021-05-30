# Cost-Efficient Resource Allocator for Distributed Systems 
Zoheb Eftali (45983569)
## Introduction (1/2 Page)
This stage of the Cost-Efficient Resource Allocator for Distributed Systems focuses on dispatching/scheduling jobs on the basis of a number of performance metrics/objectives, mainly turnaround time, resource utilisation and costs of execution. The goal of this stage is to design and implement a new scheduling algorithm that differs from the previous allToLargest algorithm so that it can perform better than the preset algorithms worst-fit, best-fit and first-fit. This new algorithm should also be optimised in one or more objectives: 
* Minimisation of average turnaround time 
* Maximisation of average resource utilisation 
* Minimisation of total server rental cost 

## Problem Definition (1/2 Page)
The baseline algorithms (WF, BF and FF) have not been optimised correctly and so an alternative algorithm is required to be made. This alternative algorithm will have conflicting optimisation as the optimisation on one objective could lead to sacrifising another. E.g. Minimising average turnaround time by minimising waiting time might end up with more resources and hence resulting in higher cost. For this, I have decided to optimise for rental cost as it can be somewhat forgiving on turnaround time and not the other way around. In essense, the newScheduler algorithm will optimise for total rental cost at the expense of turnaround time. 

## Algorithm Description (1 Page)
The purpose of the newScheduler algorithm is to complete all jobs as fast as possible with the least amount of wait time for each available job. This is achieved by querying through which jobs are best capable of being held by a server and if none are found then to continue onto the next job and repeat the process. We know that the rental cost of a server is determined by: 
* ((ENDTIME - STARTTIME)/3600) * WAITINGTIME 
The code structure is similar to that of turnaround time with ENDTIME - STARTTIME and so initially the algorithm was a modified version of First Fit that was optimised for turnaround time. This was changed to be better optimised for rental cost as the algorithm would reduce the ENDTIME and WAITINGTIME by checking if the server has the neccesary amount of resources available. For jobs that did not fit that criteria, the algorithm would not schedule that job, further reducing overall rental cost. 
### Psuedocode 
1. Empty string is made to hold server information 
2. For loop with server 
3.    Nested for loop with job 
4.        Finds server for job based on server's disk, cores, memory and job's startTime and                updates serverInfo with the server's type and ID. Finally schedules job with the job's ID         and serverInfo
5.        Else
6.            Jobs that are not able to be sent to a getsCapable list are deferred to the first                   server from that list
7. Returns the first job's ID along with the serverInfo

## Implementation Details (1/2 Page)

## Evaluation (2 Pages)

## Conclusion (1/4 Page)

## References (1/4 Page)
