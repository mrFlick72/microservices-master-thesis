"use strict"

angular.module("private-e-market-app")
    .controller("createNewPurchaseOrderCtrl", ["privateSectionService", "$state",
        function (privateSectionService, $state) {
            console.log("data: ");
            privateSectionService.getProductCatalog()
                .then(function (data) {
                    privateSectionService.createNewPurchaseOrder()
                        .then(function (data) {
                            var purchaseOrderId = data;
                            $state.go("create-new-purchase-order.add-goods-in-purchase-order",{"purchaseOrderId": purchaseOrderId});

                        })
                });

            $state.go("create-new-purchase-order.add-goods-in-purchase-order",{"purchaseOrderId": "4254543"});
        }])
    .controller("addGoodsInNewPurchaseOrderCtrl", ["$scope", "$stateParams", "privateSectionService",
        function ($scope, $stateParams, privateSectionService) {

            $scope.purchaseOrderId = $stateParams.purchaseOrderId;
            console.log("purchaseOrderId : " + $scope.purchaseOrderId);
            privateSectionService.getProductCatalog()
                .then(function (data) {
                    $scope.priceListList = data;
                });
        }])
    .controller("shipmentDataInNewPurchaseOrderCtrl", ["$scope", "$stateParams", "privateSectionService",
        function ($scope, $stateParams, privateSectionService) {

            $scope.purchaseOrderId = $stateParams.purchaseOrderId;
            console.log("purchaseOrderId : " + $scope.purchaseOrderId);
        }])
    .controller("resumeDataInNewPurchaseOrderCtrl", ["$scope",  "$state", "$stateParams", "privateSectionService",
        function ($scope, $state, $stateParams, privateSectionService) {

            $scope.purchaseOrderId = $stateParams.purchaseOrderId;
            $scope.savePurchaseOrder = function () {
                $state.go("main");

            };

            console.log("purchaseOrderId : " + $scope.purchaseOrderId);
        }]);



angular.module("private-e-market-app")
    .controller("purchaseOrderListCtrl", ["$scope",
        function ($scope) {

    }])
    .controller("purchaseOrderDetailsCtrl", ["$scope",
        function ($scope) {

    }]);


angular.module("private-e-market-app")
    .controller("toPublicAreaCtrl", ["$window",
        function ($window) {
            $window.location= "/site/public/index";
    }]);