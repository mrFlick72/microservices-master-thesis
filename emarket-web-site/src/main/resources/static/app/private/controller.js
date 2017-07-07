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
    .controller("addGoodsInNewPurchaseOrderCtrl", ["$scope", "$state", "$stateParams", "privateSectionService",
                                                                function ($scope, $state, $stateParams, privateSectionService) {
        $scope.purchaseOrderId = $stateParams.purchaseOrderId;

        $scope.addGoods = function(goodsId, priceListId, quantity){
            privateSectionService.addGoodsInPurchaseOrder($scope.purchaseOrderId, priceListId, goodsId, quantity);
        };

        $scope.abortOrder = function () {
            privateSectionService.deletePurchaseOrder($scope.purchaseOrderId)
                .then(function (data) {
                    $state.go("purchase-order-list");
                })
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
            var shipment = $scope.shipment;
            privateSectionService.withShipmentData($stateParams.purchaseOrderId, shipment)
                .then(function (data) {
                    $state.go("create-new-purchase-order.resume-purchase-order",{'purchaseOrderId': $scope.purchaseOrderId})
                });
        };
    }])
    .controller("resumeDataInNewPurchaseOrderCtrl", ["$scope",  "$state", "$stateParams", "privateSectionService",
                                                        function ($scope, $state, $stateParams, privateSectionService) {
        $scope.purchaseOrderId = $stateParams.purchaseOrderId;
        console.log("$scope.purchaseOrderId: " + $scope.purchaseOrderId);
        privateSectionService.getPurchaseOrder($stateParams.purchaseOrderId)
            .then(function (data) {
                $scope.purchaseOrder = data;
            });

        $scope.savePurchaseOrder = function () {
            privateSectionService.completePurchaseOrderList($scope.purchaseOrderId)
                .then(function (data) {
                    $state.go("main");
                });
        };

        $scope.abortOrder = function () {
            privateSectionService.deletePurchaseOrder($scope.purchaseOrderId)
                .then(function (data) {
                    $state.go("purchase-order-list");
                })
        };


        $scope.removeGoodsInPurchaseOrder = function (index) {
            var goods = $scope.purchaseOrder.goodsList[index];
            privateSectionService.removeGoodsInPurchaseOrder($scope.purchaseOrderId,goods.priceListId,goods.id)
                .then(function (data) {
                    $scope.purchaseOrder.goodsList.splice(index,1);
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
    .controller("purchaseOrderDetailsCtrl", ["$scope", "$stateParams", "privateSectionService",
        function ($scope, $stateParams, privateSectionService) {
            privateSectionService.getPurchaseOrder($stateParams.purchaseOrderId)
                .then(function (data) {
                    $scope.purchaseOrder = data;
                });

    }]);


angular.module("private-e-market-app")
    .controller("toPublicAreaCtrl", ["$window",
        function ($window) {
            $window.location= "/site/public/index";
    }]);