package market.common.service;

import market.common.orm.model.Permission;
import market.common.orm.model.SystemRole;
import market.common.orm.model.SystemUser;
import market.common.orm.repo.SystemUserRepository;
import market.common.orm.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("systemUserDetailsService")
public class SystemUserDetailService implements UserDetailsService {

    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    @Autowired
    private SystemUserRepository adminUserRepository;
    @Autowired
    private PermissionRepository permissionRepository;
//    @Value("${super.user.username}")
//    private String superUsername;
//    @Value("${super.user.password}")
//    private String superUserPassword;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException if the user could not be found or the user has no
     *                                                                                 GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = adminUserRepository.findByUsernameAndState(username, SystemUser.State.ACTIVE);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null) {
            String password = user.getPassword();
            SystemRole role = user.getRole();
            for (Permission permission : role.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
            return new User(username, password, true, true, true, true, authorities);
        }
//        else if (superUsername != null && superUserPassword != null) {
//            authorities.add(new SimpleGrantedAuthority(ROLE_SUPER_ADMIN));
//            for (Permission permission : permissionRepository.findAll()) {
//                authorities.add(new SimpleGrantedAuthority(permission.getName()));
//            }
//            return new User(superUsername, superUserPassword, true, true, true, true, authorities);
//        }
        else {
            throw new UsernameNotFoundException("User Not Found!!!");
        }
    }
}
