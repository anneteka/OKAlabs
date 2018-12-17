import edu.princeton.cs.algs4.Picture;

public class PrintSeams {

    private static void printHorizontalSeam(SeamCarver sc)
    {
        double totalSeamEnergy = 0;

        int[] horizontalSeam = sc.findHorizontalSeam();
        for (int j = 0; j < sc.height(); j++)
        {
            for (int i = 0; i < sc.width(); i++)
            {
                char lMarker = ' ';
                char rMarker = ' ';
                if (j == horizontalSeam[i])
                {
                    lMarker = '[';
                    rMarker = ']';
                    totalSeamEnergy += sc.energy(i, j);
                }

                System.out.printf("%c%6.0f%c ", lMarker, sc.energy(i, j), rMarker);
            }
            System.out.println();
        }

        System.out.printf("\nTotal energy: %.0f\n\n", totalSeamEnergy);
    }


    private static void printVerticalSeam(SeamCarver sc)
    {
        double totalSeamEnergy = 0;

        int[] verticalSeam = sc.findVerticalSeam();
        for (int j = 0; j < sc.height(); j++)
        {
            for (int i = 0; i < sc.width(); i++)
            {
                char lMarker = ' ';
                char rMarker = ' ';
                if (i == verticalSeam[j])
                {
                    lMarker = '[';
                    rMarker = ']';
                    totalSeamEnergy += sc.energy(i, j);
                }

                System.out.printf("%c%6.0f%c ", lMarker, sc.energy(i, j), rMarker);
            }

            System.out.println();
        }

        System.out.printf("\nTotal energy: %.0f\n\n", totalSeamEnergy);
    }

    public static void main(String[] args)
    {
        Picture inputImg = new Picture(args[0]);
        System.out.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        //inputImg.show();
        SeamCarver sc = new SeamCarver(inputImg);

        System.out.printf("Displaying horizontal seam calculated.\n");
        printHorizontalSeam(sc);

        System.out.printf("Displaying vertical seam calculated.\n");
        printVerticalSeam(sc);


    }

}