package com.wisehub.platform.data.model;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;
import com.datastax.driver.extras.codecs.jdk8.LocalDateCodec;
import com.google.common.collect.Lists;

@Component
public class CassandraConnector {

	private static final Logger logger = LoggerFactory.getLogger(CassandraConnector.class);

	@Value("${cassandra.host}")
	private String hostList;

	@Value("${cassandra.cluster.name}")
	private String clusterName;

	@Value("${cassandra.cluster.username}")
	private String userName;

	@Value("${cassandra.cluster.password}")
	private String password;

	private static Cluster cluster;
	private static Session session;
	private static ConsistencyLevel consistencyLevel;
	
	public CassandraConnector() {
	}

	@PostConstruct
	public void connectToCluster() throws Exception {
		try {
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, 10);
			poolingOptions.setPoolTimeoutMillis(5000);
			poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, 10);
			PlainTextAuthProvider authProvider = new PlainTextAuthProvider(userName, password);

			cluster = Cluster.builder().addContactPointsWithPorts(getHostIntetSocketAddressList(hostList)).withClusterName(clusterName)
					.withAuthProvider(authProvider).withPoolingOptions(poolingOptions).build();
			cluster.getConfiguration().getCodecRegistry()
					.register(new EnumNameCodec<AccountType>(AccountType.class))
					.register(new EnumNameCodec<AccountStatus>(AccountStatus.class))
					.register(new EnumNameCodec<ProductStatus>(ProductStatus.class));
			cluster.getConfiguration().getCodecRegistry().register(LocalDateCodec.instance);
			
			consistencyLevel = getSession().getCluster().getMetadata().getAllHosts().size() > 1 ? ConsistencyLevel.ALL : ConsistencyLevel.ONE;
		} catch (Exception ex) {
			throw ex;
		}
	}

	private List<InetSocketAddress> getHostIntetSocketAddressList(String hostList) {
		List<InetSocketAddress> cassandraHosts = Lists.newArrayList();
		for (String host : hostList.split(",")) {
			InetSocketAddress socketAddress = new InetSocketAddress(host.split(":")[0], Integer.valueOf(host.split(":")[1]));
			cassandraHosts.add(socketAddress);
		}
		return cassandraHosts;
	}

	public ConsistencyLevel getConsistencyLevel() {
		Optional<ConsistencyLevel> consistencyLevelOptional = Optional.of(consistencyLevel);
		if (consistencyLevelOptional.isPresent()) {
			return consistencyLevel;
		}
		return null;
	}

	public Session getSession() {
		if (cluster == null) {
			throw new RuntimeException("Cassandra Cluster in NULL");
		}
		if (session == null) {
			session = cluster.connect();
		}
		return session;
	}

	public void close() {
		if (session != null) {
			logger.debug("Closing Session....");
			try {
				session.close();
			} catch (Exception e) {
				logger.error("Error while closing the Session ...");
			}
		}
		if (cluster != null) {
			logger.debug("Closing Cluster....");
			try {
				cluster.close();
			} catch (Exception e) {
				logger.error("Error while closing the Cluster ...");
			}
		}
	}
}