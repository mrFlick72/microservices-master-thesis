package it.valeriovaudi.emarket.model;

import lombok.Data;
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
public class GoodsCategory implements Serializable {

    @Id
    private String id;

    @Version
    private Long version;

    private String name;
    private String type;

    private List<GoodsAttributeSchema> goodsAttributeSchemaList;
}
