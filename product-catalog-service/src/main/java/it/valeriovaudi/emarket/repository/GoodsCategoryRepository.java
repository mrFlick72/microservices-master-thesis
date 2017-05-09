package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.GoodsCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsCategoryRepository extends MongoRepository<GoodsCategory, String> {

}