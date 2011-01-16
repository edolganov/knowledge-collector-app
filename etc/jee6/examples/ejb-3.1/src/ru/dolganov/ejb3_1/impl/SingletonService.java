package ru.dolganov.ejb3_1.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

@Singleton
public class SingletonService {
	
	public String getInfo(){
		return toString();
	}
	
    @PostConstruct
    public void init() {
        System.out.println("Initialize singleton");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Shutting down singleton");
    }

}
