package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.service.PriceListService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@RestController
@RequestMapping("/price-list")
public class PriceListRestFullEndPoint {


    @Autowired
    private PriceListService priceListService;

    @GetMapping
    public ResponseEntity findPriceLists(){
        return ResponseEntity.ok(priceListService.findPriceLists());
    }

    @GetMapping("/{idPriceList}")
    public ResponseEntity findPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(priceListService.findPriceList(idPriceList));
    }

    @GetMapping("/{idPriceList}/goods")
    public ResponseEntity findGoodsInPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(priceListService.findPriceList(idPriceList).getGoodsInPriceList());
    }

    @PatchMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity saveGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods, @RequestBody BigDecimal price){
        priceListService.saveGoodsInPriceList(idPriceList,idGoods,price);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity  removeGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods){
        priceListService.removeGoodsInPriceList(idPriceList,idGoods);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPriceList}")
    public ResponseEntity  updatePriceList(@PathVariable String idPriceList, @RequestBody PriceList priceList){
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
