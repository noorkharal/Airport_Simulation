package finalfinal;

public class Time {
	protected int hour;
	protected int min;
	protected int sec;
	
	public Time(int h, int m, int s) {
		if (h >= 0 && h < 24 && m >= 0 && m < 60 && s >= 0 && s < 60) {
            hour = h;
            min = m;
            sec = s;
        } else {
            throw new IllegalArgumentException("Invalid time parameters");
        }
	}
	
	public boolean compareTime(Time t) {
		
		if((hour==t.hour)&&(min==t.min)&&(sec==t.sec)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean greaterThan(Time t) {
		if(hour>t.hour) {
			return true;
		}else if((hour==t.hour)&&(min>t.min)){
			return true;
		}else if((hour==t.hour)&&(min==t.min)&&(sec>t.sec)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean lessThan(Time t) {
		if(hour<t.hour) {
			return true;
		}else if((hour==t.hour)&&(min<t.min)){
			return true;
		}else if((hour==t.hour)&&(min==t.min)&&(sec<t.sec)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Time subtractTime(int m) {
		Time ans = null;
		int ansM = min-m;
		if (ansM>=0) {
			ans = new Time(hour, ansM, sec);
		}else{
			ansM+=60;
			int ansH = hour-1;
			ans = new Time(ansH,ansM,sec);
		}
		return ans;
	}
	
	public Time addTime(int m) {
		Time ans = null;
		int ansM = min+m;
		if (ansM<60) {
			ans = new Time(hour, ansM, sec);
		}else{
			ansM-=60;
			int ansH = hour+1;
			ans = new Time(ansH,ansM,sec);
		}
		return ans;
	}
	
	public String getTime() {
		return String.format("%02d:%02d:%02d", hour, min, sec);
		
	}
}
