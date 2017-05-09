package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsCategory;
import it.valeriovaudi.emarket.repository.GoodsCategoryRepository;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vvaudi on 09/05/17.
 */

@Data
@Service
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public GoodsCategory createGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryRepository.save(goodsCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoodsCategory> findGoodsCategoryList() {
        return goodsCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public GoodsCategory findGoodsCategoryList(String goodsBarCode) {
        return goodsRepository.getOne(goodsBarCode).getGoodsCategory();
    }

    @Override
    public GoodsCategory findGoodsCategory(String name) {
        return goodsCategoryRepository.findOne(name);
    }

    @Override
    public GoodsCategory saveCategoryAttributeSchema(String goodsCategoryName, GoodsAttributeSchema goodsAttributeSchema) {
        GoodsCategory one = goodsCategoryRepository.findOne(goodsCategoryName);


        one.getGoodsAttributeSchemaList().stream().map(goodsAttributeSchema1 -> );
        return one;
    }

    @Override
    public GoodsCategory removeCategoryAttributeSchema(String goodsCategoryName, GoodsAttributeSchema goodsAttributeSchema) {
        return null;
    }

    @Override
    public GoodsCategory updateGoods(GoodsCategory account) {
        return null;
    }

    @Override
    public void deleteGoodsCategory(Long idGoodsCategory) {

    }
}
