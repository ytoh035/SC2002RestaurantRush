package util;

public class Money {
    
    // Converts a double like 9.00 to the integer 900
    public static int fromDollars(double dollars) {
        // Math.round is critical here to prevent 9.00 * 100 from becoming 899.999999999
        return (int) Math.round(dollars * 100);
    }
    
    // Converts the integer 900 back to a formatted "$9.00" string for the UI
    public static String format(int cents) {
        return String.format("$%.2f", cents / 100.0);
    }
}