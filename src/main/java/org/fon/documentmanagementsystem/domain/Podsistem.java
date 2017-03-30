/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nevenac
 */
@Entity
@Table(name = "podsistem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Podsistem.findAll", query = "SELECT p FROM Podsistem p")
    , @NamedQuery(name = "Podsistem.findById", query = "SELECT p FROM Podsistem p WHERE p.id = :id")
    , @NamedQuery(name = "Podsistem.findByNaziv", query = "SELECT p FROM Podsistem p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Podsistem.findByOznaka", query = "SELECT p FROM Podsistem p WHERE p.oznaka = :oznaka")
    , @NamedQuery(name = "Podsistem.findByOpis", query = "SELECT p FROM Podsistem p WHERE p.opis = :opis")})
public class Podsistem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Size(max = 100)
    @Column(name = "Naziv")
    private String naziv;
    @Size(max = 100)
    @Column(name = "Oznaka")
    private String oznaka;
    @Size(max = 100)
    @Column(name = "Opis")
    private String opis;
    @OneToMany(mappedBy = "idPodsistema")
    private List<Proces> procesList;
    @OneToMany(mappedBy = "idPodsistema")
    private List<User> userList;

    public Podsistem() {
    }

    public Podsistem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @XmlTransient
    public List<Proces> getProcesList() {
        return procesList;
    }

    public void setProcesList(List<Proces> procesList) {
        this.procesList = procesList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Podsistem)) {
            return false;
        }
        Podsistem other = (Podsistem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fon.documentmanagementsystem.domain.Podsistem[ id=" + id + " ]";
    }
    
}
