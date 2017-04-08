/**
 * Created by Nish on 05/04/2017.
 */
public class Main {
    public static void main(String[] args){
//        Gui g = new Gui();

        Trie t = new Trie();
        t.insert("atcg");
        t.insert("catc");
        t.insert("aaaa");
        t.insert("gggg");

        System.out.println(t.search("gggg"));
    }
}