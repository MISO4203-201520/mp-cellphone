(function(ng){
    var mod = ng.module('questionModule');
    mod.service('questionService', ['CrudCreator','questionContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            var self = this;
            this.createAnswer = function(data){
                return self.saveRecord(data).then(function(){});
            };
            this.finByFatherId = function(id){
                return this.api.one('byFather', id).get();
            };
    }]);
})(window.angular);
