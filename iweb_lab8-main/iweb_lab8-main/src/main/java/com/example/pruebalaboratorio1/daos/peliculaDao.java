package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                "(SELECT * FROM PELICULA ) AS A \n" +
                "INNER JOIN \n" +
                "(SELECT * FROM GENERO) AS B\n" +
                "ON A.IDGENERO = B.IDGENERO\n" +
                "INNER JOIN \n" +
                "(SELECT * FROM STREAMING) AS C\n" +
                "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                "ORDER BY RATING DESC , BOXOFFICE DESC";


        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){
            generoDao gDao = new generoDao();
            streamingDao sDao = new streamingDao();

            while (rs.next()) {
                pelicula movie = new pelicula();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idgenero = rs.getInt(7);
                String nombregenero = gDao.obtenerGenero(idgenero).getNombre();
                movie.setGenero(nombregenero);
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                int idstreaming = rs.getInt(10);
                String nameStream = sDao.obtenerStreaming(idstreaming).getNombre();
                movie.setStreaming(nameStream);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();

        return listaPeliculasFiltradas;
    }

    public void crearPelicula( ){

    }


    public void borrarPelicula(int idPelicula) {


    }


}
