package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@Entity
public class PriceList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private List<GoodsInPriceList> goodsInPriceList;
}
