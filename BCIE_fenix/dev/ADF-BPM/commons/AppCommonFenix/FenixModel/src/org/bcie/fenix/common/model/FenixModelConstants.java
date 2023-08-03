package org.bcie.fenix.common.model;



public class  FenixModelConstants
{
    
    public static final String ERROR_AL = "Error al ";
    public static final String SUSPENDIDO = "TIPO_SOLICITUD";
    public static final Long TIPO_MONTO_TOTAL = 1L;
    public static final Long TIPO_MONTO_SOLICITADO = 2L;
    public static final String ID_USUARIO_REUNION_CC = "17";
    public static final Integer TIPO_MONTO_ESCRITURADO = 4;
    public static final Integer TIPO_ACCION_SINCRONIZADO = 4; // De acuerdo a tabla TCA_ACCION_DOCUMENTO
    
    /********* START CONSTANTES PARA REGISTRAR DATOS LINEA CREDITO ********/
    public static final String ES_REVOLVENTE_TRUE="Y";
    public static final String ES_REVOLVENTE_FALSE="N";
    public static final Integer ES_REVOLVENTE_TRUE_INT=1;
    public static final Integer ES_REVOLVENTE_FALSE_INT=0;
    public static final Integer ES_RECURSOS_ORDINARIOS_TRUE=0;
    public static final Integer ES_RECURSOS_ORDINARIOS_FALSE=1;
    public static final String ID_TAREA_VALIDAR_LINEA_CREDITO="64";
    public static final String ID_TAREA_REGISTRAR_LINEA_CREDITO="62";
    /********* END CONSTANTES PARA REGISTRAR DATOS LINEA CREDITO *********/
    
    /**************************MATRIZ TCC ************/
    public static final String ACTUALIZAR_ESTADO_TCC = "Actualizar Estado TCC ";
    public static final Integer ESTADO_TCC_FORMALIZADA = 15;
    public static final Integer ESTADO_TCC_POR_VALIDAR = 23;
    public static final Integer ESTADO_TCC_NUEVA = 7;
    public static final Integer ESTADO_TCC_SUGERIDA = 1;
    public static final Integer ESTADO_TCC_MANDATORIA = 8;
    public static final Integer ESTADO_TCC_POR_APROBAR = 13;
    public static final Integer ESTADO_TCC_NUEVA_POR_APROBAR = 16;
    public static final Integer ESTADO_TCC_APROBADA = 14;
    public static final Integer ESTADO_TCC_POR_PAGAR = 4;
    public static final Integer ESTADO_TCC_ELIMINADA = 22;
    public static final Integer ESTADO_TCC_DISPENSADA = 5;
    public static final Integer ESTADO_TCC_INACTIVA = 30;
    public static final Integer ESTADO_TCC_ACTUALIZADA = 32;
    public static final Integer SUBESTADO_TCC_POR_VALIDAR = 21;
    public static final Integer SUBESTADO_TCC_EDITADA = 9;
    public static final Integer SUBESTADO_TCC_ELIMINADA = 10;
    public static final Integer SUBESTADO_TCC_MANDATORIA_EDITADA = 11;
    public static final Integer SUBESTADO_TCC_EXCEPTUADA = 12;
    public static final Integer SUBESTADO_TCC_APROBADA_EDITADA = 27;
    public static final Integer SUBESTADO_TCC_INACTIVA = 31;
    
    public static final Integer SUBESTADO_TCC_POR_MODIFICAR = 18;
    public static final Integer SUBESTADO_TCC_POR_DISPENSAR = 17;
    public static final Integer SUBESTADO_TCC_POR_ELIMINAR = 19;
    
    public static final Integer SUBESTADO_TCC_CREADA = 33;
    public static final Integer SUBESTADO_TCC_EN_REGISTRO = 34;
    public static final Integer ESTADO_TCC_REGISTRADA = 35;
    /**************************PA01 LAVADO ACTIVOS ************/
    public static final String TIPO_ORIGEN_NUEVA = "NUEVA";
    
    /**************************PA03 ADMINISTRACIÓN CONDICIONES ************/
    public static final String ACTUALIZAR_ESTADO_TCC_CONDICIONES = "Actualizar Estado TCC - Condiciones ";
    public static final String CONSULTAR_VALIDACION_CONDICION_BY_ROL = "Consultar Validacion Condicion By Rol ";
    public static final String AGREGAR_CONDICIONES_POR_VALIDAR = "Agregando condiciones por validar..";
    public static final Integer ESTADO_TCC_VALIDADA = 26;
    public static final Integer SUBESTADO_TCC_VALIDADA = 28;
    public static final Integer SUBESTADO_TCC_VALIDA_PARCIALMENTE = 25;
    public static final Integer ID_TCA_CONTROL_CONDICION = 2;
    public static final Integer ID_TAREA_CUMPLIR_CONDICIONES = 69;
    public static final Integer FINALIZO_TAREA = 1;
    public static final Integer RESPONSABLE_OPERACION = 1;
    public static final Integer ESTADO_CUMPLIDOR = 0;
    public static final String ACTUALIZAR_ESTADO_CONDICIONES = "Actualizar Estado de las condiciones..";
    
    /**************************PC08 CIERRE *************************/
    // WSC - WEB SERVICE CALL
    public static final String WSC_CONSULTAR_CONTRATO_OPERACION = "Consultar contrato por opercion en PC08 Cierre";
    
    /**************************PA12 PREPAGO CONSTANTES *************************/
    //Tabla Consolidado del pago
    public static final String MONTO_PREPAGO="Monto prepago (principal)";
    public static final String INTERESES="Intereses";
    public static final String PENALIDAD="Penalidad";
    public static final String CARGO_TRAMITE="Cargo por trámite";
    public static final String OTRO_CARGO="Otros cargos";
    public static final String FONDO ="1";
    
    //Tabla Detalle de cargos y penalidades
    public static final String OTRO_CARGO_COPRES="Otros cargos(COPRES)";
    public static final String FUENTE_EXTERNA="Fuentes externas(DAECI)";
    public static final String COBERTURAS="Coberturas(MDC)";
    public static final String ESTRUCTURACIONES="Estructuraciones(SEPRI)";
    public static final String PENALIDAD_CARGO_TRAMITE="Penalidades y cargos por tramite(COFI)";
    public static final Integer MONEDA_USD =1;
    public static final String MONEDA_DESCRIPCION_USD ="USD";
    //Identificador de roles
    public static final Integer COPRES = 53;
    public static final Integer DAECI = 21;
    public static final Integer MDC = 25;
    public static final Integer SEPRI = 2;
    public static final Integer COFI = 7;
    
