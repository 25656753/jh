package com.masschip.jh.enties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class User  {

    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator",strategy = "uuid")
    private String userid;
    @Column(nullable = false)
    @Length(max = 64)
    private String username;
    private String password;
    @Column(nullable = false)
    @Length(max = 64)
    private String nickname;

    private boolean enabled=true;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns ={@JoinColumn(name="userid",referencedColumnName = "userid")},
            inverseJoinColumns = {@JoinColumn(name="roleid",referencedColumnName ="roleid" ) })
    private Set<Role> roles;

    private String email;
    private String userface;
    private Timestamp regTime;
    private String ps;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private User create_by;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Column(name ="create_time" )
    @NotNull
    private Date create_time;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    private User update_by;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Column(name ="update_time" )
    private Date update_time;
}

