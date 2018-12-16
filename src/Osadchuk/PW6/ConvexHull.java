package PW6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ConvexHull {
	
	private Poiint[] pon;
	
	public ConvexHull(ArrayList<Poiint> points)
	{
		pon = new Poiint[points.size()];
		for(int i=0; i<points.size(); ++i)
			pon[i] = points.get(i);
		
		Arrays.sort(pon);
		Arrays.sort(pon, 1, pon.length, pon[0].SLOPE_ORDER);
		
		
		ArrayList<Poiint> resP = new ArrayList<Poiint>();
		int n = pon.length;
        resP.add(pon[0]);
        resP.add(pon[1]);
        
        for (int i = 2; i < n; ++i) {
        	Poiint top = resP.remove(resP.size()-1);
        	
            while (Poiint.ccw(resP.get(resP.size()-1), top, pon[i]) < 0)
            	top = resP.remove(resP.size()-1);
            
            resP.add(top);
            resP.add(pon[i]);
        }
			
		for(int i=0; i<resP.size()-1;++i)
			ActionArea.addLine(new Line(resP.get(i).getX(), resP.get(i).getY(), resP.get(i+1).getX(), resP.get(i+1).getY()));
		ActionArea.addLine(new Line(resP.get(0).getX(), resP.get(0).getY(), resP.get(resP.size()-1).getX(), resP.get(resP.size()-1).getY()));
		
	}
	
}