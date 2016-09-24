package test;

/**
 * Created by hukai on 2016/9/19.
 */
public class ExceptionTest {
    public static void main(String [] args){
//        try {
//            hehe("dd");
//        } catch (myException e) {
//            e.printStackTrace();
//        }

        privateTest privateTest = new privateTest();
        privateTest.secondMethod();
    }

    public static void hehe(String flag) throws myException{
        if(flag.equals("dd")){
            throw new myException(flag);
        }
    }
}

class myException extends Exception{
    public myException(String info){
        System.out.println("have a "+info);
    }
}

class privateTest{
    private void firstMethod(){
        System.out.println("first");
    }

    public void secondMethod(){
        firstMethod();
        System.out.println("second");
    }
}