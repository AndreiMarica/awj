var app = angular.module('blog', [ ]);

app.controller('ElevController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.elevi = [];
    $scope.keys = [];

    $scope.elev={};



    /* */
    $http({url: 'http://localhost:8080/elev', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.elevi= data;
        });

    /* */
    $http.get('http://localhost:8080/elev').then(
        function successCallback(response) {

            $scope.elevi = response;
            $scope.keys = Object.keys(response.data[0]);
        });

    /* */
    $scope.reset = function () {
        this.elev= {};
    };


    /* */
    $scope.saveElev = function(){
        $http.post('http://localhost:8080/elev')
            .then(
            function successCallback(response) {
                /* */
                $scope.elev = response;

                $http.get('http://localhost:8080/elev').then(
                    function successCallback(response) {

                        $scope.elevi = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.updateElev = function(id) {
        $http.put('http://localhost:8080/elev/' + id)
            .then(
            function successCallback(response) {
                /* */
                $scope.elev = response;

                $http.get('http://localhost:8080/elev').then(
                    function successCallback(response) {

                        $scope.elevi = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.deleteElev = function(id) {
        $http.delete('http://localhost:8080/elev/' + id)
            .then(
            function successCallback(response) {
                /* */
                $http.get('http://localhost:8080/elev').then(
                    function successCallback(response) {

                        $scope.elevi = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };



}]);
