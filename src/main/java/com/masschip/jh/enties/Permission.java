package com.masschip.jh.enties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Permission {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid")
    @Length(max = 64)
    private String perid;

    @Length(max = 64)
    private String permissname;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu", columnDefinition = "varchar(64)")
    private Menu menu;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_permisson",
            joinColumns = {@JoinColumn(name = "user_perid",referencedColumnName = "perid",columnDefinition = "varchar(64)")},
            inverseJoinColumns = {@JoinColumn(name = "userid")}
    )
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permisson",
            joinColumns = {@JoinColumn(name = "role_perid",referencedColumnName = "perid",columnDefinition = "varchar(64)")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")}
    )
    private Role role;


    @Length(max = 128)
    private String permissps;
}
