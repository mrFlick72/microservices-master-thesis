"use strict"

angular.module("private-e-market-app")
    .config(["$stateProvider",  "$urlRouterProvider",
        function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state('main', {
                url: '/',
                redirectTo: 'purchase-order-list'
            })
            .state('purchase-order-list', {
                url: '/purchase-order-list',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-list.html',
                        controller : 'purchaseOrderListCtrl'
                    }
                }
            })
            .state('purchase-order-list.details', {
                url: '/{purchaseOrderId}',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-details.html',
                        controller : 'purchaseOrderDetailsCtrl'
                    }
                }
            })
            .state('create-new-purcase-order', {
                url: '/new-purchase-order',
                controller: 'createNewPurchaseOrderCtrl'
            })
            .state('create-new-purcase-order.add-goods-in-purchase-order', {
                url: '/new-purchase-order/{purchaseOrderId}',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/product-catalog.html',
                        controller : 'addGoodsInNewPurchaseOrderCtrl'
                    }
                }
            })
            .state('create-new-purcase-order.add-shipment-data-in-purchase-order', {
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-shipment-data.html',
                        controller : 'newPurchaseOrderCtrl'
                    }
                }
            })
            .state('create-new-purcase-order.resume-purchase-order', {
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-resume.html',
                        controller : 'newPurchaseOrderCtrl'
                    }
                }
            })
    }]);