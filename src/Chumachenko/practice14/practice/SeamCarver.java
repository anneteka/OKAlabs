package practice;

import java.awt.Color;

import prinston.Picture;

public class SeamCarver {
	private Picture picture;
	private double[][] weightOfPixels;
	private Color[][] rgbOfPixels;
	
	
	 /** create a seam carver object based on the given picture*/
	public SeamCarver(Picture picture) {
	     this.picture = picture; 
	     convertToMagtrix();
	     computingWeightOfPixel();
	}

	/**Computing weight of each pixel*/
	private void computingWeightOfPixel() {
		int pW= picture.width();
		int pH = picture.height();
		
	
		for (int i = 0; i< pW; i++) {
			for (int j = 0; j< pH; j++){
				 weightOfPixels[i][j]=energy(i, j);		     
		     //     System.out.print(weightOfPixels[i][j]+" ");		
			}
			//System.out.println();
		}
	}
	
	/**Create picture in matrix form*/
	private void convertToMagtrix() {
		
		int pW= picture.width();
		int pH = picture.height();
		
		rgbOfPixels = new Color[pW][pH];
		for (int i = 0; i< pW; i++)
			for (int j = 0; j< pH; j++) {
				 rgbOfPixels[i][j] = picture.get(i, j);
			    
			}
		
	}
	/** current picture*/
	public Picture picture() {
		return null;

	} 

	public int width() {
		return 0;
	
	} // width of current picture

	public int height() {
		return 0;
	
	} // height of current picture

	public double energy(int x, int y) {
		if(x==0||x==picture.width()-1||y==0||y==picture.height()-1) 
		return 1000;
        double deltaX = 
Math.pow((rgbOfPixels[x-1][y].getRed()-rgbOfPixels[x+1][y].getRed()),2)+
Math.pow((rgbOfPixels[x-1][y].getBlue()-rgbOfPixels[x+1][y].getBlue()),2)+
Math.pow((rgbOfPixels[x-1][y].getGreen()-rgbOfPixels[x+1][y].getGreen()),2);
        double deltaY = 
Math.pow((rgbOfPixels[x][y-1].getRed()-rgbOfPixels[x][y+1].getRed()),2)+
Math.pow((rgbOfPixels[x][y-1].getBlue()-rgbOfPixels[x][y+1].getBlue()),2)+
Math.pow((rgbOfPixels[x][y-1].getGreen()-rgbOfPixels[x][y+1].getGreen()),2);
		return Math.sqrt(deltaY+deltaX);
		

	
	} // energy of pixel at column x and row y

	public int[] findVerticalSeam() {
		int[] tempArray =  new int[picture.height()];
		int[] minimumArray =  new int[picture.height()];
		double minimumWeight = Double.MAX_VALUE;
		double tempWeight =0;
		for(int i = 0; i< picture.width(); i++) {
			 int vertex = i;
			 tempArray[0] = i;
			 for(int j = 1; j<picture.height(); j++) {
		          int minVertex = findMinimum(vertex, j);
		          tempWeight+=weightOfPixels[minVertex][j];
		          tempArray[j] = minVertex;
		          vertex = minVertex;
			 }
			 if(tempWeight<minimumWeight) {
				 for(int g=0; g<tempArray.length; g++) {
					 minimumArray[g]=tempArray[g];
				 }
				 minimumWeight = tempWeight;
			 }
			 tempWeight = 0;
		}
		return minimumArray;
	
	} // sequence of indices for vertical seam

	private int findMinimum(int vertex, int j) {
	    int res = vertex;
	    if(vertex+1==picture.width()) {
	    	if(weightOfPixels[vertex][j]>weightOfPixels[vertex-1][j])
	    		return vertex-1;
	    	return vertex;	
	    }
	    if(vertex-1 < 0) {
	    	if(weightOfPixels[vertex][j]>weightOfPixels[vertex+1][j])
	    		return vertex+1;
	    	return vertex;	
	    }
	    if(weightOfPixels[vertex][j]>weightOfPixels[vertex-1][j])
	    	res = vertex-1;
	    if(weightOfPixels[res][j]>weightOfPixels[vertex+1][j])
    		res = vertex+1;
	    return res;	
	}

	public void removeVerticalSeam(int[] seam) {
	
	}// remove vertical seam from current picture
	public static void main(String args[]) {
	   Picture pic = new Picture("src/12x10.png");
	   SeamCarver sc = new SeamCarver(pic);
	  	int[] g =	sc.findVerticalSeam();
	  	for(int i = 0; i <pic.height(); i++) {
	  		for(int j = 0; j <pic.width(); j++)
	                 System.out.print(Math.round(sc.weightOfPixels[j][i])+" ");
	  		System.out.println();
	  	}
	  	for(int h: g) {
	  		System.out.print(h+" ");
	  	}
	}
	
	
}