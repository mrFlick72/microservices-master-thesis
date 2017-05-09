package it.valeriovaudi.emarket.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.List;


/**
 * Created by mrflick72 on 09/05/17.
 */


@Data
@Document
public class Goods implements Serializable {

    @Id
    private String barCode;
    private String name;
    private String description;

    @DBRef
    private GoodsCategory goodsCategory;

    private List<GoodsAttributeValue> goodsAttributeValues;
}
