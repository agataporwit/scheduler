package os;

import java.util.logging.Level;
import java.util.logging.Logger;
import processcontrol.Pcb;
import processcontrol.ProcessTable;
import processcontrol.ProcessTableException;
import scheduling.Scheduler;

/**
 *
 * @author tomabot
 */
public class LWTechOS {

    static ProcessTable processTbl;
    static Scheduler scheduler;

    public static void main(String[] args) {

        try {
            // instantiate the process table and load
            // the json file of processes into it
            processTbl = new ProcessTable("ptable.json");
            System.out.println(processTbl);

            // instantiate the scheduler and load the
            // processes from the process table into it
            scheduler = new Scheduler();
            loadNewProcesses(processTbl);
            System.out.println(scheduler);

            // activate the processes by moving
            // them into the ready queue
            
            
            // 
            
        } catch (ProcessTableException pte) {
            System.out.println(pte.getMessage());
            System.exit(-1);

        } catch (Exception ex) {
            Logger.getLogger(LWTechOS.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

        System.out.println("...fini...");
    }

    private static void loadNewProcesses(ProcessTable ptbl) {
        for(Pcb pcb : ptbl.getProcessList()) {
            scheduler.NewProcess(pcb);
        }
    }
}