    public static final Integer ID_TCA_COMISION = 14;
    public static final Integer ID_TCA_ESTADO_TCC = 7;
    public static final String DESCRIPCION_COMISION = "Incumplimiento de prepago";
    
    //Tipos de resolucion
    public static final Integer PRE10_2008 = 1;
    public static final Integer PRE28_2003 = 2;
    public static final Integer OTRAS_CONDICIONES = 3;
    
    //Gestionar coberturas  
    public static final Integer EXISTE_DIFERENCIA_COBERTURA_SI = 1;
    public static final Integer EXISTE_DIFERENCIA_COBERTURA_NO = 0;
    
    // WSC - WEB SERVICE CALL
    public static final String WSC_CONSULTAR_REPORTE_PREPAGO = "Consultar reporte de prepago por ID en PA12 PREPAGO";
    public static final String WSC_CONSOLIDADO_PREPAGO = "Consolidado del pago de prepago por ID en PA12 PREPAGO";
    
    /**************************CG10 GESTOR DE DESEMBOLSOS *************************/
    
    public static final String WSC_CONSULTAR_OPERACION_DESEMBOLSOS = "Consultar Operacion por idOperacion para Gestor de desembolsos";
    public static final String WSC_PROPAGAR_TRANSFERENCIA = "Propagar transferencia para obtener un BHQ";
    public static final String WSC_PROPAGAR_TRANSFERENCIA_DIRECTO = "Propagar transferencia Sin salida de recursos";
    public static final String WSC_PROPAGAR_CONTRATO_DESEMBOLSO = "Propagar contrato desembolso para obtener un BHQ y fecha efectiva";
    public static final String WSC_PROPAGAR_RESERVA_FONDOS = "Propagar reserva de fondos";
    public static final String WSC_APLICAR_COMPENSACION_A_DESEMBOLSO = "Aplicando la compensacion a el desembolso";
    public static final String WSC_LIQUIDAR_CONTRATO = "Realizar la generacion de la FT05 para el contrato de desembolso";
    public static final String WSC_ENVIAR_CORRO_ELECTRONICO = "Enviando correo electronico";
    public static final long TIPO_MONTO_FORMALIZADO = 5;
    public static final long TIPO_MONTO_DISPONIBLE_DESEMBOLSO = 6;
    public static final String DIRECTO_CLIENTE = "DIRECTO_CLIENTE";
    //Estados de la solicitud para el gestor de desembolsos
    public static final int ESTADO_SOLICITUD_CREADA = 5;
    public static final int ESTADO_SOLUCITUD_EN_VALIDACION_RECURSOS = 6;
    public static final int ESTADO_SOLICITUD_EN_CUMPLIMIENTO_CONDICIONES = 7;
    public static final int ESTADO_SOLICITUD_EN_PROCESO = 8;
    public static final int ESTADO_SOLICITUD_CERRADA = 9;
    public static final int ESTADO_SOLICITUD_CREADO = 10;
    public static final int ESTADO_SOLICITUD_VALIDACION_DE_RECURSOS = 11;
    public static final int ESTADO_SOLICITUD_Cumplimiento_de_condiciones = 12;
    public static final int ESTADO_SOLICITUD_Preparado = 13;
    public static final int ESTADO_SOLICITUD_EN_TRANSITO = 14;
    public static final int ESTADO_SOLICITUD_APROBADO_POR_GERENTE = 15;
    public static final int ESTADO_SOLICITUD_FONDOS_RESERVADOS = 16;
    public static final int ESTADO_SOLICITUD_REGISTRADO = 17;
    public static final int ESTADO_SOLICITUD_DESEMBOLSADO = 18;
    public static final int ESTADO_SOLICITUD_DESESTIMADO = 19;
    public static final int ESTADO_SOLICITUD_LIQUIDADO = 22;
    public static final int ESTADO_SOLICITUD_NOLIQUIDADO = 25;
    
    /**************************PC05 FORMALIZACION *************************/
    public static final String VALIDAR_CONDICIONES_FORMALIZACION ="Validar Condiciones de Formalización";
    public static final Integer TIPO_PLAZO_DIA = 1;
    public static final Integer TIPO_PLAZO_MES = 2;
    public static final Integer TIPO_PLAZO_ANIO = 3;
    
