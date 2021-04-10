package me.lozm.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProps {

    private String secret;
    private Long expirationTime;
    private String tokenPrefix;
    private String headerString;

}
