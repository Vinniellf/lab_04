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
                if(idGenero != rs.getInt(7) ) continue;
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

                listaPeliculasFiltradas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculasFiltradas;
    }

    public void crearPelicula(pelicula p ){
        String sql = "INSERT INTO pelicula (titulo, director, anoPublicacion, duracion, rating, boxOffice, premioOscar, idGenero, idStreaming) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDirector());
            stmt.setInt(3, p.getAnoPublicacion());
            stmt.setString(4, p.getDuracion());
            stmt.setDouble(5, p.getRating());
            stmt.setDouble(6, p.getBoxOffice());
            stmt.setBoolean(7, p.isPremioOscar());
            stmt.setInt(8, Integer.parseInt(p.getGenero()));
            stmt.setInt(9, Integer.parseInt(p.getStreaming()));

            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public void borrarPelicula(int idPelicula) {
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pelicula WHERE idPelicula = ?");
             PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM protagonistas WHERE idPelicula = ?");) {

            pstmt2.setInt(1, idPelicula);
            pstmt2.executeUpdate();

            pstmt.setInt(1, idPelicula);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
