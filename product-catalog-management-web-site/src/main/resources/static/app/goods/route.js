"use strict"

angular.module("product-catalog-management-app")
    .config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('goods', {
                url: '/goods',
                redirectTo: 'goods.list'
            })
            .state('goods.list', {
                views: {
                    'container@': {
                        templateUrl: 'app/goods/templates/list.html',
                        controller : 'goodsCtrl'
                    }
                }
            })
            .state('goods.edit', {
                url: '/{goodsId}',
                views: {
                    'container@': {
                        templateUrl: 'app/goods/templates/edit.html',
                        controller : 'editGoodsCtrl'
                    }
                }
            })
            .state('goods.create', {
                views: {
                    'container@': {
                        templateUrl: 'app/goods/templates/edit.html',
                        controller : 'editGoodsCtrl'
                    }
                }
            });
    }]);