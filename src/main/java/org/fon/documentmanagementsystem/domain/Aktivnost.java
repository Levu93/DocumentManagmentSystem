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
 * @author nevenac
 */
@Entity
@Table(name = "aktivnost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aktivnost.findAll", query = "SELECT a FROM Aktivnost a")
    , @NamedQuery(name = "Aktivnost.findById", query = "SELECT a FROM Aktivnost a WHERE a.id = :id")
    , @NamedQuery(name = "Aktivnost.findByNaziv", query = "SELECT a FROM Aktivnost a WHERE a.naziv = :naziv")
    , @NamedQuery(name = "Aktivnost.findByOznaka", query = "SELECT a FROM Aktivnost a WHERE a.oznaka = :oznaka")
    , @NamedQuery(name = "Aktivnost.findByOpis", query = "SELECT a FROM Aktivnost a WHERE a.opis = :opis")})
public class Aktivnost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id", nullable = false)
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
    @OneToMany(mappedBy = "idAktivnosti")
    private List<Dokument> dokumentList;
    @JoinColumn(name = "IdProcesa", referencedColumnName = "Id")
    @ManyToOne
    private Proces idProcesa;

    public Aktivnost() {
    }

    public Aktivnost(Long id) {
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
    public List<Dokument> getDokumentList() {
        return dokumentList;
    }

    public void setDokumentList(List<Dokument> dokumentList) {
        this.dokumentList = dokumentList;
    }

    public Proces getIdProcesa() {
        return idProcesa;
    }

    public void setIdProcesa(Proces idProcesa) {
        this.idProcesa = idProcesa;
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
        if (!(object instanceof Aktivnost)) {
            return false;
        }
        Aktivnost other = (Aktivnost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fon.documentmanagementsystem.domain.Aktivnost[ id=" + id + " ]";
    }
    
}
