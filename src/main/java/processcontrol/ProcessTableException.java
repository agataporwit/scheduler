
package main.java.processcontrol;

/**
 *
 * @author tomabot
 */
public class ProcessTableException extends Exception {
    public ProcessTableException(String msg) {
        super(msg);
    }
    public ProcessTableException() {
        System.out.println("Invalid Process Table");
    }
}
