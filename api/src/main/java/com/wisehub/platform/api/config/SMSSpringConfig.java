package com.wisehub.platform.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.twilio.Twilio;

@Configuration
public class SMSSpringConfig extends BaseConfig {

	@Value("${twilio.accountSid}")
	public String twilioAccountSid = "ACdfe5c889d4e6003a6a836dcc6ac27ed6";
	@Value("${twilio.authToken}")
	public String twilioAuthToken = "4405d0b606bd9a57653b4b662ba1d525";
	@Value("${twilio.fromPhoneNumber}")
	public String fromPhoneNumber = "+447492886416";

	@Bean
	@Profile("prod")
	public void initTwilioSMSProd() {
		Twilio.init(twilioAccountSid, twilioAuthToken);
	}
	
	@Bean
	@Profile("prod")
	public String defaultFromPhoneNumber() {
		return this.fromPhoneNumber;
	}
}
