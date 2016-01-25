package com.abd.mfc.jsf.model;

import java.util.Locale;

import com.abd.mfc.jsf.model.BaseMO;

public class LanguageMO extends BaseMO {

	private String label;
	private String classFlag;
	private Locale locale;
	private boolean arabic;
	
	public LanguageMO() {}
	
	public LanguageMO(String label, String classFlag, Locale locale) {
		this(label, classFlag, locale, false);
	}
	
	public LanguageMO(String label, String classFlag, Locale locale, boolean arabic) {
		super();
		this.label = label;
		this.classFlag = classFlag;
		this.locale = locale;
		this.arabic = arabic;
	}


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public String getClassFlag() {
		return classFlag;
	}


	public void setClassFlag(String classFlag) {
		this.classFlag = classFlag;
	}

	public boolean isArabic() {
		return arabic;
	}

	public void setArabic(boolean arabic) {
		this.arabic = arabic;
	}
	
	
}
