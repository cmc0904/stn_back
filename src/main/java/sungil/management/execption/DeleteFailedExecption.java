package sungil.management.execption;

public class DeleteFailedExecption extends Exception{
    private static final long serialVersionUID = 1L;

    public DeleteFailedExecption(){
        super("DELETE_FAILED");
    }

}
