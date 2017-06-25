"use strict"

angular.module("product-catalog-management-app")
    .controller("goodsCtrl",["$scope","$http", function($scope, $http){

        console.log("before");
        $http.get('/site/api/v1/product-catalog-service/goods')
            .then(function(data) {
                console.log("data");
                console.log(data);
            });
        console.log("after");

    }]);