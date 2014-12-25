<%--
  Created by IntelliJ IDEA.
  User: sadra
  Date: 10/31/14
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>

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
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<section>

    <h1>Who's hungry?</h1>

    <table>
        <caption>September 21st - September 28th</caption>
        <thead>
        <tr>
            <td></td>
            <td class="disabled">Sunday</td>
            <td class="disabled">Monday</td>
            <td>Tuesday</td>
            <td>Wednesday</td>
            <td>Thursday</td>
            <td>Friday</td>
            <td class="disabled">Saturday</td>
            <td></td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${users}" var="user">
            <tr>
                <td class="name"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>

                <c:forEach items="${user.dinerSchedule}" var="meal">
                    <c:choose>
                        <c:when test="${meal.availabe}">
                            <td class="selected">
                                <i class="fa fa-remove"></i>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td class="weekend"><i class="fa fa-remove"></i></td>
                        </c:otherwise>

                    </c:choose>

                </c:forEach>

                <%--<td class="weekend"></td>--%>
                <%--<td class="selected"><i class="fa fa-check"></i></td>--%>
                <%--<td class="available"><i class="fa fa-remove"></i></td>--%>
                <%--<td class="selected"><i class="fa fa-check"></i></td>--%>
                <%--<td class="holiday"><i class="fa fa-remove"></i></td>--%>


                <%--<td class="weekend"></td>--%>
                <%--<td class="weekend"></td>--%>
                <td class="total">$ ${user.balance}</td>
            </tr>
        </c:forEach>

        </tbody>

    </table>


</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="<c:url value="/resources/js/vendor/jquery-1.10.2.min.js" />"><\/script>')</script>
<script src="<c:url value="/resources/js/plugins.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

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
