package org.bcie.fenix.view.gestoroperaciones;

import java.io.Serializable;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class CrearClienteBean implements Serializable{
    @SuppressWarnings("compatibility:-6475370075050406469")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    public CrearClienteBean() {
        super();
    if (logger == null) {
                   logger = ADFLogger.createADFLogger(this.getClass());
               }
    }
    
    public String crearCliente() {
        logger.log(ADFLogger.TRACE, "Inside crearCliente");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("crearCliente");
        operationBinding.execute();
          if(operationBinding.getErrors().size() != 0){
            // Manejo de errores
            return "";
        }
        else{
            Long  idCliente=(Long)operationBinding.getResult();
            return "irCrearOperacion";
        }
      
    }
    
      public BindingContainer getBindings() {
          return BindingContext.getCurrent().getCurrentBindingsEntry();
      }
}
