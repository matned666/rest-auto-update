package eu.mrndesign.matned.jsonplaceholder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("eu.mrndesign.matned.jsonplaceholder")
public class ScheduledConfig {
}
