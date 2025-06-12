package finalfinal;

public class Global_Clock implements Runnable {
	protected Time globalTime;
	public boolean isRunning;
    public Global_Clock(int hour, int minute, int second) {
        globalTime = new Time(hour, minute, second);
        isRunning = false;
    }

    public void setTime(int hour, int minute, int second) {
       globalTime = new Time(hour, minute, second);
    }

    public void startClock2() {
        isRunning = true;
        Thread taskQueue = new Thread(Main.mainTasks);
        taskQueue.start();
    }

    public void stopClock() {
        isRunning = false;
    }

    public void advanceTime() {
        globalTime.sec++;
        if (globalTime.sec >= 60) {
        	globalTime.sec = 0;
        	globalTime.min++;
            if (globalTime.min >= 60) {
            	globalTime.min = 0;
            	globalTime.hour = (globalTime.hour + 1) % 24;
            }
        }
    }

    public String getCurrentTime() {
        return String.format("%02d:%02d:%02d", globalTime.hour, globalTime.min, globalTime.sec);
    }
    
    public int getHour() {
    	return globalTime.hour;
    }

    public int getMinute() {
    	return globalTime.min;
    }
    public int getSeconds() {
    	return globalTime.sec;
    }
    
    public void run() {
    	while (isRunning) {
          advanceTime();
          try {
              Thread.sleep(1000); // Advance time every second
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
    	}    	
    }
}
