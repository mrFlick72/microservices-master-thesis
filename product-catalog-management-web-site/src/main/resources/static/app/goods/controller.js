"use strict"

angular.module("product-catalog-management-app")
    .controller("goodsCtrl",["$scope","goodsService", function($scope, goodsService){

        goodsService.findAll().then(function(data) {$scope.goodsList = data});

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
            var goodsAttributeAux = {};
            $scope.goods.itemList.forEach(function (item) {
                goodsAttributeAux[item.key] = item.value;
            });

            var aux = {
                name: $scope.goods.name || "",
                barCode: $scope.goods.barCode || "",
                description: $scope.goods.description || "",
                version: $scope.goods.version || "0",
                category: $scope.goods.category || "",
                goodsAttribute:  goodsAttributeAux
            };

            if($scope.goods.id){
                goodsService.edit($scope.goods.id, aux).then(function (data) {
                    $scope.goods.version++;
                });
            } else {
                goodsService.create(aux).then(function (data) {
                    $scope.goods.id = data;
                    $scope.goods.version = 0;
                });
            }
        };

    }]);