package org.bentech.annotation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api", produces = {(MediaType.APPLICATION_JSON_VALUE)})
public @interface ApiMapping {
}
