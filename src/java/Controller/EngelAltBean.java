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

    @PostConstruct
    public void init() {
        getDao().setIslemBasariliMesaj(null); // Sayfa yüklendiğinde mesajı sıfırlar
        entity = new EngelAlt(); // Yeni bir EngelAlt nesnesi oluşturur
    }

    public void engelAltEkle() {
        this.getDao().EngelAltEkle(getEntity());
    }

    public void engelAltSil(int engelAltId) {
        this.getDao().EngelAltSil(engelAltId);
    }

    // Engel alt tiplerini getiren metot
    public List<SelectItem> engelTipGetir() {
        return this.getDao().EngelAltGetir(selectedEngelId);
    }

    // Engel alt tiplerini yükleyen ve tipList'i güncelleyen metot
    public void engelYukle(AjaxBehaviorEvent event) {
        if (selectedEngelId != 0) {
            tipList = this.getDao().EngelAltGetir(selectedEngelId);
        } else {
            tipList = new ArrayList<>();
        }
    }

    public EngelAlt getEntity() {
        if (this.entity == null) {
            this.entity = new EngelAlt();
        }
        return this.entity;
    }

    public void setEntity(EngelAlt entity) {
        this.entity = entity;
    }

    public EngelAltDAO getDao() {
        if (this.dao == null) {
            this.dao = new EngelAltDAO();
        }
        return this.dao;
    }

    public void setDao(EngelAltDAO dao) {
        this.dao = dao;
    }

    public List<EngelAlt> getList() {
        if (this.list == null) {
            this.list = this.getDao().EngelAltListesi();
        }
        return this.list;
    }

    public void listeyenile() {
        this.list = this.getDao().EngelAltListesi();
    }

    public void setList(List<EngelAlt> list) {
        this.list = list;
    }

    public List<SelectItem> getTipList() {
        if (tipList == null) {
            tipList = engelTipGetir();
        }
        return tipList;
    }

    public void setTipList(List<SelectItem> tipList) {
        this.tipList = tipList;
    }

    public int getSelectedEngelId() {
        return selectedEngelId;
    }

    public void setSelectedEngelId(int selectedEngelId) {
        this.selectedEngelId = selectedEngelId;
    }

    public EngelAltBean() {
    }
}
