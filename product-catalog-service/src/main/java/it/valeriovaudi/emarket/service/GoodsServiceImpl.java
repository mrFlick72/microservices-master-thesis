package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
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

    @Override
    public Goods createGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public List<Goods> findGoodsList() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> findGoodsList(String idGoodsCategory) {
        return null;
    }

    @Override
    public Goods findGoods(String idGoods) {
        return goodsRepository.findOne(idGoods);
    }

    @Override
    public Goods saveGoodsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue) {
        Goods goods = goodsRepository.findOne(idGoods);
        goods.getGoodsAttribute().putIfAbsent(goodsAttributeKey, goodsAttributeValue);
        goodsRepository.save(goods);
        return goods;
    }

    @Override
    public Goods removeGoodsAttributeValue(String idGoods, String goodsAttributeKey) {
        Goods goods = goodsRepository.findOne(idGoods);
        goods.getGoodsAttribute().remove(goodsAttributeKey);
        goodsRepository.save(goods);
        return goods;
    }


    @Override
    public Goods updateGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public void deleteGoods(String idGoods) {
        goodsRepository.delete(idGoods);
    }


}
