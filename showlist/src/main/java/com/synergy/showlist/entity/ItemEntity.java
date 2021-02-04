package com.synergy.showlist.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    public ItemEntity(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int itemId;

    @NotNull
    private int itemNumber;
}