    public static final Integer ASIGNA_TAREA_BPM_ACTUALIZA_TCC = 52;
    public static final Integer CONVERTIR_MILISEGUNDOS_SEGUNDOS = 1000;
    public static final Integer INIT_ATTR_ID = 1;
    public static final String PARAM_INIT_CONFIG_VISIBILIDAD = "1";
    public static final String PARAM_PORSEGUIMIENTO = "PORSEGUIMIENTO";
    public static final String PARAM_CANCELAREFORMULACION = "CANCELAREFORMULACION";
    public static final String PARAM_TIPOENMIENDA = "TIPO_ENMIENDA";
    public static final String PARAM_TIPOINICIO = "TIPO_INICIO";
    public static final String PARAM_TRUE = "true";
    public static final String PARAM_FALSE = "false";
    public static final String PARAM_DEFAULT_ID = "1";
    public static final Integer PARAM_PROGRAMADO_SI = 1;
    public static final Integer PARAM_PROGRAMADO_NO = 0;
    public static final Integer PARAM_COMP_CONCILIADO_SI = 1;
    public static final Integer PARAM_COMP_CONCILIADO_NO = 0;
    public static final Integer PARAM_ETAPA_ELEGIBILIDAD = 1;
    public static final Long PARAM_INIT_ID = 1L;
    public static final Integer BANESTATUS_TRUE = 1;
    public static final Integer BANESTATUS_FALSE = 0;
    public static final Integer COMISIONCOMPARTIDA_DEFAULT = 0;
    public static final Integer IDTCAESTADOTCC_DEFAULT = 1;
    public static final Integer IDTCASUBESTADOTCC_DEFAULT = 9;
    public static final Integer BANSUGERIDA_DEFAULT = 0;
    public static final String SI_TEXT = "Si";
    public static final String NO_TEXT = "No";
    public static final String ACTUALIZAR_TEXT = "Actualizar";
    public static final String ELIMINAR_TEXT = "Eliminar";
    public static final Integer EN_ANALISIS_DEFAULT = 1;
    public static final Integer VERSION_EVALUACION = 0;
    public static final String PRELIMINAR_TEXT = "PRELIMINAR";
    public static final String APROBADO_TEXT = "APROBADO";
    public static final oracle.jbo.domain.Number NIVEL_APROBACION_COMITE_CREDITO_NUM = new oracle.jbo.domain.Number(1);
    public static final oracle.jbo.domain.Number NIVEL_APROBACION_PRESIDENCIA_NUM = new oracle.jbo.domain.Number(2);
    public static final Integer NIVEL_APROBACION_COMITE_CREDITO_INT = 1;
    public static final Integer NIVEL_APROBACION_PRESIDENCIA_INT = 2;
    public static final Integer ES_FUERA_SISTEMA = 1;
    public static final Integer TIPO_MIEMBRO_COMITE_CREDITO = 1;
    public static final Integer TIPO_MIEMBRO_NOTIFICACION = 2;
    public static final Integer TIPO_MIEMBRO_EQUIPO_TRABAJO = 3;
    public static final Integer EMITE_VOTO = 1;
    public static final Integer TAREA_RAROC_C = 11;
    public static final Integer TAREA_HOJAPCT_C = 12;
    public static final Integer TAREA_OPINION_C = 13;
    public static final Integer TAREA_ADQUISION_C = 14;
    public static final Integer TAREA_REVCOMISION_C = 15;
    public static final Integer TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD_C = 234;
    public static final Integer TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD_C = 235;
    public static final Integer TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD_C = 236;
    public static final Integer RAROC_C = 4;
    public static final Integer OPINION_TECNICA_C = 5;
    public static final Integer HOJA_PCT_C = 6;
    public static final Integer ADQUISICIONES_C = 7;
    public static final String TAREA_EQUIPOS_C = "9";
    public static final String TAREA_ENVIAR_ANALISIS_C = "16";
    public static final Integer BAN_RETORNO = 1;
    public static final Integer BANVALIDADOJEFATURA = 1;
    public static final Integer TIPO_REUNION_PRESENCIAL = 1;
    public static final Integer TIPO_REUNION_VIRTUAL = 2;
    public static final Integer ES_FINANCIERA_ESPECIAL_TRUE=1;
    public static final Integer ES_FINANCIERA_ESPECIAL_FALSE=0;
    public static final Integer ES_PRINCIPAL_FALSE = 0;
    public static final Integer TIPO_CLIENTE_PROSPECTO = 1;
    public static final Integer TIPO_CLIENTE_DEFINITIVO = 2;
    public static final Integer ES_COMISION_COMPARTIDA = 1;
    public static final Integer NO_ES_COMISION_COMPARTIDA = 0;
    public static final Integer TERMINO_TIPO_MONEDA_USD=1;
    public static final Integer TERMINO_MONTO_SOLICITADO=10;
    public static final Integer TERMINO_MONTO_APROBADO=11;
    public static final Integer TERMINO_MONTO_FORMALIZADO=12;
    public static final Integer TERMINO_MONTO_ESPECIFICO=13;
    public static final Integer TERMINO_OTRAS_CONDICIONES_TASA_PORCENTAJE=2;
    public static final Integer TERMINO_OTRAS_CONDICIONES_TASA_INTERES=14;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_APROBACION=8;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_ESCRITURACION=9;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_PRIMER_DESEMBOLSO=10;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_NOTIFICACION=12;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_VIGENCIA=13;
    public static final Integer TERMINO_TIPO_FECHA_INICIO_ESPECIFICA=11;
    public static final Integer TERMINO_PLAZO_FORMALIZACION=1;
    public static final Integer TERMINO_PLAZO_INICIO_DESEMBOLSO=2;
    public static final Integer TERMINO_PLAZO_DESEMBOLSO_TOTALIDAD_RECURSOS=3;
    public static final Integer TERMINO_PLAZO_LINEA=4;
    public static final Integer TERMINO_PLAZO_PRESENTAR_INFORMES=5;
    public static final Integer TERMINO_PLAZO_FINANCIAMIENTO=6;
    public static final Integer TERMINO_PLAZO_GARANTIA=7;
    public static final Integer TERMINO_PLAZO_PERIODO_GRACIA=8;
    //[KB:14513]
    public static final Integer TERMINO_EN_DESEMBOLSO=26;
    //[KB:14513]
    public static final Integer TERMINO_RECURSO_EXTERNO=18;
    //[KB:14513]
    public static final Integer TERMINO_FECHA_ESCRITURACION=30;
    //[KB:14513]
    public static final Integer TERMINO_FECHA_VIGENCIA=31;
    public static final Integer ID_MODALIDAD_TCC_TREE=1;
        
    public static final oracle.jbo.domain.Number ID_CLIENTE_INIT_NUM = new oracle.jbo.domain.Number(0);
    public static final oracle.jbo.domain.Number ID_OPERACION_INIT_NUM = new oracle.jbo.domain.Number(0);
    public static final oracle.jbo.domain.Number ID_ANALISIS_INIT_NUM = new oracle.jbo.domain.Number(0);
    public static final int ID_ROL_SECRETARIO_COMITE_CREDITO = 17;
    public static final int ID_ROL_ASISTENTE_PRESIDENCIA = 18;
    
    public static final int COL_01 = 1;
    public static final int COL_02 = 2;
    public static final int COL_03 = 3;
    public static final int COL_04 = 4;
    public static final int COL_05 = 5;
    public static final int COL_06 = 6;
    public static final int COL_07 = 7;
    public static final int COL_08 = 8;
    public static final int COL_09 = 9;
    public static final int COL_10 = 10;
    public static final int COL_11 = 11;
    public static final int COL_12 = 12;
    public static final int COL_13 = 13;
    public static final int COL_14 = 14;
    public static final int COL_15 = 15;
    public static final int COL_16 = 16;
    
