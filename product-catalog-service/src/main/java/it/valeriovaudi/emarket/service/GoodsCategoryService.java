package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsCategory;

import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsCategoryService {

    GoodsCategory createGoodsCategory(GoodsCategory goodsCategory);

    List<GoodsCategory> findGoodsCategoryList();

    List<GoodsCategory> findGoodsCategoryList(String goodsBarCode);

    GoodsCategory findGoodsCategory(String name);

    GoodsCategory saveCategoryAttributeSchema(String goodsCategoty, GoodsAttributeSchema goodsAttributeSchema);

    GoodsCategory removeCategoryAttributeSchema(String goodsCategoty, GoodsAttributeSchema goodsAttributeSchema);

    GoodsCategory updateGoods(GoodsCategory account);

    void deleteGoodsCategory(Long idGoodsCategory);
}