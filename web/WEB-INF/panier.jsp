<%@ page import="fr.eni.bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.bo.ListeCourse" %><%--
  Created by IntelliJ IDEA.
  User: dhannane2021
  Date: 15/06/2021
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Panier</title>
</head>
<body>
<%
    ListeCourse ListeCourseCourante = (ListeCourse) request.getAttribute("listeCourseCourante");
    List<Article> listeArticlesCourants = (List<Article>) request.getAttribute("listeArticlesCourants");
%>
<h1>Courses</h1>
<hr>
<h2>Votre panier
<br>
(${ListeCourseCourante.nom}<!--Mettre ici le nom de la liste prédéfinie sélectionnée-->)</h2>
<hr>
<div class ="scroller">
<!--overflow: scroll; dans un css pour une barre de défilement entre ces 2 <hr>-->
<c:if test = "${!empty ListeCourseCourante}" >
<div>${ListeCourseCourante.nom}</div>
</c:if>
<form method ="GET" action="panier">
    <c:forEach items="${ListeCourseCourante}" var="chaqueArticle">
    <div><input type = checkbox/>${chaqueArticle.nom}<i class="fa-solid fa-circle-xmark"></i></div>
    </c:forEach>
</form>
<!--inclure une barre déroulante sur le côté droit et
    De haut en bas:
    La liste des articles de cette liste prédéfinie  précédés de checkbox pour les sélectionner dans le panier
-->
</div>
<hr>
<a href="<%=request.getContextPath()%>/accueil">
<i class="fa-solid fa-circle-left"></i>
</a>
<form method ="POST" action ="panier">
    <input type ="submit" name="reinitialiserPanier"><i class ="fas fa-eraser fa-lg"></i></input>
</form>

<!-- Un bouton  flèche droite pour revenir à l'accueil(listes prédéfinies)-->
<!-- un bouton gomme pour décocher tous les articles
</body>
</html>
