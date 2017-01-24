package br.com.xavier.zipper.enums;

public enum ExecutionStrategy {
	
	//XXX ENUM MEMBERS
	EAGER,
	LAZY;
	
	//XXX STATIC METHODS
	public static boolean isLazy(ExecutionStrategy executionStrategy){
		return isEqual(LAZY, executionStrategy);
	}
	
	public static boolean isEager(ExecutionStrategy executionStrategy){
		return isEqual(EAGER, executionStrategy);
	}

	private static boolean isEqual(ExecutionStrategy execStr1, ExecutionStrategy execStr2) {
		if(execStr1 == null || execStr2 == null){
			return false;
		}
		
		return execStr1.equals(execStr2);
	}
	

}
