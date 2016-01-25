package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.jsf.model.LanguageMO;

@Controller
@Scope("session")
public class LocaleBean extends BaseBean {
	
	private LanguageMO language;
	private List<LanguageMO> listLang;
	private List<LanguageMO> listLangDisplay;
	private static ResourceBundle rb = ResourceBundle.getBundle("com.abd.mfc.i18n.messages");
    
    @PostConstruct
    public void init() {
    	
    	listLang = new ArrayList<LanguageMO>(5);
    	
    	listLang.add(new LanguageMO(rb.getString("langue_label_en"), "flag-gb", Locale.ENGLISH));
    	listLang.add(new LanguageMO(rb.getString("langue_label_fr"), "flag-fr", Locale.FRENCH));
    	listLang.add(new LanguageMO(rb.getString("langue_label_de"), "flag-de", Locale.GERMANY));
    	listLang.add(new LanguageMO(rb.getString("langue_label_es"), "flag-es", new Locale("es")));
    	listLang.add(new LanguageMO(rb.getString("langue_label_ar"), "flag-sa", new Locale("ar"), true));
    	
    	Locale locale = new Locale("ar");//FacesContext.getCurrentInstance().getViewRoot().getLocale();
    	initView(locale);
    	
    	
	}
    
    private void initView(Locale locale) {
    	listLangDisplay = new ArrayList<LanguageMO>(4);
    	
    	for (LanguageMO l : listLang) {
			if (locale.equals(l.getLocale())) {
				language = l;
			} else {
				listLangDisplay.add(l);
			}
		}
    }

	//value change event listener
		/*public void countryLocaleCodeChanged(ValueChangeEvent e){
	 
			String newLocaleValue = e.getNewValue().toString();
	 
			//loop country map to compare the locale code
	                for (Map.Entry<String, Object> entry : countries.entrySet()) {
	 
	        	   if(entry.getValue().toString().equals(newLocaleValue)){
	 
	        		FacesContext.getCurrentInstance()
	        			.getViewRoot().setLocale((Locale)entry.getValue());
	 
	        	  }
	               }
		}*/
		
		public String countryLocaleCodeChanged(LanguageMO e) {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(e.getLocale());
			language = e;
			
			initView(e.getLocale());
			
			return null;
		}

	public void setLanguage(LanguageMO language) {
		this.language = language;
	}

	public List<LanguageMO> getListLang() {
		return listLang;
	}

	public void setListLang(List<LanguageMO> listLang) {
		this.listLang = listLang;
	}


	public LanguageMO getLanguage() {
		return language;
	}


	public List<LanguageMO> getListLangDisplay() {
		return listLangDisplay;
	}


	public void setListLangDisplay(List<LanguageMO> listLangDisplay) {
		this.listLangDisplay = listLangDisplay;
	}

}
