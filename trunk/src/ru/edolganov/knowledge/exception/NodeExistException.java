package ru.edolganov.knowledge.exception;

public class NodeExistException extends RuntimeException{

	String name;
	
	public String getName() {
		return name;
	}

	public NodeExistException(String name) {
		super();
		this.name = name;
	}



}
