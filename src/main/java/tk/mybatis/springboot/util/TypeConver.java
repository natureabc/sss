package tk.mybatis.springboot.util;

public class TypeConver {

	
	public static long objConverLong(Object obj) {
		return  Long.valueOf(String.valueOf(obj));
	}
	
	public static boolean objConverBoolean(Object obj) {
		return Boolean.valueOf(String.valueOf(obj));
	}
}
