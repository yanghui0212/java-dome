package com.yqh.springcloud.service;

import com.google.common.collect.Sets;
import com.yqh.springcloud.mapper.UserMapper;
import com.yqh.springcloud.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

/**
 * @author yangq
 * Create time in 2018-08-01 17:54
 */
public class DomainUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       /* UserModel userModel = userMapper.findUserAuthorityInfo(username.toLowerCase());*/
        Set<SimpleGrantedAuthority> authoritySet = Sets.newHashSet();
        authoritySet.add(new SimpleGrantedAuthority("user"));
        return new User(username, "$2a$10$.DkRRc3aOSt0fcOpJS8VX.KOT7gTkGayO4AcFory.TdSkTXMexHyS", authoritySet);
    }
}
