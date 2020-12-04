
package main.java.processcontrol;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author tomabot
 */
public class ProcessTable {

    List<Pcb> processList;

    public ProcessTable(String procTable) 
            throws FileNotFoundException, IOException, Exception {
        processList = new ArrayList<>();
        
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(procTable));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray processList = (JSONArray) jsonObject.get("processes");

            Iterator<JSONObject> it = processList.iterator();
            while (it.hasNext()) {
                //System.out.println(it.next());
                parseSingleElement(it.next());
            }
            
        } catch (FileNotFoundException fne) {
            System.out.println("...file not found exception...");
            System.out.println(fne.getMessage());
            fne.printStackTrace();
            throw fne;
            
        } catch (IOException ioe) {
            System.out.println("...ioexception caught...");
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
            throw ioe;
            
        } catch (Exception e) {
            System.out.println("...exception caught...");
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<Pcb> getProcessList() {
        return new ArrayList<Pcb> ( processList );
    }
    
    private void parseSingleElement(JSONObject jo) {
        Pcb pcb = new Pcb();
        pcb.pid = (long) jo.get("pid");
        pcb.ppid = (long) jo.get("ppid");
        
        pcb.cpid = new ArrayList<>();
        JSONArray cpidArray = (JSONArray) jo.get("cpid");
        Iterator it = cpidArray.iterator();
        while(it.hasNext()) {
            pcb.cpid.add((long) it.next());
        }
       
        pcb.runState = (String) jo.get("runState");
        pcb.cpuTime = (long) jo.get("cpuTime");
        pcb.timeInSystem = (long) jo.get("timeInSystem");
        
        JSONObject cpuStateObject = (JSONObject) jo.get("cpuState");
        pcb.r0 = (long) cpuStateObject.get("r0");
        pcb.r1 = (long) cpuStateObject.get("r1");
        pcb.r2 = (long) cpuStateObject.get("r2");
        pcb.r3 = (long) cpuStateObject.get("r3");
        pcb.pc = (long) cpuStateObject.get("pc");
        pcb.sp = (long) cpuStateObject.get("sp");
        
        pcb.priority = (long) jo.get("priority");
        
        processList.add(pcb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for( Pcb pcb : processList) {
            sb.append(pcb).append("\n");
        }
        return sb.toString();
    }
}
