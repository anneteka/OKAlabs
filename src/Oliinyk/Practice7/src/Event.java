class Event implements Comparable<Event>{
	 double time; // time of event
	 Particle a, b; // particles involved in event
	private int countA, countB; // collision counts for a and b
		public Event(double t, Particle a, Particle b) { 
			this.a=a;
			this.b=b;
			this.time=t;
			if(a!=null)countA=a.getCount();
			else countA=-1;
			if(b!=null)countB=b.getCount();
			else countB=-1;
		}
		public int compareTo(Event that){
			return (int) (this.time-that.time); 
		}
		
		public boolean isValid(){
			if(a==null&& b==null) return true;
			if(a!=null && countA==a.getCount() ) {
	            if(b==null || (b!=null && countB==b.getCount())) return true;
	            else return false;
			} else if(a==null) {
				if(b!=null && countB==b.getCount()) return true;
				else return false;
			} else return false;
			/*
			if(a==null && b!=null) {
				if( b.count==countB) return true;
				else return false;
			}else if(b==null && a!=null) {
				if(a.count==countA) return true;
				else return false;
			} else if(a==null && b==null) return false;
			else {
				if(a.count==countA && b.count==countB) return true;
				else return false;
			}*/
		}
}

