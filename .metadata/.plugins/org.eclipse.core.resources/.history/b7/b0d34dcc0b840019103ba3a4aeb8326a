
<!-- Download aside start -->


  $(function(){
    var isMob = isMobile.any(),
    osType = detectOS();
    
    if (osType === 'macOS') {
      $('.meetings-app').attr('href','https://akamaicdn.webex.com/client/webexapp.dmg');
      $('.teams-app').attr('href','https://akamaicdn.webex.com/client/webexapp.dmg');
    }
    
    $('.meetings-app').on('click', function() {
      window.location.href = $(this).attr("href");
      if (!isMob) {
        setTimeout(function() {window.location.href="./downloads/downloads-thank-you-meetings.html";},5000);
      }
    });
    $('.teams-app').on('click', function() {
      window.location.href = $(this).attr("href");;
      if (!isMob) {
        setTimeout(function() {window.location.href="./downloads/downloads-thank-you.html";},5000);
      }
    });
  });

  // if(isMobile.any()) alert('Mobile'); if( isMobile.iOS() ) alert('iOS');
  var isMobile = {
    Android: function() {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
        return navigator.userAgent.match(/IEMobile/i) || navigator.userAgent.match(/WPDesktop/i);
    },
    any: function() {
        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
  };
  
  function detectOS() {
    var userAgent = window.navigator.userAgent,
    platform = window.navigator.platform,
    macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K'],
    windowsPlatforms = ['Win32', 'Win64', 'Windows', 'WinCE'],
    iosPlatforms = ['iPhone', 'iPad', 'iPod'],
    os = null;
    
    if (macosPlatforms.indexOf(platform) !== -1) {
      os = 'macOS';
    } else if (iosPlatforms.indexOf(platform) !== -1) {
      os = 'iOS';
    } else if (windowsPlatforms.indexOf(platform) !== -1) {
      os = 'Windows';
    } else if (/Android/.test(userAgent)) {
      os = 'Android';
    } else if (!os && /Linux/.test(platform)) {
      os = 'Linux';
    }
    
    return os;
  };
