/**
 * Created by Nish on 05/04/2017.
 */
public class Main {
    public static void main(String[] args){
//        Gui g = new Gui();

        Trie t = new Trie();
        t.insertWord("atcg");
        t.insertWord("catc");
        t.insertWord("aaaa");
        t.insertWord("gggg");

        System.out.println(t.searchWord("gggg"));
        System.out.println(t.searchWord("abcd"));
        System.out.println(t.startsWith("at"));
        System.out.println(t.startsWith("aa"));
        System.out.println(t.searchWord("tcg"));
    }
}