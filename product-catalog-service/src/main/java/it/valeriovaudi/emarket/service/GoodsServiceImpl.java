package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsCategory;
import it.valeriovaudi.emarket.repository.GoodsCategoryRepository;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vvaudi on 09/05/17.
 */

@Data
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Override
    public Goods createGoods(Goods goods) {
        return null;
    }

    @Override
    public List<Goods> findGoodsList() {
        return null;
    }

    @Override
    public List<Goods> findGoodsList(String idGoodsCategory) {
        return null;
    }

    @Override
    public Goods findGoods(String idGoods) {
        return null;
    }

    @Override
    public Goods saveCategoryAttributeValue(String idGoods, GoodsAttributeSchema goodsAttributeSchema) {
        return null;
    }

    @Override
    public Goods removeCategoryAttributeValue(String idGoods, GoodsAttributeSchema goodsAttributeSchema) {
        return null;
    }

    @Override
    public Goods updateGoods(Goods goods) {
        return null;
    }

    @Override
    public void deleteGoods(String idGoods) {

    }
}
