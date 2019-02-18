package com.wisehub.platform.api.config;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

//@Configuration
public class JettyConfigurationRedirect {

	
//	@Value("${server.ssl.key-store}") 
	private String keystoreFile;
//	@Value("${server.ssl.key-store-password}") 
	private String keystorePass;
//	@Value("${server.ssl.port}") 
	private Integer sslPort;
//	@Value("${server.port}") 
	private Integer port;
	

//	@Bean
	public EmbeddedServletContainerCustomizer servletContainerCustomizer()  throws Exception {
		
        final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();

		
	    return new EmbeddedServletContainerCustomizer() {

	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            if (container instanceof JettyEmbeddedServletContainerFactory) {
	                configureJetty((JettyEmbeddedServletContainerFactory) container);
	            }
	        }
	        
	        
	        private void configureJetty(JettyEmbeddedServletContainerFactory container) {

	            container.addServerCustomizers(new JettyServerCustomizer() {

	                @Override
	                public void customize(Server server) {

	                    // HTTP
	                    ServerConnector connector = new ServerConnector(server);
	                    connector.setPort(port);

	                    // HTTPS
	                    SslContextFactory sslContextFactory = new SslContextFactory();
	                    sslContextFactory.setKeyStorePath(absoluteKeystoreFile); //Specify the location for the keystore.jks
	                    sslContextFactory.setKeyStorePassword(keystorePass); //Hardcoding the password for the keystore.
                        sslContextFactory.setKeyStoreType("PKCS12");

	                    HttpConfiguration https = new HttpConfiguration();
	                    https.addCustomizer(new SecureRequestCustomizer());

	                    ServerConnector sslConnector = new ServerConnector(
	                            server,
	                            new SslConnectionFactory(sslContextFactory, "http/1.1"),
	                            new HttpConnectionFactory(https));
	                    sslConnector.setPort(sslPort);

	                    server.setConnectors(new Connector[]{connector, sslConnector});

	                }
	            });
	        }

//	        private void configureJetty(JettyEmbeddedServletContainerFactory jettyFactory) {
//	            jettyFactory.addServerCustomizers(new JettyServerCustomizer() {
//
//	                @Override
//	                public void customize(Server server) {
//	                    ServerConnector serverConnector = new ServerConnector(server);
//	                    serverConnector.setPort(8089);
//	                    server.addConnector(serverConnector);
//	                }
//	            });
//	        }
	    };
	}
	
	
	
}
