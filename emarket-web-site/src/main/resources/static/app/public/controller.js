"use strict"

angular.module("public-e-market-app")
    .controller("singupCtrl", ["$window", "$scope", "publicSectionService",
        function ($window, $scope, publicSectionService) {
        $scope.singup = function (data) {
            publicSectionService.createAccount(data)
                .then(function (data) {
                    $window.location= "/site/private/index";
                })
        }
    }]).controller("productCatalogCtrl", ["$scope", "publicSectionService",
        function ($scope, publicSectionService) {
            publicSectionService.getProductCatalog()
                .then(function (data) {
                    $scope.priceListList = data;
                });
    }]).controller("privateAreaCtrl", ["$window",
        function ($window) {
            $window.location= "/site/private/index";
        }]);
