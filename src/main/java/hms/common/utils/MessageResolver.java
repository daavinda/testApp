package hms.common.utils;

import hms.common.orm.model.SystemUser;
import hms.common.orm.repo.SystemUserRepository;
import hms.common.service.SystemUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Locale;

/**
 * Common utility services.
 * e.x. Resource messages by Locale language, common session details load.
 */
public class MessageResolver {

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    @Autowired
    private SystemUserRepository userRepository;

    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    public String getMessage(String key, String... arg) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, arg, locale);
    }

    protected SystemUser getCurrentUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        String username = authUser.getName();
        return userRepository.findByUsername(username);
    }

    protected boolean isSuperUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority : authUser.getAuthorities()) {
            if (SystemUserDetailService.ROLE_SUPER_ADMIN.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
