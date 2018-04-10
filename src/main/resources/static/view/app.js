var app = angular.module('app', ['ngAnimate']);

app.controller('dnaController', ['$scope', 'dnaService', function ($scope, dnaService) {
    var minMatrixSize = 4;
    var maxMatrixSize = 10;
    $scope.validRegex = '[ACGT]';

    initForm();

    function initForm() {
        $scope.matrix = [
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}]
        ];
    };

    $scope.sendDna = function () {
        var dna = [];
        for (var i = 0; i < $scope.matrix.length; i++) {
            var str = '';
            for (var j = 0; j < $scope.matrix[i].length; j++) {
                str = str.concat($scope.matrix[i][j].charVal);
            }
            dna.push(str);
        }

        dnaService.postDna(dna).then(
            function success(response) {
                alert(response.data.message);
                initForm();
            }, function error(response) {
                alert(response.data.message);
                initForm();
            }
        );
    };

    $scope.increaseMatrixSize = function () {
        var columnCount = $scope.matrix.length;
        if (columnCount < maxMatrixSize) {
            var newRow = [];
            for (var i = 0; i < columnCount; i++) {
                newRow.push({value: ''});
            }
            $scope.matrix.push(newRow);
            $scope.matrix.forEach(function (row) {
                row.push({value: ''});
            });
        }
    };

    $scope.decreaseMatrixSize = function () {
        var columnCount = $scope.matrix.length;
        if (columnCount > minMatrixSize) {
            $scope.matrix.pop();

            $scope.matrix.forEach(function (row) {
                row.pop();
            });
        }
    };

}]);

app.service('dnaService', ['$http', function ($http) {

    this.postDna = function (dnaArray) {
        return $http({
            method: 'POST',
            url: '/mutant/',
            data: {dna: dnaArray}
        });
    };

    this.getStats = function () {
        return $http({
            method: 'GET',
            url: '/stats/'
        });
    };

    this.resetStats = function () {
        return $http({
            method: 'PUT',
            url: '/stats/reset/'
        });
    };

}]);

app.directive('matrixInput', function () {
    return {
        restrict: 'E',
        templateUrl: 'fragments/matrix.html'
    };
});