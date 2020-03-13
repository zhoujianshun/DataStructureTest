package app;

import java.io.ObjectInputStream.GetField;

/**
 * Person
 */
public class Person {
    public String name;
    public int age;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
       
        return "{ name:" + name + ", age:" + age+" }";
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("Person finalize");
    }

}
