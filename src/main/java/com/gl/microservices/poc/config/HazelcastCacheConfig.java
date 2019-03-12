package com.gl.microservices.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class HazelcastCacheConfig {

  @Bean
  public Config hazelcastConfig() {
    return new Config().setInstanceName("hazelcast-instance")
        .addMapConfig(new MapConfig().setName("brandCache")
            .setMaxSizeConfig(new MaxSizeConfig(300, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
            .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000))
        .addMapConfig(new MapConfig().setName("supplierCache")
            .setMaxSizeConfig(new MaxSizeConfig(300, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
            .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000))
        .addMapConfig(new MapConfig().setName("productCache")
            .setMaxSizeConfig(new MaxSizeConfig(300, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
            .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
  }

}
