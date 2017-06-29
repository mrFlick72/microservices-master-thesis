package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@ToString
@EqualsAndHashCode(of = "goods")
public class GoodsInPriceList implements Serializable {

    @DBRef
    private Goods goods;
    private BigDecimal price;
}
