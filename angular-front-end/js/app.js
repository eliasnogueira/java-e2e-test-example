(function () {
    var app = angular.module('app', [
        'ngResource',
        'ui.router',
        'service',
        'controller'
    ]);
    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/person');
        $stateProvider.state('person', {
            url: '/person',
            templateUrl: 'views/person.html',
            controller: 'personCtrl'
        }).state('form', {
            url: '/form',
            templateUrl: 'views/form.html',
            controller: 'formCtrl'
        });
    });
})();