    public static final Integer RANGE_SIZE_ALL = -1;
    
    //US2012
    public static final String ID_HT03_ELABORAR_BORRADOR_CONTRATO = "55";
    
    // WSC - WEB SERVICE CALL
    public static final String WSC_VALIDAR_CLIENTE = "Validar Cliente";
    public static final String WSC_CREAR_CALIFICACION_INICIAL_RIESGO = "Crear calificación inicial riesgo";
    public static final String WSC_CREAR_OPERACION = "Crear operación";
    public static final String WSC_INICIO_ENMIENDA = "Inicio Enmienda";
    public static final String WSC_VALIDAR_GERENTE_RESPONSABLE = "Validar generente responsable";
    public static final String WSC_ACTUALIZAR_DETALLE_OPERACION = "Actualizar detalle operación";
    public static final String WSC_SUSPENDER_CANCELAR_OPERACION = "Suspender cancelar operación";
    public static final String WSC_REACTIVAR_OPERACION = "Reactivar operación";
    public static final String WSC_ACTUALIZAR_OPERACION_REFORMULAR = "Actualizar operación reformular";
    public static final String WSC_CONSULTAR_OPERACION_REFORMULAR = "Consultar operación reformular";
    public static final String WSC_CONSULTAR_DETALLE_OPERACION = "Consultar detalle operación";
    public static final String WSC_ELIMINACION_TAREA_DOCUMENTO = "Eliminación de documento por tarea";
    public static final String WSC_VALIDAR_ESTADO_OPERACION = "Validar estado operación";
    public static final String WSC_INGRESAR_RAROC = "Ingresar RAROC";
    public static final String WSC_OBTENER_DETALLE_OPERACION = "Obtener detalle operación";
    public static final String WSC_ACTUALIZAR_ELIMINAR_DOCUMENTO = "Actualizar eliminar documento";
    public static final String WSC_CONSULTAR_CUESTIONARIOS = "Consultar cuestionarios";
    public static final String WSC_CONSULTAR_EVALUACION = "Consultar evaluación";
    public static final String WSC_DUPLICAR_EVALUACION = "Duplicar evaluación";
    public static final String WSC_CREAR_EVALUACION = "Crear evaluación";
    public static final String WSC_ELIMINAR_CUESTIONARIO_EVALUACION = "Eliminar cuestionario evaluación";
    public static final String WSC_ACTUALIZAR_OPERACION_ANALISIS = "Actualizar operación análisis";
    public static final String WSC_ACTUALIZAR_OPERACION_EVALUACION = "Actualizar operación evaluación";
    public static final String WSC_CREAR_COMISION = "Crear comisión";
    public static final String WSC_CONFIGURAR_SOLICITUD_OPERACION = "Configurar solicitud operación";
    public static final String WSC_CREAR_SOLICITUD_APROBACION = "Crear solicitud aprobación";
    public static final String WSC_CREAR_SOLICITUD_APROBACION_WS = "Crear solicitud aprobación WS";
    public static final String WSC_CONSULTAR_LINEA_CREDITO = "Consultar línea de crédito";
    public static final String WSC_CONSULTAR_LINEA_CREDITO_BY_ID_LINEA_CREDITO = "Consultar línea de crédito por ID línea de crédito";
    public static final String WSC_ACTUALIZAR_LINEA_CREDITO = "Actualizar línea de crédito";
    public static final String WSC_ACTIVAR_OPINION = "Activar opinión";
    public static final String WSC_VALIDA_LINEA_CREDITO = "Valida línea de crédito";
    public static final String WSC_OBTENER_COMISION = "Obtener comisión";
    public static final String WSC_ACTUALIZAR_TCC_ADMON = "Actualizar TCC Admon";
    public static final String WSC_ACTUALIZAR_TCC = "Actualizar TCC";
    public static final String WSC_GRUPOS_USUARIOS = "Grupos usuarios";
    public static final String WSC_ACTUALIZAR_CONTACTO = "Actualizar contacto";
    public static final String WSC_AGREGAR_CONTACTO = "Agregar contacto";
    public static final String WSC_ELIMINAR_CONTACTO = "Eliminar contacto";
    public static final String WSC_REFRESCAR_CONTACTOS = "Refrescar contactos";
    public static final String WSC_VALIDAR_CONDICION = "Validar condición";
    public static final String WSC_CONSULTAR_CLIENTE = "Consultar cliente";
    public static final String WSC_ACTUALIZAR_CLIENTE = "Actualizar cliente";
    public static final String WSC_CREAR_CLIENTE = "Crear cliente";
    public static final String WSC_ELIMINAR_CLIENTE = "Eliminar cliente";
    public static final String WSC_INICIALIZAR_DETALLE_CLIENTE = "Inicializar detalle cliente";
    public static final String WSC_INICIO_ADMINISTRAR_CLIENTE = "Inicio administrar cliente";
    public static final String WSC_INICIO_ADMINISTRAR_OPERACION = "Inicio administrar operacion";
    public static final String WSC_REFRESCAR_DATOS_CLIENTE = "Refrescar datos cliente";
    public static final String WSC_ACTUALIZAR_DECLARACION_JURADA = "Actualizar declaración jurada";
    public static final String WSC_CONSULTAR_DECLARACION_JURADA = "Consultar declaración jurada";
    public static final String WSC_CREAR_DECLARACION_JURADA = "Crear declaración jurada";
    public static final String WSC_PROPAGAR_FUENTE = "Propagar fuente";
    public static final String WSC_CONSULTAR_FUENTE = "Consultar fuente";
    public static final String WSC_GENERAR_AVISO_COBRO_CLIENTE = "Generar aviso cobro cliente";
    public static final String WSC_GENERAR_AVISO_COBRO_CLIENTE_V2 = "Generar aviso cobro cliente V2";
    public static final String WSC_CONSULTAR_DESEMBOLSO = "Consultar desembolso";
    public static final String WSC_CONSULTAR_SALDO_CONTRATO_DESEMBOLSO = "Consultar Saldo";
    public static final String WSC_OBTENER_URL = "Obtener URL";
    public static final String WSC_CONSOLIDAR_TRANSFERENCIA = "Propagar transferencia";
    public static final String WSC_VALIDAR_REGLAS = "Validar reglas";
    public static final String WSC_VALIDAR_REGLAS_TAREA = "Validar tarea reglas";
    public static final String WSC_RESPONSABLE_OPERACION = "Actualizar responsable de operación";
    public static final String WSC_CAMBIAR_RESPONSABLE_OPERACION = "Cambiar responsable de operación";
    public static final String WSC_CAMBIAR_RESPONSABLE_CLIENTE = "Cambiar responsable del cliente";
    public static final String WS_VERIFICAR_VALIDACION_ASIGNACION = "Verificar validación de asignación";
    public static final String WSC_OBTENER_COMISIONES_NO_PAGADAS = "Obtener comisiones no pagadas";
    public static final String WSC_COMPENSAR_EVALUACION = "Compensar evaluación";
    public static final String WSC_IMPLEMENTACION_PCT = "Iniciar proceso de implementacion PCT";
    public static final String WSC_CALCULAR_CUOTAS = "Coalcular cuotas";
    public static final String WSC_CONSULTAR_FECHA_CARGO= "Coalcular cuotas";
    public static final String WSC_APLICAR_ENVIO_COBRO= "Invocando servicio Aplicar envio al cobro..";
    public static final String WSC_OBTENER_FACTIBILIDAD= "Invocando servicio Obtener factibilidad..";
    public static final String WSC_CREAR_LINEA_CREDITO = "Crear línea de crédito";
    public static final String WSC_CONSULTAR_GERENTE_PAIS = "Consultar Gerente de país";
    public static final String WSC_CONSULTAR_USUARIOS_GRUPO = "Consultar Usuarios por Grupo";
    public static final String WSC_CONSULTAR_COMISION = "Consultando comisiones por operacion";
    public static final String WSC_CONSULTAR_LISTA_USUARIOS = "Consultar Usuarios por Lista";
    public static final String WSC_CONSULTAR_LIMITES= "Consultar Limites Excepcion";
    public static final String WSC_CONFIGURAR_SOLICITUD_APROBACION = "Configurar solicitud aprobación";
    public static final String WSC_ELIMINAR_DOCUMENTO_FINALIZA_PROCESO = "Eliminación de documento en cancelar proceso Gestion de cobro.";
    public static final String WSC_BLOQUEAR_PREGUNTAS = "Bloqueo de preguntas service";
    public static final String WSC_CREAR_ACTUALIZAR_BIT_DOC = "Crear actualizar bitacora documentos";
    public static final String WSC_INICIO_FORMALIZACION_ENMIENDA = "Inicio Formalización Enmienda";
    public static final String WSC_ACTUALIZAR_ESTADO_CONTRATO = "Actualizar Estado Contrato";
    public static final String WSC_LINEA_CREDITO= "Inicio Linea de Credito";
    
