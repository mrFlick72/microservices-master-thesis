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
        priceListService.find($stateParams.priceListId)
            .then(function (data) {
                var priceListMaster = data;
                $scope.priceList = {
                    id: priceListMaster.id || null,
                    name: priceListMaster.name || "",
                    version: priceListMaster.version || "0",
                    barCode: priceListMaster.goodsInPriceList || []
                };
            });

        $scope.save = function () {
            priceListService.create($scope.priceList);
        }
    }]);