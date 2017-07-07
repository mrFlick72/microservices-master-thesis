package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@ToString
@EqualsAndHashCode(of = {"id","priceListId"})
public class Goods implements Serializable {

    private String id;
    private String priceListId;
    private String name;
    private String barCode;
    private BigDecimal price;
    private int quantity;
    private Map<String, String> goodsAttribute;

}
