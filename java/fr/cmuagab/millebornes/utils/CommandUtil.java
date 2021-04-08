package fr.cmuagab.millebornes.utils;

import java.util.regex.Pattern;

public class CommandUtil {

	public static boolean isCommandWithArgs(String s) {
		return (Pattern.compile(".*\\(.*\\)").matcher(s).matches());
	}
	
	public static String getArg(String s) {
		if(isCommandWithArgs(s)) {
			return  replaceLast(s.split("\\(")[1], "\\)", "");
		}
		
		return null;
	}
	
	public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
	
	public static String[] getArgs(String s) {
		return getArgs(s, ",");
	}
	
	public static String[] getArgs(String s, String splitter) {
		// stack(0,0)
		
		if(isCommandWithArgs(s)) {
			String temp = getArg(s);
			
			if(temp.contains(splitter)) return temp.split(splitter);
			
			return new String[] {temp};
		}
		
		return new String[] {};
	}
	
	public static String clearString(String s) {
		while(s.contains(" ")) s.replace(" ", "");
		return s;
	}
	
}
