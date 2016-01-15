var app = angular.module('blog', [ ]);

app.controller('ClasaController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.clase = [];
    $scope.keys = [];

    $scope.clasa={};



    /* */
    $http({url: 'http://localhost:8080/clasa', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.clase= data;
        });

    /* */
    $http.get('http://localhost:8080/clasa').then(
        function successCallback(response) {

            $scope.clase = response;
            $scope.keys = Object.keys(response.data[0]);
        });

    /* */
    $scope.reset = function () {
        this.clasa= {};
    };


    /* */
    $scope.saveClasa = function(){
        $http.post('http://localhost:8080/clasa')
            .then(
            function successCallback(response) {
                /* */
                $scope.clasa = response;

                $http.get('http://localhost:8080/clasa').then(
                    function successCallback(response) {

                        $scope.clase= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.updateClasa= function(id) {
        $http.put('http://localhost:8080/clasa/' + id)
            .then(
            function successCallback(response) {
                /* */
                $scope.clasa = response;

                $http.get('http://localhost:8080/clasa').then(
                    function successCallback(response) {

                        $scope.clase = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.deleteClasa = function(id) {
        $http.delete('http://localhost:8080/clasa/' + id)
            .then(
            function successCallback(response) {
                /* */
                $http.get('http://localhost:8080/clasa').then(
                    function successCallback(response) {

                        $scope.clase = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };



}]);
