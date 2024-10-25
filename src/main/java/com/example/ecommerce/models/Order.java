package com.example.ecommerce.models;

import com.example.ecommerce.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
//@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdAt",updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();


}
