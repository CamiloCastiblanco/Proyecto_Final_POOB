package presentacion;

public class POOBTrizException extends Exception {
	
    public final static String ERROR_NAME_NULL = "The player's name cannot be empty.";
    public final static String ERROR_SPEED_ZERO = "The desired speed cannot be zero.";

    public POOBTrizException(String exception) {
        super(exception);
    }
}