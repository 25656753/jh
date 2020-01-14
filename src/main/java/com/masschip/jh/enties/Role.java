package com.masschip.jh.enties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid")
    @Length(max = 64)
    private String roleid;
    @NotBlank
    @Length(max = 64)
    private String rolename;

    @Length(max = 200)
    private String ps;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "roleid", referencedColumnName = "roleid")},
            inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName = "userid")})
    private Set<User> users;


   @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
   @Length(max = 64)
    private User create_by;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name ="create_time" )
    @NotNull
    private Date create_time;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    @Length(max = 64)
    private User update_by;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name ="update_time" )
    private Date update_time;

}