  // WSC - WEB SERVICE CALL
    
    public static final String TERMINO="TERMINO";
    public static final String CONDICION="CONDICION";
    public static final String COMISION="COMISION";
    
    public static final Integer ID_TERMINO = 1;
    public static final Integer ID_COMISION = 3;
    public static final Integer ID_CONDICION = 2;

    
    public static final Integer ID_PROCESO_ANALISIS = 3;
    public static final Integer ID_PROCESO_APROBACION = 4;
    public static final Integer ID_PROCESO_EVALUACION = 6;
    public static final Integer ID_EVALUACION_SIEMAS = 31;
    
    // Seguimiento Crediticio
    public static final Integer ID_ESTADO_SCR_VIGENTE = 2;
    public static final Integer ID_ESTADO_SCR_SUSPENSO = 5;
    public static final Integer ID_ESTADO_SCR_ARGUMENTACION = 6;
    
    public static final Integer ID_ESTADO_SCR_DETERIORO = 4;
    public static final Boolean CAMBIO_SCR_PERSPECTIVA_DETERIORO_TRUE = Boolean.TRUE;
    public static final Boolean CAMBIO_SCR_PERSPECTIVA_DETERIORO_FALSE = Boolean.FALSE;	
    
    public static final int ID_CATEGORIA_SEGUIMIENTO = 1;
    public static final int ID_CATEGORIA_UCE = 2;
    public static final int ID_CATEGORIA_SUPERVISION = 3;
    public static final int ID_CATEGORIA_EVALUACION_IBCIE = 4;
    public static final int ID_CATEGORIA_EVALUACION_SIEMAS = 5;
    public static final int CATEGORIA_CIERRE = 19;
    public static final int CATEGORIA_CIERRE_FIDEICOMISOS = 28;

    public static final int ESTADO_ACCION_REGISTRADO = 2;
    public static final int ESTADO_ACCION_PORATENDER = 3;
    public static final int ESTADO_ACCION_ATENDIDA = 4;
    public static final int ESTADO_ACCION_VALIDADA = 5;
    public static final int ESTADO_ACCION_DESESTIMADA = 6;
    public static final int ESTADO_ACCION_ELIMINADA = 7;
    public static final int ESTADO_ACCION_VENCIDA = 1;
    
    public static final int ID_PROCESO_SEGUIMIENTO = 20;
    public static final int ID_PROCESO_UCE = 27;
    public static final int ID_PROCESO_SUPERVISION = 23;
    public static final int ID_PROCESO_EVALUACIONES = 4;
    public static final int ID_PROCESO_EVALUACION_IBCIE = 6;
    public static final int ID_PROCESO_EVALUACION_SIEMAS = 7;
    public static final int ID_GESTOR_CLIENTES = 15;
    public static final int ID_GESTOR_OPERACIONES = 13;
    
    public static final int ID_CATEGORIA_OPERACIONES = 2;
    public static final int ID_CATEGORIA_CLIENTES = 1;
    public static final int ID_CATEGORIA_PROCESOS = 3;
    
    public static final String viewCriteriaGestor="AccionesTablaVOCriteriaGestores";
    public static final String viewCriteriaValidacion="AccionesTablaVOValidacionCategoria";
    public static final String viewCriteriaCategoria="AccionesTablaVOPorCategoria";
    public static final String viewCriteriaCategoriaOtros="AccionOtrosCategoriaVC";
    public static final String viewCriteriaGestorOtros="AccionOtrosGestorVC";
    public static final String viewCriteriaGestorIngresador="AccionIngresadorValidaGestorVC";
    public static final String viewCriteriaCategoriaIngresador="AccionIngresadorValidaCategoriaVC";
    
