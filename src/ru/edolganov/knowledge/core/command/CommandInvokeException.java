package ru.edolganov.knowledge.core.command;

public class CommandInvokeException extends RuntimeException {

	public CommandInvokeException() {
		super();
	}

	public CommandInvokeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandInvokeException(String message) {
		super(message);
	}

	public CommandInvokeException(Throwable cause) {
		super(cause);
	}

}
