package com.example.demo.aop;

import com.example.demo.config.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationAdvice {


    @Before(value = "@annotation(role)")
    public void check(JoinPoint joinPoint, Role role) {
        // 파라미터 값
        Object[] args = joinPoint.getArgs();
        log.warn("args -> {}", Arrays.toString(args));

        // 파라미터 명
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.warn("annotation variables -> {}", Arrays.toString(signature.getParameterNames()));


        // 어노테이션 값
//        Role annotation = signature.getMethod().getAnnotation(Role.class);
        log.warn("annotation variables -> {}",  Arrays.toString(role.variables()));

        // [1,2,3]
        // [age, name, body]
        Map<String, Object> collect = IntStream.range(0, args.length)
                .boxed()
                .collect(Collectors.toMap(idx -> signature.getParameterNames()[idx], idx -> args[idx]));

        log.warn("map -> {}", collect);
    }

    @AllArgsConstructor
    private static class CheckPayload {
        private Integer userId;
        private Integer boardId;
        private Integer channelId;
    }

}