    public static final String ROL_PROCCESS_CONTROL="RolProcess";
    public static final String ROL_INGRESADOR_VALIDADOR="RolIngresadorValidador";
    public static final String ROL_OTROS="RolOtros";
    
    
    public static final String TIPO_INICIO_NORMAL="NORMAL";
    public static final String TIPO_INICIO_ARGUMENTACION="ARGUMENTACION";
    public static final String TIPO_INICIO_SUSPENSO="SUSPENSO";
    public static final String TIPO_INICIO_RETORNO="RETORNO";
    
    public static final int ID_TAREA_GESTOR_OPERACION= 65;
    public static final int ID_TAREA_GESTOR_CLIENTES = 112;
    public static final int ID_REALIZAR_AJUSTES_ASIGNACION= 163;
    
    //GESTOR DESEMBOLSOS - Id's Estados de Contratos de desembolso en Tabla TCA_ESTADO_OPERACION
    //Donde el tipo de estado es 3 (Contrato de desembolso).
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO=10;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDACION_RECURSOS=11;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO_CONDICIONES=12;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO=13;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_TRANISTO=14;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO_CLIENTE=15;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS_RESERVADOS=16;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO=17;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO=18;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO=19;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR=21;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION=24;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDADO_POR_IMPLEMENTACION=26;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO=22;
    
    public static final String OPERACION_INTERMEDIARIO="LGC";
    public static final int ESTADO_CONTRATO_REGISTRADO = 17;
    public static final int ESTADO_CONTRATO_DESEMBOLSADO = 18;
    public static final String CODIGO_MONEDA_USD="USD";
    
    //SP_CONTRATO_DESEMBOLSO parameters value
    public static final int P_ID_OPERACION=1;
    public static final int P_ID_RESOLUCION=2;
    public static final int P_ID_MONEDA=3;
    public static final int P_ID_PREPAGO=4;
    public static final int P_FECHA_RENOVACION=5;
    public static final int P_FECHA_PREPAGO=6;
    public static final int P_SIN_TRE_PREPAGO_CONTRATO=7;
    public static final int P_CODIGO_RES=8;
    public static final int P_MENSAJE=9;
    public static final int RECORDSET=10;
    
    //SSP_CALCULO_DE_INTERES parameters value
    public static final int P_PREPAGO=1;
    public static final int P_RECORDSET=2;
    
    //SP_CONTRATO_DESEM_PROX_PAGO parameters value
    //input
    public static final int P_ID_MONEDA_SP_CONTRATO_DESEM_PROX_PAGO=1;
    public static final int P_ID_OPERACION_SP_CONTRATO_DESEM_PROX_PAGO=2;
    public static final int P_CONTRATO_DESEMBOLSO_SP_CONTRATO_DESEM_PROX_PAGO=3;
    public static final int P_FECHA_SOLICITUD_SP_CONTRATO_DESEM_PROX_PAGO=4;
    public static final int P_FECHA_PREPAGO_SP_CONTRATO_DESEM_PROX_PAGO=5;
    //output
    public static final int P_CODIGO_RES_SP_CONTRATO_DESEM_PROX_PAGO=6;
    public static final int P_MENSAJE_SP_CONTRATO_DESEM_PROX_PAGO=7;
    public static final int RECORDSET_SP_CONTRATO_DESEM_PROX_PAGO=8;
    
    public static final Integer ESTADO_SOLICITUD_DESEMBOLSO_CREADO= 5;
    public static final Integer ESTADO_SOLICITUD_DESEMBOLSO_VALIDACION= 6;
    public static final Integer ESTADO_SOLICITUD_DESEMBOLSO_CUMPLIMIENTO= 7;
    public static final Integer ESTADO_SOLICITUD_DESEMBOLSO_PROCESO= 8;
    public static final Integer ESTADO_SOLICITUD_DESEMBOLSO_CERRADO= 9;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_CREADO= 10;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_VALIDACION= 11;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO= 12;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_PREPARADO= 13;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_TRANSITO= 14;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_APROBADO= 15;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_FONDOS= 16;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO= 17;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO= 18;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO= 19;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR= 21;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO= 22;
    
    public static final Integer TAREA_REALIZAR_CALIFICACION = 111;
    
    public static final String PROGRAMA_PROMUNI="PR";
    public static final Integer MODALIDAD_DATOS_GENERALES_LECTURA= 1;
    public static final Integer MODALIDAD_DATOS_GENERALES_ESCRITURA= 2;
    public static final Integer MODALIDAD_DATOS_GENERALES_OTRO= 3;
    
    public static final Integer TAREA_DEFINIR_CLASIFICACION_DESEMBOLSO= 152;
    public static final Integer TAREA_VALIDAR_ENVIO_AL_COBRO= 140;
    public static final Integer TAREA_GESTOR_DESEMBOLSO= 192;
    public static final Integer TAREA_REALIZAR_AJUSTES_DESEMBOLSO= 151;
    public static final Integer TAREA_REALIZAR_AJUSTES_ASIGN_REC= 163;
    public static final Integer TAREA_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS= 150;
    public static final Integer TAREA_REGISTRAR_DESEMBOLSO= 157;
    
    public static final int FORMA_TRANSF_FUENTE_DESEMBOLSO_DIRECTO_CLIENTE = 1;
    public static final int FORMA_TRANSF_FUENTE_DESEMBOLSO_CUENTAS_BCIE = 2;
    
    public static final String IDENTIFICADOR_PARAMETRO_PROCESO_DESEMBOLSO="ID_DESEMBOLSO";
    public static final String IDENTIFICADOR_PARAMETRO_PROCESO_DESEMBOLSO_SOLICITUD="ID_SOLICITUD";
    
    public static final String IDENTIFICADOR_PARAMETRO_REGLAS_DESEMBOLSO="ID_DESEMBOLSO";
    public static final String IDENTIFICADOR_PARAMETRO_REGLAS_INSTANCIA="INSTANCIA";
    public static final String IDENTIFICADOR_PARAMETRO_REGLAS_TAREA="ID_TAREA";
    public static final String IDENTIFICADOR_PARAMETRO_REGLAS_PROCESO="PROCESO";
    
