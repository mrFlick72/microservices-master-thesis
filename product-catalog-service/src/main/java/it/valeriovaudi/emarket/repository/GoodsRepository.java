package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsRepository extends JpaRepository<Goods, String> {


}
