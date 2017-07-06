"use strict"

angular.module("private-e-market-app")
    .controller("createNewPurchaseOrderCtrl", ["privateSectionService", "$state",
                                                function (privateSectionService, $state) {
        privateSectionService.getProductCatalog()
            .then(function (data) {
                privateSectionService.createNewPurchaseOrder()
                    .then(function (data) {
                        $state.go("create-new-purchase-order.add-goods-in-purchase-order",{"purchaseOrderId": data});
                    });
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
    .controller("shipmentDataInNewPurchaseOrderCtrl", ["$scope", "$state", "$stateParams", "privateSectionService",
                                                        function ($scope, $state, $stateParams, privateSectionService) {
        $scope.purchaseOrderId = $stateParams.purchaseOrderId;
        $scope.next = function () {
            privateSectionService.withShipmentData($stateParams.purchaseOrderId, $scope.shipment)
                .then(function (data) {
                    $state.go("create-new-purchase-order.resume-purchase-order",{'purchaseOrderId': $scope.purchaseOrderId})
                });
        };
    }])
    .controller("resumeDataInNewPurchaseOrderCtrl", ["$scope",  "$state", "$stateParams", "privateSectionService",
                                                        function ($scope, $state, $stateParams, privateSectionService) {
        $scope.purchaseOrderId = $stateParams.purchaseOrderId;
        console.log("$scope.purchaseOrderId: " + $scope.purchaseOrderId);
        privateSectionService.getPurchaseOrder($scope.purchaseOrderId)
            .then(function (data) {
                console.log(data);
                $scope.purchaseOrderDetails = data.data;
            });

        $scope.savePurchaseOrder = function () {
            privateSectionService.completePurchaseOrderList($scope.purchaseOrderId)
                .then(function (data) {
                    $state.go("main");
                });
        };
    }]);


angular.module("private-e-market-app")
    .controller("purchaseOrderListCtrl", ["$scope", "$state","privateSectionService",
                                            function ($scope, $state, privateSectionService) {
        init();

        function init() {
            privateSectionService.getPurchaseOrderList()
                .then(function (data) {
                    $scope.purchaseOrderList = data;
                });

        }

        $scope.delete = function (orderNumber) {
            privateSectionService.deletePurchaseOrder(orderNumber)
                .then(function (data) {
                    init();
                })
        };

        $scope.choose = function (orderNumber, status) {
            if("DRAFT" === status){
                $state.go("create-new-purchase-order.add-goods-in-purchase-order",{'purchaseOrderId': orderNumber});
            } else {
                $state.go("purchase-order-list.details",{'purchaseOrderId': orderNumber});
            }
        };
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