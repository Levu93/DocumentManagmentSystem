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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nevenac
 */
@Entity
@Table(name = "tipdokumenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipdokumenta.findAll", query = "SELECT t FROM Tipdokumenta t")
    , @NamedQuery(name = "Tipdokumenta.findByIdTipaDokumenta", query = "SELECT t FROM Tipdokumenta t WHERE t.idTipaDokumenta = :idTipaDokumenta")
    , @NamedQuery(name = "Tipdokumenta.findByNazivTipa", query = "SELECT t FROM Tipdokumenta t WHERE t.nazivTipa = :nazivTipa")})
public class Tipdokumenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdTipaDokumenta")
    private Long idTipaDokumenta;
    @Size(max = 100)
    @Column(name = "NazivTipa")
    private String nazivTipa;
    @OneToMany(mappedBy = "idTipaDokumenta")
    private List<Dokument> dokumentList;

    public Tipdokumenta() {
    }

    public Tipdokumenta(Long idTipaDokumenta) {
        this.idTipaDokumenta = idTipaDokumenta;
    }

    public Long getIdTipaDokumenta() {
        return idTipaDokumenta;
    }

    public void setIdTipaDokumenta(Long idTipaDokumenta) {
        this.idTipaDokumenta = idTipaDokumenta;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @XmlTransient
    public List<Dokument> getDokumentList() {
        return dokumentList;
    }

    public void setDokumentList(List<Dokument> dokumentList) {
        this.dokumentList = dokumentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipaDokumenta != null ? idTipaDokumenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipdokumenta)) {
            return false;
        }
        Tipdokumenta other = (Tipdokumenta) object;
        if ((this.idTipaDokumenta == null && other.idTipaDokumenta != null) || (this.idTipaDokumenta != null && !this.idTipaDokumenta.equals(other.idTipaDokumenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fon.documentmanagementsystem.domain.Tipdokumenta[ idTipaDokumenta=" + idTipaDokumenta + " ]";
    }
    
}
