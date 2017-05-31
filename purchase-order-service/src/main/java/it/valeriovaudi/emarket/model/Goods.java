package it.valeriovaudi.emarket.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
public class Goods implements Serializable {

    private String name;
    private String barCode;
    private BigDecimal price;
    private int quantity;
    private Map<String, String> goodsAttribute;

}
