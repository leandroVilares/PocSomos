package com.somos.poc.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonPropertyOrder({"partition","component","action","instance","state","tempo"})

@JsonRootName(value = "row")
public class ListaSomos {
	String partition;
	String component;
	String action;
	String instance;
	String state;
	String tempo;
	
	@Override
	public String toString() {
		return "ListaSomos [partition=" + partition + ", component=" + component + ", action=" + action + ", instance="
				+ instance + ", state=" + state + ", tempo=" + tempo + "]";
	}
	public String getPartition() {
		return partition;
	}
	public void setPartition(String partition) {
		this.partition = partition;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	
	
	
	
}
