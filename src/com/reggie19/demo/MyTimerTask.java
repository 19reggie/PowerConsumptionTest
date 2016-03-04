package com.reggie19.demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	public static void main(String[] args) {
		Timer timer = new Timer();
		// delay为long，period为long：从现在起过delay毫秒以后，每隔period毫秒执行一次
		// 1秒后开始每个2秒钟执行一次TimerTaskTest中的run()方法
//		timer.schedule(new MyTimerTask(), 1000, 2000);
		timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 5000);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}

	int i = 0;

	@Override
	public void run() {
		// 每次需要执行的代码放到这里
		System.out.println(new Date());
		System.out.println("---TimerTask---" + i);
		System.out.println(new Date() + "\n");
		i++;
	}

}
