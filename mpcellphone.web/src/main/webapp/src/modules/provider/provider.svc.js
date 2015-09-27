(function(ng){
    var mod = ng.module('providerModule');
    
    mod.service('providerService', ['CrudCreator','providerContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.getRolePr = function() {
                return this.api.one('../users/currentUser').get();   
            }
    }]);
})(window.angular);
