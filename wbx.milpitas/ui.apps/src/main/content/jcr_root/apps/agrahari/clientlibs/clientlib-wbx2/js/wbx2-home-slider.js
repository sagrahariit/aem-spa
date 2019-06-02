
    var $heroConta = $(".HomepageHero--homepageHero__slide--1HbNE");
    var $heroHeader = $(".HomepageHero--homepageHero__tab--26ERd");
    var $heroLeft = $(".HomepageHero--homepageHero__children--D3AKl .ArrowButton--left--L_iAG");
    var $heroRight = $(".HomepageHero--homepageHero__children--D3AKl .ArrowButton--right--i0JBf");
    
    $(document).ready(function () {
        $heroHeader.bind('click', {flag:true}, heroScroll);
        $heroLeft.bind('click', {dir:-1}, heroScroll);
        $heroRight.bind('click', {dir:1}, heroScroll);
        
        var isLarge = Webex.util.getWindowWidth() < 960 ? 0 : 1;
        initHeroScroll(isLarge);
        $(window).resize(function() {
            var isNewLarge = Webex.util.getWindowWidth() < 960 ? 0 : 1;
            if(isNewLarge != isLarge) {
                isLarge = isNewLarge;
                initHeroScroll(isLarge);
            } 
        });
    });

    var heroTimer = null;
    var heroTimerList = [];
    function clearHeroTimer() {
        heroTimerList.forEach(function(item){
            clearInterval(item)
        });
        heroTimerList = [];
        heroTimer = 0;
    }

    function initHeroScroll(flag) {
        if(flag) {
            heroTimer = setInterval(function(){
                heroScroll()
            }, 11000);
            heroTimerList.push(heroTimer);
        } else {
            clearHeroTimer();
        }
    }

    function heroScroll(event) {
        var actIndex = $heroConta.attr('class').split('HeroSlide_')[1].substr(0,1) - 0;
        var finActIndex = actIndex > 2 ? 0 : actIndex + 1;

        // click on hero header
        if (event && event.data && event.data.flag) {
            clearHeroTimer();
            finActIndex = $(this).index() - 1;
        }
        // click on left / right arrow
        if (event && event.data && event.data.dir) {
            finActIndex = actIndex + event.data.dir;
        }

        if(finActIndex > -1 && finActIndex < 4) {
            $heroConta.attr('class', "HomepageHero--homepageHero__slide--1HbNE HeroSlide_" + finActIndex);
            $heroHeader.eq(finActIndex).addClass("HomepageHero--isActive--1Lv3O").siblings().removeClass("HomepageHero--isActive--1Lv3O");
        }
    }

<!-- hero slide end -->



<!-- story slide -->


    var $storyConta = $(".FlipStories--flip--8-Icr .Row--row--26xi7");
    var $storyItem = $(".FlipStories--flip__transform--2geiO");
    var $storyLeft = $(".FlipStories--flip__controls--2HR3E .ArrowButton--left--L_iAG");
    var $storyRight = $(".FlipStories--flip__controls--2HR3E .ArrowButton--right--i0JBf");
    
    $(document).ready(function () {
        $storyLeft.bind('click', {dir:-1}, storyScroll);
        $storyRight.bind('click', {dir:1}, storyScroll);
        
        function storyScroll(event) {
            var actIndex = $storyConta.find('.center_front').index() - 0;
            var finActIndex = actIndex - (-event.data.dir);

            if(finActIndex > -1 && finActIndex < 3) {
                $storyItem.removeClass('center_front left_back right_back');
                if(finActIndex != 0) {
                    $storyItem.eq(finActIndex - 1).addClass('left_back');
                }
                $storyItem.eq(finActIndex).addClass('center_front');
                $storyItem.eq(finActIndex + 1).addClass('right_back');
                $storyLeft.next().text(finActIndex + 1 + " / 3");

                $storyConta.css('transform', 'translateX(' + finActIndex * -85 + '%)');
            }
        }
    });

<!-- story slide end -->



<!-- HowTo slide -->

    var $howToConta = $(".HowTo--how--2Fn5M .Carousel--carousel__children--2EZxD");
    var $howToLeft = $(".HowTo--how--2Fn5M .ArrowButton--left--L_iAG");
    var $howToRight = $(".HowTo--how--2Fn5M .ArrowButton--right--i0JBf");

    $(document).ready(function () {
        $howToLeft.bind('click', {dir:-1}, howToScroll);
        $howToRight.bind('click', {dir:1}, howToScroll);

        var isLarge = Webex.util.getWindowWidth() < 768 ? 0 : 1;
        $(window).resize(function() {
            var isNewLarge = Webex.util.getWindowWidth() < 768 ? 0 : 1;
            if(isNewLarge != isLarge) {
                isLarge = isNewLarge;
                $howToConta.css('transform', 'none');
                $howToConta.attr('active-index', 0);
            }
        });

        function howToScroll(event) {
            var actIndex = $howToConta.attr('active-index') || 0;
            var finActIndex = actIndex - (-event.data.dir);

            if(!isLarge && finActIndex > -1 && finActIndex < 4) {
                $howToConta.css('transform', 'translateX(' + finActIndex * -85 + '%)');
                $howToConta.attr('active-index', finActIndex);
            }
            if(isLarge && finActIndex > -1 && finActIndex < 2) {
                $howToConta.css('transform', 'translateX(' + finActIndex * -33.3 + '%)');
                $howToConta.attr('active-index', finActIndex);
            }
        }
    });

<!-- HowTo slide end -->
