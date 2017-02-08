package com.caps.main;

import java.util.LinkedList;

public class Queue {

	private LinkedList<GameObject> queue = new LinkedList<GameObject>();
	private LinkedList<Integer> queueTime = new LinkedList<Integer>();
	private Handler handler;
	public Queue(Handler handler){
		this.handler = handler;
	}
	public void tick(){
		
	}
	public void addItemToQueue(GameObject g, int time){
		queueTime.add(time);
		queue.add(g);
	}
	public GameObject getItemFromQueue(int posInQueue){
		return queue.get(posInQueue - 1);
	}
	public int getTimeFromQueue(int posInQueue){
		return queueTime.get(posInQueue - 1);
	}
	public void removeFromQueue(int posInQueue){
		queueTime.remove(posInQueue - 1);
		handler.addObject(queue.get(posInQueue-1));
		queue.remove(posInQueue - 1);
	}
	public void removeFromQueue(){
		queueTime.remove(0);
		handler.addObject(queue.get(0));
		queue.remove(0);
	}
	public int getPosInQueue(GameObject g){
		if(queue.contains(g)){
			int time = 0;
			for(int i = 0; i < queue.size(); i++){
				if(queue.get(i) == g){
					time = queueTime.get(i);
					break;
				}
			}
			return time;
		}else{
			return 0;
		}
		
	}
	public int getQueueSize(){
		return queue.size();
	}
	public int getFirstTime(){
		return queueTime.get(0);
	}
	public void setTime(int pos, int time){
		queueTime.set(pos-1, time);
	}
}
