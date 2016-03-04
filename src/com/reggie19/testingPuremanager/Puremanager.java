package com.reggie19.testingPuremanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import uiautomator1.helper.UIAutomatorHelper;

/**
 * Created on 2016/03/04
 * 
 * @author Reggie
 * 
 * 脚本使用方法：
 * ①每个测试场景，会循环操作500次，每次都会记录并保存当前被测应用的内存PSS值
 * ②每个测试场景，测试前后会分别生成一份XXX.hprof文件
 *
 */


public class Puremanager extends UiAutomatorTestCase {

	public static void main(String[] args) {
		String jarName, testClass, testName, androidId;
		jarName = "PuremanagerDL6.0";
		testClass = "com.reggie19.testingPuremanager.Puremanager";
		testName = "testCase_Main";
		androidId = "1";
		new UIAutomatorHelper(jarName, testClass, testName, androidId);
	}

	UIAutomatorUtils uu = new UIAutomatorUtils();
	private static int TESTCOUNT = 500;
	private static String PCKNAME = "com.aurora.puremanager";

	public void testCase_Main() throws IOException {
		testCase_TrafficRanking_0();
		testCase_TrafficRanking_1();
		testCase_AppManager_0();
		testCase_AppManager_1();
		// testCase_AppManager_2();
		// testCase_AppManager_3();
		// testCase_AppManager_4();
		// testCase_BatteryManager_0();
	}

	public void testCase_TrafficRanking_0() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("流量管理");
		uu.clickByText("流量排行");
		get_DumpHeapHprof(PCKNAME,"TrafficRanking_0");
		delay(3);
		File mmFile = createFile("TrafficRanking_0");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_left();
			delay(3);
			swipe_right();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);

		}
		get_DumpHeapHprof(PCKNAME,"TrafficRanking_0");
		delay(3);
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_TrafficRanking_1() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("流量管理");
		uu.clickByText("联网权限");
		delay(3);
		File mmFile = createFile("TrafficRanking_1");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_Upward();
			delay(3);
			swipe_Downward();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_AppManager_0() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("应用管理");
		uu.clickByResourceId("com.aurora.puremanager:id/app_num");
		delay(3);
		File mmFile = createFile("AppManager_0");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_left();
			delay(3);
			swipe_right();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_AppManager_1() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("应用管理");
		uu.clickByText("权限管理");
		delay(3);
		File mmFile = createFile("AppManager_1");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_Upward();
			delay(3);
			swipe_Downward();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_AppManager_2() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("应用管理");
		uu.clickByText("应用卸载");
		delay(3);
		File mmFile = createFile("AppManager_2");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_Upward();
			delay(3);
			swipe_Downward();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_AppManager_3() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("应用管理");
		uu.clickByText("应用冻结");
		delay(3);
		File mmFile = createFile("AppManager_3");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_Upward();
			delay(3);
			swipe_Downward();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_AppManager_4() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("应用管理");
		uu.clickByText("默认应用");
		delay(3);
		File mmFile = createFile("AppManager_4");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_Upward();
			delay(3);
			swipe_Downward();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	public void testCase_BatteryManager_0() throws IOException {
		uu.openApplication("纯净管家");
		uu.clickByText("省电管理");
		uu.clickByText("耗电排行");
		delay(3);
		File mmFile = createFile("BatteryManager_0");
		FileOutputStream fos = null;
		fos = new FileOutputStream(mmFile);
		for (int i = 0; i < TESTCOUNT; i++) {
			swipe_left();
			delay(3);
			swipe_right();
			String mPSS = getPss(PCKNAME);
			byte[] b = (mPSS + "\n").getBytes();
			fos.write(b);
			fos.flush();
			delay(3);
		}
		fos.close();
		stop_app(PCKNAME);
	}

	/**
	 * delay
	 * 
	 * @param t
	 */
	public void delay(int t) {
		try {
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	 * the method of swipe downward
	 */
	public void swipe_Downward() {
		getUiDevice().swipe(get_DisplayWidth() / 2, get_DisplayHeight() / 3, get_DisplayWidth() / 2,
				2 * get_DisplayHeight() / 3, 10);
	}

	/**
	 * the method of swipe upward
	 */
	public void swipe_Upward() {
		getUiDevice().swipe(get_DisplayWidth() / 2, 2 * get_DisplayHeight() / 3, get_DisplayWidth() / 2,
				get_DisplayHeight() / 3, 10);
	}

	/**
	 * the method of swipe left
	 */
	public void swipe_left() {
		getUiDevice().swipe(2 * get_DisplayWidth() / 3, get_DisplayHeight() / 2, get_DisplayWidth() / 3,
				get_DisplayHeight() / 2, 5);
	}

	/**
	 * the method of swipe right
	 */
	public void swipe_right() {
		getUiDevice().swipe(get_DisplayWidth() / 3, get_DisplayHeight() / 2, 2 * get_DisplayWidth() / 3,
				get_DisplayHeight() / 2, 5);
	}

	/**
	 * get PSS
	 * 
	 * @param packageName
	 * @return
	 */
	public String getPss(String packageName) {
		String command = "dumpsys meminfo " + packageName;
		String PSS = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("TOTAL")) {
					stringBuffer.append(line);
				}
			}
			String[] s = stringBuffer.toString().split("TOTAL");
			String s2 = s[1].trim();
			String[] s3 = s2.split("    ");
			PSS = s3[0].trim();
			System.out.println("---***Start***---");
			System.out.println(stringBuffer.toString());// 打印字符串
			System.out.println(PSS);
			System.out.println("---***end***---");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PSS;
	}

	/**
	 * stop app
	 * 
	 * @param packageName
	 */
	public void stop_app(String packageName) {
		String command = "am force-stop " + packageName;
		try {
			Process proc = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
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

	/**
	 * create directory
	 * 
	 * @return
	 */
	public File createDirectory() {
		String directory = "/storage/sdcard0/uiautomator/";// directory
		File mDirectory = new File(directory);
		if (!mDirectory.exists()) {
			mDirectory.mkdirs();
		}
		return mDirectory;
	}

	/**
	 * get DumpHeap
	 * @param packageName
	 * @param type
	 */
	public void get_DumpHeapHprof(String packageName,String type) {
		String hprofName = packageName.split("\\.")[2] + "_"+type+"_" + formatDate("yyyyMMddHms") + ".hprof";
		String command = "am dumpheap " + packageName + " " + createDirectory() + "/" + hprofName;
		command = command.replaceAll("\\\\", "/");
		// System.out.println(command);
		try {
			Process proc = Runtime.getRuntime().exec(command);
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
