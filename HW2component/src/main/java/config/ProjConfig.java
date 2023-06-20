package config;

import beans.Cat;
import beans.Dog;
import beans.Human;
import beans.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "beans")
public class ProjConfig {

}
