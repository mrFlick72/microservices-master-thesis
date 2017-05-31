package it.valeriovaudi.emarket.anticorruptation;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface AnticCorruptationLayerStrategy<T> {

    T traslate(String body);

}
