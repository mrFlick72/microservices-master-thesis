"use strict"

angular.module("private-e-market-app")
    .controller("createNewPurchaseOrderCtrl", ["privateSectionService", "$state",
        function (privateSectionService, $state) {
            console.log("data: " + privateSectionService.getProductCatalog());

            privateSectionService.createNewPurchaseOrder()
                .then(function (data) {
                    console.log("data: " + data);
                    $state.go("create-new-purcase-order.add-goods-in-purchase-order",{"purchaseOrderId": data});
                });
        }])
    .controller("addGoodsInNewPurchaseOrderCtrl", ["$scope",
        function ($scope) {
            privateSectionService.getProductCatalog()
                .then(function (data) {
                    $scope.priceListList = data;
                })
        }]);



angular.module("private-e-market-app")
    .controller("purchaseOrderListCtrl", ["$scope",
        function ($scope) {

    }])
    .controller("purchaseOrderDetailsCtrl", ["$scope",
        function ($scope) {

    }]);
