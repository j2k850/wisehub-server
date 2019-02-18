package com.wisehub.platform.data.producer.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAPIAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

    @JsonCreator
    public static JwtAPIAuthenticationResponse createTestClass(
            @JsonProperty("token") String token) {
        return new JwtAPIAuthenticationResponse(token);
    }
    
	
	public JwtAPIAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}