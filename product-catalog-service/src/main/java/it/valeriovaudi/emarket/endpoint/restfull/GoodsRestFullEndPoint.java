package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.service.GoodsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Data
@RestController
@RequestMapping("/goods")
public class GoodsRestFullEndPoint {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity findAllGoods(@RequestParam String category){
        List<Goods> goodsList = Optional.ofNullable(category)
                .map(categoryAux -> goodsService.findGoodsList(categoryAux))
                .orElse(goodsService.findGoodsList());

        return ResponseEntity.ok(goodsList);
    }

    @GetMapping("/{idGoods}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity findGoods(@PathVariable String idGoods){
        return ResponseEntity.ok(goodsService.findGoods(idGoods));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity createGoods(@RequestBody Goods goods){
        Goods goodsAux = goodsService.createGoods(goods);
        URI findGoods = MvcUriComponentsBuilder.fromMethodName(GoodsRestFullEndPoint.class,
                "findGoods", goodsAux.getId()).build().toUri();
        return ResponseEntity.created(findGoods).build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PatchMapping("/{idGoods}/category-attribute")
    public ResponseEntity saveGoodsAttributeValue(@PathVariable String idGoods, @RequestBody HashMap<String,String> goods){
        goods.entrySet().forEach(entry -> goodsService.saveGoodsAttributeValue(idGoods,entry.getKey(), entry.getValue()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idGoods}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity updateGoods(@PathVariable String idGoods, @RequestBody Goods goods){
        goods.setId(idGoods);
        goodsService.updateGoods(goods);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idGoods}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity deleteGoods(@PathVariable String idGoods){
        goodsService.deleteGoods(idGoods);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("/{idGoods}/category-attribute")
    public ResponseEntity removeGoodsAttributeValue(@PathVariable String idGoods, @PathVariable String goodsAttributeKey){
        goodsService.removeGoodsAttributeValue(idGoods, goodsAttributeKey);
        return ResponseEntity.noContent().build();
    }
}