    //Mensajes de errores para desembolsos
    public static final String ERROR_VALIDAR_INICIO_DESEMBOLSOS="Ocurrió un error al iniciar el proceso de desembolso. Intente de nuevo más tarde.";
    public static final String ERROR_VALIDAR_INICIO_DESEMBOLSO_VENCIDA="El plazo para iniciar desembolsos se ha vencido.";
    public static final String ERROR_VALIDAR_DESEMBOLSO_TOTALIDAD_VENCIDA="El plazo para desembolsar la totalidad de los recursos se ha vencido.";
    public static final String ERROR_VALIDAR_REGLAS_FECHAS="Se ha presentado una falla en la validación de reglas.";
    public static final String ERROR_VALIDAR_REGLA_FECHA_INICIO_DESEMBOLSOS="La validación de la fecha máxima para iniciar desembolsos no se realizó correctamente.";
    public static final String ERROR_VALIDAR_REGLA_FECHA_TOTALIDAD_RECURSOS="La validación de la fecha para desembolsar la totalidad de los recursos no se realizó correctamente.";
    //Terminos para validaciones de fechas de desembolsos
    public static final Integer ID_TCA_TERMINO_INICIO_DESEMBOLSOS=2;
    public static final Integer ID_TCA_TERMINO_DESEMBOLSO_TOTALIDAD_RECURSOS=3;
    
    public static final Integer ADQUISICION_PGA=5;
    public static final Integer ROL_ABOGADO=11;
    public static final Integer ROL_OFICIAL_ADQUISICIONES=6;
    public static final Integer ROL_ANALISTA_SUPERVISION=49;
    public static final Integer NORMATIVA_BCIE=1;
    public static final int MODALIDAD_LICITACION1=1;
    public static final int MODALIDAD_LICITACION2=2;
    public static final int MODALIDAD_CONCURSO5=5;
    public static final int MODALIDAD_CONCURSO6=6;
    public static final int MODALIDAD_COMPARACION3=3;
    public static final int MODALIDAD_COMPARACION7=7;
    public static final int MODALIDAD_DIRECTO4=4;
    
    public static final Integer TCA_TERMINO_GESTIONAR_CONTRATO_CLIENTE = 34;
    public static final Integer CLIENTE_GESTIONA_CONTRATO = 1;
    
    // ************************* IDS DE TIPO DE NO OBJECION ****************************
    public static final Integer ID_NO_OBJECION_AVISO = 2;
    public static final Integer ID_NO_OBJECION_INFORME_RESULTADOS = 7;
    public static final Integer ID_NO_OBJECION_CONTRATADO = 9;
    public static final Integer ID_NO_OBJECION_RESULTDO_PROCESO = 10;
    public static final Integer ID_NO_OBJECION_ORDEN_COMPRA = 11;
    public static final Integer ID_NO_OBJECION_EXPRESION_INTERES = 12;
    public static final Integer ID_NO_OBJECION_INFORME_SOLICITUD_INTERES = 14;
    public static final Integer ID_NO_OBJECION_ENMIENDAS_CONTRATO = 16;
    
    
    // ************************* IDS DE TIPO DE RESOLUCION ****************************
    public static final Integer ID_TIPO_PERFIL_OFERENTE = 1;
    public static final Integer ID_TIPO_PERFIL_ADJUDICATARIO = 2;
    public static final Integer ID_TIPO_PERFIL_CONTRATADO = 3;
    public static final String WSC_INICIO_ADQUISCION = "Inicio Adquiscion";
    public static final String WS_ADQUISICION_PREVIA= "Inicio Adquiscion previa";

    public static final int NO_OBJECION_AVISO = 2;
    public static final int NO_OBJECION_INFORME_RESULTADOS = 7;
    public static final int NO_OBJECION_CONTRATO = 9;
    public static final int NO_OBJECION_RESULTADO_PROCESO = 10;
    public static final int NO_OBJECION_CARGO_COMPRA = 11;
    public static final int NO_OBJECION_EXPRESION_INTERES = 12;
    public static final int NO_OBJECION_ENMIENDAS_DOCUMENTO_BASE = 13;
    public static final int NO_OBJECION_INFORME_SOLICITUD_INTERES = 14;
    public static final int NO_OBJECIO_INFORME_EVALUACION = 15;
    public static final int NO_OBJECION_ENMIENDAS_CONTRATO = 16;
    public static final int NO_OBJECION_TERMINOS_REFERENCIA = 17;
    
    public static final int NO_OBJECION_RESULTADO_ADJUDICADO = 1;
    
    public static final String AUTOMATICO_SI = "SI";
    public static final String AUTOMATICO_NO = "NO";
    
    
    public static final String PGA_ADQUISICION = "PGA";
    public static final String PGA_ADQUISICION2 = "PGA-";
    public static final Integer ID_TIPO_PGA = 5;
    public static final Integer ESTATUS_ADQUISICION = 1;
    public static final Integer ID_NORMATIVA_BCIE = 1;
    public static final Integer ID_TAREA_GESTOR_OPERACIONES = 65;
    public static final Integer ID_TAREA_REGISTRAR_OBJECION = 198;
    
    //*********************  ID TCA TIPO MONEDA*****************************************
    
    
    //***********************   TRANSACCION REGLAS SCR *********************************
    
    public static final String TRANSACCION_REGLA_SCR = "SCR";
    
    public static final String TRANSACCION_SCR_CUMPLIDA = "CUMPLIDA";
    public static final String TRANSACCION_SCR_EXCEPTUADA = "EXCEPTUADA";
    public static final String TRANSACCION_SCR_NO_CMPLIDA = "NO_CUMPLIDA";
    public static final String TRANSACCION_SCR_NO_EVALUADA = "NO_EVALUADA";
    public static final String TRANSACCION_SCR_ERROR = "ERROR";
    
    //***********************   FIN TRANSACCION REGLAS SCR *****************************
    
    public static final int TIPO_MONEDA_USD = 1;
    
    //***********************   ID PRODUCTO *****************************
    
    public static final Integer AMPLIACION_LGC_IFI = 26;
    
    //***********************   ID TCA TIPO FECHA INICIO *****************************
    
