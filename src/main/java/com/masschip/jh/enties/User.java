package com.masschip.jh.enties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator",strategy = "uuid")
    @Length(max = 64)
    private String userid;
    @Column(nullable = false)
    @Length(max = 64)
    @NotEmpty
    private String username;
    @Length(max = 64)
    private String password;
    @Column(nullable = false)
    @Length(max = 64)
    @NotEmpty
    private String nickname;

    private boolean enabled=true;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns ={@JoinColumn(name="userid",referencedColumnName = "userid")},
            inverseJoinColumns = {@JoinColumn(name="roleid",referencedColumnName ="roleid" ) })
    private Set<Role> roles;


    @JsonBackReference
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Permission> permissionSet;



    @Length(max = 28)
    private String email;
    @Length(max = 20)
    private String userface;
    private Timestamp regTime;
    @Length(max = 500)
    private String ps;
    private Boolean issuper=false;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by",columnDefinition = "varchar(64)")
    private User create_by;


    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Column(name ="create_time" )
    @NotNull
    private Date create_time;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by",columnDefinition = "varchar(64)")
    private User update_by;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Column(name ="update_time" )
    private Date update_time;



    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() { // 帐户是否过期
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() { // 帐户是否被冻结
        return true;
    }

    // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {  // 帐号是否可用

        return enabled;
    }

    public void setEnabled(boolean enabled) {
       this.enabled=enabled;
    }

    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
        }
        return authorities;
    }




}

