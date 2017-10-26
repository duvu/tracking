package com.vd5.tracking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author beou on 10/25/17 13:03
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu implements Serializable {

    private static final long serialVersionUID = 8465697624924889316L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 64)
    private String name;

    @Column(length = 32)
    private String matIcon;

    @Column(length = 256)
    private String routeLink;

    @Column(length = 64)
    private String text;

    private Integer ordered;

    @ManyToOne (cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private Menu parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    List<Menu> children = new ArrayList<>();
}
