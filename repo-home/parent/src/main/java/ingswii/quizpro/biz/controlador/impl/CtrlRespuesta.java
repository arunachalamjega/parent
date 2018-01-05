package ingswii.quizpro.biz.controlador.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoRespuesta;
import ingswii.quizpro.biz.controlador.*;
import ingswii.quizpro.biz.vo.ram.Respuesta;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Alejandro
 */
@Service("ctrlRespuesta")
public class CtrlRespuesta implements ICtrlRespuesta {
    
    private IDaoRespuesta daoRespuesta;

    public Respuesta obtenerRespuestaPorId(Long id) {
        return daoRespuesta.buscarPorId(id);
    }

    public IDaoRespuesta getDaoRespuesta() {
        return daoRespuesta;
    }

    public void setDaoRespuesta(IDaoRespuesta daoRespuesta) {
        this.daoRespuesta = daoRespuesta;
    }
}
