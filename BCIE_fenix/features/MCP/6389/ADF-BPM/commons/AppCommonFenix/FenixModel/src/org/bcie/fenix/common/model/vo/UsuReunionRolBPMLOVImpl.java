package org.bcie.fenix.common.model.vo;

import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.UsuReunionRolBPMLOV;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Dec 12 22:20:08 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UsuReunionRolBPMLOVImpl extends ViewObjectImpl implements UsuReunionRolBPMLOV {
    /**
     * This is the default constructor (do not remove).
     */
    public UsuReunionRolBPMLOVImpl() {
    }
    
    public void buscarPorIdSolicitud(Number idSolicitud, Integer emiteVoto){
        try{
            setidSolicitud(idSolicitud);
            setemiteVoto(emiteVoto);
            ViewCriteria vc = getViewCriteria("UsuReunionRolBPMLOVByIdSolicitudCriteria");
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void buscarPorIdSolicitudRolBPM(Number idSolicitud, Number idRolBPM){
        try{
            setidSolicitud(idSolicitud);
            setidRolBPM(idRolBPM);
            ViewCriteria vc = getViewCriteria("UsuReunionRolBPMByIdSOlicitudRolBPM");
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the variable value for idSolicitud.
     * @return variable value for idSolicitud
     */
    public Number getidSolicitud() {
        return (Number) ensureVariableManager().getVariableValue("idSolicitud");
    }

    /**
     * Sets <code>value</code> for variable idSolicitud.
     * @param value value to bind as idSolicitud
     */
    public void setidSolicitud(Number value) {
        ensureVariableManager().setVariableValue("idSolicitud", value);
    }

    /**
     * Returns the variable value for idRolBPM.
     * @return variable value for idRolBPM
     */
    public Number getidRolBPM() {
        return (Number) ensureVariableManager().getVariableValue("idRolBPM");
    }

    /**
     * Sets <code>value</code> for variable idRolBPM.
     * @param value value to bind as idRolBPM
     */
    public void setidRolBPM(Number value) {
        ensureVariableManager().setVariableValue("idRolBPM", value);
    }

    /**
     * Returns the variable value for emiteVoto.
     * @return variable value for emiteVoto
     */
    public Integer getemiteVoto() {
        return (Integer) ensureVariableManager().getVariableValue("emiteVoto");
    }

    /**
     * Sets <code>value</code> for variable emiteVoto.
     * @param value value to bind as emiteVoto
     */
    public void setemiteVoto(Integer value) {
        ensureVariableManager().setVariableValue("emiteVoto", value);
    }
}

