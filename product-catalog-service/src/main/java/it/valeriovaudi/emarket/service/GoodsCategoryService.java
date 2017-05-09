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

    GoodsCategory findGoodsCategory(String idGoodsCategory);

    GoodsCategory saveCategoryAttributeSchema(String idGoodsCategory, GoodsAttributeSchema goodsAttributeSchema);

    GoodsCategory removeCategoryAttributeSchema(String goodsCategoryName, GoodsAttributeSchema goodsAttributeSchema);

    GoodsCategory updateGoods(GoodsCategory goodsCategory);

    void deleteGoodsCategory(String idGoodsCategory);
}