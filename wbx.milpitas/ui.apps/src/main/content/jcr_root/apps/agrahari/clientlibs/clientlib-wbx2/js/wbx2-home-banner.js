function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return "";
    }
    
    setTimeout(function(){
        console.log("500");
        var flag = location.href.indexOf("/content/webex/global") > -1;
        var tempFlag = location.href.indexOf("/global/") > -1 && location.href.indexOf("tempFlag") > -1;
        var cLocale = $.cookie("user_locale");
        if(!flag || tempFlag) {
            //come from other countries, with param r (region)
            var region = getQueryString("r").toUpperCase();
            if(region) {
                var arrUrl = location.href.split(/[\?&]r=/);
                if(region == 'US' || region == 'AU' || region == 'GB' || region == 'IT') {
                    $.cookie("user_locale", region, {path: '/',expires:30});
                    cLocale = region;
                }
                if(location.href.indexOf("/en_US/") > -1) {
                    location.assign(arrUrl[0]);
                } else {
                    window.history.pushState({}, 0, arrUrl[0])
                } 
            }

            //located in other countries
            switch(cLocale){
                case "BR" : location.href = "https://www.webex.com.br/"; break;
                case "CA": 
                    if($.cookie("user_lang") == "fr") {
                        location.href = "https://www.webex.ca/fr/";
                    } else {
                        location.href = "https://www.webex.ca/en/";
                    }
                    break;
                case "CN" : location.href = "https://www.webex.com.cn/"; break;
                case "HK" : location.href = "https://www.webex.com.hk/"; break;
                case "IN" : location.href = "https://www.webex.co.in/"; break;
                // case "IT" : location.href = "https://www.webex.co.it/"; break;
                case "KR" : location.href = "https://www.webex.co.kr/"; break;
                case "MX" : location.href = "https://www.webex.com.mx/"; break;
            };

            //redirect to corresponding lang
            change_currency(cLocale);
            var cLang = get_lang(cLocale);
            $.cookie("user_lang", cLang, {path: '/',expires:30});
            if(location.href.indexOf("/en_US/") > -1) {
                redirUrl = location.href.replace("/en_US/","/c/" + cLang + "/");
            } else {
                redirUrl = location.protocol + "//" + location.host + "/" + cLang + "/index.html";
            } 
            switch(cLang){
                case "de" : location.href = redirUrl; break;
                case "fr" : location.href = redirUrl; break;
                case "es" : location.href = redirUrl; break;
                case "ja" : location.href = redirUrl; break;
            };
        }
    },500);
