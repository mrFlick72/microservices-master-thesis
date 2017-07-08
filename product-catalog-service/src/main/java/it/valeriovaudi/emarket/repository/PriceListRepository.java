package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.PriceList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */


public interface PriceListRepository extends MongoRepository<PriceList, String> {

    PriceList findByName(String name);

    @Query(value="{}", fields="{goodsInPriceList : 0}")
    List<PriceList> findAllWithoutGoodsInPriceList();
}
