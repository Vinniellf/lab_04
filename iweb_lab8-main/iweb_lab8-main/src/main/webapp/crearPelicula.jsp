<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.pelicula"%>
<%@page import="com.example.pruebalaboratorio1.beans.genero"%>
<%@page import="com.example.pruebalaboratorio1.beans.streaming"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Job job = (Job) request.getAttribute("job");
    ArrayList<streaming> listaStr = (ArrayList) request.getAttribute("listaStr");
    ArrayList<genero> listaGeneros = (ArrayList) request.getAttribute("listaGeneros");
    //ArrayList<streaming> listaStreaming = (ArrayList) request.getAttribute("listarStraming");
    String searchTerm = request.getParameter("searchTerm");
    NumberFormat formatter = NumberFormat.getInstance();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creacion Pelicula</title>
</head>
<body>
<h2>Formulario de Creacion de Pelicula</h2>
<form action="listaPeliculas?action=enviar" method="post">


    <label for="titulo">Titulo:</label><br>
    <input type="text" id="titulo" name="titulo" required><br>

    <label for="director">Director:</label><br>
    <input type="text" id="director" name="director" required><br>

    <label for="anoPubicacion">Ano Publicacion:</label><br>
    <input type="text" id="anoPubicacion" name="anoPubicacion" required><br>

    <label for="rating">Rating:</label><br>
    <input type="text" id="rating" name="rating" required><br>

    <label for="boxOffice">BoxOffice:</label><br>
    <input type="text" id="boxOffice" name="boxOffice" required><br>

    <label for="duracion">Duracion:</label><br>
    <input type="text" id="duracion" name="duracion" required><br>

    <label for="genero">Genero</label><br>
    <select name="genero" class="form-select" id="genero">
        <% for(genero g: listaGeneros){ %>
        <option value="<%=g.getIdGenero()%>"> <%=g.getNombre()%> </option>
        <% } %>
    </select><br>

    <label for="streaming">Streaming</label><br>
    <select name="streaming" class="form-select" id="streaming">
        <% for(streaming s: listaStr){ %>
        <option value="<%=s.getIdSreaming()%>"> <%=s.getNombre()%> </option>
        <% } %>
    </select><br>


    <label for="premioOscar">PremioOscar:</label><br>
    <select id="premioOscar" name="premioOscar">
        <option value="true">Sí</option>
        <option value="false">No</option>
    </select><br>



    <input type="submit" value="enviar">
</form>
</body>
</html>
