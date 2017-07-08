package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@Document
@ToString
public class PriceList implements Serializable {

    @Id
    private String id;
    private String name;

    @Version
    private Long version;

    private List<GoodsInPriceList> goodsInPriceList;
}
