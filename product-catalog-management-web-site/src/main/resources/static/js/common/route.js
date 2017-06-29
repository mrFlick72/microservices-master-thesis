"use strict"

angular.module("product-catalog-management-app")
    .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state('main', {
                url: '/',
                redirectTo: 'goods'
            })
    }]);