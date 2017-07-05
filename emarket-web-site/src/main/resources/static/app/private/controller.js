"use strict"

angular.module("private-e-market-app")
    .controller("createNewPurchaseOrderCtrl", ["privateSectionService", "$state",
        function (privateSectionService, $state) {
            privateSectionService.getProductCatalog()
                .then(function (data) {
                    privateSectionService.createNewPurchaseOrder()
                        .then(function (data) {
                            var purchaseOrderId = data;
                            $state.go("create-new-purchase-order.add-goods-in-purchase-order",{"purchaseOrderId": purchaseOrderId});
                        })
                });
        }])
    .controller("addGoodsInNewPurchaseOrderCtrl", ["$scope", "$stateParams", "privateSectionService",
        function ($scope, $stateParams, privateSectionService) {
            $scope.purchaseOrderId = $stateParams.purchaseOrderId;

            $scope.addGoods = function(goodsId,priceListId,quantity){
                console.log("goodsId: " + goodsId);
                console.log("priceListId: " + priceListId);
                console.log("quantity: " + quantity);

                privateSectionService.addGoodsInPurchaseOrder($scope.purchaseOrderId, priceListId, goodsId, quantity)
                    .then(function (data) {
                        console.log("data: " + data);
                    });
            };

            privateSectionService.getProductCatalog()
                .then(function (data) {
                    $scope.priceListList = data;
                });
        }])
    .controller("shipmentDataInNewPurchaseOrderCtrl", ["$scope", "$stateParams", "privateSectionService",
        function ($scope, $stateParams, privateSectionService) {
            $scope.purchaseOrderId = $stateParams.purchaseOrderId;
        }])
    .controller("resumeDataInNewPurchaseOrderCtrl", ["$scope",  "$state", "$stateParams", "privateSectionService",
        function ($scope, $state, $stateParams, privateSectionService) {
            $scope.purchaseOrderId = $stateParams.purchaseOrderId;
            $scope.savePurchaseOrder = function () {
                $state.go("main");
            };
        }]);


angular.module("private-e-market-app")
    .controller("purchaseOrderListCtrl", ["$scope",
        function ($scope) {
            $scope.purchaseOrderList = [];
    }])
    .controller("purchaseOrderDetailsCtrl", ["$scope",
        function ($scope) {
            $scope.purchaseOrder = {};

    }]);


angular.module("private-e-market-app")
    .controller("toPublicAreaCtrl", ["$window",
        function ($window) {
            $window.location= "/site/public/index";
    }]);