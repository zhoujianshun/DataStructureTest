package app;

import java.util.HashMap;
import java.util.Map;

import app.map.TreeMap;
import app.map.Map.Visitor;

public class App {
    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test1() {

        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("class", 2);
        map.put("public", 11);
        map.put("class", 7);
        map.put("text", 6);
        map.put("void", 20);

        map.traversal(new Visitor<String, Integer>() {

            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + " = " + value);
                return false;
            }
        });
    }

    public static void test2() {
        Person p1 = new Person(10, 1.67f, "jack");
        Person p2 = new Person(10, 1.67f, "jack");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(""+(p1.equals(p2)?"true":"false"));
        System.out.println(""+(p1.hashCode() == p2.hashCode()?"true":"false"));
        

    }
}