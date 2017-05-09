package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@Entity
public class GoodsInPriceList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Goods goods;
    private PriceList priceList;
    private BigDecimal price;

}