    public static final Integer CONDICION_TIPO_FECHA_INICIO_APROBACION = 1;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_ESCRITURACION = 2;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_PRIMER_DESEMBOLSO = 3;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_ULTIMO_DESEMBOLSO = 4;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_AVANCE_FISICO = 5;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_IMPLEMENTACION_PGI = 6;
    public static final Integer CONDICION_TIPO_FECHA_INICIO_FECHA_ESPECIFICA = 7;
    
    public static final String FECHA_EFECTIVA_TERMINO = "El plazo para iniciar desembolsos se ha vencido.";
    public static final String FECHA_EFECTIVA_MAXIMA = "El plazo para desembolsar la totalidad de los recursos se ha vencido. ";
    public static final String MONTO_DESEMBOLSO_DISPONIBLE = "La línea de crédito no cuenta con monto disponible para cubrir el contrato de de desembolso. ";
    
    //CONSTANTES PARA ASIGNAR PROGRAMA_OPERACION Y DESTINO_FINANCIAMIENTO SI EL PRODUCTO DE OPERACION NO ES IFI
    public static final String PROGRAMA_OPERACION_OR = "OR";
    public static final String DESTINO_FINANCIAMIENTO_ND = "ND";
    
    public static final Integer CONCURSANTE_CONTRATADO= 3;
    public static final Integer CONCURSANTE_ADJUDICADO= 2;
    
    public static final Integer INTRUMENTO_FINANCIERO_6= 6;
    public static final Integer INTRUMENTO_FINANCIERO_4= 4;
    public static final Integer INTRUMENTO_FINANCIERO_7= 7;
    
    public static final String PROGRAMA_APE = "APE";
    public static final String PROGRAMA_OR = "OR";
    public static final String DESTINO_ND = "ND";
    
    //CONSTANTES DE APLICACIONES EXTERNAS
    public static final String APP_EXTERNA_LLAVE_ID_CLIENTE = "ID_CLIENTE";
    public static final String APP_EXTERNA_LLAVE_ID_OPERACION = "ID_OPERACION";
    public static final String APP_EXTERNA_LLAVE_CONSTANTE = "CONSTANTE";
    public static final String APP_EXTERNA_LLAVE_ID_PARAMETRO = ":ID_PARAMETRO";
    
    //CONSTANTES DE OID
    public static final String OID_PREFIJO_GRUPO_REPRESENTANTE = "FENIX_REPRESENTANTES_";

    public static final Integer VARIANTE_1= 1;
    public static final Integer VARIANTE_2= 2;
    public static final Integer VARIANTE_3= 3;
    
    public static final Integer CALENDARIO_SENCILLO= 2;
    public static final Integer ESPECIFICACION_FECHAS= 1;
    public static final Integer ESPECIFICACION_PLAZOS= 2;
    
    public static final Integer TASA_FIJA= 2;
    public static final Integer TASA_ESPECIAL= 1;
    public static final Integer TASA_VARIABLE= 3;
    public static final String GD_TIPO_TAZA_VARIABLE = "F";
    
    public static final Long ID_REGLA_TRANSACCION_MORA = 1L;
    public static final Long ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO = 2L;
    public static final Long ID_REGLA_TRANSACCION_LIMITES = 6L;
    public static final Long ID_REGLA_TRANSACCION_LIMITE_GLOBAL = 22L;
    public static final Long ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO = 23L;
    public static final Long ID_REGLA_VALIDACION_TEMPRANA_FLEXCUBE = 24L;
    
    public static final Integer FRECUENCIA_AL_VENCIMIENTO = 4;
    public static final Integer FRECUENCIA_PERIODO_CERO = 0;
    public static final Integer ID_TCA_FRECUENCIA_PERIODO_DIAS = 1;
    public static final String ESTADO_VALIDADA = "VALIDADA";
    public static final Integer PLANTILLA_ACTUALIZACION_CUESTIONARIO_ELEGIBILIDAD = 105;
    
    
    /**************************PA15 FORMALIZACION ENMIENDAS ************/
    //Constantes para el origen de formalizaciones de enmiendas
    public static final int ORIGEN_FORMALIZACION_ENMIENDA_FENIX_VALUE = 1;
    public static final int ORIGEN_FORMALIZACION_ENMIENDA_GENERICO_VALUE = 2;
    public static final int ORIGEN_FORMALIZACION_ENMIENDA_OTROS_VALUE = 3;
    public static final int ORIGEN_FORMALIZACION_ENMIENDA_ORIGEN4_VALUE = 4;
    public static final int ORIGEN_FORMALIZACION_ENMIENDA_ORIGEN5_VALUE = 5;
    
    //Llaves del mapa recibido en el método que incia formalización de enmiendas
    public static final String AM_PARAM_KEY_IDOPERACION = "IDOPERACION";
    public static final String AM_PARAM_KEY_IDENMIENDA = "IDENMIENDA";
    public static final String AM_PARAM_KEY_ORIGEN = "ORIGEN";
    public static final String AM_PARAM_KEY_CODCLIENTE = "CODCLIENTE";
    public static final String AM_PARAM_KEY_NOMCLIENTE = "NOMCLIENTE";
    public static final String AM_PARAM_KEY_CODPRODUCTO = "CODPRODUCTO";
    public static final String AM_PARAM_KEY_NOMPRODUCTO = "NOMPRODUCTO";
    public static final String AM_PARAM_KEY_MONTOSOLICITADO = "MONTOSOLICITADO";
    public static final String AM_PARAM_KEY_PAIS = "PAIS";
    public static final String AM_PARAM_KEY_DESCRIPCION = "DESCRIPCION";
    public static final String AM_PARAM_KEY_RESPONSABLE = "RESPONSABLE";
    public static final String AM_PARAM_KEY_VALIDAR_COND_FINANCIERAS = "VALIDAR_COND_FINANCIERAS";
    public static final String AM_PARAM_KEY_DESOBLIGACION = "DESOBLIGACION";
    public static final String AM_PARAM_KEY_CAMBIO_PLAZO = "CAMBIO_PLAZO";
    
    //Llave para parametros del proceso
    public static final String PARAM_TIPO_FORMALIZACIONENMIENDA = "TIPO_FORMALIZACION_ENMIENDA";

    
    public FenixModelConstants()
    {
      super();
    }
}
