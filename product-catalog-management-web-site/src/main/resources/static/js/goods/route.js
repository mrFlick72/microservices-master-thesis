"use strict"

angular.module("product-catalog-management-app")
    .config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('goods', {
                url: '/goods',
                views: {
                    'container@': {
                        controller : function ($state) {
                            $state.go("goods.list")
                        }
                    }
                }
            })
            .state('goods.list', {
                views: {
                    'container@': {
                        templateUrl: 'js/goods/templates/list.html',
                        controller : 'goodsCtrl'
                    }
                }
            })
            .state('goods.create', {
                views: {
                    'container@': {
                        templateUrl: 'js/goods/templates/create.html',
                        controller : 'goodsCtrl'
                    }
                }
            });
    }]);