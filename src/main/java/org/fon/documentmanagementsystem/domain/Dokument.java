/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nevenac
 */
@Entity
@Table(name = "dokument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dokument.findAll", query = "SELECT d FROM Dokument d")
    , @NamedQuery(name = "Dokument.findByIdDokumenta", query = "SELECT d FROM Dokument d WHERE d.idDokumenta = :idDokumenta")
    , @NamedQuery(name = "Dokument.findByNaziv", query = "SELECT d FROM Dokument d WHERE d.naziv = :naziv")
    , @NamedQuery(name = "Dokument.findByDatumKreiranja", query = "SELECT d FROM Dokument d WHERE d.datumKreiranja = :datumKreiranja")
    , @NamedQuery(name = "Dokument.findByNapomena", query = "SELECT d FROM Dokument d WHERE d.napomena = :napomena")})
public class Dokument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdDokumenta")
    private Long idDokumenta;
    @Size(max = 100)
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "DatumKreiranja")
    @Temporal(TemporalType.DATE)
    private Date datumKreiranja;
    @Size(max = 1000)
    @Column(name = "Napomena")
    private String napomena;
    @Lob
    @Size(max = 65535)
    @Column(name = "Fajl")
    private String fajl;
    @JoinColumn(name = "IdTipaDokumenta", referencedColumnName = "IdTipaDokumenta")
    @ManyToOne
    private Tipdokumenta idTipaDokumenta;
    @JoinColumn(name = "IdAktivnosti", referencedColumnName = "Id")
    @ManyToOne
    private Aktivnost idAktivnosti;

    public Dokument() {
    }

    public Dokument(Long idDokumenta) {
        this.idDokumenta = idDokumenta;
    }

    public Long getIdDokumenta() {
        return idDokumenta;
    }

    public void setIdDokumenta(Long idDokumenta) {
        this.idDokumenta = idDokumenta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getFajl() {
        return fajl;
    }

    public void setFajl(String fajl) {
        this.fajl = fajl;
    }

    public Tipdokumenta getIdTipaDokumenta() {
        return idTipaDokumenta;
    }

    public void setIdTipaDokumenta(Tipdokumenta idTipaDokumenta) {
        this.idTipaDokumenta = idTipaDokumenta;
    }

    public Aktivnost getIdAktivnosti() {
        return idAktivnosti;
    }

    public void setIdAktivnosti(Aktivnost idAktivnosti) {
        this.idAktivnosti = idAktivnosti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDokumenta != null ? idDokumenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dokument)) {
            return false;
        }
        Dokument other = (Dokument) object;
        if ((this.idDokumenta == null && other.idDokumenta != null) || (this.idDokumenta != null && !this.idDokumenta.equals(other.idDokumenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fon.documentmanagementsystem.domain.Dokument[ idDokumenta=" + idDokumenta + " ]";
    }
    
}
