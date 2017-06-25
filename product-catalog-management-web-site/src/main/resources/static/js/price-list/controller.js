"use strict"

angular.module("product-catalog-management-app")
    .controller("goodsCtrl",["$scope","$http", function($window, $scope, $http){
        $scope.createGoods = function () {
            $http.post('/logout', {}).finally(function() {
                $window.location="/site/index";
            });

            $http.post('/site/logout', {}).finally(function() {
                $window.location="/site/index";
            });
        }
    }]);