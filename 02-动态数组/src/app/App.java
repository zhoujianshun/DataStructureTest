package app;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < 22; i++) {
            array.add(i);
        }

        System.out.println(array);
        array.add(0, 20);
        array.add(3, 30);
        System.out.println(array);
        array.remove(3);
        array.remove(3);
        System.out.println(array);
        array.set(3, 80);
        System.out.println(array);
        array.add(3, null);
        System.out.println(array);
        System.out.println("index of null:"+ array.indexOf(null));

        ArrayList<Person> array2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Person p = new Person();
            p.name = "" + i;
            p.age = 20 + i;
            array2.add(p);
        }
        System.out.println(array2);
        array2.clear();
        System.out.println(array2);
        System.gc();
    }
}