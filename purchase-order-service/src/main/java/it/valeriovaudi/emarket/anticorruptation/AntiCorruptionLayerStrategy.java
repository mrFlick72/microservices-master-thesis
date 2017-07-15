package it.valeriovaudi.emarket.anticorruptation;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface AntiCorruptionLayerStrategy<T> {

    T traslate(String body);

}
