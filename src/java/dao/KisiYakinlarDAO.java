package dao;

import Entity.KisiYakinlar;
import static Various.ErrorFinder.DetectError;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class KisiYakinlarDAO extends DBConnection {

    private Connection db;
    private String mesaj;

    // KisiYakinlik ekleme metodu
    public Integer KisiYakinlarEkle(KisiYakinlar yakinlik) {
        try {
            Connection conn = this.getDb();

            String callQuery = "{call INSERT_KISI_YAKINLIK(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cs = conn.prepareCall(callQuery);
            cs.setInt(1, yakinlik.getYakinlar_id());
            cs.setInt(2, yakinlik.getYakinlar_sira_no());
            cs.setInt(3, yakinlik.getMuracaat_id());
            cs.setInt(4, yakinlik.getKisi_id());
            cs.setInt(5, yakinlik.getOzel_statu_id());
            cs.setString(6, yakinlik.getYapilan_is());
            cs.setDate(7, new java.sql.Date(yakinlik.getOlum_tarihi().getTime()));
            cs.setDate(8, new java.sql.Date(yakinlik.getKayit_tarihi().getTime()));
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.registerOutParameter(10, java.sql.Types.TIMESTAMP);
            cs.registerOutParameter(11, java.sql.Types.TIMESTAMP);
            cs.execute();
            
            this.mesaj = "İşlemler başarıyla gerçekleşmiştir.";

            return cs.getInt(9);
            
        } catch (Exception ex) {
            this.mesaj = DetectError(ex);
            return null;
        }
    }

    // KisiYakinlik silme metodu
    public void KisiYakinlarSil(int yakinlarId) {
        String deleteQuery = "DELETE FROM KISI_YAKINLIK WHERE YAKINLAR_ID = ?";
        try {
            PreparedStatement ps = getDb().prepareStatement(deleteQuery);
            ps.setInt(1, yakinlarId);
            int rowsDeleted = ps.executeUpdate();

            this.mesaj = "İşlemler başarıyla gerçekleşmiştir.";
        } catch (SQLException ex) {
            this.mesaj = DetectError(ex);
        }
    }

    public Connection getDb() {
        if (this.db == null) {
            this.db = this.connect();
        }
        return db;
    }

    public void setDb(Connection db) {
        this.db = db;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
