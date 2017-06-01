package it.valeriovaudi.emarket.anticorruptation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * Created by vvaudi on 02/06/17.
 */

@Data
public abstract class AbstractAnticCorruptationLayerStrategy<T> implements AnticCorruptationLayerStrategy<T>{

    @Autowired
    protected ObjectMapper objectMapper;

}
