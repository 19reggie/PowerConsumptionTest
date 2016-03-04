package com.reggie19.powerConsumption;

import java.io.File;
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

import uiautomator1.helper.UIAutomatorHelper;

/**
 * Created on 2016/03/01
 * 
 * @author Reggie
 * 
 * 脚本使用方法：
 * ①预置条件：在Android手机安装新浪微博客户端，登录账户密码
 * ②实现原理：基于UIAutomator框架编写脚本。封装openApplication()传入一个参数(应用名称)
 * 左右滑动桌面，通过应用名称查找到新浪微博客户端，并将之打开。
 * 创建一个定时器Timer，二个TimerTask，其中一个TimerTask控制刷新微博swipe_Downward()，另一个TimerTask
 * 记录在刷新微博过程中，整个Android手机的内存、CPU、CPU温度、电量等级以及电池温度变化情况，
 * 并将这些变化值保存在手机存储根目录下。
 * ③优缺点：脚本是基于UIAutomator框架进行编写的，在脚本的命令中引入--nohup参数，可以在跑脚本过程中，断开USB。
 * 便于功耗测试过程中由于连接USB不停地在给Android手机设备供电。缺点是，脚本是编译成jar包的形式在手机中运行的，
 * 会产生一定的影响。
 *
 */

public class WeiboRefresh extends UiAutomatorTestCase {

	private static UiObject mUiObject;
	private static UiScrollable mUiScrollable;
	PerformanceMethod pc = new PerformanceMethod();

	public static void main(String[] args) {
		String jarName, testClass, testName, androidId;
		jarName = "WeiboRefresh0301.1557";
		testClass = "com.reggie19.powerConsumption.WeiboRefresh";
		testName = "testCase_0";
		androidId = "1";
		new UIAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase_0() throws Exception {
		System.out.println("---***Start***---" + "\n" + formatDate("yyyy-MM-dd HH:mm:ss"));

		openApplication("微博");

		Timer timer = new Timer();
		TimerTask timerTask0 = new TimerTask() {
			int i = 0;

			@Override
			public void run() {
				swipe_Downward();
				System.out.println("第" + i + "次刷新微博");
				i++;
			}
		};

		TimerTask timerTask1 = new TimerTask() {
			File usedRAM = createFile("UsedRAM");
			File usedCPU = createFile("UsedCPU");
			File cpuTemp = createFile("CpuTemp");
			File batteryLevel = createFile("BatteryLevel");
			File batteryTemp = createFile("BatteryTemp");
			FileOutputStream fos_usedRAM = new FileOutputStream(usedRAM);
			FileOutputStream fos_usedCPU = new FileOutputStream(usedCPU);
			FileOutputStream fos_cpuTemp = new FileOutputStream(cpuTemp);
			FileOutputStream fos_batteryLevel = new FileOutputStream(batteryLevel);
			FileOutputStream fos_batteryTemp = new FileOutputStream(batteryTemp);
			int i = 0;

			@Override
			public void run() {
				String mUsedRAM = pc.get_UsedRAM();
				String mUsedCPU = pc.get_UsedCpu();
				String mCpuTemp = pc.get_CpuTemperature();
				String mBatteryLevel = pc.get_BatteryLevel();
				String mBatteryTemp = pc.get_BatteryTemperature();

				System.out.println("第" + i + "次的RAM取值为:" + mUsedRAM + "KB," + "CPU取值为:" + mUsedCPU + "%," + "CPU温度:"
						+ mCpuTemp + "℃," + "电量等级:" + mBatteryLevel + "%," + "电池温度:" + mBatteryTemp + "℃");

				byte[] b_mUsedRAM = (mUsedRAM + "\n").getBytes();
				byte[] b_mUsedCPU = (mUsedCPU + "\n").getBytes();
				byte[] b_mCpuTemp = (mCpuTemp + "\n").getBytes();
				byte[] b_mBatteryLevel = (mBatteryLevel + "\n").getBytes();
				byte[] b_mBatteryTemp = (mBatteryTemp + "\n").getBytes();

				try {
					fos_usedRAM.write(b_mUsedRAM);
					fos_usedCPU.write(b_mUsedCPU);
					fos_cpuTemp.write(b_mCpuTemp);
					fos_batteryLevel.write(b_mBatteryLevel);
					fos_batteryTemp.write(b_mBatteryTemp);

					fos_usedRAM.flush();
					fos_usedCPU.flush();
					fos_cpuTemp.flush();
					fos_batteryLevel.flush();
					fos_batteryTemp.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
			}
		};

		// 在2min内
		// 每隔6s刷新1次微博
		timer.schedule(timerTask0, 0, 6000);
		// 每隔3s获取一次RAM、CPU
		timer.schedule(timerTask1, 0, 3000);

		period_of_time(120);// 间隔120s
		timer.cancel();// 取消timer定时器

		System.out.println(formatDate("yyyy-MM-dd HH:mm:ss") + "\n" + "---***End***---");

	}

	/**
	 * the method of swipe downward
	 */
	public void swipe_Downward() {
		getUiDevice().swipe(get_DisplayWidth() / 2, get_DisplayHeight() / 3, get_DisplayWidth() / 2,
				2 * get_DisplayHeight() / 3, 10);
	}

	/**
	 * get display width
	 * 
	 * @return
	 */
	public int get_DisplayWidth() {
		return getUiDevice().getDisplayWidth();
	}

	/**
	 * get display height
	 * 
	 * @return
	 */
	public int get_DisplayHeight() {
		return getUiDevice().getDisplayHeight();
	}

	/**
	 * Open Application by appName
	 * 
	 * @param appName
	 */
	public void openApplication(String appName) {
		getUiDevice().pressHome();
		mUiScrollable = new UiScrollable(new UiSelector().scrollable(true));
		mUiScrollable.setAsHorizontalList();
		try {
			mUiObject = mUiScrollable
					.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), appName);
			mUiObject.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * period of time
	 * 
	 * @param t
	 */
	public void period_of_time(int t) {
		try {
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * format date
	 * 
	 * @param str
	 * @return
	 */
	public String formatDate(String str) {
		return new SimpleDateFormat(str).format(new Date());
	}

	/**
	 * create file
	 * 
	 * @param type
	 * @return
	 */
	public File createFile(String type) {
		String directory = "/storage/sdcard0/uiautomator/";// directory
		String file = directory + "/" + type + "_" + formatDate("yyyyMMddHms") + ".xls";// file
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
