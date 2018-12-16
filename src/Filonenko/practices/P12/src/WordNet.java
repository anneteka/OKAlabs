import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class WordNet {
    ArrayList<Synset> synsetArray;
    public WordNet(String synsets, String hyperonyms) {
        synsetArray = new ArrayList<Synset>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(synsets));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split(",");
            synsetArray.add(new Synset(Integer.parseInt(params[0]), params[1], params[2]));
        }
        try {
            scanner = new Scanner(new File(hyperonyms));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i=0;
        while(scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split(",");
            if(params.length > 0) {
                for(int j=1;j<params.length;j++) {
                    synsetArray.get(i).hyperonims.add( synsetArray.get( Integer.parseInt(params[j]) ) );
                }
            }
            i++;
        }
    }

    public Iterable<String> nouns() {
        ArrayList<String> nouns = new ArrayList<String>(synsetArray.size());
        for(Synset s: synsetArray) {
            nouns.add(s.noun);
        }
        return nouns;
    }
    public boolean contains(String word) {
        for(String noun: nouns()) {
            if(word.equals(noun)) return true;
        }
        return false;
    }

    public int distance(String word1, String word2) {
        Synset source=null, target=null;
        for(Synset s: synsetArray) {
            if(word1.equals(s.noun)) {
                source = s;
            }
            if(word2.equals(s.noun)) {
                target = s;
            }
        }
        LinkedList<SynsetVertex> queue = new LinkedList<SynsetVertex>();
        SynsetVertex[] vertices = new SynsetVertex[synsetArray.size()];
        for(int i=0;i<vertices.length;i++) {
            vertices[i] = new SynsetVertex(synsetArray.get(i));
        }
        queue.add(new SynsetVertex(source));

        while(!queue.isEmpty()) {
            SynsetVertex vertex = queue.removeFirst();
            for(Synset s: vertex.synset.hyperonims) {
                if(s.equals(target)) return vertices[s.id].distance;
                vertices[s.id].distance++;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        String fileName = "/Users/michaelfilonenko/Downloads/Practice12/synsets.txt";
        String filenameHyperonyms = "/Users/michaelfilonenko/Downloads/Practice12/hypernyms.txt";
        WordNet wordNet = new WordNet(fileName,  filenameHyperonyms);
        for(Synset s: wordNet.synsetArray) {
            System.out.println(s);
        }
    }
}
class SynsetVertex {
    Synset synset;
    int distance = 1;
    public SynsetVertex(Synset synset) {
        this.synset = synset;
    }
    public boolean equals(Object o) {
        SynsetVertex that = (SynsetVertex) o;
        return this.synset.equals(that.synset);
    }
}
class Synset {
    int id;
    String noun;
    String description;
    ArrayList<Synset> hyperonims;
    public Synset(int id, String noun, String description) {
        this.id = id;
        this.noun = noun;
        this.description = description;
        this.hyperonims = new ArrayList<Synset>();
    }
    public String toString() {
        StringBuilder builder = new StringBuilder(0);
        builder.append(id);
        builder.append(", ");
        builder.append(noun);
        builder.append(", ");
        builder.append(description);
        for(Synset s: hyperonims) {
            builder.append(" ");
            builder.append(s.id);
        }
        return builder.toString();
    }
    public boolean equals(Object o) {
        Synset that = (Synset) o;
        return this.id == that.id;
    }
}
