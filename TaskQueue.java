package finalfinal;

import java.util.ArrayList;
import java.util.Collections;

public class TaskQueue implements Runnable{
	protected ArrayList <Task> tasksList = new ArrayList<Task>();
	public TaskQueue(){
		
	}
	
	public void addTask(Task t) {
		tasksList.add(t);
		sortTask();
	}
	
	public void sortTask() {
		for (int i =0; i< tasksList.size(); i++) {
			for (int j = i+1; j<tasksList.size();j++ ) {
				if(!tasksList.get(j).taskExecution.greaterThan(tasksList.get(i).taskExecution)) {
					Collections.swap(tasksList, i, j);
				}
			}
		}
	}
	
	public void displayTasks() {
		for (int i =0; i< tasksList.size(); i++) {
			tasksList.get(i).display();
		}
	}
	
	public boolean checkTaskRun(int i) {
		if(!tasksList.isEmpty()) {
			if(i<tasksList.size()) {
				if(tasksList.get(i).taskExecution.compareTime(GUI.clock.globalTime)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void runTask() {
		
		if(checkTaskRun(0)) {
			//check how many tasks have the same time
			int count = 1;
			boolean check=false;
			while (!check) {
				if(checkTaskRun(count)) {
					count++;
				}else {
					check=true;
				}
			}
			System.out.println("Tasks simultaneously to be done: " + count);
			
			for(int i=0; i<count; i++) {
				ControlLogic temp = new ControlLogic();
				temp.setTask(tasksList.get(0));
				tasksList.remove(0);
				temp.runTask();
			}
//			Main.mainBrain.setTask(tasksList.get(0));
//			tasksList.remove(i);
		}
	}
	
	public StringBuilder tasksDisplayGUI() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i <tasksList.size();i++) {
			s.append(tasksList.get(i).tasksTranslation().toString());
		}
		return s;
	}
	
	public void run() {
		System.out.println("task thread is running");
		while (GUI.clock.isRunning) {
			runTask();
	          try {
	              Thread.sleep(1000); // Advance time every second
	          } catch (InterruptedException e) {
	              e.printStackTrace();
	          }
	    }    	
	}
}

