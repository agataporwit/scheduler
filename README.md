# FinalPt1
final project for Operating Systems Class

Multi-Level Feedback Queue

This is the final programming assignment for CSD 415 Fall 2020. You will implement a simulator for a Multi Level Feedback Queue scheduler. 

For a complete description of this type of scheduling algorithm, see section 3.3 of the course zybook. There are some differences detailed in the comments in the starter code. 

Starter code is provided in the form of a zipped netbeans project. You are free to use whatever IDE you prefer. The Java source files that make up the project are:

    LWTechOS.java – This class contains method main. It is the highest level module in the program. It simulates the part of a kernel that calls the scheduler.
    Pcb.java – This class encapsulates a process control block. It includes fields, and accessors to manage process context, previous state, process ID, parent process ID, and child process ID’s.
    ProcessTable.java – this class is a container class for the PCBs corresponding to the running processes on a system.
    ProcessTableException.java – this is an exception class for things that might go wrong when instantiating the process table. For one, the table is populated from a json file (e.g. the table might not be found. the contents may be invalid).
    Scheduler.java – This class abstracts the scheduling algorithm. It has entry points for handling the different reasons for interrupting a running process and replacing it with a ready process. Things like:
        a new process entering the system
        a process exhausting its time quantum
        a process blocked waiting on I/O
        a process suspended
        a terminated process
    ptable.json – This file contains data used to initialize PCB’s. When the program starts, it is parsed by the LWTechOS class and used to build the process table. 

You are free to modify any of the files listed above. However, most of your programming should be limited to Scheduler.java.

I will be testing your Scheduler class with my own implementation of the other files. So it is important that you don’t change the existing public interface to the Scheduler class, and if possible, don’t modify Scheduler.toString().

It’s OK to add public methods. But I will be testing your version of Scheduler.java with the public interface from the starter code.
Processing

The following are the public methods in Scheduler:

The state changing methods are:

    Scheduler.Schedule
    Scheduler.NewProcess
    Scheduler.Activate
    Scheduler.RequestResource
    Scheduler.ReleaseResource
    Scheduler.Suspend
    Scheduler.Reactivate
    Scheduler.Exit

These methods are described in detail in code comments in Scheduler.java. They affect the state changes described in participation activity 2.1.7 of the course zybook. 
Testing

Your Scheduler class will be tested by loading it into an existing project made up of the original versions of the starter code provided with this assignment:

    LWTechOS.java
    Pcb.java
    ProcessTable.java
    ProcessTableException.java

The resulting application will be run with a ptable.json files that provide an increasingly complicated mix of processes, and state transitions, all of which should result with all of the processes in the predictable state. The state transitions will be verified by inspection of the output from calls to Scheduler.toString.
What to Hand In

Submit your version of Scheduler.java through canvas by the due date.
