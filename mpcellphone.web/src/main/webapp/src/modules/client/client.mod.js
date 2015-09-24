(function (ng) {
    var mod = ng.module('clientModule', ['ngCrud']);

    mod.constant('clientContext', 'clients');

    mod.constant('clientModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'givenName',
                displayName: 'Given Name',
                type: 'String',
                required: true
            }, {
                name: 'surname',
                displayName: 'Surname',
                type: 'String',
                required: true
            }, {
                name: 'email',
                displayName: 'Email',
                type: 'String',
                required: true
            }, {
                name: 'phone',
                displayName: 'Phone',
                type: 'String',
                required: true
            }, {
                name: 'address',
                displayName: 'Address',
                type: 'String',
                required: true
            }, {
                name: 'city',
                displayName: 'City',
                type: 'String',
                required: true
            }, {
                name: 'country',
                displayName: 'Country',
                type: 'String',
                required: true
            }, {
                name: 'userId',
                displayName: 'UserId',
                type: 'String',
                required: true
            }], 
        childs: [{
                name: 'shoppingCart',
                displayName: 'ShoppingCart',
                //template: '', //override generic template
                ctrl: 'shoppingCartCtrl'            }        ]});
})(window.angular);
