package com.example.pruebalaboratorio1.daos;
import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class generoDao extends baseDao{
    public ArrayList<genero> listarGeneros()  {

        ArrayList<genero> listaGeneros = new ArrayList<>();
        String sql = "select *from genero";


        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                genero g1 = new genero();
                g1.setIdGenero(rs.getInt(1));
                g1.setNombre(rs.getString(2));
                listaGeneros.add(g1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaGeneros;
    }
    public genero obtenerGenero(int id) {
        genero g = new genero();
        String sql = "select * from genero where idGenero = ?";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    g.setIdGenero(rs.getInt(1));
                    g.setNombre(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return g;
    }
}
