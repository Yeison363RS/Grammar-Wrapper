package test;


public class testLogic {
    
    private String c;
    public void start(){
        System.out.println(c);
        c="";
    }
    public static void main(String[] args) {
       testLogic test=new testLogic();
       test.start();
    }
}
