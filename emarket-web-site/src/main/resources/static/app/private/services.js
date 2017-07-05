"use strict"

angular.module("private-e-market-app")
    .value("accountBaseUrl","/site/api/v1/account-service")
    .value("productCatalogBaseUrl","/site/api/v1/product-catalog-service")
    .value("purchaseOrderBaseUrl","/site/api/v1/purchase-order-service")
    .service("privateSectionService", ["$http", "$q", "accountBaseUrl", "productCatalogBaseUrl", "purchaseOrderBaseUrl",
        function ($http, $q, accountBaseUrl, productCatalogBaseUrl, purchaseOrderBaseUrl) {
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
                console.log("idExtractorFromResponse: " + data)
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
            "createNewPurchaseOrder":function () {
                return baseRestExec("POST", [purchaseOrderBaseUrl,"purchase-order"].join("/"),null, idExtractorFromResponse, logger);
            },
            "addGoodsInPurchaseOrder":function (orderNumber, priceListId, goodsId, quantity) {
                return baseRestExec("PATCH", [purchaseOrderBaseUrl,"purchase-order",orderNumber,"goods",goodsId,"price-list",priceListId].join("/"),quantity, logger, logger);
            },
            "getProductCatalog":function () {
                return baseRestExec("GET", [productCatalogBaseUrl,"price-list"].join("/"),null, priceListListExtractor, logger);
            }
        }
    }]);
