package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@EqualsAndHashCode
public class GoodsAttributeSchema implements Serializable {
    private String name;
    private String type;
    private String pattern;
}
