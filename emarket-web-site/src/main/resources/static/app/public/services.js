"use strict"

angular.module("product-catalog-management-app")
    .value("accountBaseUrl","/v1/account-service")
    .value("productCatalogBaseUrl","/v1/product-catalog-service")
    .service("publicSectionService", ["$http", "accountBaseUrl", "productCatalogBaseUrl",
        function ($http, accountBaseUrl, productCatalogBaseUrl) {
            function baseRestExec(method, path, data, successExtractorDataFn, errorExtractorDataFn) {
                var defer = $q.defer();
                var request = {method: method,url: path};
                if(data){
                    request.data = data
                }
                $http(request).
                    then(function (data) {defer.resolve(successExtractorDataFn(data));},
                        function (data) {defer.reject(errorExtractorDataFn(data));});
                return defer.promise;
            }

            var priceListListExtractor = function(data) {
                var result = [];
                console.log(data);
                if(data){
                    result = data.data["_embedded"] || [];
                    result = result.priceListList || [];
                }

                return result;
            };
            var idExtractorFromResponse = function (data) {
                var result = null;
                if(data){
                    var locationAux = data.headers('Location').split("/");
                    result = locationAux[locationAux.length - 1]
                }

                return result;
            };

            return {
            "createAccount":function (account) {
                return baseRestExec("POST", [accountBaseUrl,"account"].join("/"),account, idExtractorFromResponse, null);
            },
            "getProductCatalog":function () {
                return baseRestExec("GET", [productCatalogBaseUrl,"account"].join("/"),account, idExtractorFromResponse, null);
            }
        }
    }]);
