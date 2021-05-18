package com.selimsql.lesson.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static final String NEWLINE = System.getProperty("line.separator");


	public static int length(String st) {
		if (isEmpty(st))
			return 0;
		return st.length();
	}


	// ------------------------------------------------------
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static boolean isFull(String str) {
		return (isEmpty(str) == false);
	}


	public static boolean isEqual(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null)
			return true;
		if (obj1 == null || obj2 == null)
			return false;
		return obj1.equals(obj2);
	}


	public static boolean isNotEqual(Object obj1, Object obj2) {
		return (isEqual(obj1, obj2) == false);
	}


	public static long getLong(Object obj, long valueError) {
		if (obj == null)
			return valueError;
		try {
			if (obj instanceof Number)
				return ((Number) obj).longValue();

			if (obj instanceof String)
				return Long.parseLong((String) obj);

			return Long.parseLong(obj.toString());
		}
		catch (Exception err) {
			return valueError;
		}
	}

	public static long getLong(Object objSayi) {
		return getLong(objSayi, /* hataDurumunda */0);
	}

	public static int getInt(Object objSayi, int valueError) {
		return (int) getLong(objSayi, valueError);
	}

	public static int getInt(Object objSayi) {
		return getInt(objSayi, /* valueError */0);
	}



	public static String getErrorMessage(Exception err, int traceLevel) {
		if (err == null)
			return null;

		String errorMessage = null;
		if (err instanceof NullPointerException)
			errorMessage = "NPE";
		else if (err instanceof IndexOutOfBoundsException)
			errorMessage = "IOB";

		if (errorMessage != null) {
			if (traceLevel < 3)
				traceLevel = 3;
			errorMessage += ", " + getStackTraceInfo(err, traceLevel);
		}
		/*
		 * else if (err instanceof RuntimeException) { RuntimeException
		 * runtimeException = (RuntimeException)err; errorMessage =
		 * runtimeException.getMessage(); }
		 */
		else {
			errorMessage = err.getMessage(); //TODO? err.getCause()
			if (traceLevel > 0)
				errorMessage += getStackTraceInfo(err, traceLevel);

			Throwable errCause = err.getCause();
			if (errCause!=null && err!=errCause)
				errorMessage += ", Cause:" + errCause.getMessage();
		}
		return errorMessage;
	}// get_ErrorMessage

	public static String getErrorMessage(Exception err) {
		final short traceLevel = 0;
		return getErrorMessage(err, traceLevel);
	}


	public static String getStackTraceInfo(Throwable th, int level) {
		if (th == null)
			return null;

		StringBuilder sbuf = new StringBuilder();
		StackTraceElement[] trace = th.getStackTrace();
		int scanLevel = (trace == null ? 0 : trace.length);
		if (scanLevel > level)
			scanLevel = level;
		sbuf.append(NEWLINE + "ExceptionDesc:" + NEWLINE + th.toString());
		for(int i = 0; i < scanLevel; i++)
			sbuf.append(NEWLINE + " at " + trace[i]);
		// sb.append("\tat " + trace[i]);

		return sbuf.toString();
	}


    public static boolean isValidCharForEnMain(char ch) {
    	if (ch >= 'a' && ch <= 'z')
    		return true;

    	if (ch >= 'A' && ch <= 'Z')
    		return true;

    	if (ch >= '0' && ch <= '9')
    		return true;

    	//?
    	if (ch == '_')
    		return true;

		return false;
    }

	public static String trim(String st) {
		if (isEmpty(st))
			return st;
		return st.trim();
	}

    //In prm Locale!
    public static String codeFromStr(String str) {
		str = trim(str);
		int len = length(str);
		if (len == 0)
			return null;

		StringBuilder sb = new StringBuilder();
		for(int i=0; i < len; i++) {
			char ch = str.charAt(i);
			if (isValidCharForEnMain(ch)) {
				sb.append(ch);
			}
			else
			if (ch=='\u0131') {
				sb.append('i');
			}
			else
			if (ch=='\u0130') {
				sb.append('I');
			}
			else
			if (ch=='\u00e7') {
				sb.append('c');
			}
			else
			if (ch=='\u00c7') {
				sb.append('C');
			}
			else
			if (ch=='\u015f') {
				sb.append('s');
			}
			else
			if (ch=='\u015e') {
				sb.append('S');
			}
			else
			if (ch=='\u011f') {
				sb.append('g');
			}
			else
			if (ch=='\u011e') {
				sb.append('G');
			}
			else
			if (ch=='\u00fc') {
				sb.append('u');
			}
			else
			if (ch=='\u00dc') {
				sb.append('U');
			}
			else
			if (ch=='\u00f6') {
				sb.append('o');
			}
			else
			if (ch=='\u00d6') {
				sb.append('O');
			}
		}//for-i:len

		return sb.toString();
    }//code_FromStr

	//------------------------------------------------------------
	private static final String EMAIL_PATTERN_STR = "^(.+)@(.+)$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_STR);

	public static boolean isEMailValid(String email) {
		if (Util.isEmpty(email))
			return false;
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.find();
	}
}
