package com.tfk.main;

public class AtomicInteger {
	private int i;
	public AtomicInteger(int initialValue) {
		i = initialValue;
	}
	public int get(){
		return i;
	}
	public void set(int i){
		this.i = i;
	}
	public int getAndIncrement(){
		i++;
		return i-1;
	}
	public int incrementAndGet(){
		i++;
		return i;
	}
}
