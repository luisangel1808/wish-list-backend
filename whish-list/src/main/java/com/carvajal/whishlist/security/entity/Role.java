package com.carvajal.whishlist.security.entity;

import com.carvajal.whishlist.security.enums.RoleName;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(){

    }

    public Role(@NotNull RoleName roleName){
        this.roleName=roleName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
