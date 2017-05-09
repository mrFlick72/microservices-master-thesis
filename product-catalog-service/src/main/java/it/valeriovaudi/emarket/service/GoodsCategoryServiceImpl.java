package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsCategory;
import it.valeriovaudi.emarket.repository.GoodsCategoryRepository;
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
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Override
    public GoodsCategory createGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryRepository.save(goodsCategory);
    }

    @Override
    public List<GoodsCategory> findGoodsCategoryList() {
        return goodsCategoryRepository.findAll();
    }

    @Override
    public GoodsCategory findGoodsCategory(String idGoodsCategory) {
        return goodsCategoryRepository.findOne(idGoodsCategory);
    }

    @Override
    public GoodsCategory saveCategoryAttributeSchema(String idGoodsCategory, GoodsAttributeSchema goodsAttributeSchema) {
        GoodsCategory one = goodsCategoryRepository.findOne(idGoodsCategory);
        List<GoodsAttributeSchema> goodsAttributeSchemaList = one.getGoodsAttributeSchemaList();
        int index = goodsAttributeSchemaList.indexOf(goodsAttributeSchema);
        if(index != -1){
            goodsAttributeSchemaList.add(goodsAttributeSchema);
        } else {
            goodsAttributeSchemaList.set(index, goodsAttributeSchema);
        }
        return goodsCategoryRepository.save(one);
    }

    @Override
    public GoodsCategory removeCategoryAttributeSchema(String idGoodsCategory, GoodsAttributeSchema goodsAttributeSchema) {
        GoodsCategory one = goodsCategoryRepository.findOne(idGoodsCategory);
        List<GoodsAttributeSchema> goodsAttributeSchemaList = one.getGoodsAttributeSchemaList();
        goodsAttributeSchemaList.remove(goodsAttributeSchema);
        return goodsCategoryRepository.save(one);
    }

    @Override
    public GoodsCategory updateGoods(GoodsCategory goodsCategory) {
        return goodsCategoryRepository.save(goodsCategory);
    }

    @Override
    public void deleteGoodsCategory(String idGoodsCategory) {
        goodsCategoryRepository.delete(idGoodsCategory);
    }
}
