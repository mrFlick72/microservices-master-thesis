"use strict"

angular.module("product-catalog-management-app")
    .controller("goodsCtrl",["$scope","goodsService", function($scope, goodsService){

        console.log("before");
        goodsService.findAll().then(function(data) {$scope.goodsList = data});
        console.log("after");

        $scope.goodsDetails = function (goodsId) {
            goodsService.find(goodsId);
        };

        $scope.goodsEdit = function (goodsId) {
            goodsService.find(goodsId);
        };

        $scope.goodsRemove = function (goodsId) {
            goodsService.delete(goodsId).then(function () {
                goodsService.findAll().then(function(data) {$scope.goodsList = data});
            });

        };
    }]);