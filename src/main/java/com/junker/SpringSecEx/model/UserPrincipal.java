// Define the package where the class is located
package com.junker.SpringSecEx.model;

// Import necessary Spring Security classes
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// UserPrincipal class implements UserDetails and represents the authenticated user for Spring Security
public class UserPrincipal implements UserDetails {

    // A private field to hold the Users entity, which represents the user's data
    private Users user;

    /**
     * Constructor to create a UserPrincipal from a Users object.
     * This is used to wrap the Users entity into a UserDetails object
     * to be used by Spring Security.
     *
     * @param user - The Users entity to be wrapped
     */
    public UserPrincipal(Users user) {
        this.user = user;
    }

    /**
     * Gets the authorities (roles/permissions) associated with the user.
     * This method returns a collection of granted authorities that the user has.
     * 
     * In this case, the user is assigned a role of "USER" as a simple granted authority.
     *
     * @return A collection of GrantedAuthority (roles or permissions) of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return a singleton collection with a "USER" authority for this user
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Gets the password of the user.
     * This method returns the password of the user, which Spring Security
     * will use to authenticate the user.
     *
     * @return The user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword(); // Fetch the password from the wrapped Users entity
    }

    /**
     * Gets the username of the user.
     * This method returns the username that Spring Security will use to identify the user.
     *
     * @return The user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername(); // Fetch the username from the wrapped Users entity
    }

    /**
     * Returns whether the user account is expired or not.
     * By default, this method returns `true`, meaning the user is not expired.
     * If the user has an expiration condition, it should be overridden here.
     *
     * @return `true` if the account is non-expired, `false` otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Default implementation, can be customized
    }

    /**
     * Returns whether the user's credentials (password) are expired.
     * By default, this method returns `true`, meaning the credentials are not expired.
     * If the user has an expiration condition, it should be overridden here.
     *
     * @return `true` if the credentials are non-expired, `false` otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Default implementation, can be customized
    }

    /**
     * Returns whether the user is locked or not.
     * By default, this method returns `true`, meaning the user is not locked.
     * If the user is locked, it should be overridden here.
     *
     * @return `true` if the account is non-locked, `false` otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // Default implementation, can be customized
    }

    /**
     * Returns whether the user is enabled or not.
     * By default, this method returns `true`, meaning the user is enabled.
     * If the user is disabled (e.g., account disabled for some reason), this should be overridden.
     *
     * @return `true` if the account is enabled, `false` otherwise
     */
    @Override
    public boolean isEnabled() {
        return true; // Default implementation, can be customized
    }
}

