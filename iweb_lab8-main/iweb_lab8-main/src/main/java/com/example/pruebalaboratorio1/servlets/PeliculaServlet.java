package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.daos.generoDao;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import com.example.pruebalaboratorio1.daos.streamingDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "filtrar" : request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        generoDao gDao = new generoDao();
        streamingDao streamingDao = new streamingDao();
        //listasDao listaDao = new listasDao();
        //ArrayList<genero> listarGeneros = listaDao.listarGeneros();
        //ArrayList<streaming> listarStraming = listaDao.listarStraming();

        switch (action) {
            case "listar":

                //request.setAttribute("listarGeneros", listarGeneros);
                //request.setAttribute("listarStraming", listarStraming);

                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                ArrayList<genero> listaGeneros = gDao.listarGeneros();
                request.setAttribute("listaPeliculas", listaPeliculas);
                request.setAttribute("listaGeneros", listaGeneros);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request,response);
                break;

            case "filtrar":
                //request.setAttribute("listarGeneros", listarGeneros);
                //request.setAttribute("listarStraming", listarStraming);
                int id2 = Integer.parseInt(request.getParameter("genero"));
                ArrayList<pelicula> listaPeliculas2 = null;
                if(id2 == 0) listaPeliculas2 = peliculaDao.listarPeliculas();
                else listaPeliculas2 = peliculaDao.listarPeliculasFiltradas(id2) ;
                ArrayList<genero> listaGeneros2 = gDao.listarGeneros();
                request.setAttribute("listaPeliculas", listaPeliculas2);
                request.setAttribute("listaGeneros", listaGeneros2);

                RequestDispatcher view2 = request.getRequestDispatcher("listaPeliculas.jsp");
                view2.forward(request,response);
                break;

            case "crear":
                ArrayList<genero> listaGeneros3 = gDao.listarGeneros();
                ArrayList<streaming> listaStr = streamingDao.listarStreaming();
                request.setAttribute("listaStr", listaStr);
                request.setAttribute("listaGeneros", listaGeneros3);
                RequestDispatcher view3 = request.getRequestDispatcher("crearPelicula.jsp");
                view3.forward(request,response);
                break;


            case "borrar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                peliculaDao.borrarPelicula(idPelicula);
                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;

        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        pelicula p = new pelicula();
        p.setTitulo(request.getParameter("titulo"));
        p.setDirector(request.getParameter("director"));
        p.setAnoPublicacion(Integer.parseInt(request.getParameter("anoPubicacion")));
        p.setRating(Double.parseDouble(request.getParameter("rating")));
        p.setBoxOffice(Double.parseDouble(request.getParameter("boxOffice")));
        p.setDuracion(request.getParameter("duracion"));
        p.setGenero(request.getParameter("genero"));
        p.setStreaming(request.getParameter("streaming"));
        p.setPremioOscar(Boolean.parseBoolean(request.getParameter("isPremioOscar")));


        switch (action){
            case "enviar":
                peliculaDao.crearPelicula(p);

                response.sendRedirect("listaPeliculas?action=listar");
                break;
        }
    }


}
