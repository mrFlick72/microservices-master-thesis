"use strict"

angular.module("public-e-market-app")
    .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state('main', {
                url: '/',
                views: {
                    'container@': {
                        templateUrl: '../app/public/templates/product-catalog.html',
                        controller : 'productCatalogCtrl'
                    }
                }
            })
            .state('signup', {
                url: '/signup',
                views: {
                    'container@': {
                        templateUrl: '../app/public/templates/singup.html',
                        controller : 'singupCtrl'
                    }
                }
            }).state('private-area', {
                views: {
                    'container@': {
                        controller : 'privateAreaCtrl'
                    }
                }
            })
    }]);