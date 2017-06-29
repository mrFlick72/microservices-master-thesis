"use strict"

angular.module("product-catalog-management-app")
    .controller("priceListCtrl",["$scope","priceListService", function($scope, priceListService){
        priceListService.findAll().then(function(data) {$scope.priceListList = data});

        $scope.priceListRemove = function (priceListId) {
            priceListService.delete(priceListId).then(function () {
                priceListService.findAll().then(function(data) {$scope.priceListList = data});
            });
        }
    }])
    .controller("editPriceListCtrl",["$scope","$stateParams", "priceListService", function($scope, $stateParams, priceListService){
        $scope.priceListId = $stateParams.priceListId;
        priceListService.find($stateParams.priceListId)
            .then(function (data) {
                var priceListMaster = data;
                $scope.priceList = {
                    id: priceListMaster.id || null,
                    name: priceListMaster.name || "",
                    version: priceListMaster.version || "0",
                    goodsInPriceList: priceListMaster.goodsInPriceList || []
                };
            });

        $scope.updateGoodsInPriceList = function (goodsId, price) {
            priceListService.saveGoodsInPriceList($scope.priceListId, goodsId, new String(price));
        };

        $scope.removeGoodsInPriceList = function (goodsId) {
            priceListService.removeGoodsInPriceList($scope.priceListId, goodsId);
        };

        $scope.save = function () {
            if($scope.priceList.id){
                $scope.priceList.goodsInPriceList.forEach(function (item) {
                    priceListService.saveGoodsInPriceList($scope.priceListId, item.goods.id, item.price);
                });
            } else {
                priceListService.create($scope.priceList).then(function (data) {
                    $scope.priceListId = data;
                });
            }
        }
    }])
    .controller("addGoodsInPriceListCtrl",["$scope","$stateParams", "goodsService", "priceListService",
        function($scope, $stateParams,goodsService, priceListService){
            $scope.priceListId = $stateParams.priceListId;

            goodsService.findAll().then(function(data) {$scope.goodsList = data});

            $scope.addGoodsInPriceList = function (goodsId) {
                priceListService.saveGoodsInPriceList($scope.priceListId, goodsId, "0.0");
            };
    }]);