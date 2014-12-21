<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

    <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <script src="<c:url value="/resources/js/vendor/modernizr-2.6.2.min.js" />"></script>

    <link href='http://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
</head>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<section>

    <h1>What's for lunch Ari?</h1>


        <legend class="visuallyhidden">Food order form</legend>
        <ol>
            <li>
                <label for="name">My name</label><br/>
                <input id="name" type="text" name="name" autofocus>
            </li>
            <form>
            <li class="calendar clearfix"><label for="calendar">I want my shit on:</label><br/>
                <c:forEach items="${weekList}" var="day">
                    <figure id="day1" class="available">

                        <span id="month">${day.month}</span>
                        <span id="date">${day.dayNumber}</span>
                        <span id="day">${day.day}</span>
                    </figure>
                </c:forEach>

                <%--<figure id="day1" class="selected">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">1</span>--%>
                    <%--<span id="day">Tuesday</span>--%>
                <%--</figure>--%>
                <%--<figure id="day1" class="holiday">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">2</span>--%>
                    <%--<span id="day">Wednsday</span>--%>
                <%--</figure>--%>
                <%--<figure id="day1" class="unavailable">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">3</span>--%>
                    <%--<span id="day">Thursday</span>--%>
                <%--</figure>--%>
                <%--<figure id="day1" class="available">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">4</span>--%>
                    <%--<span id="day">Friday</span>--%>
                <%--</figure>--%>
                <%--<figure id="day1" class="weekend">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">5</span>--%>
                    <%--<span id="day">Suthurday</span>--%>
                <%--</figure>--%>
                <%--<figure id="day1" class="weekend">--%>
                    <%--<span id="month">November</span>--%>
                    <%--<span id="date">6</span>--%>
                    <%--<span id="day">Sunday</span>--%>
                <%--</figure>--%>
            </li>
            <li><label for="price">Total price</label><br/>
                <span id="price">$11.00</span>
            </li>
            <li class="tip"><label for="tip">Tip <em>(optional)</em></label><br/>
                <input id="tip" type="number" name="tip" value="0">
            </li>
            <li><label for="total">Total due</label><br/>
                <span id="total">$11.00</span>
            </li>
            <li><button>Submit</button></li>
            </form>
        </ol>


    <p class="confirmation">Chill. You will get your shit on <strong>Tuesady, October 30th</strong> and <strong>Friday, November 3th</strong>. All you need to do is bring <strong>$13.50</strong> to Ari by Monday October 29th.</p>


</section>

<%--<script>requestPath=${pageContext.request.contextPath}</script>--%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="<c:url value="/resources/js/vendor/jquery-1.10.2.min.js" />"><\/script>')</script>
<script src="<c:url value="/resources/js/vendor/jquery.autocomplete.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
<%--<script src="<c:url value="/resources/js/autocomplete.js" />"></script>--%>

<script>
$(document).ready(function() {
    $( "#name" ).autocomplete({
        source: function( request, response ) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax/customer/' + $( this ).val() ,
                dataType: "json",
                data: {
                    customerName: request.term
                },
                success: function( data ) {
                    response( data );
                }
            });
        },
        minLength: 1,
//        select: function( event, ui ) {
//            log( ui.item ?
//                "Selected: " + ui.item.label :
//                "Nothing selected, input was " + this.value);
//        },
        open: function() {
            $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        },
        close: function() {
            $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        }
    });
});
</script>


<script>
    <%--$(document).ready(function() {--%>

         <%--////--%>

        <%--$('#name').autocomplete({--%>
            <%--serviceUrl: '${pageContext.request.contextPath}/ajax/customer/',--%>

            <%--paramName: "customerName",--%>
            <%--delimiter: ",",--%>
            <%--transformResult: function(response) {--%>
                <%--return {--%>

                    <%--//must convert json to javascript object before process--%>
                    <%--suggestions: $.map($.parseJSON(response), function(item) {--%>
                        <%--console.log( item );--%>
                        <%--return {--%>
                            <%--value: item.firstName,--%>
                            <%--data: item.id--%>
                        <%--};--%>
                    <%--})--%>
                <%--};--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
</script>

<!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
<script>
    (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
        e=o.createElement(i);r=o.getElementsByTagName(i)[0];
        e.src='//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
    ga('create','UA-XXXXX-X');ga('send','pageview');
</script>
</body>
</html>