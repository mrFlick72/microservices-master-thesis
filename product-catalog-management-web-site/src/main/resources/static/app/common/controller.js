"use strict"

angular.module("product-catalog-management-app")
    .controller("baseCtrl",["$window", "$scope","$http", function($window, $scope, $http){
        $scope.logout = function () {
            $http.post('/logout', {}).finally(function() {
                $window.location="/site/index";
            });

            $http.post('/site/logout', {}).finally(function() {
                $window.location="/site/index";
            });
        }
    }]);