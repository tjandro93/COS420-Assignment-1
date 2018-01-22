/*
 * CItem.java
 *
 * Created on August 28, 2001, 11:46 AM
 */

package edu.usm.cos420.assignment1.domain;

import java.io.Serializable;


/**
 *
 *  For the purposes of this example, CItem holds three 
 *  piece of data. The class implements the interface 
 *  Serializable so that we can store and retrieve the 
 *  information in this class 
 * 
 */
public class CItem implements Serializable {
    
	private static final long serialVersionUID = 7526472295622776147L;
    private Long id;
    private static Long COUNTER = 0L;
	private Integer myInteger;
    private String  myString;

	/** 
	 * Default Constructor : 
	 * Creates new CItem with an autogenenerated sequence ID 
	 */
	public CItem() {
        myInteger = new Integer(0);
        myString = new String("");
        	id = generateId();
    }

	/** 
	 * Two field Constructor : 
	 * Creates new CItem with an autogenenerated sequence ID 
	 */
    public CItem(int n, String str) {
        myInteger = new Integer(n);
        myString = str;
    	    id = generateId();
    }

    /** 
     * Three field constructor 
     */
    public CItem(Long id, int n, String str) {
        myInteger = new Integer(n);
        myString = str;
    	    this.id = id;
    }

    /**
     * get the ID of the CItem 
     * @return ID 
     */
     public Long getId() {
 		return id;
 	}

     /**
      * Set the ID of the CItem
      * @param id new id 
      */
 	public void setId(Long id) {
 		this.id = id;
 	}
     
	/**
	 * @return Returns the Integer.
	 */
	public Integer getMyInteger() {
		return myInteger;
	}
	/**
	 * @param i The integer to set.
	 */
	public void setMyInteger(int i) {
		this.myInteger = new Integer(i);
	}
	/**
	 * @return Returns  myString.
	 */
	public String getMyString() {
		return myString;
	}
	/**
	 * @param myString The string to set.
	 */
	public void setMyString(String myString) {
		this.myString = myString;
	}

    /**
     * Returns the String representation of this User. Not required, it just pleases reading logs.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("CItem [id=%d,myString=%s,myInteger=%s]", getId(), myString, myInteger);
    }

    // for autogeneration of ids
    private Long generateId()
    {
    	return COUNTER++;
    }

}




