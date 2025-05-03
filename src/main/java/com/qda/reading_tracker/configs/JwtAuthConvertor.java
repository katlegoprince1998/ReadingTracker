package com.qda.reading_tracker.configs;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConvertor implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {

        Collection<GrantedAuthority> authorities = Stream.concat(
                Optional.of(jwtGrantedAuthoritiesConverter.convert(jwt)).orElseGet(Collections::emptyList).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());


        return new JwtAuthenticationToken(jwt, authorities, getPrincipleClaimName(jwt));

    }

    private String getPrincipleClaimName(Jwt jwt) {

        String principleAttribute = "preferred_username";
        return jwt.getClaim(principleAttribute);


    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(@NonNull Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (jwt.getClaim("resource_access") == null)
            return Set.of();

        resourceAccess = jwt.getClaim("resource_access");

        if (resourceAccess.get("katlego-mashego-98") == null)
            return Set.of();

        resource = (Map<String, Object>) resourceAccess.get("katlego-mashego-98");

        resourceRoles = (Collection<String>) resource.get("roles");

        return  resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("Role_" + role))
                .collect(Collectors.toSet());
    }
}
