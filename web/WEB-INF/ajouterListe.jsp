<%@ page import="fr.eni.bo.ListeCourse" %>
<%@ page import="fr.eni.bo.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dhannane2021
  Date: 15/06/2021
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ajout de la liste</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<%! List<Article> listeArticlesDeLaListe; %>
<%
    listeArticlesDeLaListe = (List<Article>) request.getAttribute("listeArticlesDeLaListe");
%>
<h1>Courses</h1>
<hr>
<h2>Nouvelle Liste</h2>
<hr>
<form method ="POST" action ="ajout">
<!--overflow: scroll; dans un css pour une barre de défilement entre le  <hr> et Article: -->
Nom: <input type ="text" name="nom" id = "nom" />
<div class ="scroller">

    <c:if test = "${!empty requestScope.listeArticlesDeLaListe}" >
        <div>${listeArticlesDeLaListe.nom}</div>
    </c:if>

    <c:forEach items="${listeArticlesDeLaListe}" var="chaqueArticle">
    <div>
        ${chaqueArticle.nom}
        <form method="POST"  id =${chaqueArticle.id} action="ajout">
            <input type ="submit" name ="supprimer">
            <i class="fa-solid fa-circle-xmark">fa-shopping-basket</i>
            </input>
        </form>
    </div>
    </c:forEach>

</div>

Article: <input type = "text" name ="article" id ="article">
<!--inclure une barre déroulante sur le côté droit et
    De haut en bas:
    -Le nom de la nouvelle liste à saisir
    -Les différents articles à intégrés dans la liste avec un bouton croix pour les y supprimer.
    Un champ de saisie Suivi d'un bouton plus pour ajouter un nouvel article
    -->
    <input type="submit" name ="ajouter"><i class="fa-solid fa-circle-plus">fa-shopping-basket</i></input>
</form>
<hr>
<a href="<%=request.getContextPath()%>/accueil">
    <i class="fa-solid fa-circle-right">fa-shopping-basket</i>
</a>
<!-- Un bouton  flèche droite pour revenir à l'accueil(listes prédéfinies)-->
</body>
</html>
