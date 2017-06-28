package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findPriceLists(@RequestParam(name = "withoutGoodsInPriceList", defaultValue = "false") boolean withoutGoodsInPriceList){
        return ResponseEntity.ok(priceListHateoasFactory.toResources(priceListService.findPriceLists(withoutGoodsInPriceList)));
    }

    @GetMapping("/{idPriceList}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(priceListHateoasFactory.toResource(priceListService.findPriceList(idPriceList)));
    }

    @GetMapping("/{idPriceList}/goods")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findGoodsListInPriceList(@PathVariable String idPriceList){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResources(idPriceList, priceListService.findGoodsListInPriceList(idPriceList)));
    }

    @GetMapping("/{idPriceList}/goods/{idGoods}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findGoodsInPriceList(@PathVariable String idPriceList, @PathVariable String idGoods){
        return ResponseEntity.ok(goodsInPriceListHeateoasFactory.toResource(idPriceList, priceListService.findGoodsInPriceList(idPriceList, idGoods)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createPriceList(@RequestBody PriceList priceList){
        PriceList priceListAux = priceListService.createPriceList(priceList);
        return ResponseEntity.created(MvcUriComponentsBuilder.fromMethodName(PriceListRestFullEndPoint.class,
                "findPriceList", priceListAux.getId()).build().toUri()).build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PatchMapping("/{idPriceList}/goods/{idGoods}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity saveGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods, @RequestBody BigDecimal price){
        priceListService.saveGoodsInPriceList(idPriceList,idGoods,price);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("/{idPriceList}/goods/{idGoods}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity removeGoodsInPriceList(@PathVariable String idPriceList, @PathVariable  String idGoods){
        priceListService.removeGoodsInPriceList(idPriceList,idGoods);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPriceList}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity updatePriceList(@PathVariable String idPriceList, @RequestBody PriceList priceList){
        priceList.setId(idPriceList);
        priceListService.updatePriceList(priceList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPriceList}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deletePriceList(@PathVariable String idPriceList){
        priceListService.deletePriceList(idPriceList);
        return ResponseEntity.noContent().build();
    }
}
