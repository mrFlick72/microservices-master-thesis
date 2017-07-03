"use strict"

angular.module("public-e-market-app")
    .controller("singupCtrl", ["$windows", "publicSectionService",
        function ($windows, publicSectionService) {
        $rootScope.singup = function (data) {
            publicSectionService.createAccount(data)
                .then(function (data) {
                    $windows.location= "/private/index";
                })
        }
    }]);
