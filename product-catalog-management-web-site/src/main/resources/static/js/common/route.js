"use strict"

angular.module("product-catalog-management-app")
    .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
    }]);