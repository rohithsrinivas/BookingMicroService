package com.rest.util;

import java.util.List;
import java.util.function.Predicate;

public class GenericUtils {
	
	public static Predicate<Object> objectIsNotNull = o ->  o!=null;
	
	public static Predicate<List<?>> listIsNotNullAndNonEmpty = 
		list -> list!=null && list.size()>0;

}
