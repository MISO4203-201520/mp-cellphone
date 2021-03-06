(function (ng) {
    var mod = ng.module('paymentModule', ['ngCrud']);

    mod.constant('checkoutContext', 'orders');
    mod.constant('shippingContext', 'ship');
    mod.constant('creditCardContext', 'payment_method');
    mod.constant('shippingTypeContext', 'shipping_type');
    mod.constant('saleContext', 'sale');
    mod.constant('shippingProductContext', 'products');
    mod.constant('stateContext', 'states');
    mod.constant('cityContext', 'cities');
/*
    mod.constant('checkoutModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'quantity',
                displayName: 'Quantity',
                type: 'Integer',
                required: true
            }, {
                name: 'product',
                displayName: 'Product',
                type: 'Reference',
                service: 'productService',
                options: [],
                required: true
            }]});*/
})(window.angular);
