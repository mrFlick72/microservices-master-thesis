package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@Entity
public class GoodsCategory implements Serializable {

    @Id
    private String name;

    private String type;

    @OneToMany
    private List<GoodsAttributeSchema> goodsAttributeSchemaList;
}
