<%@ page import="fr.eni.bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.bo.ListeCourse" %><%--
  Created by IntelliJ IDEA.
  User: dhannane2021
  Date: 15/06/2021
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Listes Prédéfinies</title>

</head>
<body>
<%
    List<ListeCourse> listeDeListesDeCourses = (List<ListeCourse>) request.getAttribute("listeDeListesDeCourses");
    //Test de l'affichage des noms des listes de courses
    //List
%>
<h1>Courses</h1>
<hr>
<h2>Listes prédéfinies</h2>
<hr>
<div class="scroller">

<!--overflow: scroll; dans un css pour une barre de défilement entre ces 2 <hr>-->
<c:if test = "${!empty listeDeListesDeCourses}" >
    <div>${requestScope.listeDeListesDeCourses.get(0).nom}</div>
</c:if>
<c:forEach items="${listeDeListesDeCourses}" var="chaqueListe">
    <div>${requestScope.chaqueListe.nom}
        <a href="${pageContext.request.contextPath}/panier?id=${chaqueListe.id}" class="badge"
           title="Editer la liste"><i class="fas fa-shopping-cart fa-lg">create</i></a>
        <a href="<%=request.getContextPath()%>/panier"> <!-- <form method ="GET" id=${chaqueListe.id} action="panier">
            <input type = submit name="selectionnerListe"><i class='fas fa-shopping-cart fa-lg'></i></a></input>
        </form>
        <form method ="GET" id=${chaqueListe.id} action="accueil">
            <input type = "submit" name ="supprimerListe"><i class="fa-solid fa-circle-xmark"></i></input>
        </form>-->

    </div>
</c:forEach>
    <!-- récupérer chaqueListe.id quand je clique sur le bouton selectionnerListe et envoyer la valeur sur panierServlet qui est à /panier ou panier-->
<!--inclure une barre déroulante sur le côté droit et
    De gauche à droite:
    -Le nom de la liste
    -Un symbole de caddies (cliquable) pour commencer les courses
    -Une croix pour supprimer la liste
-->

</div>
<hr>
<a href="<%=request.getContextPath()%>/ajout">
<i class="fa-solid fa-circle-plus"></i>
</a>
<!-- Un bouton + pour aller vers la pages d'ajout des listes -->
</body>
</html>
