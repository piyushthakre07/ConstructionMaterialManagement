package com.app.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author piyush
 *
 */
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class StatusBean implements Serializable {

	private static final long serialVersionUID = -7087642111043657280L;
	private String message;
	private String satusCode;
	private boolean status;
	private String errorCode;

}
