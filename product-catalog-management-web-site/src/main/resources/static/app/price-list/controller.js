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
        var reloadPriceListDataFN = function () {
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
        };

        $scope.priceListId = $stateParams.priceListId;
        reloadPriceListDataFN();

        $scope.updateGoodsInPriceList = function (goodsId, price) {
            priceListService.saveGoodsInPriceList($scope.priceListId, goodsId, price)
                .then(reloadPriceListDataFN);
        };

        $scope.removeGoodsInPriceList = function (goodsId) {
            priceListService.removeGoodsInPriceList($scope.priceListId, goodsId)
                .then(reloadPriceListDataFN);
        };

        $scope.save = function () {
            if($scope.priceList.id){
                var index = 0;
                priceListService.saveGoodsInPriceList($scope.priceListId,
                    $scope.priceList.goodsInPriceList[index].goods.id,
                    $scope.priceList.goodsInPriceList[index].price).then(function (data) {
                        index++;
                        if(index < $scope.priceList.goodsInPriceList.length){
                            priceListService.saveGoodsInPriceList($scope.priceListId,
                                $scope.priceList.goodsInPriceList[index].goods.id,
                                $scope.priceList.goodsInPriceList[index].price)
                        }
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