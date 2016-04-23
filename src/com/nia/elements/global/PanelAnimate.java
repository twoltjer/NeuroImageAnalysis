package com.nia.elements.global;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelAnimate {

	public static final int RUN_TIME = 2000;
	public static final int FPS = 120;
	public static final int REFRESH_DELAY = 1000 /*millis in a second*/ / FPS;
	
	private JPanel panel;
	private Rectangle from;
	private Rectangle to;
	private Trigger completeTrigger;
	
	private long startTime;
	public boolean timerIsRunning;

	public PanelAnimate(JPanel panel, Rectangle from, Rectangle to, Trigger completeTrigger) {
		this.panel = panel;
		this.from = from;
		this.to = to;
		this.completeTrigger = completeTrigger;
	}

	public void start() {
		Timer timer = new Timer(REFRESH_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long duration = System.currentTimeMillis() - startTime;
				double progress = (double) duration / (double) RUN_TIME;
				if (progress > 1f) {
					progress = 1f;
					((Timer) e.getSource()).stop();
					completeTrigger.trigger();
				}
				Rectangle target = calculateProgress(from, to, progress);
				panel.setBounds(target);
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		startTime = System.currentTimeMillis();
		timer.start();
		
		
	}
	
	public static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

		Rectangle bounds = new Rectangle();

		if (startBounds != null && targetBounds != null) {

			bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
			bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

		}

		return bounds;

	}
	
	public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

		Point point = new Point();

		if (startPoint != null && targetPoint != null) {

			point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
			point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

		}

		return point;

	}

	public static int calculateProgress(int startValue, int endValue, double fraction) {

		int value = 0;
		int distance = endValue - startValue;
		value = (int) Math.round((double) distance * fraction);
		value += startValue;

		return value;

	}

	public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

		Dimension size = new Dimension();

		if (startSize != null && targetSize != null) {

			size.width = calculateProgress(startSize.width, targetSize.width, progress);
			size.height = calculateProgress(startSize.height, targetSize.height, progress);

		}

		return size;

	}
}