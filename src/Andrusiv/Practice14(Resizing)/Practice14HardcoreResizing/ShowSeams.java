import edu.princeton.cs.algs4.Picture;

public class ShowSeams {

    private static void showHorizontalSeam(SeamCarver sc)
    {
        Picture ep = SCUtility.toEnergyPicture(sc);
        int[] horizontalSeam = sc.findHorizontalSeam();
        Picture epOverlay = SCUtility.seamOverlay(ep, true, horizontalSeam);
        epOverlay.show();
    }


    private static void showVerticalSeam(SeamCarver sc)
    {
        Picture ep = SCUtility.toEnergyPicture(sc);
        int[] verticalSeam = sc.findVerticalSeam();
        Picture epOverlay = SCUtility.seamOverlay(ep, false, verticalSeam);
        epOverlay.show();
    }

    public static void main(String[] args)
    {
        Picture inputImg = new Picture(args[0]);
        System.out.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        inputImg.show();
        SeamCarver sc = new SeamCarver(inputImg);

        System.out.printf("Displaying horizontal seam calculated.\n");
        showHorizontalSeam(sc);

        System.out.printf("Displaying vertical seam calculated.\n");
        showVerticalSeam(sc);

    }

}