package view.customizationimpl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;
import oracle.javatools.resourcebundle.BundleFactory;

import oracle.adf.share.ADFContext;

import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.runtimeconfig.IRuntimeConfigService;
import oracle.bpel.services.workflow.runtimeconfig.model.AttributeLabelType;
import oracle.bpel.services.workflow.runtimeconfig.model.AttributeLabelUsageList;
import oracle.bpel.services.workflow.runtimeconfig.model.AttributeLabelUsages;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import oracle.bpm.ui.customization.CustomLink;
import oracle.bpm.ui.customization.IBPMUICustomizations;


public class WorkspaceCustomisationImpl implements IBPMUICustomizations {
    
    /**
     * Define paquete y nombre de Resource Bundle
     */
    private static final String BUNDLE = "view.customizationimpl.CustomizationBundle";
    
    /**
     * Define la clave de Bundle para el nombre del vinculo del Gestor de Operaciones
     */
    private static final String GESTOR_OPERACIONES_KEY = "vinculo.gestor.operaciones.etiqueta";
    
    /**
     * Define la clave de Bundle para el nomnbre del vinculo del Gestor de Clientes
     */
    private static final String GESTOR_CLIENTES_KEY = "vinculo.gestor.clientes.etiqueta";
    
    /**
     * Define la clave de Bundle para el nombre del vinculo del Gestor de Desembolsos
     */
    private static final String GESTOR_DESEMBOLSOS_KEY = "vinculo.gestor.desembolso.etiqueta";
    
    private static Map displayNameMap = new HashMap();

    @SuppressWarnings("unchecked")
    public WorkspaceCustomisationImpl() {
        //displayNameMap.put("instanceId", "Instance ID");
        displayNameMap.put("protectedTextAttribute1", "No. Operación");
    }

    public List<CustomLink> getCustomGlobalLinks() {
            
        String[] rolesUsuario = ADFContext.getCurrent().getSecurityContext().getUserRoles();
        Boolean accesoFenix = false;
        for (String rolUsuario : rolesUsuario) {
            if (rolUsuario.toUpperCase().contains("FENIX")) {
                accesoFenix = true;
            }
        }
        
        String strUrlGestorOper = null;
        String strUrlGestorCli = null;
        String strUrlGestorDes = null;
        
        ConfiguracionImpl confImpl = new ConfiguracionImpl();
        strUrlGestorOper = confImpl.getUrlApp(ConfiguracionImpl.AppKey.GESTOR_OPERACIONES);
        strUrlGestorCli = confImpl.getUrlApp(ConfiguracionImpl.AppKey.GESTOR_CLIENTES);
        strUrlGestorDes = confImpl.getUrlApp(ConfiguracionImpl.AppKey.GESTOR_DESEMBOLSO);
        
        String strEtiquetaGestorOper = null;
        String strEtiquetaGestorCli = null;
        String strEtiquetaGestorDes = null;
        
        strEtiquetaGestorOper = getStringFromBundle(GESTOR_OPERACIONES_KEY);
        strEtiquetaGestorCli = getStringFromBundle(GESTOR_CLIENTES_KEY);
        strEtiquetaGestorDes = getStringFromBundle(GESTOR_DESEMBOLSOS_KEY);
        
        CustomLink globalLink1 = null;
        CustomLink globalLink2 = null;
        CustomLink globalLink3 = null;
        
        if(strUrlGestorOper != null){
            globalLink1 = new CustomLink(strEtiquetaGestorOper, strUrlGestorOper, "user");    
        }
        
        if(strUrlGestorCli != null){
            globalLink2 = new CustomLink(strEtiquetaGestorCli, strUrlGestorCli, "user");    
        }
        
        if(strUrlGestorDes != null){
            globalLink3 = new CustomLink(strEtiquetaGestorDes, strUrlGestorDes, "user");    
        }

        List<CustomLink> globalLinks = new ArrayList<CustomLink>();
        if (accesoFenix) {
            if(globalLink1 != null){
                globalLinks.add(globalLink1);    
            }
            
            if(globalLink2 != null){
                globalLinks.add(globalLink2);    
            }
            
            if(globalLink3 != null){
                globalLinks.add(globalLink3);    
            }
        }
        return globalLinks;
    }

    public String getColumnNames() {
        return "title,protectedTextAttribute1,assigned,creator"; //taskNumber,instanceId 
    }

    public String getColumnDisplayName(IWorkflowServiceClient iWorkflowServiceClient,
                                       IWorkflowContext iWorkflowContext,
                                       String string) {
        initDisplayMap(iWorkflowServiceClient, iWorkflowContext);
        return (String)displayNameMap.get(string);
    }

    public static void initDisplayMap(IWorkflowServiceClient client,
                                      IWorkflowContext context) {
        if (displayNameMap == null) {
            synchronized (String.class) {
                if (displayNameMap == null) {
                    displayNameMap = new HashMap();
                    try {
                        IRuntimeConfigService service =
                            client.getRuntimeConfigService();
                        AttributeLabelUsageList list =
                            service.getAttributeLabelUsages(context, "Text");
                        List<AttributeLabelUsages> list1 =
                            list.getAttributeLabelUsages();
                        for (AttributeLabelUsages usage : list1) {
                            AttributeLabelType type = usage.getLabel();
                            displayNameMap.put(type.getTaskAttribute(),
                                               type.getLabelName());
                        }
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * Obtiene cadena valor del Resource Bundle
     * @param psKey contiene clave de bundle
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
}

