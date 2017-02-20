/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vukasin
 */
@Entity
@Table(name = "proces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proces.findAll", query = "SELECT p FROM Proces p")
    , @NamedQuery(name = "Proces.findById", query = "SELECT p FROM Proces p WHERE p.id = :id")
    , @NamedQuery(name = "Proces.findByNaziv", query = "SELECT p FROM Proces p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Proces.findByOznaka", query = "SELECT p FROM Proces p WHERE p.oznaka = :oznaka")
    , @NamedQuery(name = "Proces.findByOpis", query = "SELECT p FROM Proces p WHERE p.opis = :opis")
    , @NamedQuery(name = "Proces.findByNivo", query = "SELECT p FROM Proces p WHERE p.nivo = :nivo")})
public class Proces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @Column(name = "Nivo")
    private Long nivo;
    @OneToMany(mappedBy = "idProcesa")
    private List<Aktivnost> aktivnostList;
    @JoinColumn(name = "IdPodsistema", referencedColumnName = "Id")
    @ManyToOne
    private Podsistem idPodsistema;
    @OneToMany(mappedBy = "idNadProcesa")
    private List<Proces> procesList;
    @JoinColumn(name = "IdNadProcesa", referencedColumnName = "Id")
    @ManyToOne
    private Proces idNadProcesa;

    public Proces() {
    }

    public Proces(Long id) {
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

    public long getNivo() {
        return nivo;
    }

    public void setNivo(long nivo) {
        this.nivo = nivo;
    }

    @XmlTransient
    public List<Aktivnost> getAktivnostList() {
        return aktivnostList;
    }

    public void setAktivnostList(List<Aktivnost> aktivnostList) {
        this.aktivnostList = aktivnostList;
    }

    public Podsistem getIdPodsistema() {
        return idPodsistema;
    }

    public void setIdPodsistema(Podsistem idPodsistema) {
        this.idPodsistema = idPodsistema;
    }

    @XmlTransient
    public List<Proces> getProcesList() {
        return procesList;
    }

    public void setProcesList(List<Proces> procesList) {
        this.procesList = procesList;
    }

    public Proces getIdNadProcesa() {
        return idNadProcesa;
    }

    public void setIdNadProcesa(Proces idNadProcesa) {
        this.idNadProcesa = idNadProcesa;
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
        if (!(object instanceof Proces)) {
            return false;
        }
        Proces other = (Proces) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fon.documentmanagementsystem.domain.Proces[ id=" + id + " ]";
    }
    
}
