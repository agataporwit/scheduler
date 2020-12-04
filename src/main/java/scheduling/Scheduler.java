package main.java.scheduling;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.processcontrol.Pcb;
import main.java.processcontrol.ProcessTable;

/**
 *
 * @author <you>
 *
 * The Scheduler simulates a Multi-Level Feedback scheduling algorithm, with
 * some modifications due to the fact that you won't be using interrupts. 
 * Start by making sure you understand the description of a Multi-Level 
 * Feedback scheduling algorithm from section 3.3 of the course text book. 
 * 
 * For this simulation the passage of time is measured in clock ticks. 
 * Assume that every call to a state changing method occurs on a clock 
 * tick. A process resides in the running state for a single clock tick
 * and is then preempted.
 * 
 * Every process remains in the running state for a single clock tick. This
 * is different from the way the zybook describes the algorithm. When one of
 * the state changing methods is called, assume that a single clock tick has
 * elapsed. The zybook states that a process from the level N priority list 
 * executes in the running state for Q. When a process exceeds that time 
 * quantum, it moves to the next lower priority list and when it is scheduled
 * (i.e., moved to the run state), it executes for 2Q. If it exceeds 2Q, it 
 * them moves to the next lower priority list and executes for 4Q, and so on. 
 * 
 * For this algorithm, a process first enters the ready state in the highest 
 * priority list, priority N. When it is scheduled, it executes for a single
 * clock tick (a time quantum of Q). If it exceeds that amount of time, the 
 * next time it is preempted, it is moved to the N-1 priority list. 
 * 
 * Processes can be scheduled from the N-1 priority list at the most, 2 times.
 * The time quantum allowed in the running state before being preempted is 
 * still Q. But if after moving 2 times through the N-1 priority list the 
 * process is still not finished, it moves to the N-2 priority list. 
 * 
 * Processes from the N-2 priority list are limited to four transitions to
 * the running state. As before, the time in the running state is still Q. 
 * But this time, if after four trips to the running state it is still not 
 * finished, after being preempted the fourth time, it moves to the N-3 
 * priority list. 
 * 
 * Processes in the N-3 priority list remain at this level until they finish.
 * 
 */

public class Scheduler {

    // newProcess - waiting to enter the system
    private static List<Pcb> newProcess;

    // ready - waiting to run
    private static List<List<Pcb>> ready;

    // suspended - like when you're single stepping in a debug session
    private static List<Pcb> suspended;

    // terminated - waiting for parent to collect the exit status
    private static List<Pcb> terminated;

    // blocked - waiting for a resource
    // the key is the resource ID and the value is
    // a list of PCBs waiting for the resource
    private static Map<Integer, List<Pcb>> blocked;

    // running (scalar) - this is the process running on the cpu
    private static Pcb running;

    public Scheduler() {
        newProcess = new LinkedList<>();    // waiting to enter

        ready = new LinkedList<>();              // waiting to run
        for (int i = 0; i < 4; i++) {
            ready.add(new LinkedList<Pcb>());    // individual priority lists
        }

        blocked = new HashMap<>();         // waiting for I/O
        suspended = new LinkedList<>();     // single-stepping
        terminated = new LinkedList<>();    // waiting to exit 
    }

    ///////////////////////////////////////////////////////////
    //
    // state changing methods
    //
    ///////////////////////////////////////////////////////////
    
    public void NewProcess(Pcb pcb) {
        newProcess.add(pcb);
    }

    public void Activate(Pcb pcb) {
        // TODO - move process from the new state to the ready state
    }

    public void Schedule() {
        // TODO - move running process to the ready state, 
        // move a process from the ready state to running
    }

    public void RequestResource(int resourceId) {
        // TODO - move the running process to the blocked state,
        // move a ready process to the running state
    }

    public void ReleaseResource(int resourceId) {
        // TODO - choose a blocked process and move it to the ready state
    }

    public void Suspend(Pcb pcb) {
        // TODO - move the running process to the suspended state, 
        // move a ready process to the running state
    }

    public void Reactivate(Pcb pcb) {
        // TODO - move process from suspended to ready state
    }

    public void Exit() {
        // TODO - move the running process to the terminated state
    }

    ///////////////////////////////////////////////////////////
    //
    //    support methods
    //
    //        do not modify or remove these methods... 
    //        I need them to test your code
    //
    ///////////////////////////////////////////////////////////
    
    public static Pcb getRunning() {
        return running;
    }

    // return the number of Pcb's in the new process list
    public static int newProcessCt() {
        return newProcess.size();
    }

    // return the number of Pcb's in one row of the ready list
    public static int readyCt(int priority) {
        return ready.get(priority).size();
    }

    // return the number of Pcb's in the suspended list
    public static int suspendedCt() {
        return suspended.size();
    }

    // using the resourceID passed in as a parameter, return
    // the number of Pcb's in the blocked list that are waiting 
    // for the resource to become available
    public static int blockedCt(Integer resourceID) {
        return blocked.get(resourceID).size();
    }

    // return the number of Pcb's in the terminated list
    public static int terminatedCt() {
        return terminated.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (running != null) {
            sb.append("Running:").append(running.getPid()).append("\n");
        } else {
            sb.append("Running:null\n");
        }

        sb.append("NewProcess:").append(newProcess).append("\n");
        sb.append("Ready:").append(ready).append("\n");
        sb.append("Blocked:").append(blocked).append("\n");
        sb.append("Suspended:").append(suspended).append("\n");
        sb.append("Terminated:").append(terminated).append("\n");

        return sb.toString();
    }
}
