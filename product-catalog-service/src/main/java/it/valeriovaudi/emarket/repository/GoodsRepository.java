package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsRepository extends MongoRepository<Goods, String> {

    List<Goods> findByCategory(String category);
}
