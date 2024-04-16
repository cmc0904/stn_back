package sungil.management.execption;

public class UpdateFailedExecption extends Exception{
    private static final long serialVersionUID = 1L;

    public UpdateFailedExecption(){
        super("UPDATE_FAILED");
    }

}
