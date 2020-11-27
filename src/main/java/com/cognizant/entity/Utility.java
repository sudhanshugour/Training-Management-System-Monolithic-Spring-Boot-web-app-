package com.cognizant.entity;

public class Utility {

	public static String CourseIdGenerator(String CName, long CId) {
		String a = "";
		String b = "";
		String c = "";
		try {
		CName = CName.trim();

		String CNameC[] = CName.split(" ");
		
		if (CNameC.length > 0)
			a = ((Character) (CNameC[0].charAt(0))).toString().trim().toUpperCase();

		if (CNameC.length > 1)
			b = ((Character) (CNameC[1].charAt(0))).toString().trim().toUpperCase();

		if (CNameC.length > 2)
			c = ((Character) (CNameC[2].charAt(0))).toString().trim().toUpperCase();
		}
		catch(Exception ex) {
			
		}
		
		return a + b + c + "_" + CId;
	}

}