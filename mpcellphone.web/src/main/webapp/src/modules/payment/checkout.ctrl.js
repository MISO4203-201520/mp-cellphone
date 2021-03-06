(function (ng) {
    var mod = ng.module('paymentModule');
    mod.controller('checkoutCtrl', ['CrudCreator', '$scope', 'authService', 'cartItemService', '$location'
                , 'checkoutService', 'shippingService', 'creditCardService', 'shippingTypeService', '$log',
        'shippingProductService', 'stateService', 'cityService',
        function (CrudCreator, $scope, authSvc, ciSvc, $location, chSvc, shpSvc, ccSvc, stSvc, $log, proSvc, stateSvc, citySvc) {
            $scope.cartItems = [];
            $scope.payment = {};
            $scope.totalCompra = 0;
            $scope.totalC = 0;
            ciSvc.fetchRecords().then(function (data) {
                for (var i = 0, l = data.length; i < l; i++) {
                    if (data[i].name) {
                        $scope.cartItems.push(data[i]);
                        $scope.totalCompra += data[i].product.price;
                    } else {
                        break;
                    }
                }
            });
            /*------------ Shipping Type ----------*/
            $scope.shippingTypeHolders = [];
            stSvc.fetchRecords().then(function (data) {
                for (var i = 0, l = data.length; i < l; i++) {
                    if (data[i].name) {
                        $scope.shippingTypeHolders.push(data[i]);
                    }
                }
                setTimeout(function () {
                    var types = $('#shippingType').children();
                    while ($(types[0]).text().indexOf('Select a Shipping Type') === -1) {
                        $(types[0]).remove();
                        types = $('#shippingType').children();
                    }
                }, 200);
            });
            $scope.onShippingChange = function () {
                $scope.shippingType = $('#shippingType').val();
                var e = document.getElementById("shippingType");
                $scope.shippingTypeValue = $(e.options[e.selectedIndex]).attr('data-price');
                $('.alert.alert-dismissible.alert-success.hidden').removeClass('hidden');
            };
            $scope.states = [];
            stateSvc.fetchRecords().then(function (data) {
                $scope.states = data;
                setTimeout(function () {
                    var types = $('#state').children();
                    while ($(types[0]).text().indexOf('Select a State') === -1) {
                        $(types[0]).remove();
                        types = $('#state').children();
                    }
                }, 200);
            });
            $scope.onChangeCity = function () {
                var state = $('#state').val();
                citySvc.getCityByState(state).then(function (data) {
                    $scope.city = data;
                    setTimeout(function () {
                        var types = $('#city').children();
                        while ($(types[0]).text().indexOf('Select a City') === -1) {
                            $(types[0]).remove();
                            types = $('#city').children();
                        }
                    }, 200);
                });
            };
            setTimeout(function () {
                var types = $('#city').children();
                while ($(types[0]).text().indexOf('Select a City') === -1) {
                    $(types[0]).remove();
                    types = $('#city').children();
                }
            }, 200);
            /*-------------- Shipping ------------*/
            $scope.shipping = {};
            $scope.shipping.time = Math.ceil(Math.random() * 5);
            $scope.submitShippingData = function () {
                var shippingData = {};
                citySvc.getCityById($scope.shipping.city).then(function (data) {
                    $scope.c = data.name;
                });
                stateSvc.getStateById($scope.shipping.state).then(function (data) {
                    $scope.s = data.name;
                });
                shippingData.state = $scope.shipping.state;
                shippingData.country = $scope.shipping.country;
                shippingData.city = $scope.shipping.city;
                shippingData.address = $scope.shipping.address;
                shippingData.stimatedTime = $scope.shipping.time;
                var shippingType = {};
                var data = $scope.shipping.shippingTypeHolders;
                for (var i = 0; i < $scope.shippingTypeHolders.length; i++) {
                    shippingType = $scope.shippingTypeHolders[i];
                    if ($scope.shippingTypeHolders[i].id === data) {
                        break;
                    }
                }
                shippingData.shipType = shippingType;
                if (shippingData.state && shippingData.country && shippingData.city
                        && shippingData.address) {
                    shpSvc.saveRecord(shippingData).then(function (data) {
                        $scope.shippingData = data;
                        var pestana = $('li.active');
                        document.getElementById('shippingData').style.display = "none";
                        document.getElementById('paymentData').style.display = "block";
                        pestana.next('li').addClass('active').show();
                        pestana.removeClass('active');
                    });
                }
                $scope.totalC = parseInt($scope.totalCompra) + parseInt($scope.shippingTypeValue);
            };
            /*------------ Credit card ----------*/
            $scope.creditCardHolders = [];
            ccSvc.fetchRecords().then(function (data) {
                for (var i = 0, l = data.length; i < l; i++) {
                    if (data[i].methodName) {
                        $scope.creditCardHolders.push(data[i]);
                    }
                }
                setTimeout(function () {
                    var types = $('#creditCardType').children();
                    while ($(types[0]).text().indexOf('Select a Card Provider') === -1) {
                        $(types[0]).remove();
                        types = $('#creditCardType').children();
                    }
                }, 200);
            });
            $scope.changePayment = function (evt) {
                switch (evt.currentTarget.id) {
                    case 'creditCard':
                        document.querySelector('#pseForm').style.display = "none";
                        document.querySelector('#creditCardForm').style.display = "block";
                        break;
                    case 'pse':
                        document.querySelector('#creditCardForm').style.display = "none";
                        document.querySelector('#pseForm').style.display = "block";
                        break;
                }
            };
            /*------------ Order ----------*/
            $scope.submitPayment = function () {
                var order = {};
                order.ship = $scope.shippingData;
                order.state = "En proceso";
                order.client = authSvc.getCurrentUser();
                order.totalDiscount = 0;
                order.totalSale = $scope.totalC;
                var creditCard = {};
                var data = $scope.payment.creditCardHolder;
                for (var i = 0; i < $scope.creditCardHolders.length; i++) {
                    creditCard = $scope.creditCardHolders[i];
                    if ($scope.creditCardHolders[i].id == data) {
                        break;
                    }
                }
                order.paymentMethod = creditCard;
                order.numberCard = $scope.payment.cardNumber;
                order.totalTax = 0;
                order.bank = $scope.payment.clientBankName;
                order.expirationDate = $scope.payment.cardExpiration;
                order.svc = $scope.payment.cardCode;
                chSvc.saveRecord(order).then(function (data) {
                    $scope.order = data;
                    $scope.submitOrderData();
                });
            };
            $scope.changeView = function () {
                var pestana = $('li.active');
                document.querySelector('#paymentData').style.display = "none";
                document.querySelector('#confirmationTmpl').style.display = "block";
                pestana.next('li').addClass('active').show();
                pestana.removeClass('active');
            };
            $scope.saleService = function (i) {
                $log.log($scope.cartItems[i]);
                $log.log($scope.cartItems[i].product.id);
                $.ajax({
                    url: '/mpcellphone.web/webresources/sale',
                    method: 'POST',
                    data: JSON.stringify({
                        productId: $scope.cartItems[i].product,
                        orderId: $scope.order,
                        providerId: $scope.cartItems[i].product.provider,
                        clientId: authSvc.getCurrentUser()}),
                    dataType: 'json',
                    contentType: "application/json"
                }).success(function () {
                    $log.log("Request success");
                    proSvc.updateProduct($scope.cartItems[i]);
                    ciSvc.deleteRecord($scope.cartItems[i]);
                    if (i === ($scope.cartItems.length - 1)) {
                        $('.mask').remove();
                        $('#modalPay').modal('show');
                    }
                }).fail(function (jqXHR, textStatus) {
                    $log.log("Request failed: " + textStatus);
                });
            };
            /** Compendio de orden **/
            $scope.submitOrderData = function () {
                for (var i = 0, l = $scope.cartItems.length; i < l; i++) {
                    $scope.saleService(i);
                }
            };
            $scope.pay = function () {
                $('body').append('<div class="mask modal" style="display:block;">');
                $('.mask').append('<div class="modal-body">' +
                        '<div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 50%; height: 25px; margin: 45vh 25vw;">' +
                        '<span class="sr-only">100% Complete</span>' +
                        '</div>' +
                        '</div>');
                $scope.submitPayment();
            };
            $scope.finish = function () {
                $('#modalPay').modal('hide');
                $location.path('/catalog');
            };
        }]);
})(window.angular);