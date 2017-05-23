package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.hateoas.GoodsInPriceListHeateoasFactory;
import it.valeriovaudi.emarket.hateoas.PriceListHateoasFactory;
import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.service.PriceListService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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

    @Autowired
    private PriceListHateoasFactory priceListHateoasFactory;

    @Autowired
    private GoodsInPriceListHeateoasFactory goodsInPriceListHeateoasFactory;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity findPriceLists(){
        return ResponseEntity.ok(priceListHateoasFactory.toResources(priceListService.findPriceLists()));
    }

    @GetMapping("/{idPriceList}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity findPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(priceListHateoasFactory.toResource(priceListService.findPriceList(idPriceList)));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{idPriceList}/goods")
    public ResponseEntity findGoodsListInPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResources(idPriceList, priceListService.findGoodsListInPriceList(idPriceList)));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity findGoodsInPriceList(@PathVariable String idPriceList, @PathVariable String idGoods){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResource(idPriceList, priceListService.findGoodsInPriceList(idPriceList, idGoods)));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPriceList(@RequestBody PriceList priceList){
        PriceList priceListAux = priceListService.createPriceList(priceList);
        return ResponseEntity.created(MvcUriComponentsBuilder.fromMethodName(PriceListRestFullEndPoint.class,
                "findPriceList", priceListAux.getId()).build().toUri()).build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PatchMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity saveGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods, @RequestBody BigDecimal price){
        priceListService.saveGoodsInPriceList(idPriceList,idGoods,price);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("/{idPriceList}/goods/{idGoods}")
    public ResponseEntity removeGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods){
        priceListService.removeGoodsInPriceList(idPriceList,idGoods);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPriceList}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity updatePriceList(@PathVariable String idPriceList, @RequestBody PriceList priceList){
        priceList.setId(idPriceList);
        priceListService.updatePriceList(priceList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPriceList}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity deletePriceList(@PathVariable String idPriceList){
        priceListService.deletePriceList(idPriceList);
        return ResponseEntity.noContent().build();
    }
}
