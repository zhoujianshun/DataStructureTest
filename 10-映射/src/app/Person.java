package app;

/**
 * Person
 */
public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;

        Person person = (Person) obj;
        if (person.age == age && person.height == height
                && (person.name == null ? name == null : person.name.equals(name))) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(this.age);
        hashCode = hashCode * 31 + Float.hashCode(this.height);
        hashCode = hashCode * 31 + (name == null ? 0 : name.hashCode());
        return hashCode;
    }

}