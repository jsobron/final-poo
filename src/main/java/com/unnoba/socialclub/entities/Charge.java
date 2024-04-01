/**
 * 
 */
package com.unnoba.socialclub.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author lfalcioni
 *
 */

@Entity
@Table(name= "charge") 
public class Charge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String value;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private Member member_id;
	

	public Charge() {
		super();
	}
	  public Charge getCharge() {
	        return this;
	    }
	

	public Charge(Member member_id) {
		super();
		this.member_id= member_id;
	}


	public Charge(String value, Date date, Member member_id) {
		super();
		this.value = value;
		this.date = date;
		this.member_id = member_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	   public Member getMember() {
	        return this.member_id; // O devuelve el valor correcto según tu lógica
	    }


	public Member getMember_id() {
		return member_id;
	}

	public void setMember_id(Member member_id) {
		this.member_id = member_id;
	}
	
	
	
}
