package com.github.omenstudio.weblibrary.aspect;

import com.github.omenstudio.weblibrary.AppConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class HydraControllerInterceptor {

    private static final Logger log = LoggerFactory.getLogger("application");


    @Pointcut("@annotation(com.github.omenstudio.weblibrary.annotation.HydraGet)")
    public void hydraGet() {
    }


    @Around("hydraGet()")
    public Object logHydraGet(ProceedingJoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();

        Object result = null;
        try {
            result = thisJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (result == null) {
            return ResponseEntity.badRequest();
        }

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<" + AppConfig.HTTP_ADDRESS + "/api/vocab>; " +
                        "rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(result.toString());
    }


}