package com.wisehub.platform.data.model.util;

import java.util.UUID;

public class TimeUUID {
	
	
    /**
     * The current clock and node value.
     */
	private static long CLOCK_SEQ_AND_NODE = 0x8000000000000000L;

	
	private static long createQueryTime(long currentTimeMillis) {
		long time;
		final long timeMillis = (currentTimeMillis * 10000) + 0x01B21DD213814000L;
		time = timeMillis << 32;
		time |= (timeMillis & 0xFFFF00000000L) >> 16;
		time |= 0x1000 | ((timeMillis >> 48) & 0x0FFF); // version 1
		return time;
	}
	// http://callistaenterprise.se/blogg/teknik/2012/12/05/apache-cassandra-and-time-series-with-timeuuidtype-in-java/
	public static UUID buildTimeUUID(long time) {
		return new UUID(createQueryTime(time), CLOCK_SEQ_AND_NODE);
	}

}
