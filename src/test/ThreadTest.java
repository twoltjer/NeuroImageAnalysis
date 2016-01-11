package test;

import Threads.GUIThread;

public class ThreadTest {
	   public static void main(String args[]) {
	   
	      GUIThread R1 = new GUIThread( "Thread-1");
	      R1.start();
	      
	      GUIThread R2 = new GUIThread( "Thread-2");
	      R2.start();
	   }   
	}