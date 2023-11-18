package com.sapient.hiring.tech;

import java.util.HashMap;
import java.util.Map;

public class CustomCache<K, V> {
	private Map<K, V> map;
	private Map<Class, Class> typeMap;

    public CustomCache(){
    	this.map = new HashMap<>();
    	this.typeMap = new HashMap<>();
    	
    }

    public void put(K key, V value){
    	if( map.isEmpty()) {
    		map.put(key, value);
    		typeMap.put(key.getClass(), value.getClass());
    	}
    	else {
    		Class valueType = typeMap.get(key.getClass());
    		if(valueType == null) {
    			typeMap.put(key.getClass(), value.getClass());
    			map.put(key, value);
    		}
    		else {
    			Class valueToBeInserted = value.getClass();
    			Class supClass = valueToBeInserted.getSuperclass();
    			Class valTypeSupClass = valueType.getSuperclass();
    			
    			if(valueType.equals(valueToBeInserted) || valueType.equals(supClass) ||  valueToBeInserted.equals(valTypeSupClass)) {
    				map.put(key, value);
    			}
    			else {
    				if(valueType.equals(supClass)) {
    				throw new RuntimeException("Object of class ["+ valueToBeInserted +"] not allowable for this Key Type ["+key.getClass()+"]. " +
    	                    "Allowed types are ["+valueType +"] or it sub and super types");
    				}
    				else if(valueToBeInserted.equals(valTypeSupClass)) {
    					throw new RuntimeException("Object of class ["+ valueToBeInserted +"] not allowable for this Key Type ["+key.getClass()+"]. " +
        	                    "Allowed types are ["+valueToBeInserted +"] or it sub and super types");
    				}
    			}
    		}
    		
    		
    		
    	}
        //TODO implement this method
    }

    public boolean remove(K key){
    	if( map.get(key) != null) {
    		typeMap.remove(key.getClass());
    	    map.remove(key);
    	    return true;
    	}
        //TODO implement this method
        return false;
    }


    public V get(K key){
        //TODO implement this method
        return map.get(key);
    }


}