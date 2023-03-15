package com.regex;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDto {	/**
	 * percent
	 */
	private double percent;

	/**
	 * process
	 */
	private String process;

	/**
	 * regex
	 */
	private String regex;

	/**
	 * regex_List
	 */
	private List<String> regexList = new ArrayList<String>();

	/**
	 * percentを取得します。
	 *
	 * @return percent
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * percentを設定します。
	 *
	 * @param percent percent
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

	/**
	 * processを取得します。
	 *
	 * @return process
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * processを設定します。
	 *
	 * @param process process
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * regexを取得します。
	 *
	 * @return regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * regexを設定します。
	 *
	 * @param regex regex
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * regex_Listを取得します。
	 *
	 * @return regex_List
	 */
	public List<String> getRegexList() {
		return regexList;
	}

	/**
	 * regex_Listを設定します。
	 *
	 * @param regexList regex_List
	 */
	public void setRegexList(List<String> regexList) {
		this.regexList = regexList;
	}
}
