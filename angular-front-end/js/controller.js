(function () {
    var person = {};
    var app = angular.module('controller', []);
    app.controller('personCtrl', function ($scope, $state, personSrv) {
        function getAll() {
            $scope.datas = personSrv.query();
        };
        getAll();
        $scope.add = function () {
            person = {};
            $state.go('form');
        };
        $scope.edit = function (data) {
            person = data;
            $state.go('form');
        };
        $scope.remove = function (id){
            var r = confirm("Are you sure?");
            if (r === true) {
                personSrv.delete({id: id}, function () {
                    getAll();
                });
            } else {
                getAll();
            }
        };
    });
    app.controller('formCtrl', function ($scope, $state, personSrv) {
        $scope.post = person;
        $scope.back = function () {
            $state.go('person');
        };
        $scope.save = function () {
            if ($scope.post.id === undefined) {
                personSrv.save($scope.post, function () {
                    $scope.post = '';
                    $state.go('person');
                });
            } else {
                personSrv.update($scope.post, function () {
                    $state.go('person');
                });
            }
        };
    });
})();
