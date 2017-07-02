"use strict"

angular.module("public-e-market-app")
    .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state('main', {
                url: '/'
            })
            .state('signup', {
                views: {
                    'container@': {
                        templateUrl: 'app/public/templates/singup.html',
                        controller : 'singupCtrl'
                    }
                }
            })
    }]);