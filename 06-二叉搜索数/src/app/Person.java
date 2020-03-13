package app;

/**
 * Person
 */
public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person o) {
        if (this.age - o.age > 0) {
            return 1;
        } else if (this.age - o.age < 0) {
            return -1;
        }
        return 0;
    }


    @Override
    public String toString() {
       return this.name + ": "+this.age;
    }
}