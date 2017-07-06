"use strict"

angular.module("private-e-market-app")
    .config(["$stateProvider",  "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
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
                url: '/purchase-order/{purchaseOrderId}',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-details.html',
                        controller : 'purchaseOrderDetailsCtrl'
                    }
                }
            })
            .state('create-new-purchase-order', {
                url: '/new-purchase-order',
                views: {
                    'container@': {
                        controller: 'createNewPurchaseOrderCtrl'
                    }
                }
            })
            .state('create-new-purchase-order.add-goods-in-purchase-order', {
                url: '/{purchaseOrderId}',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/product-catalog.html',
                        controller : 'addGoodsInNewPurchaseOrderCtrl'
                    }
                }
            })
           .state('create-new-purchase-order.add-shipment-data-in-purchase-order', {
               url: '/{purchaseOrderId}/shipment',
               views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-shipment-data.html',
                        controller : 'shipmentDataInNewPurchaseOrderCtrl'
                    }
                }
            })
            .state('create-new-purchase-order.resume-purchase-order', {
                url: '/{purchaseOrderId}/resume',
                views: {
                    'container@': {
                        templateUrl: '../app/private/templates/purchase-order-resume.html',
                        controller : 'resumeDataInNewPurchaseOrderCtrl'
                    }
                }
            })
            .state('to-public-area', {
                views: {
                    'container@': {
                        controller : 'toPublicAreaCtrl'
                    }
                }
            })
    }]);