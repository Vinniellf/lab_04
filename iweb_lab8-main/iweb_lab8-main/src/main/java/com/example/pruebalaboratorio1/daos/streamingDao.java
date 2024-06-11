package com.example.pruebalaboratorio1.daos;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class streamingDao extends baseDao{
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
