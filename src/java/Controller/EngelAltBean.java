package Controller;

import Entity.EngelAlt;
import dao.EngelAltDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "engelAltBean")
@ViewScoped
public class EngelAltBean implements Serializable {

    private EngelAlt entity;
    private EngelAltDAO dao;
    private List<EngelAlt> list;
    private List<SelectItem> tipList;
    private int selectedEngelId;

    // Sayfa yüklendiğinde mesajı sıfırlar ve yeni bir EngelAlt nesnesi oluşturur
    @PostConstruct
    public void init() {
        getDao().setIslemBasariliMesaj(null);
        entity = new EngelAlt();
    }

    // Yeni bir EngelAlt ekler ve listeyi günceller
    public void engelAltEkle() {
        this.getDao().EngelAltEkle(getEntity());
        this.list = this.getDao().EngelAltListesi();
    }

    // Belirtilen EngelAlt'ı siler
    public void engelAltSil(int engelAltId) {
        this.getDao().EngelAltSil(engelAltId);
    }

    // Seçilen Engel tipine göre alt tipleri getirir
    public List<SelectItem> engelliTipGetir() {
        return this.getDao().EngelliTipGetir();
    }

    // Seçilen Engel tipine göre tipList'i günceller
    public void engelYukle(AjaxBehaviorEvent event) {
        if (selectedEngelId != 0) {
            tipList = this.getDao().EngelAltGetir(selectedEngelId);
        } else {
            tipList = new ArrayList<>();
        }
    }

    // EngelAlt entity'sini döndürür, eğer null ise yeni bir EngelAlt oluşturur
    public EngelAlt getEntity() {
        if (this.entity == null) {
            this.entity = new EngelAlt();
        }
        return this.entity;
    }

    public void setEntity(EngelAlt entity) {
        this.entity = entity;
    }

    // DAO nesnesini döndürür, eğer null ise yeni bir EngelAltDAO oluşturur
    public EngelAltDAO getDao() {
        if (this.dao == null) {
            this.dao = new EngelAltDAO();
        }
        return this.dao;
    }

    public void setDao(EngelAltDAO dao) {
        this.dao = dao;
    }

    // EngelAlt listesini döndürür, eğer null ise DAO'dan listeyi getirir
    public List<EngelAlt> getList() {
        if (this.list == null) {
            this.list = this.getDao().EngelAltListesi();
        }
        return this.list;
    }

    // Listeyi yeniden yükler
    public void listeyenile() {
        this.list = this.getDao().EngelAltListesi();
    }

    public void setList(List<EngelAlt> list) {
        this.list = list;
    }

    // Tip listesini döndürür, eğer null ise engelTipGetir() ile listeyi doldurur
    public List<SelectItem> getTipList() {
        if (tipList == null) {
            tipList = engelliTipGetir();
        }
        return tipList;
    }

    public void setTipList(List<SelectItem> tipList) {
        this.tipList = tipList;
    }

    // Seçilen Engel ID'sini döndürür ve ayarlar
    public int getSelectedEngelId() {
        return selectedEngelId;
    }

    public void setSelectedEngelId(int selectedEngelId) {
        this.selectedEngelId = selectedEngelId;
    }

    public EngelAltBean() {
    }
}
