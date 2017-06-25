"use strict"

angular.module("product-catalog-management-app")
    .controller("goodsCtrl",["$scope","goodsService", function($scope, goodsService){

        console.log("before");
        goodsService.findAll().then(function(data) {$scope.goodsList = data});
        console.log("after");

        $scope.goodsDetails = function (goodsId) {
            goodsService.find(goodsId);
        };

        $scope.goodsRemove = function (goodsId) {
            goodsService.delete(goodsId).then(function () {
                goodsService.findAll().then(function(data) {$scope.goodsList = data});
            });

        };
    }])
    .controller("editGoodsCtrl",["$stateParams", "$scope","goodsService", function($stateParams, $scope, goodsService){
        if($stateParams.goodsId){
            goodsService.find($stateParams.goodsId)
                .then(function (data) {
                    var goodsMaster = data;
                    $scope.goods = {
                        id: goodsMaster.id || null,
                        name: goodsMaster.name || "",
                        version: goodsMaster.version || "0",
                        barCode: goodsMaster.barCode || "",
                        description: goodsMaster.description || "",
                        category: goodsMaster.category || "",
                        itemList:  Object.keys(goodsMaster.goodsAttribute)
                            .map(function (key) { return {"key":key, "value":goodsMaster.goodsAttribute[key]}})
                    };
                });
            } else {
            $scope.goods = {
                itemList: []
            }
        }

        $scope.removeItem = function (index) {
            $scope.goods.itemList.splice(index,1);
        };

        $scope.addAttribute = function(){
            $scope.goods.itemList.push({key:"",value:""});
        };

        $scope.save = function(){
            var goods = $scope.goods;
            var goodsAttributeAux = {};
            goods.itemList.forEach(function (item) {
                goodsAttributeAux[item.key] = item.value;
            });

            var aux = {
                name: goods.name || "",
                barCode: goods.barCode || "",
                description: goods.description || "",
                version: goods.version || "0",
                category: goods.category || "",
                goodsAttribute:  goodsAttributeAux
            };

            console.log(aux);

            if(goods.id){
                goodsService.edit(goods.id, aux).then(function (data) {
                    $scope.goods.version++;
                });
            } else {
                goodsService.create(aux);
            }
        };

    }]);