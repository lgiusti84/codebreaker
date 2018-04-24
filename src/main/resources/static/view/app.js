var app = angular.module('app', ['ngAnimate']);

app.controller('dnaController', ['$scope', 'dnaService', function ($scope, dnaService) {
    var minMatrixSize = 4;
    var maxMatrixSize = 10;
    $scope.validRegex = '[ACGTacgt]';
    $scope.matrix = [[]];
    $scope.mutantBarStyle = {};
    $scope.percentBallStyle = {};
    $scope.humanBarStyle = {};

    initForm();

    function initForm () {
        while($scope.matrix.pop()) { }
        $scope.matrix = [
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}],
            [{charVal: ''}, {charVal: ''}, {charVal: ''}, {charVal: ''}]
        ];
        updateStats();
    }

    $scope.resetForm = function () {
        initForm();
    };

    $scope.sendDna = function () {
        var dna = [];
        for (var i = 0; i < $scope.matrix.length; i++) {
            var str = '';
            for (var j = 0; j < $scope.matrix[i].length; j++) {
                str = str.concat($scope.matrix[i][j].charVal);
            }
            dna.push(str.toUpperCase());
        }

        dnaService.postDna(dna).then(
            function success() {
                alert("Bienvenido hermano Mutante");
                initForm();
            }, function error(response) {
                if(response.status === 403) {
                    alert("No se aceptan simples humanos!");
                    initForm();
                }
            }
        )
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

    function updateStats () {
        dnaService.getStats().then(
            function success(response) {
                console.log(response.data);

                if(response.status === 200) {
                    let stats = response.data;
                    $scope.stats = stats;
                    if(stats.countHumanDna !== 0) {
                        $scope.stats.percent = stats.countMutantDna / (stats.countHumanDna + stats.countMutantDna);
                    } else {
                        $scope.stats.percent = 1;
                    }
                } else {
                    $scope.stats = { countMutantDna: 0, countHumanDna: 0, ratio: 0, percent: 0.5 };
                }
                updateStyles();
            }
        );
    }

    function updateStyles() {
        let stats = $scope.stats;
        let totalCount = stats.countMutantDna + stats.countHumanDna;
        let mutantPercent = totalCount ? Math.floor(stats.percent * 100) : 0;
        let humanPercent = totalCount ? Math.ceil((1 - stats.percent) * 100) : 0;
        let leftStr = "";

        if (totalCount) {
            if(mutantPercent > 90) { leftStr = "90"; }
            else if (humanPercent > 90) { leftStr = "10" }
            else { leftStr = mutantPercent; }
            leftStr = "calc(" + leftStr + "% - 20px)";
        }

        $scope.mutantBarStyle = {
            width: mutantPercent + "%",
            opacity: mutantPercent ? 1 : 0,
            padding: "0 " + (mutantPercent > 90 ? "calc(10% + 40px)" : "40px") };
        $scope.humanBarStyle = {
            width: humanPercent + "%",
            opacity: humanPercent ? 1 : 0,
            padding: "0 " + (humanPercent > 90 ? "calc(10% + 40px)" : "40px") };
        $scope.percentBallStyle = {
            left: leftStr,
            opacity: (totalCount ? 1 : 0) };
    }

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
}]);

app.directive('matrixInput', function () {
    return {
        restrict: 'E',
        templateUrl: 'fragments/matrix.html'
    };
});
app.directive('statusBar', function () {
    return {
        restrict: 'E',
        templateUrl: 'fragments/status-bar.html'
    };
});