package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @deprecated 세션이 없어 지금은 사용하지 않습니다.
 */
@Deprecated(forRemoval = false)
@RequiredArgsConstructor
public class UserAuditorAware implements AuditorAware<String> {

    private final HttpSession httpSession;

    @Override
    public Optional<String> getCurrentAuditor() {
        /**
         *  SessionUser user = (SessionUser) httpSession.getAttribute("user");
         *  if (user == null)
         *      return null;
         *  return Optional.ofNullable(user.getId());
         */
        return Optional.empty();
    }

}




