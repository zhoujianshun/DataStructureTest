package app;

import app.map.HashMap;
import app.map.LinkedHashMap;
import app.map.TreeMap;
import app.map.Map.Visitor;

public class App {
    public static void main(String[] args) throws Exception {
        test3();
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
        System.out.println("" + (p1.equals(p2) ? "true" : "false"));
        System.out.println("" + (p1.hashCode() == p2.hashCode() ? "true" : "false"));

        HashMap<Object, Integer> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put("aaa", 3);
        map.put("aaa", 5);
        map.put("rose", 15);
        map.put(null, 6);
        map.put(null, 7);

        System.out.println(map.size());
        // System.out.println("--------");
        // System.out.println(map.get("aaa"));
        // System.out.println(map.get("rose"));
        // System.out.println(map.get(p1));
        // System.out.println(map.get(null));
        System.out.println("--------");

        map.traversal(new Visitor<Object, Integer>() {

            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + " = " + value);
                return false;
            }
        });

    }

    public static void test3() {
        Person p1 = new Person(10, 1.67f, "jack");
        Person p2 = new Person(10, 1.67f, "jack");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println("" + (p1.equals(p2) ? "true" : "false"));
        System.out.println("" + (p1.hashCode() == p2.hashCode() ? "true" : "false"));

        LinkedHashMap<Object, Integer> map = new LinkedHashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put("aaa", 3);
        map.put("aaa", 5);
        map.put("rose", 15);
        map.put(null, 6);
        map.put(null, 7);

        System.out.println(map.size());
        // System.out.println("--------");
        // System.out.println(map.get("aaa"));
        // System.out.println(map.get("rose"));
        // System.out.println(map.get(p1));
        // System.out.println(map.get(null));
        System.out.println("--------");

        map.traversal(new Visitor<Object, Integer>() {

            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + " = " + value);
                return false;
            }
        });


        System.out.println("--------");
        map.remove(null);
        map.remove("aaa");
        map.traversal(new Visitor<Object, Integer>() {

            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + " = " + value);
                return false;
            }
        });

    }
}