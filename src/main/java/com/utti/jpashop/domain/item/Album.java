package com.utti.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item{

    private String artist;
    private String etc;

}
