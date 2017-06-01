package it.valeriovaudi.emarket.anticorruptation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vvaudi on 02/06/17.
 */

@Data
public abstract class AbstractAntiCorruptationLayerService {

    @Autowired
    protected ObjectMapper objectMapper;

}
