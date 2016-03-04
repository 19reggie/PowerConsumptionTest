package com.reggie19.testingPuremanager;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UIAutomatorUtils extends UiAutomatorTestCase {

	private static UiObject mUiObject;
	private static UiScrollable mUiScrollable;

	/**
	 * Open Application by appName
	 * 
	 * @param appName
	 */
	public void openApplication(String appName) {
		// getUiDevice().pressHome();
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
	 * click by element text
	 * 
	 * @param id
	 * @return
	 */
	public UiObject clickByText(String text) {
		mUiObject = new UiObject(new UiSelector().textMatches(text));
		try {
			mUiObject.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		return mUiObject;
	}
	
	/**
	 * click by ResourceId
	 * 
	 * @param id
	 * @return
	 */
	public UiObject clickByResourceId(String id) {
		mUiObject = new UiObject(new UiSelector().resourceId(id));
		try {
			mUiObject.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		return mUiObject;
	}


}
