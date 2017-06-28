"use strict"

angular.module("product-catalog-management-app")
    .value("priceListBasePath","/site/api/v1/product-catalog-service/price-list")
    .service("priceListService", ["priceListBasePath","$q", "$http", function (priceListBasePath, $q, $http) {

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
            console.log(data)
            if(data){
                result = data.data["_embedded"] || [];
                result = result.priceListList || [];
            }

            return result;
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
                return baseRestExec('POST', priceListBasePath, priceListData, priceListListExtractor,null);
            },
            "edit":function (priceListId, priceListData) {
                return baseRestExec('PUT', [priceListBasePath, priceListId].join('/'), priceListData, priceListListExtractor,function (data) {
                    console.log(data)
                });
            },
            "delete":function (priceListId) {
                var extractor = function() {return true;};
                var errorExtractor = function() {return false;};

                return baseRestExec('DELETE', [priceListBasePath,priceListId].join('/'),
                    extractor,errorExtractor);
            }
        }
    }]);