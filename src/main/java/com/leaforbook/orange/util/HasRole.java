package com.leaforbook.orange.util;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRole {
    String preKey();
    String sufKey();
}
