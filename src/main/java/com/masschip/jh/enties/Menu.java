package com.masschip.jh.enties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;



@Setter
@Getter
@Entity
public class Menu {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid")
    @Length(max = 64)
    private String menuid;
    @Length(max = 32)
    private String menuname;
    @Length(max = 64)
    private String menutitle;
    @Length(max = 256)
    private String url;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,
    mappedBy = "menu")
    private Set<Permission> permissionSet;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentMenu",referencedColumnName = "menuid",columnDefinition = "varchar(64)")
    private Menu parentMenu;
}
