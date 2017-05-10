package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@EqualsAndHashCode(exclude = {"value"})
public class GoodsAttributeValue implements Serializable {
    private String name;
    private String type;
    private String pattern;
    private String value;
}
