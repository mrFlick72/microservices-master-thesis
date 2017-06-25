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
                        templateUrl: 'js/goods/templates/list.html',
                        controller : 'goodsCtrl'
                    }
                }
            })
            .state('price-list.create', {
                views: {
                    'container@': {
                        templateUrl: 'js/goods/templates/create.html',
                        controller : 'goodsCtrl'
                    }
                }
            });
    }]);