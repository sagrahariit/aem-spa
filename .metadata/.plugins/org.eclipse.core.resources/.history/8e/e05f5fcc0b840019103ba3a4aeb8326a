


function clientTimeZone() {
        var munites = new Date().getTimezoneOffset();
        var hour = parseInt(munites / 60);
        var munite = munites % 60;
        var prefix = "-";
        if (hour < 0 || munite < 0) {
            prefix = "+";
            hour = -hour;
            if (munite < 0) {
                munite = -munite;
            }
        }

        hour = hour + "";
        munite = munite + "";
        if (hour.length == 1) {
            hour = "0" + hour;
        }

        if (munite.length == 1) {
            munite = "0" + munite;
        }

        return prefix + hour + munite;

    }


    var Webex = Webex || {};
    var page_prod_lists = {};
    var prod_list_num;
    var selected_item_list;
    var callme_int_price = "";
    var token;
    var promocode = "";
    user_locale = $.cookie("user_locale");

    var locale_currency_lang = new Array();
    locale_currency_lang[0] = ["US","USD","en","en_US","USA"];
    locale_currency_lang[1] = ["GB","GBP","en","en_US","United Kingdom"];
    locale_currency_lang[2] = ["IE","EUR","en","en_US","Ireland"];
    locale_currency_lang[3] = ["NL","EUR","en","en_US","Netherlands"];
    locale_currency_lang[4] = ["BE","EUR","en","en_US","Belgium"];
    locale_currency_lang[5] = ["AT","EUR","en","en_US","Austria"];
    locale_currency_lang[6] = ["PL","EUR","en","en_US","Poland"];
    locale_currency_lang[7] = ["SE","EUR","en","en_US","Sweden"];
    locale_currency_lang[8] = ["DK","EUR","en","en_US","Denmark"];
    locale_currency_lang[9] = ["NO","EUR","en","en_US","Norway"];
    locale_currency_lang[10] = ["CH","EUR","en","en_US","Switzerland"];
    locale_currency_lang[11] = ["DE","EUR","de","de_DE","Germany"];
    locale_currency_lang[12] = ["FR","EUR","fr","fr_FR","France"];
    locale_currency_lang[13] = ["ZA","USD","en","en_IE","South Africa"];
    locale_currency_lang[14] = ["IL","USD","en","en_IE","Israel"];
    locale_currency_lang[15] = ["RO","EUR","en","en_IE","Romania"];
    locale_currency_lang[16] = ["CZ","EUR","en","en_IE","Czech Republic"];
    locale_currency_lang[17] = ["FI","EUR","en","en_IE","Finland"];
    locale_currency_lang[18] = ["HU","EUR","en","en_IE","Hungary"];
    locale_currency_lang[19] = ["BG","EUR","en","en_IE","Bulgaria"];
    locale_currency_lang[20] = ["SK","EUR","en","en_IE","Slovakia"];
    locale_currency_lang[21] = ["JP","JPY","ja","ja_JP","Japan"];
    locale_currency_lang[22] = ["SG","USD","en","en_SG","Singapore"];
    locale_currency_lang[23] = ["NZ","USD","en","en_IE","New Zealand"];
    locale_currency_lang[24] = ["VN","USD","en","en_SG","Vietnam"];
    locale_currency_lang[25] = ["AE","USD","en","en_SG","UAE"];
    locale_currency_lang[26] = ["MY","USD","en","en_SG","Malaysia"];
    locale_currency_lang[27] = ["TH","USD","en","en_SG","Thailand"];
    locale_currency_lang[28] = ["IT","EUR","en","en_IE","Italy"];
    locale_currency_lang[29] = ["ES","EUR","es","es_ES","Spain"];
    locale_currency_lang[30] = ["PT","EUR","en","en_IE","Portugal"];
    locale_currency_lang[31] = ["LU","EUR","fr","fr_FR","Luxembourg"];
    locale_currency_lang[32] = ["GR","EUR","en","en_IE","Greece"];
    locale_currency_lang[33] = ["CY","EUR","en","en_IE","Cyprus"];
    locale_currency_lang[34] = ["UA","EUR","en","en_SG","Ukraine"];
    locale_currency_lang[35] = ["SI","EUR","en","en_IE","Slovenia"];
    locale_currency_lang[36] = ["HR","EUR","en","en_IE","Croatia"];
    locale_currency_lang[37] = ["GI","GBP","en","en_IE","Gibraltar"];
    locale_currency_lang[38] = ["MT","EUR","en","en_IE","Malta"];
    locale_currency_lang[39] = ["LV","EUR","en","en_IE","Latvia"];
    locale_currency_lang[40] = ["EE","EUR","en","en_IE","Estonia"];
    locale_currency_lang[41] = ["LT","EUR","en","en_IE","Lithuania"];

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);

        if (r != null) {
            return unescape(r[2]);
        }
        return "";
    }

    Webex.digitalRiver = {
        get_token: function () {
            $.ajax({
                url: "https://buy.webex.com/store/ciscoctg/SessionToken/?apiKey=3db6d8afe2d24a78a2e7b976457a40f5&format=json&locale=" + dr_locale + "&currency=" + user_currency,
                type: 'GET',
                async: false,
                contentType: "application/json",
                dataType: "jsonp",
                error: function (data) {
                },
                success: function (data) {
                    token = data.access_token;
                    $.cookie("DR_SESSION_TOKEN", token, {expires: 30, path: '/'});
                }
            });
        },

        get_prod_list: function () {
            var timestamp = (new Date()).valueOf();
            $.ajax({
                url: "https://api.digitalriver.com/v1/shoppers/me/carts/active",
                data: {
                    token: token,
                    method: "GET",
                    format: "json",
                    expand: "all",
                    t: timestamp
                },
                dataType: "jsonp",
                error: function (data) {
                    var error_msg = JSON.stringify(data);
                    if (error_msg.indexOf("invalid_token") > 0) {
                        Webex.digitalRiver.get_token();
                    }
                },
                success: function (data) {
                    var error_msg = JSON.stringify(data);
                    if (error_msg.indexOf("invalid_token") > 0 || error_msg.indexOf("invalid token") > 0) {
                        Webex.digitalRiver.get_token();
                    }
                    else {
                    }
                }
            });
        }
    };


    Webex.digitalRiver.reset_callme_info = function(terms){
        var call_me_sku = "";
        var call_me_price = "";
        if(terms == "y"){
            call_me_sku = "WX-TNU+I-ONL" + user_currency + "-A";
        }
        else {
            call_me_sku = "WX-TNU+I-ONL" + user_currency + "-M";
        }
        for (var i = 1; i <= prod_list_num - 1; i++) {
            var prod_sku = page_prod_lists[i][0].sku;
            var prod_price = page_prod_lists[i][0].listPrice_value;

            if(prod_sku == call_me_sku){
                if(terms == "y"){
                    call_me_price = Number(prod_price) / 12;
                }
                else {
                    //modified by Chad 11-29
                    //call_me_price = prod_price;
                    call_me_price = Number(prod_price);
                }
            }
        }
        if(user_currency == "GBP"){
            callme_int_price = "&#163;" + call_me_price.toFixed(2);
        }
        if(user_currency == "EUR"){
            callme_int_price = call_me_price.toFixed(2).replace(".", ",") + " &#8364;";
        }
        if(user_currency == "USD"){
            if(terms == "y"){
                callme_int_price = "$35.75";
            }
            else {
                callme_int_price = "$42.25";
            }
        }
    };



     Webex.digitalRiver.change_product = function (obj, type) {
        if (type == 'Y') {
            $("#" + obj).removeClass("monthly-check").addClass('annual-check');
            $("#" + obj + " .Annual").addClass('disabled');

            $("#" + obj + " .Monthly").removeClass('disabled');
            //$("#"+obj+" .user-plan").html("year");
            var yid = $("#" + obj).attr("annprod");
            var mid = $("#" + obj).attr("monprod");
            $("#" + obj + " .add_dr_prod").attr("addid", yid);

            for (var i = 1; i <= prod_list_num; i++) {
                var prod_id = page_prod_lists[i][0].id;

                if (prod_id === yid) {
                    $("#" + obj + " .plan-price").html(Number(page_prod_lists[i][0].listPrice_value) / 12);
                }

                if (prod_id === mid) {
                    $("#" + obj + " .offer-prod-plan").html('Save with annual');
                }
            }
        } else {
            $("#" + obj).removeClass('annual-check').addClass("monthly-check");
            $("#" + obj + " .Monthly").addClass('disabled');
            $("#" + obj + " .Annual").removeClass('disabled');
            //$("#"+obj+" .user-plan").html("month");
            var mid = $("#" + obj).attr("monprod");
            var yid = $("#" + obj).attr("annprod");
            $("#" + obj + " .add_dr_prod").attr("addid", mid);
            for (var i = 1; i <= prod_list_num; i++) {
                var prod_id = page_prod_lists[i][0].id;

                if (prod_id === mid) {
                    $("#" + obj + " .plan-price").html(page_prod_lists[i][0].listPrice_value);
                }

                if (prod_id === yid) {
                    $("#" + obj + " .offer-prod-plan").html('$' + Number(page_prod_lists[i][0].listPrice_value) / 12 + '/month annual plan');
                }
            }
        }
    }



    Webex.digitalRiver.page_load = function(){
        var len_locale = locale_currency_lang.length;
        var country_full_name = "";
        for(var i = 0; i < len_locale; i++){
            if(locale_currency_lang[i][0] == user_locale){
                user_currency = locale_currency_lang[i][1];
                user_lang = locale_currency_lang[i][2];
                dr_locale = locale_currency_lang[i][3];
                country_full_name = locale_currency_lang[i][4];
            }
        }
        $.cookie('user_currency',user_currency, {path: '/',expires:30});
        $.cookie('user_lang',user_lang, {path: '/',expires:30});

        
        // get product information
        prod_list_num = $(".products.section div").length;

        for (var i = 1; i <= prod_list_num - 1; i++) {
            var target_id = "prod-json-" + i;
            var prod_info = $("#" + target_id).html().replace('{"p', '{"');
            var page_prod_list = $.parseJSON(prod_info);
            page_prod_lists[i] = page_prod_list[i];
        }

        if(user_currency != "USD"){
            Webex.digitalRiver.reset_callme_info("y");
        }

        if(user_currency == "USD"){
            $("#prod2-box").attr("annprod","5195652000").attr("monprod","5195651900");
            $("#prod3-box").attr("annprod","5195651300").attr("monprod","5195651200");
            $("#prod4-box").attr("annprod","5195652300").attr("monprod","5195652200");
            $("#prod2-box .add_dr_prod").attr("addid","5195652000");
            $("#prod3-box .add_dr_prod").attr("addid","5195651300");
            $("#prod4-box .add_dr_prod").attr("addid","5195651300");
            $(".plan-currency-eur").css("display", "none");
            $(".plan-currency-usd").html("&#36;").css("display", "inline-block");
            $(".add-ons-container .addon-info-outus").css("display", "none");
            $(".add-ons-container .addon-info-us").css("display", "block");
            $(".mc-plan-info .usd-c-notice").css("display", "block");
        }
        if(user_currency == "GBP"){
            $("#prod2-box").attr("annprod","5253133200").attr("monprod","5253133100");
            $("#prod3-box").attr("annprod","5253107800").attr("monprod","5253107700");
            $("#prod4-box").attr("annprod","5253108100").attr("monprod","5253108000");
            $("#prod2-box .add_dr_prod").attr("addid","5253133200");
            $("#prod3-box .add_dr_prod").attr("addid","5253107800");
            $("#prod4-box .add_dr_prod").attr("addid","5253108100");
            $(".plan-currency-eur").css("display", "none");
            $(".plan-currency-usd").html("&#163;").css("display", "inline-block");
            $(".add-ons-container .addon-info-us").css("display", "none");
            $(".add-ons-container .addon-info-outus").css("display", "block");
            // change ".add-ons-container" height , by qifa
            $(".offer-plan-contain-last .add-ons-container").addClass("no_us");
        }
        if(user_currency == "EUR"){
            $("#prod2-box").attr("annprod","5254402200").attr("monprod","5254402100");
            $("#prod3-box").attr("annprod","5254402500").attr("monprod","5254402400");
            $("#prod4-box").attr("annprod","5254402800").attr("monprod","5254402700");
            $("#prod2-box .add_dr_prod").attr("addid","5254402200");
            $("#prod3-box .add_dr_prod").attr("addid","5254402500");
            $("#prod4-box .add_dr_prod").attr("addid","5254402800");
            $(".plan-currency-usd").css("display", "none");
            $(".plan-currency-eur").html("&#8364;").css("display", "inline-block");
            $(".add-ons-container .addon-info-us").css("display", "none");
            $(".add-ons-container .addon-info-outus").css("display", "block");
            // change ".add-ons-container" height , by qifa
            $(".offer-plan-contain-last .add-ons-container").addClass("no_us");
        }

        // check token
        if (document.cookie.indexOf("DR_SESSION_TOKEN") === -1) {
            Webex.digitalRiver.get_token();
        } else {
            token = $.cookie("DR_SESSION_TOKEN");
            Webex.digitalRiver.get_prod_list();
        }

        /*$(".offer-plan-contain").each(function () {
            var mid = $(this).attr("monprod");
            var yid = $(this).attr("annprod");
            var self_id = $(this).attr("id");

            for (var i = 1; i <= prod_list_num - 1; i++) {
                var prod_id = page_prod_lists[i][0].id;
                if (prod_id === mid) {
                    var mprice = Number(page_prod_lists[i][0].listPrice_value);
                    var pint = Math.floor(mprice);
                    var pdec = mprice.toFixed(2).toString().replace(/^[^.]+/,'0').substr(1,3);
                    if(user_currency == "EUR"){
                        pdec = pdec.replace(".", ",");
                    }
                    $("#"+self_id+" .plan-price").html(pint);
                    $("#"+self_id+" .plan-price2").html(pdec);
                }

                if (prod_id === yid) {
                    var mprice = Number(page_prod_lists[i][0].listPrice_value) / 12;
                    var pint = Math.floor(mprice);
                    var pdec = mprice.toFixed(2).toString().replace(/^[^.]+/,'0').substr(1,3);
                    if(user_currency == "EUR"){
                        pdec = pdec.replace(".", ",");
                    }
                    $("#"+self_id+" .plan-price").html(pint);
                    $("#"+self_id+" .plan-price2").html(pdec);
                }
            }
        });*/
    };


    Webex.digitalRiver.checkout_prod = function () {
        //window.location.href = "https://api.digitalriver.com/v1/shoppers/me/carts/active/web-checkout?token=" + token + "&themeID=4777108300";

        window.history.pushState(null, null, window.location.href);
        checkout_link = "index/pricing/cart.html?token=" + token + "&themeID=4777108300";

        var user_lang = $.cookie("user_lang");
        if(user_lang == "de"){
            checkout_link = "index/de/pricing/cart.html?token=" + token + "&themeID=4777108300";
        }
        if(user_lang == "fr"){
            checkout_link = "index/fr/pricing/cart.html?token=" + token + "&themeID=4777108300";
        }
        if(user_lang == "es"){
            checkout_link = "index/es/pricing/cart.html?token=" + token + "&themeID=4777108300";
        }
        if(user_lang == "ja"){
            checkout_link = "index/ja/pricing/cart.html?token=" + token + "&themeID=4777108300";
        }
        
        var TrackID = getQueryString("TrackID");

        if (TrackID) {
            checkout_link = checkout_link + "&TrackID=" + TrackID;
        }

        var DG = getQueryString("DG");
        if (DG) {
            checkout_link = checkout_link + "&DG=" + DG;
        }
        window.location.href = checkout_link;
    };


    Webex.digitalRiver.add_prod_1step = function (prid, prod_name) {
        var qty = 0;
        var old_qty = 0;
        var exit_prod;
        var del_id;
        var timezone = "GMT" + clientTimeZone();

        user_currency = $.cookie("user_currency");


// fix 409, check locale, currency when customer click buy
        var is_in_list = 0;
        var final_locale = "US";
        user_locale = $.cookie("user_locale");
        if(user_locale != null || user_locale !=""){
            final_locale = user_locale;
        }
        var len_locale = locale_currency_lang.length;

        for(var i = 0; i < len_locale; i++){
            if(locale_currency_lang[i][0] == final_locale){
                is_in_list = 1;
            }
        }

        if(is_in_list == 1){
            for(var i = 0; i < len_locale; i++){
                if(locale_currency_lang[i][0] == final_locale){
                    user_currency = locale_currency_lang[i][1];
                    user_lang = locale_currency_lang[i][2];
                    dr_locale = locale_currency_lang[i][3];
                }
            }
        }
        else {
            final_locale = "US";
            user_currency = "USD";
            user_lang = "en";
            dr_locale = "en_US";
        }

        $.cookie('user_locale',final_locale, {path: '/',expires:30});
        $.cookie('user_currency',user_currency, {path: '/',expires:30});
        $.cookie('user_lang',user_lang, {path: '/',expires:30});

        $.ajax({
            url: "https://api.digitalriver.com/v1/shoppers/me?format=json&locale=" + dr_locale + "&currency=" + user_currency + "&token=" + token,
            type: 'POST',
            //async: false,
            contentType: "application/json",
            dataType: "json",
            error: function (data) {
            },
            success: function (data) {

                var timestamp1 = (new Date()).valueOf();
                $.ajax({
                    url: "https://api.digitalriver.com/oauth20/access-tokens?token=" + token + "&format=json",
                    type: "GET",
                    data: {
                        expand: "",
                        format: "json",
                        method: "GET",
                        token: token,
                        t: timestamp1
                    },
                    dataType: "jsonp",
                    error: function (data) {
                    },
                    success: function (data) {
                        var dr_currency = data.currency;

                        var timestamp = (new Date()).valueOf();
                        $.ajax({
                            url: "https://api.digitalriver.com/v1/shoppers/me/carts/active",
                            type: "GET",
                            data: {
                                expand: "lineItems.lineItem.product.sku",
                                format: "json",
                                method: "GET",
                                token: token,
                                t: timestamp
                            },
                            dataType: "jsonp",
                            error: function (data) {
                                var error_msg = JSON.stringify(data);
                                if (error_msg.indexOf("invalid_token") > 0) {
                                    Webex.digitalRiver.get_token();
                                }
                            },
                            success: function (data) {
                                var error_msg = JSON.stringify(data);
                                if (error_msg.indexOf("invalid_token") > 0 || error_msg.indexOf("invalid token") > 0) {
                                    Webex.digitalRiver.get_token();
                                }
                                else {

                                    var total_item = data.cart.totalItemsInCart;

                                    var cus_sku;
                                    var cus_term = "-M";
                                    //if($("#offer-selection-annually").is(":checked")){
                                        //cus_term = "-A";
                                    //}
                                    if($('.type-select').hasClass('type_y')){
                                        cus_term = "-A";
                                   }
                                    if(dr_currency == "USD"){
                                        sku_currency = "US";
                                    }
                                    else {
                                        sku_currency = dr_currency;
                                    }

                                    if (prod_name == "buybus") {
                                        cus_sku = "ONL-BUSINESS-" + sku_currency + cus_term;
                                    }
                                    if (prod_name == "buyplus") {
                                        cus_sku = "ONL-PLUS-" + sku_currency + cus_term;
                                    }
                                    if (prod_name == "buystarter") {
                                        cus_sku = "ONL-STARTER-" + sku_currency + cus_term;
                                    }

                                    for (var i = 1; i <= prod_list_num - 1; i++) {
                                        var prod_sku = page_prod_lists[i][0].sku;
                                        if (prod_sku === cus_sku) {
                                            prid = page_prod_lists[i][0].id;
                                        }
                                    }

                                    var add_url = "https://api.digitalriver.com/v1/shoppers/me/carts/active?productId=" + prid;

                                    /*
                                     if (getQueryString("TA") == "true") {
                                     //   add_url = add_url + "&" + document.location.search.substr(1) + "&attribute=[name.isE2ETestOrder,value.true],[name.testOrder,value.true]";
                                     add_url = add_url + "&" + document.location.search.substr(1) + "&attribute=[name.e2eTest,value.true],[name.testOrder,value.true]";
                                     }
                                     */
                                     if (getQueryString("TA") == "true") {
                                        add_url = add_url + "&testOrder=true";
                                    }
                                    var attr_para = "&attribute=[name.ShopperTimezone,value." + timezone +"],";
                                    //var attr_para = "";
                                    if (getQueryString("TA") == "true") {
                                        attr_para = attr_para + "[name.e2eTest,value.true],[name.testOrder,value.true],";
                                    }

                                    var prov_env = getQueryString("prov_env");

                                    if (prov_env) {
                                        attr_para = attr_para + "[name.prov_env,value." + prov_env + "],";
                                    }

                                    var TrackID = getQueryString("TrackID");
                                    if (TrackID) {
                                        attr_para = attr_para + "[name.TrackID,value." + TrackID + "],";
                                    }

                                    var DG = getQueryString("DG");
                                    if (DG) {
                                        attr_para = attr_para + "[name.DG,value." + DG + "],";
                                    }
                                    if (attr_para != "&attribute=") {
                                        attr_para = attr_para.substring(0, attr_para.length - 1);
                                        add_url = add_url + attr_para;
                                    }

                                    if (total_item > 0) {
                                        var items = data.cart.lineItems.lineItem;
                                        var items_count = items.length;

                                        for (var i = 0; i < items_count; i++) {
                                            sub_item = items[i];
                                            var sub_uri = sub_item.product.uri;
                                            var sub_id = sub_uri.replace("https://api.digitalriver.com/v1/shoppers/me/products/", "");
                                            var sub_num = sub_item.quantity;
                                            var item_id = sub_item.id;
                                            var sub_name = sub_item.product.displayName.toLowerCase();

                                            if (sub_name.indexOf("call me") !== -1) {// is call me

                                            }
                                            else {//not call me
                                                exit_prod = sub_id;
                                                old_qty = sub_num;
                                                del_id = item_id;

                                                if (exit_prod != prid) {
                                                    $.ajax({
                                                        url: 'https://api.digitalriver.com/v1/shoppers/me/carts/active/line-items/',
                                                        type: 'DELETE',
                                                        async: false,
                                                        data: {
                                                            token: token
                                                        },
                                                        error: function () {
                                                        },
                                                        success: function (data) {
                                                        }
                                                    });
                                                    qty = 1;
                                                    if (prod_name == "buybus") {
                                                        qty = 5;
                                                    }
                                                } else {
                                                    qty = old_qty;
                                                }
                                            }

                                        }

                                        $.ajax({
                                            url: add_url,
                                            type: "POST",
                                            data: {
                                                token: token,
                                                format: "json",
                                                method: "post",
                                                quantity: qty,
                                                expand: "all",
                                                promoCode: promocode,
                                                testOrder: "true"
                                            },
                                            dataType: "jsonp",
                                            error: function (data) {
                                            },
                                            success: function (data) {
                                                Webex.digitalRiver.checkout_prod();
                                            }
                                        });
                                    }
                                    else {
                                        qty = 1;
                                        if (prod_name == "buybus") {
                                            qty = 5;
                                        }

                                        $.ajax({
                                            url: add_url,
                                            type: "POST",
                                            data: {
                                                token: token,
                                                format: "json",
                                                method: "post",
                                                quantity: qty,
                                                expand: "all",
                                                promoCode: promocode,
                                                testOrder: "true"
                                            },
                                            dataType: "jsonp",
                                            error: function (data) {
                                            },
                                            success: function (data) {
                                                Webex.digitalRiver.checkout_prod();
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });

            }
        });
    };
function price_test_change(type) {
        var price_box_length = $(".offer-plan-contain").length;
        var t = $(type).hasClass('type_m');
        if (t) {
            Webex.digitalRiver.reset_callme_info("y");
            $('.type-select').removeClass('type_m');
            $('.type-select').addClass('type_y');
            $('.Plans--active--2i0_X .Plans--plans__slider--3q2_x').css('transform','translateX(0)');
            $('.annual-text').addClass('Plans--bold--1C7zZ');
            $('.month-text').removeClass('Plans--bold--1C7zZ');
        }
        else{
            Webex.digitalRiver.reset_callme_info("m");
             $('.type-select').removeClass('type_y');
             $('.type-select').addClass('type_m');
              $('.Plans--active--2i0_X .Plans--plans__slider--3q2_x').css('transform','translateX(30px)');
             $('.annual-text').removeClass('Plans--bold--1C7zZ');
            $('.month-text').addClass('Plans--bold--1C7zZ');
        }

        for (var k = 0; k < price_box_length; k++) {
            var obj = $(".offer-plan-contain").eq(k).attr("id");
            if (t) {
              
                var yid = $("#" + obj).attr("annprod");
                var mid = $("#" + obj).attr("monprod");
                $("#" + obj + " .add_dr_prod").attr("addid", yid);
                for (var i = 1; i <= prod_list_num - 1; i++) {
                    var prod_id = page_prod_lists[i][0].id;
                    if (prod_id === yid) {
                        var mprice = Number(page_prod_lists[i][0].listPrice_value) / 12;
                        var pint = Math.floor(mprice);
                        var pdec = mprice.toFixed(2).toString().replace(/^[^.]+/,'0').substr(1,3);
                        if(user_currency == "EUR"){
                            pdec = pdec.replace(".", ",");
                        }
                        $("#" + obj + " .plan-price").html(pint);
                        $("#" + obj + " .plan-price2").html(pdec);
                    }
                }

                $(".addon-info-us .callme-intl").text("$35.75");
                $(".addon-info-outus .callme-intl").html(callme_int_price);
            } else {
               
                var mid = $("#" + obj).attr("monprod");
                var yid = $("#" + obj).attr("annprod");
                $("#" + obj + " .add_dr_prod").attr("addid", mid);
                for (var i = 1; i <= prod_list_num - 1; i++) {
                    var prod_id = page_prod_lists[i][0].id;
                    if (prod_id === mid) {
                        var mprice = Number(page_prod_lists[i][0].listPrice_value);
                        var pint = Math.floor(mprice);
                        var pdec = mprice.toFixed(2).toString().replace(/^[^.]+/,'0').substr(1,3);
                        if(user_currency == "EUR"){
                            pdec = pdec.replace(".", ",");
                        }
                        $("#" + obj + " .plan-price").html(pint);
                        $("#" + obj + " .plan-price2").html(pdec);
                    }
                }

                $(".addon-info-us .callme-intl").text("$42.25");
                $(".addon-info-outus .callme-intl").html(callme_int_price);
            }
        }
    };
$(document).ready(function () {
        
        Webex.digitalRiver.page_load();
        // Load Annual Prices upon page load.
        price_test_change('.type-select');
        var choose_item = $('#offer-selection-annually')[0];
        //choose_item.checked = true;

        $(".add_dr_prod").bind("click", function () {
            var add_id = $(this).attr("addid");
            var prod_name = $(this).attr("id");

            var len_locale = locale_currency_lang.length;
            for(var i = 0; i < len_locale; i++){
                if(locale_currency_lang[i][0] == user_locale){
                    user_currency = locale_currency_lang[i][1];
                    user_lang = locale_currency_lang[i][2];
                }
            }

            // check token
            if (document.cookie.indexOf("DR_SESSION_TOKEN") === -1) {
                $.ajax({
                    url: "https://buy.webex.com/store/ciscoctg/SessionToken/?apiKey=3db6d8afe2d24a78a2e7b976457a40f5&format=json&locale=" + dr_locale + "&currency=" + user_currency,
                    type: 'GET',
                    async: false,
                    contentType: "application/json",
                    dataType: "jsonp",
                    error: function (data) {
                    },
                    success: function (data) {
                        token = data.access_token;
                        $.cookie("DR_SESSION_TOKEN", token, {expires: 30, path: '/'});
                        Webex.digitalRiver.add_prod_1step(add_id, prod_name);
                    }
                });
            }

            if (document.cookie.indexOf("DR_SESSION_TOKEN") !== -1) {
                Webex.digitalRiver.add_prod_1step(add_id, prod_name);
            }

        });

        var ck = document.cookie.indexOf("promocode=");
        if (ck != -1){
            $("#promote-code").val($.cookie("promocode"));
            promocode = $.cookie("promocode");
        }

       
    });
