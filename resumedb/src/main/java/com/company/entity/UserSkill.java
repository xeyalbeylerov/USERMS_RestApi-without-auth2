/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Khayal Baylarov
 */
@Entity
@Table(name = "user_skill")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer power;

    @ToString.Exclude
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne
    private Skill skill;

    @ToString.Exclude
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    public UserSkill(Integer id) {
        this.id = id;
    }

}
