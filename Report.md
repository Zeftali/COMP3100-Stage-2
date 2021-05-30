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
The purpose of the newScheduler algorithm is to complete all jobs as fast as possible with the least amount of wait time for each available job. 
## Implementation Details (1/2 Page)

## Evaluation (2 Pages)

## Conclusion (1/4 Page)

## References (1/4 Page)
