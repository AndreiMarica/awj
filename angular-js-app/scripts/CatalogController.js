var app = angular.module('blog', [ ]);

app.controller('CatalogController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.cataloage= [];
    $scope.keys = [];

    $scope.ctg={};



    /* */
    $http({url: 'http://localhost:8080/catalog', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.cataloage= data;
        });

    /* */
    $http.get('http://localhost:8080/catalog').then(
        function successCallback(response) {

            $scope.cataloage= response;
            $scope.keys = Object.keys(response.data[0]);
        });

    /* */
    $scope.reset = function () {
        this.ctg= {};
    };


    /* */
    $scope.saveCatalog= function(){
        $http.post('http://localhost:8080/catalog')
            .then(
            function successCallback(response) {
                /* */
                $scope.ctg = response;

                $http.get('http://localhost:8080/catalog').then(
                    function successCallback(response) {

                        $scope.cataloage= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.updateCatalog= function(id) {
        $http.put('http://localhost:8080/catalog/' + id)
            .then(
            function successCallback(response) {
                /* */
                $scope.ctg= response;

                $http.get('http://localhost:8080/catalog').then(
                    function successCallback(response) {

                        $scope.cataloage= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.deleteCatalog= function(id) {
        $http.delete('http://localhost:8080/catalog/' + id)
            .then(
            function successCallback(response) {
                /* */
                $http.get('http://localhost:8080/catalog').then(
                    function successCallback(response) {

                        $scope.cataloage= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };
}]);
