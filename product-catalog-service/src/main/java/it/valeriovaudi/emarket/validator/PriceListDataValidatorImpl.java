package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.exception.GoodsValidationException;
import it.valeriovaudi.emarket.exception.PriceListValidationException;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import it.valeriovaudi.emarket.model.PriceList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by mrflick72 on 11/05/17.
 */

@Data
@Component
public class PriceListDataValidatorImpl implements PriceListDataValidator {

    @Autowired
    protected MessageSource messageSource;

    @Override
    public void validate(String correlationId, PriceList priceList) throws PriceListValidationException {
        Map<String,String> errors = new HashMap<>();
        validateNotNullAndNotEmpty("priceListName", priceList.getName(), "PriceListDataValidator.PriceList.name", errors);

/*        List<String> priceErrors = Optional.ofNullable(priceList.getGoodsInPriceList())
                .map(goodsInPriceListList -> goodsInPriceListList.stream()
                        .filter(priceIsInvalid)
                        .map(this::goodsPriceError)
                        .collect(Collectors.toList())).orElse(new ArrayList<>());
        if(priceErrors.size() > 0){
            errors.put("priceListGoodsPrice", priceErrors.toString());
        }*/
    }

    @Override
    public void validate(String correlationId, GoodsInPriceList goodsInPriceList) throws PriceListValidationException {
        goodsPriceError(goodsInPriceList);
    }


    @Override
    public void validate(String correlationId, Goods goods) throws GoodsValidationException {
        Map<String,String> errors = new HashMap<>();
        validateNotNullAndNotEmpty("goodsName", goods.getName(), "PriceListDataValidator.Goods.name", errors);
        validateNotNullAndNotEmpty("goodsBarCode", goods.getBarCode(), "PriceListDataValidator.Goods.barCode", errors);

    }


    private void validateNotNullAndNotEmpty(String key,String value,String validationMessageKey, Map<String,String> errors){
        if (value == null || "".equals(value)){
            errors.put(key, messageSource.getMessage(validationMessageKey,new Object[]{}, Locale.ENGLISH));
        }
    }

    Predicate<GoodsInPriceList> priceIsInvalid = item -> item.getPrice()!= null || item.getPrice().doubleValue() < 0;

    private String goodsPriceError(GoodsInPriceList itemAux){
        return messageSource.getMessage("PriceListDataValidator.Goods.invalidPrice",
            new Object[]{itemAux.getGoods().getName(), itemAux.getGoods().getBarCode()}, Locale.ENGLISH);
    }
}