var app = angular.module('blog', [ ]);

app.controller('ProfesorController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.profi = [];
    $scope.keys = [];

    $scope.prof={};



    /* */
    $http({url: 'http://localhost:8080/profesor', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.profi= data;
        });

    /* */
    $http.get('http://localhost:8080/profesor').then(
        function successCallback(response) {

            $scope.profi = response;
            $scope.keys = Object.keys(response.data[0]);
        });

    /* */
    $scope.reset = function () {
        this.prof= {};
    };


    /* */
    $scope.saveProf= function(){
        $http.post('http://localhost:8080/profesor')
            .then(
            function successCallback(response) {
                /* */
                $scope.prof = response;

                $http.get('http://localhost:8080/profesor').then(
                    function successCallback(response) {

                        $scope.profi= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.updateProf= function(id) {
        $http.put('http://localhost:8080/profesor/' + id)
            .then(
            function successCallback(response) {
                /* */
                $scope.prof = response;

                $http.get('http://localhost:8080/profesor').then(
                    function successCallback(response) {

                        $scope.profi = response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };


    /* */
    $scope.deleteProf = function(id) {
        $http.delete('http://localhost:8080/profesor/' + id)
            .then(
            function successCallback(response) {
                /* */
                $http.get('http://localhost:8080/profesor').then(
                    function successCallback(response) {

                        $scope.profi= response;
                        $scope.keys = Object.keys(response.data[0]);
                        $location.url('/index.html');
                    });

            },
            function errorCallback(response) {
                angular.element('[data-id=' + id + ']').remove();
            });
    };
}]);
