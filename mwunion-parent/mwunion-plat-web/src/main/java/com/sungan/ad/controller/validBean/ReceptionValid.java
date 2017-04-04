package com.sungan.ad.controller.validBean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sungan.ad.dao.model.Reception;

/**
 * 说明:
 */
public class ReceptionValid  extends Reception{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Override
	public String getRecPhone() {
		return super.getRecPhone();
	}

	@NotBlank
	@Override
	public String getRecName() {
		return super.getRecName();
	}
    
	@NotBlank
	@Pattern(regexp="\\w+")
	@Override
	public String getUserName() {
		return super.getUserName();
	}
	
	@NotBlank
	@Override
	public String getWorkStatus() {
		return super.getWorkStatus();
	}
	
	
	
}