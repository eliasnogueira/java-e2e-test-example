(function () {
    var app = angular.module('service', []);
    app.factory('personSrv',function ($resource){
        
         return $resource('http://localhost:4567/api/v1/person/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });
    });
})();
