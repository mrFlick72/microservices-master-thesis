"use strict"

angular.module("product-catalog-management-app")
    .value("priceListBasePath","/site/api/v1/product-catalog-service/price-list")
    .service("priceListService", ["commonService", "priceListBasePath","$q", "$http",
        function (commonService, priceListBasePath, $q, $http) {

        function baseRestExec(method, path, priceListData, successExtractorDataFn, errorExtractorDataFn) {
            var defer = $q.defer();
            var request = {method: method,url: path};
            if(priceListData){
                request.data = priceListData
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

        var logger = function (data) {
            console.log(data)
        };

        return {
            "findAll":function () {
                return baseRestExec('GET', priceListBasePath+"?withoutGoodsInPriceList=true", null, priceListListExtractor, null);
            },
            "find":function (priceListId) {
                return baseRestExec('GET', [priceListBasePath,priceListId].join('/'),null
                    ,function (data) {return data.data;},null);
            },
            "create":function (priceListData) {
                return baseRestExec('POST', priceListBasePath, priceListData, commonService.idExtractorFromResponse, null);
            },
            "edit":function (priceListId, priceListData) {
                return baseRestExec('PUT', [priceListBasePath, priceListId].join('/'), priceListData, priceListListExtractor,logger);
            },
            "saveGoodsInPriceList": function (priceListId, goodsId, price) {
                return baseRestExec('PATCH', [priceListBasePath, priceListId,"goods",goodsId].join('/'), price, logger,logger);
            },
            "removeGoodsInPriceList": function (priceListId, goodsId, price) {
                return baseRestExec('DELETE', [priceListBasePath, priceListId,"goods",goodsId].join('/'), null, logger,logger);
            },
            "delete":function (priceListId) {
                var extractor = function() {return true;};
                var errorExtractor = function() {return false;};

                return baseRestExec('DELETE', [priceListBasePath,priceListId].join('/'),
                    extractor,errorExtractor);
            }
        }
    }]);