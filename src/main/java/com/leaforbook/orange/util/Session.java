package com.leaforbook.orange.util;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Session {
    String key() default "oneofus";
    boolean notNull() default false;
}
