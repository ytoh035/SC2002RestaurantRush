package core;

public class ActionResult {
    private boolean success;
    private String message;

    private ActionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ActionResult ok(String msg) {
        return new ActionResult(true, msg);
    }

    public static ActionResult fail(String reason) {
        return new ActionResult(false, reason);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}