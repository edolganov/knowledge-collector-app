package ru.dolganov.ejb3_1.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import ru.dolganov.ejb3_1.EJBService;


@Stateless
@Local(EJBService.class)
public class StatlessEJBServiceImpl implements EJBService {

	@Override
	public String getInfo() {
		return toString();
	}

}
