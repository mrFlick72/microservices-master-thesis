package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;


/**
 * Created by mrflick72 on 09/05/17.
 */


@Data
@Document
@ToString
@EqualsAndHashCode(of = "id")
public class Goods implements Serializable {

    @Id
    private String id;

    private String barCode;
    private String name;
    private String description;
    private String category;

    @Version
    private Long version;

    private Map<String, String> goodsAttribute;
}
