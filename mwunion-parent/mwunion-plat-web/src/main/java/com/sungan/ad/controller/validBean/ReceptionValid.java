package com.sungan.ad.controller.validBean;

import com.sungan.ad.dao.model.Reception;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 说明:
 */
public class ReceptionValid  extends Reception{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Override
	public String getMobile() {
		return super.getMobile();
	}

	@NotBlank
	@Override
	public String getRecName() {
		return super.getRecName();
	}
    
	@NotBlank
	@Pattern(regexp="\\w+")
	@Override
	public String getUserAccount() {
		return super.getUserAccount();
	}
	
	@NotBlank
	@Override
	public String getWorkStatus() {
		return super.getWorkStatus();
	}
	
	
	
}