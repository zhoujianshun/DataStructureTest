package app;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        Trie<Integer> trie = new Trie<>();

        trie.add("abc", 250);
        trie.add("abd", 251);

        trie.add("abcde", 251);

        System.out.println(trie.contains("abc")?"true":"false");
        System.out.println(trie.contains("abd")?"true":"false");
        System.out.println(trie.contains("abdec")?"true":"false");
        System.out.println(trie.startWith("ab")?"true":"false");

        System.out.println("--------");
        trie.remove("abc");
        System.out.println(trie.contains("abc")?"true":"false");
        System.out.println(trie.contains("abd")?"true":"false");
        System.out.println(trie.contains("abdec")?"true":"false");
        System.out.println(trie.startWith("ab")?"true":"false");

        System.out.println("--------");
        trie.remove("abcde");
        System.out.println(trie.contains("abcde")?"true":"false");
        System.out.println(trie.contains("abd")?"true":"false");
        System.out.println(trie.startWith("ab")?"true":"false");
        System.out.println(trie.startWith("abcd")?"true":"false");
    }
}