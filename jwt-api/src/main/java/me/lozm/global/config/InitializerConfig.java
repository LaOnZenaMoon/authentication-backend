package me.lozm.global.config;

import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.service.RoleHierarchyHelperService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig implements ApplicationRunner {

    private final RoleHierarchyHelperService roleHierarchyHelperService;
    private final RoleHierarchyImpl roleHierarchy;


    @Override
    public void run(ApplicationArguments args) {
        final String allHierarchy = roleHierarchyHelperService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);
    }

}
