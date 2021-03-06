var app = angular.module('marvelApp', ['ngRoute', 'ngResource']);


app.config(function($routeProvider) {

$routeProvider
  .when('/', {
    templateUrl : 'views/home.html',
    controller  : 'HomeController'
  })

  .when('/usuarios', {
    templateUrl : 'views/usuarios.html',
    controller  : 'UsuariosController'
  })

  .when('/catalogo', {
    templateUrl : 'views/catalogo.html',
    controller  : 'CatalogoController'
  })
  
    .when('/favoritos', {
    templateUrl : 'views/favoritos.html',
    controller  : 'FavoritosController'
  })
  
    .when('/grupos', {
    templateUrl : 'views/grupos.html',
    controller  : 'GruposController'
  })
  
    .when('/login', {
    templateUrl : 'views/login.html',
    controller  : 'LoginController'
  })
  
   .when('/ranking', {
    templateUrl : 'views/ranking.html',
    controller  : 'RankingController'
  })


  .otherwise({redirectTo: '/'});
});


var urlPersonajes = 'api/personajes';
// ENDPOINTS
app.factory('FavoritosFactory', ['$http', function($http) {

    var urlBase = 'api/favoritos';
    var FavoritosFactory = {};

    FavoritosFactory.getFavoritos = function() {
        return $http.get(urlBase);
    };
    
    FavoritosFactory.getPersonajes = function() {
        return $http.get(urlPersonajes);
    };

    return FavoritosFactory;
}]);

app.factory('GruposFactory', ['$http', function($http) {

    var urlBase = '/grupos/'+ id;
//    var urlPersonajes = 'api/personajes';
    var GruposFactory = {};

    GruposFactory.getGrupos = function() {
        return $http.get(urlBase);
    };
    
    GruposFactory.addGrupo = function() {
        return $http.put(urlBase);
    };
    
    GruposFactory.getPersonajes = function() {
        return $http.get(urlPersonajes);
    };

    return GruposFactory;
}]);

app.factory('UsuariosFactory', ['$http', function($http) {

    var urlBase = 'api/usuarios';
    var urlPerfil = 'api/perfiles';
    var UsuariosFactory = {};

    UsuariosFactory.getUsuarios = function() {
        return $http.get(urlBase);
    };
    
    UsuariosFactory.getUsuarioById = function(id) {
        return $http.get(urlPerfil+'/'+id);
    };

    return UsuariosFactory;
}]);