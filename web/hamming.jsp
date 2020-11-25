<%-- 
    Document   : hamming
    Created on : Sep 20, 2020, 9:02:11 AM
    Author     : miker
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Codage</title>
    </head>
    <body>
        <h1>Codage de Hamming ETU837</h1>
        <form method="GET">
            <label for="texte">Texte</label>
            <input type="text" id="texte" name="texte"/>
            <input type="submit" value="Ok"/>
        </form>
    <c:if test='${envoye != null && envoye != ""}'>
        <fieldset>
            <legend>Envoyé: <c:out value="${envoye}" /></legend>
            <table>
                <thead>
                    <tr>
                        <th>Caractère</th>
                        <th>Binaire</th>
                        <th>Code Hamming</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${envois}" var="envoi">
                    <tr>
                        <td><c:out value="${envoi.caractere}"/></td>
                    <td style="text-align: right"><c:out value="${envoi.binaire}"/></td>
                    <td style="text-align: right"><c:out value="${envoi.hamming}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>

        <fieldset>
            <legend>Réception: <c:out value="${reception}" /></legend>
            <table>
                <thead>
                    <tr>
                        <th>Caractère</th>
                        <th>Binaire</th>
                        <th>Code Hamming</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${receptions}" var="reception">
                    <tr>
                        <td><c:out value="${reception.caractere}"/></td>
                    <td style="text-align: right"><c:out value="${reception.binaire}"/></td>
                    <td style="text-align: right"><c:out value="${reception.hamming}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>

        <fieldset>
            <legend>Correction: <c:out value="${correction}" /></legend>
            <table>
                <thead>
                    <tr>
                        <th>Caractère</th>
                        <th>Binaire</th>
                        <th>Code Hamming</th>
                        <th>Position erreur (0 si pas d'erreur, -1 si position non trouvée)</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${corrections}" var="correction">
                    <tr>
                        <td><c:out value="${correction.caractere}"/></td>
                    <td style="text-align: right"><c:out value="${correction.binaire}"/></td>
                    <td style="text-align: right"><c:out value="${correction.hamming}"/></td>
                    <td style="text-align: right"><c:out value="${correction.positionErreur}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </c:if>
</body>
</html>
