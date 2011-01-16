package ru.dolganov.ejb3_1.impl;

import javax.ejb.Stateless;

@Stateless
public class SimpleEJBBean {
	
	public String getInfo(){
		return toString();
	}

}
