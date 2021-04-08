package fr.cmuagab.millebornes.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Logger {
	
	private Scanner scanner;
	
	public Logger(InputStream in) {
		scanner = new Scanner(in);
	}
	
	public void print(String message) {
		System.out.print(message);
	}
	
	public void println(String message) {
		System.out.println(ConsoleColors.RESET + message + ConsoleColors.RESET);
	}
	
	public void info(String message) {
		println(ConsoleColors.RESET + "["+ConsoleColors.BLUE_BOLD+"INFO"+ConsoleColors.RESET+"] " + message);
	}

	public void warn(String message) {
		println(ConsoleColors.RESET + "["+ConsoleColors.YELLOW_BOLD+"WARN"+ConsoleColors.RESET+"] " + message);
	}
	
	public void line(String title) {
		println(ConsoleColors.WHITE_BOLD +"----------------< "+ConsoleColors.PURPLE_BOLD+title+ConsoleColors.WHITE_BOLD +" >----------------");
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
