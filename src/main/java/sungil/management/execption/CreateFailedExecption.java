package sungil.management.execption;

public class CreateFailedExecption extends Exception{
    private static final long serialVersionUID = 1L;

    public CreateFailedExecption(){
        super("CREATE_FAILED");
    }

}
