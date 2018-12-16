
public class Outcast {
    private WordNet wordnet;

    public Outcast(WordNet wordnet){
        this.wordnet=wordnet;
    }
    public String outcast(String[] nouns){
        int n=nouns.length;
        int max=0,pos=0;
        int[] d=new int[n];
        for(int i=0;i<n;i++){
            int dist=0;
            for(int j=0;j<n;j++){
                if(i==j)
                    continue;
                int l=wordnet.distance(nouns[i],nouns[j]);
                if(l==-1){
                    dist+=999999;
                }
                else{
                    dist+=l;
                }
            }
            if(dist>max){
                max=dist;
                pos=i;
            }
        }
        return nouns[pos];
    }
    public static void main(String[] str) {
        WordNet wordnet = new WordNet(str[0], str[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < str.length; t++) {
            In in = new In(str[t]);
            String[] nouns = in.readAllStrings();
            System.out.println(str[t] + ": " + outcast.outcast(nouns));
        }
    }

}