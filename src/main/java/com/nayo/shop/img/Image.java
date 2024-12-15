package com.nayo.shop.img;

import com.nayo.shop.item.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String image_url;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id", nullable =false)
    private Item item;
}
