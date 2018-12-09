import java.io.*;


public class PhoneNumbers {
    class PrefNode {
        PrefNode[] treeArray;
        boolean isLeaf;

        PrefNode() {
            this.treeArray = new PrefNode[10];
        }
    }

    PrefNode root;
    PhoneNumbers() {
        root = new PrefNode();
    }

    public boolean insert(String str) {
        PrefNode pn = root;
        char []strNumber = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            int index = strNumber[i] - '0'; // !!!!!!!!!!!!!!!!!!!!!!!!!
            if (pn.treeArray[index] == null) {
                PrefNode temp = new PrefNode(); // new node - like a level of the tree
                pn.treeArray[index] = temp;
                pn = temp;
            } else {
                if (i == str.length() - 1) return false; // 11 after 113
                pn = pn.treeArray[index]; //to the next level
                if (pn.isLeaf) return false; // 1113456613 after 113 is present
            }
        }

        pn.isLeaf = true;
        return true;
    }


    public static void main(String[] args) {
        BufferedReader bf;
        PrintWriter pw;
        PhoneNumbers n;

        try {

            bf = new BufferedReader(new FileReader("input.txt"));
            pw = new PrintWriter(new FileWriter("output.txt"));

            int examples = Integer.parseInt(bf.readLine());

            for (int i = 0; i < examples; i++) {
                n = new PhoneNumbers();
                long numbers = Integer.parseInt(bf.readLine());
                for (int j = 0; j < numbers; j++) {
                    if (!n.insert(bf.readLine())) {
                        //System.out.println("NO");
                        pw.println("NO");
                        for(int k = j+1; k < numbers; k++){
                            bf.readLine();
                        }
                        break;
                    } else if (j == numbers - 1) {
                        //System.out.println("YES");
                        pw.println("YES");
                        break;
                    }
                }
            }
            pw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}