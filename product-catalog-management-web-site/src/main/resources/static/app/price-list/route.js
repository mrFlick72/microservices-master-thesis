"use strict"

angular.module("product-catalog-management-app")
    .config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('price-list', {
                url: '/price-list',
                views: {
                    'container@': {
                        controller : function ($state) {
                            $state.go("price-list.list")
                        }
                    }
                }
            })
            .state('price-list.list', {
                views: {
                    'container@': {
                        templateUrl: 'app/price-list/templates/list.html',
                        controller : 'priceListCtrl'
                    }
                }
            })
            .state('price-list.create', {
                views: {
                    'container@': {
                        templateUrl: 'app/price-list/templates/edit.html',
                        controller : 'editPriceListCtrl'
                    }
                }
            })
            .state('price-list.edit', {
                url: '/edit/{priceListId}',
                views: {
                    'container@': {
                        templateUrl: 'app/price-list/templates/edit.html',
                        controller : 'editPriceListCtrl'
                    }
                }
            })
            .state('price-list.add-goods', {
                url: '/goods-list/{priceListId}',
                views: {
                    'container@': {
                        templateUrl: 'app/price-list/templates/add-goods-in-price-list.html',
                        controller : 'addGoodsInPriceListCtrl'
                    }
                }
            });
    }]);