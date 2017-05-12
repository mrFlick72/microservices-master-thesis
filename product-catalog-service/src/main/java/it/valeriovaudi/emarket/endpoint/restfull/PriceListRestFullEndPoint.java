package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.hateoas.GoodsInPriceListHeateoasFactory;
import it.valeriovaudi.emarket.hateoas.PriceListHateoasFactory;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.service.PriceListService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@RestController
@RequestMapping("/price-list")
public class PriceListRestFullEndPoint {


    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListHateoasFactory priceListHateoasFactory;

    @Autowired
    private GoodsInPriceListHeateoasFactory goodsInPriceListHeateoasFactory;

    @GetMapping
    public ResponseEntity findPriceLists(){
        return ResponseEntity.ok(priceListHateoasFactory.toResources(priceListService.findPriceLists()));
    }

    @GetMapping("/{idPriceList}")
    public ResponseEntity findPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(priceListHateoasFactory.toResource(priceListService.findPriceList(idPriceList)));
    }

    @GetMapping("/{idPriceList}/goods")
    public ResponseEntity findGoodsListInPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResources(idPriceList, priceListService.findGoodsListInPriceList(idPriceList)));
    }

    @GetMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity findGoodsInPriceList(@PathVariable String idPriceList, @PathVariable String idGoods){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResource(idPriceList, priceListService.findGoodsInPriceList(idPriceList, idGoods)));
    }

    @PatchMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity saveGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods, @RequestBody BigDecimal price){
        priceListService.saveGoodsInPriceList(idPriceList,idGoods,price);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity removeGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods){
        priceListService.removeGoodsInPriceList(idPriceList,idGoods);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPriceList}")
    public ResponseEntity updatePriceList(@PathVariable String idPriceList, @RequestBody PriceList priceList){
        priceList.setId(idPriceList);
        priceListService.updatePriceList(priceList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPriceList}")
    public ResponseEntity deletePriceList(@PathVariable String idPriceList){
        priceListService.deletePriceList(idPriceList);
        return ResponseEntity.noContent().build();
    }
}
