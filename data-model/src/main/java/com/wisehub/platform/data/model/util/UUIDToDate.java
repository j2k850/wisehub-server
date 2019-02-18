package com.wisehub.platform.data.model.util;

import java.util.Date;
import java.util.UUID;
public class UUIDToDate {
	
	
  // This method comes from Hector's TimeUUIDUtils class:
  // https://github.com/rantav/hector/blob/master/core/src/main/java/me/prettyprint/cassandra/utils/TimeUUIDUtils.java
  static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;
  public static long getTimeFromUUID(UUID uuid) {
    return (uuid.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
  }

  public static void main(String[] args) {
    String uuidString = "28442f00-2e98-11e2-0000-89a3a6fab5ef";
    UUID uuid = UUID.fromString(uuidString);
    long time = getTimeFromUUID(uuid);
    Date date = new Date(time);
    System.out.println(date);
    
    
    System.out.println((40 % 38));
    
    System.out.println((4 % 2));

    
  }
}