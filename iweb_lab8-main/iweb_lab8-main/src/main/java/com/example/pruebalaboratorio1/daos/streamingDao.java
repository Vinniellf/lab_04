package com.example.pruebalaboratorio1.daos;
import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class streamingDao extends baseDao{
    public ArrayList<streaming> listarStreaming()  {

        ArrayList<streaming> listaS = new ArrayList<>();
        String sql = "select *from streaming";


        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                streaming s1 = new streaming();
                s1.setIdSreaming(rs.getInt(1));
                s1.setNombre(rs.getString(2));
                listaS.add(s1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaS;
    }
    public streaming obtenerStreaming(int id) {
        streaming s = new streaming();
        String sql = "select * from streaming where idStreaming = ?";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    s.setIdSreaming(rs.getInt(1));
                    s.setNombre(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return s;
    }
}
