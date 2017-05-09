package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */


@Data
@Entity
public class Goods implements Serializable {

    @Id
    private String barCode;
    private String name;
    private String description;

    @OneToOne
    private GoodsCategory goodsCategory;

    @OneToMany
    private List<GoodsAttributeValue> goodsAttributeValues;
}
