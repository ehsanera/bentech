package org.bentech.entity;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    String name;

    @Column(name = "price", nullable = false)
    Long price;

    @Column(name = "max_use", nullable = false)
    Long maxUse;

    public Service() {
    }

    public Service(Long id, String name, Long price, Long maxUse) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maxUse = maxUse;
    }
}