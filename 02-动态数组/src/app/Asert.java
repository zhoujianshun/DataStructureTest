package app;

/**
 * Asert
 */
public class Asert {

    public static void test(boolean value) {
       try {
        if(!value){
            throw new Exception("测试未通过");
        }
       } catch (Exception e) {
           //TODO: handle exception
       }
    }
}