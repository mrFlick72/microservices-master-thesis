angular.module("product-catalog-management-app")
    .service("commonService", function () {
        return {
            "idExtractorFromResponse":function (data) {
                var result = null;
                if(data){
                    var locationAux = data.headers('Location').split("/");
                    result = locationAux[locationAux.length - 1]
                }

                return result;
            }
        }
    });
