package com.leaforbook.orange.util;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasResource {
    String resourceType();
    String resourceId();
}
