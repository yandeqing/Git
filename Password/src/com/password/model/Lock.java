package com.password.model;

import com.password.constant.LockType;

public abstract class Lock {
	protected String patter;
	protected LockType lockType;
	

	
	
	public Lock(String patter, LockType lockType) {
		super();
		this.patter = patter;
		this.lockType = lockType;
	}

	public String getPatter() {
		return patter;
	}

	public void setPatter(String patter) {
		this.patter = patter;
	}

	public LockType getLockType() {
		return lockType;
	}

	public void setLockType(LockType lockType) {
		this.lockType = lockType;
	}
	
	
	
	
	
}
