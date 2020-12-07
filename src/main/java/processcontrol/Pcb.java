
package processcontrol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomabot
 * 
 *  pid, ppid, and cpid are process ID, parent process ID, 
 *  child process IDs (may be more than one).
 * 
 *  runState is the current state of the process
 * 
 *  cpuTime is the amount of time the process needs on the cpu (in the running 
 *  state) to complete its work. 
 * 
 *  timeInSystem is the total amount of time the process has spent in the 
 *  system, from the time it's entered into the new process list to the time
 *  it leaves the terminated list.
 * 
 *  r0-r3, sp, pc, and flags are the cpu register contents for the process
 *  at the time that it either entered the new process list, or left the 
 *  running state (its "context").
 * 
 *  priority is the current priority of the process. This should get set
 *  by the scheduler when the process leaves the run state. When the 
 *  process enters the system by being moved from the new process list to 
 *  the ready list, it should get set to 3, the highest priority. If/when
 *  it gets moved to the next lower priority ready list it should get
 *  decremented to 2. Similarly, when the process has spent enough time
 *  in the priority 2 list and is moved to the priority 1 list, it gets 
 *  decremented to 1.
 * 
 * 
 */
public class Pcb {

    long pid;        // process id
    long ppid;       // parent process id
    
    ArrayList<Long> cpid;     // child process ids

    String runState;    // run, ready, blocked, suspended, new, terminated
    long cpuTime;       // time in the run state
    long timeInSystem;  // total time in the system 

    long r0, r1, r2, r3, sp, pc, flags;  // cpu registers

    long priority;      // current priority

    public long getPid() {
        return pid;
    }

    public long getPpid() {
        return ppid;
    }

    public ArrayList<Long> getCpid() {
        List<Long> cpidCopy = new ArrayList<>(cpid);
        return cpid;
    }

    public String getRunState() {
        return runState;
    }

    public long getCpuTime() {
        return cpuTime;
    }

    public long getTimeInSystem() {
        return timeInSystem;
    }

    public long getR0() {
        return r0;
    }

    public long getR1() {
        return r1;
    }

    public long getR2() {
        return r2;
    }

    public long getR3() {
        return r3;
    }

    public long getSp() {
        return sp;
    }

    public long getPc() {
        return pc;
    }

    public long getFlags() {
        return flags;
    }

    public long getPriority() {
        return priority;
    }

    public void setCpid(ArrayList<Long> cpid) {
        this.cpid = cpid;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public void setCpuTime(long cpuTime) {
        this.cpuTime = cpuTime;
    }

    public void setTimeInSystem(long timeInSystem) {
        this.timeInSystem = timeInSystem;
    }

    public void setR0(long r0) {
        this.r0 = r0;
    }

    public void setR1(long r1) {
        this.r1 = r1;
    }

    public void setR2(long r2) {
        this.r2 = r2;
    }

    public void setR3(long r3) {
        this.r3 = r3;
    }

    public void setSp(long sp) {
        this.sp = sp;
    }

    public void setPc(long pc) {
        this.pc = pc;
    }

    public void setFlags(long flags) {
        this.flags = flags;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("pid:").append(pid).append(" ");
        sb.append("ppid:").append(ppid).append(" ");
        sb.append("cpid:").append(cpid).append(" ");
        sb.append("priority:").append(priority).append(" ");

        sb.append("runState:").append(runState).append(" ");
        sb.append("cpuTime:").append(cpuTime).append(" ");
        sb.append("timeInSystem:").append(timeInSystem);

        return sb.toString();
    }
}
