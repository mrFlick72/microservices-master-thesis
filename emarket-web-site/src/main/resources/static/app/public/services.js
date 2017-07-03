"use strict"

angular.module("public-e-market-app")
    .value("accountBaseUrl","/site/api/v1/account-service")
    .value("productCatalogBaseUrl","/site/api/v1/product-catalog-service")
    .service("publicSectionService", ["$http", "$q", "accountBaseUrl", "productCatalogBaseUrl",
        function ($http, $q, accountBaseUrl, productCatalogBaseUrl) {
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

            var logger = function (data) {
                console.log(data);
            };

            return {
            "createAccount":function (account) {
                account.role="ROLE_USER";
                return baseRestExec("POST", [accountBaseUrl,"account"].join("/"),account, idExtractorFromResponse, logger);
            },
            "getProductCatalog":function () {
                return baseRestExec("GET", [productCatalogBaseUrl,"price-list"].join("/"),null, priceListListExtractor, logger);
            }
        }
    }]);
