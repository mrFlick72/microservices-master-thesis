package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.exception.GoodsValidationException;
import it.valeriovaudi.emarket.exception.PriceListValidationException;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.PriceList;

/**
 * Created by mrflick72 on 11/05/17.
 */
public class PriceListDataValidatorImpl implements PriceListDataValidator {
    @Override
    public void validate(String correlationId, PriceList account) throws PriceListValidationException {

    }

    @Override
    public void validate(String correlationId, Goods goods) throws GoodsValidationException {

    }
}
