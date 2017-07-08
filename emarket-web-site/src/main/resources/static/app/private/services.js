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
             var purchaseOrderListExtractor = function(data) {
                var result = [];
                if(data){
                    result = data.data["_embedded"] || [];
                    result = result.purchaseOrderList || [];
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
            "createNewPurchaseOrder":function () {
                return baseRestExec("POST", [purchaseOrderBaseUrl,"purchase-order"].join("/"),null, idExtractorFromResponse, logger);
            },
            "withShipmentData":function (orderNumber, shipment) {
                var promise;
                if(shipment){
                    promise = baseRestExec("PUT", [purchaseOrderBaseUrl,"purchase-order",orderNumber,"shipment"].join("/"), shipment, logger, logger);
                } else {
                    var defer = $q.defer();
                    defer.resolve(logger);
                    promise = defer.promise;
                }
                return promise;
            },
            "addGoodsInPurchaseOrder":function (orderNumber, priceListId, goodsId, quantity) {
                return baseRestExec("PATCH", [purchaseOrderBaseUrl,"purchase-order",orderNumber,"goods",goodsId,"price-list",priceListId].join("/"),quantity, logger, logger);
            },
            "removeGoodsInPurchaseOrder":function (orderNumber, priceListId, goodsId) {
                return baseRestExec("DELETE", [purchaseOrderBaseUrl,"purchase-order",orderNumber,"goods",goodsId,"price-list",priceListId].join("/"),null, logger, logger);
            },
            "getProductCatalog":function () {
                return baseRestExec("GET", [productCatalogBaseUrl,"price-list"].join("/"),null, priceListListExtractor, logger);
            },
            "getPurchaseOrder":function (orderNumber) {
                return baseRestExec("GET", [purchaseOrderBaseUrl,"purchase-order",orderNumber].join("/"),null, function (data) {
                    return data.data;
                }, logger);
            },
            "deletePurchaseOrder":function (orderNumber) {
                return baseRestExec("DELETE", [purchaseOrderBaseUrl,"purchase-order",orderNumber].join("/"),null, logger, logger);
            },
            "getPurchaseOrderList":function () {
                return baseRestExec("GET", [purchaseOrderBaseUrl,"purchase-order"].join("/")+"?withOnlyOrderId=true",null, purchaseOrderListExtractor, logger);
            },
            "completePurchaseOrderList":function (orderNumber) {
                return baseRestExec("PATCH", [purchaseOrderBaseUrl,"purchase-order",orderNumber].join("/"),"COMPLETE", logger, logger);
            }
        }
    }]);
