package test;

import threads.ThreadExample;

/**
 * An early and successful attempt at multithreading
 * 
 * @author twtduck
 * 
 */
public class ThreadTest {
	public static void main(String args[]) {

		ThreadExample R1 = new ThreadExample("Thread-1");
		R1.start();

		ThreadExample R2 = new ThreadExample("Thread-2");
		R2.start();
	}
}
