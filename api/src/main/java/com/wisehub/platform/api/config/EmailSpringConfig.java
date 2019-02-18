package com.wisehub.platform.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;

@Configuration
public class EmailSpringConfig extends BaseConfig{
	@Value("${mailJet.mailJetApiKey}")
	public String mailJetApiKey = "5bbc746dc04ad72e7a74fa0aed87ef57";
	@Value("${mailJet.mailJetSecretKey}")
	public String mailJetSecretKey = "39ab13797ce1a70febbd13ed0cca322c";

	@Bean
	@Profile("prod")
	public MailjetClient getMailJetEmailProd() {
		return new MailjetClient(mailJetApiKey, mailJetSecretKey, new ClientOptions("v3.1"));
	}
}
