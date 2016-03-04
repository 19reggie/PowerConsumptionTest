package com.reggie19.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.reggie19.powerConsumption.PerformanceMethod;

import uiautomator1.helper.UIAutomatorHelper;

/**
 * Created on 2016/02/27
 * 
 * @author Reggie
 *
 */

public class WeiboRefreshDemo extends UiAutomatorTestCase {

	private static UiObject mUiObject;
	private static UiScrollable mUiScrollable;
	PerformanceMethod pc = new PerformanceMethod();

	public static void main(String[] args) {
		String jarName, testClass, testName, androidId;
		jarName = "WeiboRefresh0301.05";
		testClass = "com.reggie19.powerConsumption.WeiboRefreshDemo";
		testName = "testCase_00";
		androidId = "1";
		new UIAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase_00() throws FileNotFoundException {
		System.out.println("---***Start***---");
		System.out.println(formatDate2("yyyy-MM-dd HH:mm:ss"));

		Thread t0 = new Thread() {
			boolean run = true;
			@Override
			public void run() {
				File usedRAM = createFile("UsedRAM");
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(usedRAM);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				while (run) {
					try {
						String mUsedRAM = pc.get_UsedRAM();
						byte[] b = (mUsedRAM + "\n").getBytes();
						fos.write(b);
                        fos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					period_of_time(3);//间隔3s
				}
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			

		};
		t0.start();// 启动t0线程

		openApplication("微博");

		Timer timer0 = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				swipe_Downward();
//				System.out.println(formatDate2("HH:mm:ss"));

			}
		};
		// 在5min内每隔10s刷新1次
		timer0.schedule(timerTask, 0, 10000);// 间隔10s
		period_of_time(150);//间隔150s

		timer0.cancel();// 取消timer0定时器
		t0.interrupt();// 终止t0线程

		System.out.println(formatDate2("yyyy-MM-dd HH:mm:ss"));
		System.out.println("---***End***---");

	}

	public void swipe_Downward() {
		getUiDevice().swipe(get_DisplayWidth() / 2, get_DisplayHeight() / 3, get_DisplayWidth() / 2,
				2 * get_DisplayHeight() / 3, 10);
	}

	public int get_DisplayWidth() {
		return getUiDevice().getDisplayWidth();// 屏幕宽度
	}

	public int get_DisplayHeight() {
		return getUiDevice().getDisplayHeight();// 屏幕高度
	}
	
	/**
	 * Open Application by appName
	 * 
	 * @param appName
	 */
	public void openApplication(String appName) {
		getUiDevice().pressHome();
		mUiScrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		mUiScrollable.setAsHorizontalList();
		try {
			mUiObject = mUiScrollable.getChildByText(new UiSelector()
					.className(android.widget.TextView.class.getName()),
					appName);
			mUiObject.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void period_of_time(int t){
		try {
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 格式化时间
	 * 
	 * @return
	 */

	public String formatDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHms");
		String t = sdf.format(date);
		return t;
	}

	public String formatDate2(String str) {
		return new SimpleDateFormat(str).format(new Date());
	}

	public File createFile(String type) {
		String directory = "/storage/sdcard0/uiautomator/";// directory
		String file = directory + "/" + type + "_" + formatDate() + ".xls";// file
		File directoryPath = new File(directory);
		File mfile = new File(file);
		if (!directoryPath.exists() && !mfile.exists()) {
			directoryPath.mkdirs();
			try {
				mfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mfile;
	}

}
