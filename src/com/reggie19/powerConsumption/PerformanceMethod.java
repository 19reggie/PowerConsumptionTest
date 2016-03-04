package com.reggie19.powerConsumption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceMethod implements PerformanceInter {

	@Override
	public String get_UsedRAM() {
		String command = "dumpsys meminfo";
		String usedRAM = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("Used")) {
					stringBuffer.append(line);
				}
			}
			String result = stringBuffer.toString();
			usedRAM = result.split(" ")[3];
//			System.out.println("Used RAM:" + usedRAM + "KB");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usedRAM;
	}

	@Override
	public String get_TotalRAM() {
		String command = "dumpsys meminfo";
		String totalRAM = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("Total")) {
					if (line.contains("RAM")) {
						stringBuffer.append(line);
					}
				}
			}
			String result = stringBuffer.toString();
			totalRAM = result.split(" ")[2];
//			System.out.println("Total RAM:" + totalRAM + "KB");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalRAM;

	}

	@Override
	public String get_UsedCpu() {
		String command = "dumpsys cpuinfo";
		String usedCpu = null;
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
			String result = stringBuffer.toString();
			usedCpu = result.split("TOTAL")[0].split("%")[0];
//			System.out.println("Used CPU:" + usedCpu + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usedCpu;

	}

	@Override
	public String get_CpuTemperature() {
		String command = "cat /sys/class/thermal/thermal_zone7/temp";
		String cpuTemp = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				// if (line.contains("TOTAL")) {
				stringBuffer.append(line);
				// }
			}
			String result = stringBuffer.toString();
			int result0 = Integer.parseInt(result)/1000;
			cpuTemp=String.valueOf(result0);
//			System.out.println("CPU Temperature:" + cpuTemp + "℃");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cpuTemp;


	}

	@Override
	public String get_BatteryLevel() {
		String command = "dumpsys battery";
		String batteryLevel = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.contains("level")) {
					stringBuffer.append(line);
				}
			}
			String result = stringBuffer.toString();
			batteryLevel = result.split(" ")[3];
//			System.out.println("Battery Level:"+batteryLevel+"%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return batteryLevel;
	}

	@Override
	public String get_BatteryTemperature() {
		String command = "cat /sys/class/thermal/thermal_zone6/temp";
		String batteryTemp = null;
		try {
			Process proc = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				// if (line.contains("TOTAL")) {
				stringBuffer.append(line);
				// }
			}
			String result = stringBuffer.toString();
			int result0 = Integer.parseInt(result)/1000;
			batteryTemp=String.valueOf(result0);
//			System.out.println("Battery Temperature:" + batteryTemp + "℃");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return batteryTemp;

	}

}
