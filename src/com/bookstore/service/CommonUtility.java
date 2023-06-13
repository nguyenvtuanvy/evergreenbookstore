package com.bookstore.service;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

public class CommonUtility {
	public static void getCountryList(HttpServletRequest request) {
		String[] countryCodes = Locale.getISOCountries();
		
		Map<String, String> mapCountries = new TreeMap<>();
		
		for (String coutryCode : countryCodes) {
			Locale locale = new Locale("", coutryCode);
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();
			
			mapCountries.put(name, code);
		}
		
		request.setAttribute("mapCountries", mapCountries);
	}
}
