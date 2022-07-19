package com.nozama.api.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class SpringUri {

    public static <IdType> URI buildLocationForNewResource(IdType id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
