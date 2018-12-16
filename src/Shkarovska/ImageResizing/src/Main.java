
public class Main {

	public static void main(String[] args) {
		Picture pict=new Picture("src/img.jpg");
		SCGraph c = new SCGraph(pict);
		pict.show();
		for(int i=0; i<100;i++) {
			c.next();
			pict=c.createPicture();
			
		}
		pict.show();
	}
}
