package org.bcie.fenix.common;



public class FenixConstants {
    public FenixConstants() {
        super();
    }
    /************************** ID TAREAS  (PC01 PC02 ... PA01 PA02....) ****************************/

    /**************************PC01 ELEGIBILIDAD ****************************/
    public static final int PC01_COMPLETARCUESTIONARIO = 1;
    public static final int PC01_RECOPILARINFOASJUR = 2;
    public static final int PC01_VERIFICARREQLEGALES = 3;
    public static final int PC01_ANALIZARPERFILRIESGO = 4;
    public static final int PC01_ANALIZARELEFIBILIDAD = 5;
    public static final int PC01_DETERMINAELEDIBILIDAD = 6;
    public static final int PC01_FEFORMULAR = 7;
    public static final int PC01_RECOPILARINFOGERIES = 42;
    public static final int PC01_RECOPILARINFOOEJ = 231;
    public static final int PC01_INCORPORARCONTEXTOMACRO = 232;

    /**************************PC02 PREPARACION ****************************/

    public static final Integer ESTADO_COMISION_PAGADO = 6;
    public static final Integer ESTADO_COMISION_SUGERIDO = 1;
    public static final Integer ESTADO_COMISION_DISPENSADO = 5;
    public static final Integer ESTADO_COMISION_POR_APLICAR = 2;

    public static final Integer SUBESTADO_COMISION_PORVALIDAR = 21;
    public static final Integer SUBESTADO_COMISION_VALIDADO = 28;
    public static final Integer ESTADO_COMISION_PORPAGAR = 4;
    public static final Integer ESTADO_COMISION_PORDISPENSAR = 3;
    public static final Integer ESTADO_COMISION_VALIDADO = 26;

    public static final Integer SUBESTADO_COMISION_EDITADO = 9;
    public static final Integer ESTADO_COMISION_MANDATORIO = 8;
    public static final Integer SUBESTADO_COMISION_EDITADO_MANDATORIO = 11;
    
    public static final Integer SUBESTADO_COMISION_CREADA = 33;
    public static final Integer SUBESTADO_COMISION_EN_REGISTRO = 34;
    public static final Integer ESTADO_COMISION_REGISTRADA = 35;

    public static final Integer MOMENTO_COBRO_ANALISIS = 2;
    public static final Integer MOMENTO_COBRO_FORMALIZACION = 4;
    public static final Integer MOMENTO_COBRO_APROBACION = 3;
    public static final Integer MOMENTO_COBRO_DESEMBOLSO = 5;

    public static final Integer COMISION_MONTO = 1;
    public static final Integer COMISION_TASA = 2;

    public static final String TAREA_HOJA_TERMINOS_SEPRI = "8";
    public static final String TAREA_CONFORMAR_EQUIPO_DE_PREPARACION = "9";
    public static final String TAREA_DETERMINAR_RAROC = "11";
    public static final String TAREA_HOJA_DE_TERMINOS_PCT = "12";
    public static final String TAREA_EMITIR_OPINION_TECNICA = "13";
    public static final String TAREA_ANALIZAR_ADQUISICIONES = "14";
    public static final String TAREA_REVISAR_COMISIONES = "15";
    public static final String TAREA_RECOPILAR_INFORMACION = "10";
    public static final String TAREA_ENVIAR_OPERACION_ANALISIS = "16";
    public static final String TAREA_VALIDAR_MATRIZ_ACCIONES = "221";
    
    public static final String TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD = "234";
    public static final String TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD = "235";
    public static final String TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD = "236";
    
    public static final String TITULO_AGREGAR_COMISION = "Agregar comisión";
    public static final String TITULO_MODIFICAR_COMISION = "Modificar comisión:";
    public static final int VALOR_CERO = 0;
    public static final String VALOR_CERO_CADENA = "0";


    /************************** TCC ****************************/
    public static final String TERMINO = "TERMINO";
    public static final String CONDICION = "CONDICION";
    public static final String COMISION = "COMISION";

    public static final Integer TERMINO_INT = 1;
    public static final Integer CONDICION_INT = 2;
    public static final Integer COMISION_INT = 3;

    public static final Integer MONTO_APROBADO = 3;
    public static final Integer MONTO_FORMALIZADO = 4;

    public static final Integer ESTADO_TCC_NUEVA = 7;
    public static final Integer ESTADO_TCC_POR_APROBAR = 13;
    public static final Integer SUBESTADO_TCC_NUEVA_POR_APROBAR = 16;
    public static final String ACCION_AGREGAR = "AGREGAR";

    /********************* PA01 LAFT Lavado Activos ***********************/
    public static final String TIPO_ORIGEN_ASOCIACION = "ASOCIACION";
    public static final String TIPO_ORIGEN_EXCEPCION = "EXCEPCION";
    public static final String TIPO_ORIGEN_DUPLICADA = "DUPLICADA";
    public static final String TIPO_ORIGEN_NUEVA = "NUEVA";

    public static final String TAREA_REALIZAR_LAFT = "39";
    public static final String TAREA_RECOPILAR_INFO_OFIC = "40";
    public static final String TAREA_REALIZAR_ANALISIS_LAFT = "41";

    public static final Integer ID_TIPO_DOCUMENTO_DECLARACION_JURADA = 1017;
    public static final Integer ID_TIPO_DOCUMENTO_HISTORICO_DECLARACION_JURADA = 1076;

    /**************************PC03 ANALISIS ****************************/
    public static final int PC03_REVISARDOCUMENTOS = 17;
    public static final int PC03_RECOPILARINFO = 18;
    public static final int PC03_ELABORAROPINIONJUR = 19;
    public static final int PC03_ELABORAROPINIONJURT = 25;
    public static final int PC03_ELABORARCALIFICACION = 20;
    public static final int PC03_ELABORARCALIFICACIONIFI = 21;
    public static final int PC03_APROBARCALIFICACION = 22;
    public static final int PC03_EVALUARACCIONSEGUIR = 23;
    public static final int PC03_ELABORARDICTAMENAC = 24;
    public static final int PC03_REVISARPROYECTO = 26;
    public static final int PC03_ANALIZARDICTAMEN = 27;
    public static final int PC03_ELABORARDICTAMENEAT = 28;
    public static final int PC03_VALIDARRESULTADO = 32;
    public static final int PC03_ERROR_PROPAGAR_SCR = 217;

    public static final Integer PC03_IBCIE = 1;
    public static final Integer PC03_SIEMAS = 2;

    /**************************PC04 APROBACION ****************************/
    public static final int PC04_REVISARSOLICITUDCC = 43;
    public static final int PC04_DARSEGUIMIENTOVOTACION = 44;
    public static final int PC04_CONSOLIDARDECISION = 45;
    public static final int PC04_REALIZARMODIFICACIONES = 46;
    public static final int PC04_MODIFICARPROYECTORESOLUCION = 47;
    public static final int PC04_REVISARSOLICITUDPRESIDENTE = 48;
    public static final int PC04_DARSEGUIMIENTODECISIONPRESIDENTE = 49;
    public static final int PC04_CERTIFICARDECISION = 50;
    public static final int PC04_CONSOLIDARDECISIONDIRECTORIO = 51;
    public static final int PC04_ACTUALIZARTCC = 52;
    public static final int PC04_CREAR_SOLICITUD_APROBACION = 179;
    public static final int PC04_ELEVAR_SOLICITUD_APROBACION = 180;
    public static final int PC04_REVISAR_SOLICITUD_COMITE = 181;
    public static final int PC04_DAR_SEGUIMIENTO_VOTACION = 182;
    public static final int PC04_CONSOLIDAR_DECISION_COMITE = 183;
    public static final int PC04_REALIZAR_MODIFICACIONES = 184;
    public static final int PC04_REALIZAR_MODIFICAciONES_ANALISTA_CR = 185;
    public static final int PC04_VALIDARTCC = 203;
    public static final int PC04_COMPLETARTCC = 204;
    public static final int PC04_ERROR_PROPAGAR_SCR = 218;

    //Documentos Aprobacion SCR
    public static final int TD_ARGUMENTACION_SCR = 1153;
    public static final int TD_OTROS_DOCUMENTOS_SOPORTE_SCR = 1154;
    public static final int TD_SOPORTE_VOTO_FUERA_SISTEMA_SCR = 1155;
    public static final int TD_ACTA_COMITE_CREDITO_SOBRE_SCR = 1156;

    /**************************PC06 EVALUACION ****************************/
    public static final String PC06_GENERARCUESTIONARIOS = "33";
    public static final String PC06_VALIDARDISENOCUESTIONARIOS = "34";
    public static final String PC06_COMPLETARCUESTIONARIOS = "35";
    public static final String PC06_VALIDARCUESTIONARIOS = "36";
    public static final int PC06_VALIDARCUESTIONARIOSINT = 36;
    public static final String PC06_GENERARINFORMES = "37";
    public static final String PC06_REVISARINFORMES = "38";

    public static final String PC06_REVISAR_DISENIO_CUESTIONARIO_IBCIE = "166";
    public static final String PC06_VALIDAR_DISENO_CUESTIONARIOS_IBCIE = "167";
    public static final String PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE = "168";
    public static final String PC06_COMPLETAR_CUESTIONARIO_IBCIE = "169";
    public static final String PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE = "170";
    public static final String PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE = "171";
    public static final String PC06_VALIDAR_INFORME_IBCIE = "172";
    public static final String PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION = "173";
    public static final String PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS = "174";
    public static final String PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS = "175";
    public static final String PC06_GENERAR_INFORME_SEGUIMIENTO_SIEMAS = "176";
    public static final String PC06_REVISAR_INFORME_SEGUIMIENTO_SIEMAS = "177";
    public static final String PC06_ACTUALIZAR_CONDICIONES_SIEMAS = "178";
    
    //DOCUMENTOS
    public static final Integer TD_SOPORTE_IBCIE = 1147;
    public static final Integer TD_SOPORTE_SIEMAS = 1150;
    public static final Integer TD_INFORME_IBCIE = 1148;
    public static final Integer TD_INFORME_SIEMAS = 1151;
    public static final Integer TD_OBSERVACION_INFORME_IBCIE = 1152;
      

    /**************************PC07 GESTION DE COBRO ***********************/
    public static final int PC07_VALIDAR_RECIBO_COBRO = 142;
    public static final int PC07_GENERAR_VALIDAR_AVISO_COBRO = 143;
    public static final int PC07_ENVIAR_AVISO_COBRO_MANUAL = 144;

    /* Tipo de Documentos para el proceso PC07 Gestion de cobro */
    public static final int TD_AVISO_DE_COBRO = 1122;
    public static final int TD_SALDOS_EN_MORA = 1123;
    public static final int TD_RECIBO_DE_PAGO = 1121;
    public static final String MT_DOC = "application/msword";
    public static final String MT_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    /**************************PC05 FORMALIZACION *************************/
    public static final String PC05_SOLICITAR_ELABORACION_DOCUMENTACION = "53";
    public static final String PC05_DEFINIR_RECURSOS_EXTERNOS = "54";
    public static final String PC05_ELABORAR_BORRADOR_CONTRATO = "55";
    public static final String PC05_NEGOCIAR_DOCUMENTACION_CONTRACTUAL = "56";
    public static final String PC05_ELABORAR_CORE_TYC = "57";
    public static final String PC05_ANALIZAR_DOCUMENTACION_CONTRACTUAL = "58";
    public static final String PC05_ADJUNTAR_CONTRATO_FIRMADO = "59";
    public static final String PC05_REGISTRAR_FECHA_VIGENCIA = "60";
    public static final String PC05_CUSTODIAR_CONTRATO = "61";
    public static final String PC05_REGISTRAR_DATOS_LINEA_CREDITO = "62";
    public static final String PC05_ASIGNAR_RECURSOS = "63";
    public static final String PC05_VALIDAR_DATOS_LINEA_CREDITO = "64";
    public static final String PC05_COMUNICAR_VIGENCIA_CONTRATO = "199";
    public static final String PC05_CUMPLIR_CONDICIONES_FINANCIERAS = "220";
    
    /* TABLA DINAMICA */
    public static final String TCA_TAREA_CON_BTN_DIN = "TCA_TAREA_CON_BTN_DIN";
    
    

    /**************************PC02 ADMINISTRADOR DE COMISIONES ****************************/
    public static final int PC02_ESTADO_COMISION_PAGADA = 6;
    public static final long PC02_ESTADO_COMISION_VALOR_DISPENSA = 3;
    public static final long PC02_ESTADO_COMISION_DISPENSADO_ADMON = 5;
    public static final long PC02_ESTADO_COMISION_POR_PAGAR = 4;
    public static final int PC02_GESTIONAR_COMISIONES = 67;
    public static final int PC02_VALIDAR_COMISIONES = 68;
    public static final int PA02_VALIDAR__REGISTRO_COMISION = 200;
    public static final int PA02_REGISTRAR_COMISION = 201;

    /**************************PC02 ADMINISTRADOR DE CONDICIONES ****************************/
    public static final int PA03_CUMPLIR_CONDICIONES = 69;
    public static final int PA03_VALIDAR_CONDICIONES_ASJUR = 70;
    public static final int PA03_VALIDAR_CONDICIONES_SEPRI = 71;
    public static final int PA03_VALIDAR_CONDICIONES_SUPERVISION = 72;
    public static final int PA03_VALIDAR_CONDICIONES_EVALUACION = 73;
    public static final int PA03_VALIDAR_CONDICIONES_PCT = 74;
    public static final int PA03_VALIDAR_CONDICIONES_COFI = 75;

    /**************************PA06 ADMINISTRADOR DE CLIENTES ****************************/
    public static final int PA06_APROBAR_CLIENTE = 84;
    public static final int PA06_EDITAR_CLIENTE = 85;

    /**************************PC04 ENMIENDAS ****************************/
    public static final int PAC04_INGRESAR_ENMIENDA = 76;
    public static final int PAC04_REVISAR_ASPECTOS_LEGALES = 77;
    public static final int PAC04_EMITIR_OPINION_GERENCIA_CREDITO = 78;
    public static final int PAC04_REALIZAR_AJUSTES = 79;
    public static final int PAC04_AJUSTAR_ENMIENDA = 80;
    public static final int PAC04_EVALUAR_ENMIENDA = 81;
    public static final int PAC04_VALIDAR_REGISTRO_ENMIENDA = 82;

    /***************************PC08 CIERRE *****************************/
    public static final int PC08_SOLICITAR_CIERRE_OPERACION = 95;
    public static final int PC08_CERTIFICAR_ADEUDOS = 96;
    public static final int PC08_LIBERAR_GARANTIAS = 97;
    public static final int PC08_GESTIONAR_ESCRITURA = 98;
    public static final int PC08_VALIDAR_REGISTRO_ESCRITURA = 99;
    public static final int PC08_VALIDAR_CIERRE_CLIENTE = 100;

    /* Tipo de Documentos para el proceso PC08 CIERRE */
    public static final int TD_SOLICITUD_CIERRE_PARTE_CLIENTE = 1081;
    public static final int TD_INFORME_CIERRE = 1082;
    public static final int TD_ESCRITURA_LIBERACION_GARANTIAS = 1083;
    public static final int TD_CERTIFICACION_ADEUDOS = 1085;
    public static final int TD_PLACEME = 1086;
    public static final int TD_ESCRITURA_LIBERACION_GARANTIAS_REGISTRADA = 1087;
    public static final int TD_INFORME_CIERRE_LECCIONES_APRENDIDAS = 1088;
    public static final int TD_JUSTIFICACION_AUTORIZACION_CIERRE = 1089;

    /**************************PA07 SEGUIMIENTO CREDITICIO ****************************/
    public static final int PA07_REALIZAR_REVISION_CALIFICACION_RIESGO = 111;
    public static final int PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA = 87;
    public static final int PA07_VALIDAR_NUEVO_SCR = 88;
    public static final int PA07_ERROR_PROPAGAR_SCR = 207;

    /**********************************PA08 SUPERVISION ************************************/
    public static final int PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION = 93;
    public static final int PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION = 94;

    /*****************************PA14 CIERRE FIDEICOMISOS *******************************/
    public static final int PA14_SOLICITAR_CIERRE_OPERACION = 101;
    public static final int PA14_REVISAR_CUENTAS_CONTABLES = 102;
    public static final int PA14_REVISAR_CUENTAS_BANCARIAS_INVERSIONES = 103;
    public static final int PA14_SOLICITAR_DOCUMENTO_CIERRE = 104;
    public static final int PA14_PREPARAR_DOCUMENTO_CIERRE = 105;
    public static final int PA14_NEGOCIAR_DOCUMENTO_CIERRE = 106;
    public static final int PA14_CERRAR_CUENTAS_CONTABLES = 107;
    public static final int PA14_CERRAR_CUENTAS_BANCARIAS_INVERSIONES = 108;
    public static final int PA14_VALIDAR_DOCUMENTO_CIERRE = 109;
    public static final int PA14_CUSTODIAR_DOCUMENTO_CIERRE = 110;

    /*****************************PA13 RECUPERACION UCE *******************************/
    public static final int PA13_SOLICITAR_TRASLADO_UCE = 89;
    public static final int PA13_VALIDAR_SOLICITUD_DE_TRASLADO_UCE = 90;
    public static final int PA13_ANALIZAR_Y_ELABORAR_PROPUESTAS_DE_ACCIONES = 91;
    public static final int PA13_VALIDAR_PROPUESTAS_DE_ACCIONES = 92;

    /**********************************PA12 PREPAGO ************************************/
    public static final int PA12_INGRESAR_SOLICITUD_PREPAGO = 113;
    public static final int PA12_INGRESAR_SOLICITUD_PREPAGO_145 = 145;
    public static final int PA12_DETERMINAR_CARGOS_PREPAGO_COPRES = 114;
    public static final int PA12_DETERMINAR_CARGOS_PREPAGO_DAECI = 115;
    public static final int PA12_DETERMINAR_CARGOS_PREPAGO_MDC = 116;
    public static final int PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI = 117;
    public static final int PA12_VALIDAR_PENALIDAD_PREPAGO_COFI = 118;
    public static final int PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO = 119;
    public static final int PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO_146 = 146;
    public static final int PA12_GESTIONAR_PREPAGO_CLIENTE = 120;
    public static final int PA12_GESTIONAR_PREPAGO_CLIENTE_147 = 147;
    public static final int PA12_REVISAR_COMISIONES = 121;
    public static final int PA12_CONFIRMAR_APLICACION_PREPAGO = 122;
    public static final int PA12_GESTIONAR_COBERTURA = 123;
    public static final int PA12_GESTIONAR_DIFERENCIAS = 124;
    public static final int PA12_GESTIONAR_DIFERENCIAS_148 = 148;

    public static final int TIPO_MONEDA_DOLAR = 1;
    public static final Integer PRE10_2008 = 1;
    public static final Integer PRE28_2003 = 2;
    public static final Integer OTRAS_CONDICIONES = 3;
    public static final Integer DOCUMENTO_RECIBO_PAGO_ANTICIPADO = 1118;

    /**********************************CG10 GESTOR DE DESEMBOLSOS ************************************/

    public static final int CG10_GESTOR_DESEMBOLSOS = 192;
    //--------------------- TCA_TAREA_BPM ---------------------- ID's
    public static final int CGD_VALIDAR_ENVIO_AL_COBRO_PCT = 140;
    public static final int CGD_VALIDAR_FONDOS_PRESUPUESTARIOS = 149;
    public static final int CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS = 150;
    public static final int CGD_REALIZAR_AJUSTES_A_DESEMBOLSO = 151;
    public static final int CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS = 152;
    public static final int CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO = 153;
    public static final int CGD_APROBAR_DESEMBOLSO = 154;
    public static final int CGD_GESTIONAR_PROGRAMACION = 155;
    public static final int CGD_RESERVAR_FONDOS = 156;
    public static final int CGD_REGISTRAR_DESEMBOLSO = 157;
    public static final int CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO = 158;
    public static final int CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI = 159;
    public static final int CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM = 160;
    public static final int CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT = 161;
    public static final int CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA = 162;
    public static final int CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS = 163;
    public static final int CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION = 164;
    public static final int CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION = 165;
    public static final int CGD_VALIDAR_RAROC = 224;

    /********************************* GESTOR DE DESEMBOLSOS *****************************************/
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

    public static final int TAB_ASIGNACION_RECURSOS = 1;
    public static final int TAB_DATOS_GENERALES = 2;
    public static final int TAB_CONDICIONES_FINANCIERAS = 3;
    public static final int TAB_CARGOS = 4;
    public static final int TAB_TRANSFERENCIAS = 5;
    public static final int TAB_LIQUIDAR_CONTRATO = 6;
    public static final int TAB_CONSOLIDAR_TRANSFERENCIAS = 7;
    public static final int TAB_COBERTURAS = 8;
    public static final int TAB_RESERVA_FONDOS = 9;
    
    /***  ------------  GESTOR DE DESEMBOLSOS TAB CONDICIONES FINANCIERAS        -------- */
    
    public static final String GD_TIPO_TAZA_ESPECIAL = "S";
    public static final String GD_TIPO_TAZA_FIJA = "X";
    public static final String GD_TIPO_TAZA_VARIABLE = "F";
    

    /**********************************PC06 IMPLEMENTACION - DESEMBOLSO ************************************/

    //Asignación de recursos
    public static final int PC06_VALIDAR_ASIGNACION_RECURSOS = 1001;
    public static final int PC06_REALIZAR_AJUSTES_ASIGNACION_RECURSOS = 1002;

    //Desembolso
    public static final int PC06_GESTIONAR_DESEMBOLSO_FUENTES_EXTERNAS = 150;
    public static final int PC06_VALIDAR_CLASIFICACION_ESTRATEGICA = 1004;
    public static final int PC06_VALIDAR_INFORMACION_FINANCIERA = 1005;
    public static final int PC06_REALIZAR_AJUSTES_DESEMBOLSO = 151;
    public static final int PC06_APROBAR_DESEMBOLSO = 1007;
    public static final int PC06_APROBAR_FUERA_HORARIO = 219;
    
    
    /*****************************PA11 IMPLEMENTACION PCT - Proceso Principal *******************************/
    
    public static final int PA11_SOLICITAR_CONTRATACION_CONSULTORIA = 126;
    public static final int PA11_GESTIONAR_CONTRATACION_CONSULTORIA = 127;
    public static final int PA11_ELABORAR_DOCUMENTO_BASE = 128;
    public static final int PA11_COMPLETAR_DOCUMENTO_BASE = 129;
    public static final int PA11_VALIDAR_DOCUMENTO_BASE = 130;
    public static final int PA11_INICIAR_ADQUISICION = 131;
    public static final int PA11_REGISTRAR_RESULTADO_ADQUISICION = 125;
    public static final int PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO = 132;
    public static final int PA11_VALIDAR_DATOS_LINEA_CREDITO = 133;
    
    public static final int ID_TIPO_DOCUMENTO_ACTA_EVALUACION_MIEMBROS_COMITE = 1135;
    public static final int ID_TIPO_DOCUMENTO_TERMINOS_REFERENCIA_DEFINITIVOS = 1136;
    public static final int ID_TIPO_DOCUMENTO_BORRADOR_DOCUMENTO_BASE = 1139;
    public static final int ID_TIPO_DOCUMENTO_DOCUMENTO_BASE = 1140;
    public static final Integer GESTION_CONTRATO_CLIENTE = 1;
    /*****************************PA11 IMPLEMENTACION PCT - ENVIO AL COBRO *******************************/

    public static final int PA11_VALIDAR_TASA_FECHA = 137;
    public static final int PA11_REALIZAR_AJUSTES_TASA_FECHA = 138;
    public static final int PA11_ENVIAR_AL_COBRO = 139;
    public static final int PA11_VALIDAR_ENVIO_COBRO = 140;
    public static final int PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES = 141;

    /*****************************PA11 IMPLEMENTACION PCT - ENVIO AL GASTO *******************************/

    public static final int PA11_SOLICITUD_ENVIO_GASTO = 134;
    public static final int PA11_VALIDAR_SOLICITUD = 135;
    public static final int PA11_LIQUIDAR_DESEMBOLSO = 136;
    
    /*****************************PLAN DE ACCIONES *******************************/
    public static final int PROCESO_UCE = 27;
    public static final int PROCESO_SEGUIMIENTO = 20;
    public static final int PROCESO_SUPERVISION = 23;
    public static final int PROCESO_IBCIE = 6;
    public static final int PROCESO_SIEMAS = 7;
    public static final int PROCESO_CIERRE = 19;
    public static final int PROCESO_CIERRE_FIDEICOMISOS = 28;
    public static final int PROCESO_GESTOR_OPERACIONES = 13;
    public static final int PROCESO_GESTOR_CLIENTES = 15;

    public static final int CATEGORIA_UCE = 2;
    public static final int CATEGORIA_SEGUIMIENTO = 1;
    public static final int CATEGORIA_SUPERVISION = 3;
    public static final int CATEGORIA_EVALUACION_IBCIE = 4;
    public static final int CATEGORIA_EVALUACION_SIEMAS = 5;
    public static final int CATEGORIA_CIERRE = 19;
    public static final int CATEGORIA_CIERRE_FIDEICOMISOS = 28;

    public static final int ESTADO_ACCION_REGISTRADO = 2;
    public static final int ESTADO_ACCION_PORATENDER = 3;
    public static final int ESTADO_ACCION_ATENDIDA = 4;
    public static final int ESTADO_ACCION_VALIDADA = 5;
    public static final int ESTADO_ACCION_DESESTIMADA = 6;
    public static final int ESTADO_ACCION_ELIMINADA = 7;
    public static final int ESTADO_ACCION_VENCIDA = 1;
    public static final int TAREA_GESTOR_OPERACIONES = 65;

    /*
     *
     * Tipos de documentos para el proceso de recuperacion UCE
     *
     */
    //Soporte justificación intervención de UCE
    public static final int TIPO_DOCUMENTO_SOPORTE_JUSTIFICACION_INTERVENCION_UCE = 1078;

    /**************************PA15 FORMALIZACION ENMIENDAS ****************************/
    public static final int PA15_SOLICITAR_FORMALIZACION_ENMIENDA = 216;
    public static final int PA15_ANALIZAR_SOLICITUD_ENMIENTA = 208;
    public static final int PA15_REVISAR_DOCUMENTACION_CLIENTE = 209;
    public static final int PA15_VALIDAR_CONDICIONES_FINANCIERAS = 210;
    public static final int PA15_DOCUMENTAR_FORMALIZACION_ENMIENDA = 211;
    public static final int PA15_CUSTODIAR_DOCUMENTACION_SOPORTE = 212;
    public static final int PA15_REGISTRAR_CAMBIOS = 213;
    public static final int PA15_VALIDAR_REGISTRO_CAMBIOS = 214;
    public static final int PA15_AJUSTAR_BORRADOR_ENMIENDA = 215;
    

    
    /**************************PA16 ADMINISTRAR OPERACION ****************************/
    public static final int PA16_ACTUALIZAR_DATOS_OPERACION = 222;
    public static final int PA16_APROBAR_CAMBIOS_OPERACION = 223;
    
    
    /**************************PA17 LECCIONES APRENDIDAS ****************************/
    public static final int PA17_EMITIR_OPINION_LECCION_APRENDIDA = 227;
    public static final int PA17_VALIDAR_LECCION_APRENDIDA = 228;
    public static final int PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA = 229;
    public static final int PA17_REVISAR_APELACION_LECCION_APRENDIDA = 230;
    

    /************************** ID TAREAS  (PC01 PC02 ... PA01 PA02....) ****************************/


    /****************************** ID DOCUMENTOS (TD Tipo Documento) *******************************/
    public static final int TD_DECLARACIONJURADA = 1017;
    public static final int TD_OPINIONJURIDICA = 1030;
    public static final int TD_BORRADORREPCALIFICACION = 1031;
    public static final int TD_REPCALIFICACION = 1032;
    public static final int TD_SOPORTEARGUMENTACIONSCR = 1033;
    public static final int TD_DICTAMEN = 1034;
    public static final int TD_BORRADORPROYECTORESOLUCION = 1035;
    public static final int TD_PROYECTORESOLUCION = 1036;
    public static final int TD_PERFILOPERACION = 1016;
    public static final int TD_ESTADOSFINANCIEROSNTERNOS = 1020;
    public static final int TD_ESTADOSFINANCIEROSAUDITADOS = 1021;
    public static final int TD_ESCRITURACONSTITUCION = 1019;
    public static final int TD_FICHATRABAJO = 1022;
    public static final int TD_CERTIFICACIONCUMPLIMIENTOLAFT = 1015;
    public static final int TD_DOCUMENTOSFIRMANTE = 1018;
    public static final int TD_REFERENCIASBANCARIAS = 1023;
    public static final int TD_SOPORTEVISITACAMPO = 1024;
    public static final int TD_JUSTIFOPINIONEGATIVA = 1038;

    public static final int TD_IDENTIFICACIONESPERSONASDECLARACIONJ = 3;

    //Preparacion
    public static final Integer DOCUMENTO_HOJA_TERMINOS_SEPRI = 1014;
    public static final Integer DOCUMENTO_DETERMINAR_RAROC = 1008;
    public static final Integer DOCUMENTO_HOJA_DE_TERMINOS_PCT = 1013;
    public static final Integer DOCUMENTO_EMITIR_OPINION_TECNICA = 1010;
    public static final Integer DOCUMENTO_EMITIR_OPINION_TECNICA2 = 1011;
    public static final Integer DOCUMENTO_ANALIZAR_ADQUISICIONES = 1012;
    public static final Integer DOCUMENTO_REVISAR_COMISIONES = 1009;
    public static final Integer DOCUMENTO_VALIDAR_MATRIZ = 1201;
    
    //Evaluaciones
    public static final Integer DOCUMENTO_I_BCIE = 1040;
    public static final Integer DOCUMENTO_SIEMAS = 1041;
    //Formalización
    public static final Integer DOCUMENTO_BORRADOR_DE_CONTRATO = 1053;
    public static final Integer DOCUMENTO_CORE_TYC = 1055;
    public static final Integer DOCUMENTO_ACUERDO_DEL_CORE = 1056;
    public static final Integer DOCUMENTO_CONTRATO_FIRMADO = 1057;
    public static final Integer DOCUMENTO_EVIDENCIA_DE_LA_VIGENCIA_DEL_CONTRATO = 1170;

    // Gestor de Documentos
    public static final Integer TD_GENERAL = 0;

    /************************** ID DOCUMENTOS ****************************/
    //LAFT
    public static final Integer PC07_LAFT = 3;

    //Administrador de Comisiones
    public static final Integer DOCUMENTO_COMISION_VALOR_DISPENSADO = 1;
    public static final Integer DOCUMENTO_COMISION_PAGADO = 2;
    public static final Integer DOCUMENTO_COMISION_DISPENSADO = 3;

    /**************** VALORES EN TABLA TCA_ACCION_DOCUMENTO ****************/
    public static final Integer DOCUMENTO_ACCION_AGREGADO = 1;
    public static final Integer DOCUMENTO_ACCION_MODIFICADO = 2;
    public static final Integer DOCUMENTO_ACCION_ELIMINADO = 3;
    public static final Integer DOCUMENTO_ACCION_SINCRONIZADO = 4;

    //Aprobación
    public static final Integer DOCUMENTO_VOTO_FUERA_DE_SISTEMA = 1045;
    public static final Integer DOCUMENTO_VOTO_FUERA_DE_SISTEMA2 = 1154;

    //Enmiendas
    public static final Integer DOCUMENTO_BORRADOR_PROY_RESOL = 1070;
    public static final Integer DOCUMENTO_SOPORTE_ENMIENDA = 1071;
    public static final Integer DOCUMENTO_SOLICITUD_ENMIENDA = 1069;
    public static final Integer DOCUMENTO_OPINION_JURID = 1072;
    public static final Integer DOCUMENTO_PROY_RES = 1073;
    public static final Integer DOCUMENTO_OPINION_GERENCIA_CREDITO = 1074;

    //Seguimiento Crediticio
    public static final Integer DOCUMENTO_BORRADOR_REPORTE_CALIFICACION_RIESGO = 1099;
    public static final Integer DOCUMENTO_REPORTE_CALIFICACION_RIESGO = 1100;

    //Supervisión
    public static final Integer DOCUMENTO_INFORME_SUPERVISION_PROYECTO_EJECUCION = 1103;
    public static final Integer DOCUMENTO_INFORME_SUPERVISION_PROYECTO_OPERACION = 1104;
    public static final Integer DOCUMENTO_INFORME_SUPERVISION_USO_RECURSOS = 1105;

    //Cierre
    public static final Integer DOCUMENTO_CERTIFICACION_ADEUDOS = 15;
    public static final Integer DOCUMENTO_ESCRITURA_LIBERACION_GARANTIAS = 16;
    public static final Integer DOCUMENTO_CARTA_CONFIRMACION_CIERRE = 17;
    public static final Integer DOCUMENTO_INFORME_LECCIONES_APRENDIDAS = 18;

    //Cierre Fideicomisos
    public static final Integer DOCUMENTO_ESTADOS_FINANCIEROS = 1091;
    public static final Integer DOCUMENTO_ESTADO_CUENTAS_BANCARIAS = 1092;
    public static final Integer DOCUMENTO_ESTADO_CUENTAS_CONTABLES = 1093;
    public static final Integer DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE = 1094;
    public static final Integer DOCUMENTO_PLACEME = 1086;
    public static final Integer DOCUMENTO_CIERRE_FIRMADO = 1095;
    public static final Integer DOCUMENTO_ESTADOS_FINANCIEROS_FINALES = 1096;
    public static final Integer DOCUMENTO_CONFIRMACION_CIERRE_CUENTAS_BANCARIAS_INVERSIONES = 1097;
    
    //Implementacion PCT
    //Envio al cobro
    public static final Integer DOCUMENTO_SOPORTE_ENVIO_COBRO = 1144;
    public static final Integer DOCUMENTO_SOPORTE_LIQUIDACION_DESEMBOLSOS = 1130;

    /********* START CONSTANTES DEFINICION TCC ********/
    /*-- TERMINOS --*/
    public static final String TCC_TR_NOMBRE = "NOMBRE";
    public static final String TCC_TR_DESCRIPCION = "DESCRIPCION";
    public static final String TCC_TR_ID_TCA_TIPO_FECHA_INICIO = "ID_TCA_TIPO_FECHA_INICIO";
    public static final String TCC_TR_FECHA_INICIO = "FECHA_INICIO";
    public static final String TCC_TR_PLAZO = "PLAZO";
    public static final String TCC_TR_ID_TCA_FRECUENCIA_PLAZO = "ID_TCA_FRECUENCIA_PLAZO";
    public static final String TCC_TR_FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";
    public static final String TCC_TR_ID_TCA_MONEDA = "ID_TCA_MONEDA";
    public static final String TCC_TR_MONTO = "MONTO";
    public static final String TCC_TR_TASA = "TASA";
    public static final String TCC_TR_ID_TCA_TIPO_TASA = "ID_TCA_TIPO_TASA";
    public static final String TCC_TR_FECHA = "FECHA";
    public static final String TCC_TR_FRECUENCIA_REVISION = "FRECUENCIA_REVISION";
    public static final String TCC_TR_ID_TCA_FRECUENCIA_REVISION = "ID_TCA_FRECUENCIA_REVISION";
    public static final String TCC_TR_FECHA_INICIO_REVISION = "FECHA_INICIO_REVISION";
    public static final String TCC_TR_FRECUENCIA_PAGO_INTERES = "FRECUENCIA_PAGO_INTERES";
    public static final String TCC_TR_ID_TCA_FRECUENCIA_PAGO_INTERES = "ID_TCA_FRECUENCIA_PAGO_INTERES";
    public static final String TCC_TR_FECHA_INICIO_PAGO_INTERES = "FECHA_INICIO_PAGO_INTERES";
    public static final String TCC_TR_FRECUENCIA_AMORTIZACION = "FRECUENCIA_AMORTIZACION";
    public static final String TCC_TR_ID_TCA_FRECUENCIA_AMORTIZACION = "ID_TCA_FRECUENCIA_AMORTIZACION";
    public static final String TCC_TR_MORA = "MORA";
    public static final String TCC_TR_PORCENTAJE_COBERTURA = "PORCENTAJE_COBERTURA";
    public static final String TCC_TR_SE_APLICAN_RECURSOS_CONCESION = "SE_APLICAN_RECURSOS_CONCESION";
    public static final String TCC_TR_SE_APLICAN_RECURSOS_EXTERNOS = "SE_APLICAN_RECURSOS_EXTERNOS";
    public static final String TCC_TR_REQUIERE_FONDO_PRESUPUESTARIO = "REQUIERE_FONDO_PRESUPUESTARIO";
    public static final String TCC_TR_TRE_CONTRAPARTES_TERMINO = "TRE_CONTRAPARTES_TERMINO";
    public static final String TCC_TR_TIPO_CONTRAPARTE = "TIPO_CONTRAPARTE";
    public static final String TCC_TR_MONTO_MINIMO_DESEMBOLSO = "MONTO_MINIMO_DESEMBOLSO";
    public static final String TCC_TR_MONTO_MAXIMO_DESEMBOLSO = "MONTO_MAXIMO_DESEMBOLSO";
    public static final String TCC_TR_TASA_MINIMA_DESEMBOLSO = "TASA_MINIMA_DESEMBOLSO";
    public static final String TCC_TR_TASA_MAXIMA_DESEMBOLSO = "TASA_MAXIMA_DESEMBOLSO";
    public static final String TCC_TR_REQUIERE_ORDEN_INICIO = "REQUIERE_ORDEN_INICIO";
    public static final String TCC_TR_REQ_FORMALIZACION_AUTOMATICA = "REQ_FORMALIZACION_AUTOMATICA";
    public static final String TCC_PORCENTAJE_MODIFICACION = "PORCENTAJE_MODIFICACION";
    public static final String TCC_CLIENTE_GESTIONA_CONTRATACION = "CLIENTE_GESTIONA_CONTRATACION";
    public static final String TCC_TR_ID_TCA_TIPO_AVANCE = "ID_TCA_TIPO_AVANCE";
    public static final String TCC_TR_ID_TCA_TIPO_PORCENTAJE = "ID_TCA_TIPO_PORCENTAJE";
    public static final String TCC_TR_PORCENTAJE = "PORCENTAJE";
    public static final String TCC_TR_PORCENTAJE_INICIAL = "PORCENTAJE_INICIAL";
    public static final String TCC_TR_PORCENTAJE_FINAL = "PORCENTAJE_FINAL";
    
    /*-- COMISIONES --*/
    public static final String TCC_CM_NOMBRE = "NOMBRE";
    public static final String TCC_CM_ID_TCA_TIPO_COMISION = "ID_TCA_TIPO_COMISION";
    public static final String TCC_CM_DESCRIPCION = "DESCRIPCION";
    public static final String TCC_CM_COMISION_COMPARTIDA = "COMISION_COMPARTIDA";
    public static final String TCC_CM_ID_TCA_MOMENTO_COBRO = "ID_TCA_MOMENTO_COBRO";
    public static final String TCC_CM_ID_TCA_TIPO_TASA = "ID_TCA_TIPO_TASA";
    public static final String TCC_CM_PORCENTAJE_SOBRE_MONTO_BASE = "PORCENTAJE_SOBRE_MONTO_BASE";
    public static final String TCC_CM_ID_TCA_MONTO_BASE = "ID_TCA_MONTO_BASE";
    public static final String TCC_CM_MONTO_BASE = "MONTO_BASE";
    public static final String TCC_CM_MONTO_COMISION = "MONTO_COMISION";
    public static final String TCC_CM_ID_TCA_MONEDA = "ID_TCA_MONEDA";
    public static final String TCC_CM_ID_TCA_BASE_CALCULO = "ID_TCA_BASE_CALCULO";
    public static final String TCC_CM_FECHA_VALOR = "FECHA_VALOR";
    public static final String TCC_CM_FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";
    public static final String TCC_CM_FECHA_INICIO_CAPITAL = "FECHA_INICIO_CAPITAL";
    public static final String TCC_CM_FRECUENCIA_PAGO = "FRECUENCIA_PAGO";
    public static final String TCC_CM_ID_TCA_TIPO_FRECUENCIA = "ID_TCA_TIPO_FRECUENCIA";
    public static final String TCC_CM_ID_TCA_FONDO = "ID_TCA_FONDO";
    public static final String TCC_CM_ID_TCA_ESTADO_TCC = "ID_TCA_ESTADO_TCC";
    public static final String TCC_CM_ID_TCA_SUB_ESTADO_TCC = "ID_TCA_SUB_ESTADO_TCC";
    public static final String TCC_CM_FECHA_INICIO_COMISION = "FECHA_INICIO_COMISION";
    public static final String TCC_CM_ID = "ID";

    /*-- CONDICIONES --*/
    public static final String TCC_CN_NOMBRE = "NOMBRE";
    public static final String TCC_CN_DESCRIPCION = "DESCRIPCION";
    public static final String TCC_CN_TRE_CATEGORIA_CONDICION = "TRE_CATEGORIA_CONDICION";
    public static final String TCC_CN_ID_TCA_CONTROL_CONDICION = "ID_TCA_CONTROL_CONDICION";
    public static final String TCC_CN_TRE_TCA_EVENTO_CONDICION = "TRE_TCA_EVENTO_CONDICION";
    public static final String TCC_CN_ID_TCA_TIPO_FECHA_INICIO = "ID_TCA_TIPO_FECHA_INICIO";
    public static final String TCC_CN_FECHA_INICIO = "FECHA_INICIO";
    public static final String TCC_CN_PLAZO = "PLAZO";
    public static final String TCC_CN_ID_TCA_FRECUENCIA_PLAZO = "ID_TCA_FRECUENCIA_PLAZO";
    public static final String TCC_CN_FECHA_FINAL = "FECHA_FINAL";
    public static final String TCC_CN_TRE_FUENTE_CONDICION = "TRE_FUENTE_CONDICION";
    public static final String TCC_CN_TRE_LINEA_CREDITO_TCC = "TRE_LINEA_CREDITO_TCC";
    public static final String TCC_CN_OBSERVACION_CONDICION = "OBSERVACION_CONDICION";
    public static final String TCC_CN_ID_TCA_TIPO_DESEMBOLSO = "ID_TCA_TIPO_DESEMBOLSO";

    /*-- PLAN DE ACCIONES --*/
    //Plan de acción
    public static final int TIPO_DOCUMENTO_PLAN_ACCIONES = 1079;
    //Documentos de apoyo al plan de acción
    public static final int TIPO_DOCUMENTO_APOYO_PLAN_ACCIONES = 1080;

    //**********************  TCA_ESTADO_ACCION *************************/

    public static final Integer TCA_ESTADO_ACCION_VENCIDA = 1;
    public static final Integer TCA_ESTADO_ACCION_REGISTRADA = 2;
    public static final Integer TCA_ESTADO_ACCION_POR_ATENDER = 3;
    public static final Integer TCA_ESTADO_ACCION_ATENDIDA = 4;
    public static final Integer TCA_ESTADO_ACCION_VALIDADA = 5;
    public static final Integer TCA_ESTADO_ACCION_DESESTIMADA = 6;
    public static final Integer TCA_ESTADO_ACCION_ELIMINADA = 7;

    //************************ TCA_CATEGORIA_ACCION **********************/
    public static final Integer TCA_CATEGORIA_ACCION_UNIDAD_SEGUIMIENTO_CREDITICIO = 1;
    public static final Integer TCA_CATEGORIA_ACCION_UNIDAD_CREDITOS_ESPECIALES = 2;
    public static final Integer TCA_CATEGORIA_ACCION_SUPERVISION = 3;
    public static final Integer TCA_CATEGORIA_ACCION_IBCIE = 4;
    public static final Integer TCA_CATEGORIA_ACCION_SIEMAS = 5;

    public static final String ROL_PROCCESS_CONTROL = "RolProcess";
    public static final String ROL_INGRESADOR_VALIDADOR = "RolIngresadorValidador";
    public static final String ROL_OTROS = "RolOtros";

    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_UCE = 55;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_IBCIE = 59;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_SIEMAS = 60;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_SUPERVISION = 58;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_SEGUIMIENTO = 57;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_GESTORCLIENTES = 61;
    public static final Integer ID_ROL_INGRESADOR_VALDIADOR_GESTOROPERACION = 62;
    public static final Integer ID_ROL_PROCESS_CONTROL = 48;

    public static final String ROL_ACCIONES_UCE = "FENIX_ACCIONES_UCE";
    public static final String ROL_ACCIONES_SEGUIMIENTO = "FENIX_ACCIONES_SEGUIMIENTO_CREDITICIO";
    public static final String ROL_ACCIONES_SUPERVISION = "FENIX_ACCIONES_SUPERVISION";
    public static final String ROL_ACCIONES_IBCIE = "FENIX_ACCIONES_IBCIE";
    public static final String ROL_ACCIONES_SIEMAS = "FENIX_ACCIONES_SIEMAS";
    public static final String ROL_PROCCESSCONTROL = "FENIX_PROCESS_CONTROL";
    public static final String ROL_ACCIONES_OPERACION = "FENIX_ACCIONES_OPERACION";
    public static final String ROL_ACCIONES_CLIENTE = "FENIX_ACCIONES_CLIENTE";
    public static final String ROL_RESPONSABLE = "FENIX_REPRESENTANTES";
    
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_CREADO = 5;
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_VALIDACION = 6;
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_CUMPLIMIENTO = 7;
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_PROCESO = 8;
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_DISPUESTO = 20;
    public static final int ESTADO_SOLICITUD_DESEMBOLSO_CERRADO = 9;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_CREADO = 10;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_VALIDACION = 11;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO = 12;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_PREPARADO = 13;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_TRANSITO = 14;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_APROBADO = 15;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_FONDOS = 16;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO = 17;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO = 18;
    public static final Integer ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO = 19;

    //**********************MODOS DE EJECUCION DE TABS DE CONTRATOS DESEMBOLSO*********************
    public static final Integer MODO_EJECUCION_LECTURA = 1;
    public static final Integer MODO_EJECUCION_ESCRITURA = 2;
    public static final Integer TIPO_DOCUMENTO_SOPORTE_SWIFT_DESEMBOLSO = 1129;

    public static final Integer MODO_EJECUCION_ASIGN_REC_GESTION_TRANSFERENCIAS = 3;
    public static final Integer MODO_EJECUCION_REGISTRO_DESEMBOLSO = 8;

    // ******************* MODOS DE EJECUCION ADICIONES DE TABS DE TRANSFERENCIAS *********************

    public static final Integer MODO_EJECUCION_TRANSFERENCIAS_APLICACION = 3;
    public static final Integer MODO_EJECUCION_RESERVA_DE_FONDOS = 2;
    public static final Integer MODO_LECTURA_REGISTRAR_DESEMBOLSO = 1;
    
    public static final int MODO_EJECUCION_LECTURA_INT = 1;
    public static final int MODO_EJECUCION_ESCRITURA_INT = 2;
    public static final int MODO_EJECUCION_TRANSFERENCIAS_APLICACION_INT = 3;
    // ******************* IDS DE REGLAS A EVALUAR DESEMBOLSO *******************************
    public static final Long ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO = 5L;
    public static final Long ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO = 2L;
    public static final Long ID_REGLA_TRANSACCION_MORA = 1L;
    public static final Long ID_REGLA_TRANSACCION_PROGRAMACION = 3L;
    public static final Long ID_REGLA_TRANSACCION_LAFT = 4L;
    public static final Long ID_REGLA_TRANSACCION_LIMITES = 6L;
    public static final Long ID_REGLA_TRANSACCION_CONDICIONES = 7L;
    public static final Long ID_REGLA_TRANSACCION_UMIPYME = 8L;
    public static final Long ID_REGLA_TRANSACCION_PRESENTACION_F1 = 9L;
    public static final Long ID_REGLA_TRANSACCION_AJUSTE_TASA = 10L;
    public static final Long ID_REGLA_TRANSACCION_MOMENTO_COBRO = 11L;
    public static final Long ID_REGLA_TRANSACCION_COMPONENTE_INTERES = 12L;
    public static final Long ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO = 13L;
    public static final Long ID_REGLA_TRANSACCION_PERIODO_GRACIA = 14L;
    public static final Long ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO = 15L;
    public static final Long ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO = 16L;
    public static final Long ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO = 17L;
    public static final Long ID_REGLA_TRANSACCION_REGISTRO_CONTRATO_DESEMBOLSO = 18L;
    public static final Long ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA = 19L;
    public static final Long ID_REGLA_TRANSACCION_PREVIA_HORA_CIERRE_MONEDA = 20L;
    public static final Long ID_REGLA_TRANSACCION_RESERVA_FONDOS = 21L;
    public static final Long ID_REGLA_TRANSACCION_LIMITE_GLOBAL = 22L;
    //Feature 12 (nuevas reglas) US 57
    public static final Long ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO = 23L;
    public static final Long ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA = 25L;
    
    // ******************************* IDS DE PRODUCTOS *******************************
    public static final Integer PRESTAMO_TRADICIONAL = 1;
    public static final Integer COOPERACION_TECNICA_RECUPERACION_CONTINGENTE = 17;
    public static final Integer COOPERACION_TECNICA_NO_REEMBOMSABLE = 18;
    public static final Integer COOPERACION_RECURSOS_EXT_FOPROY = 19;
    public static final Integer COOPERACION_RECURSOS_EXT_FINAM = 20;
    public static final Integer ID_AMPLIACION_LINEA_GLOBAL_CREDITO_IFI = 26;
    public static final Integer COOPERACION_NO_REEMBOLSABLE_FETS = 21;
    public static final Integer PREINVERSION = 16;

    public static final int PA09_REVISAR_ASPECTOS_JURIDICOS = 193;
    public static final int PA09_REVISAR_ASPECTOS_NORMATIVOS = 194;
    public static final int PA09_REVISAR_ASPECTOS_TECNICOS = 195;
    public static final int PA09_REALIZAR_AJUSTES = 196;
    public static final int PA09_EMITIR_RESPUESTA_CLIENTE = 197;


    public static final int DOC_REVISAR_ASPECTOS_JURIDICOS = 1167;
    public static final int DOC_REVISAR_ASPECTOS_NORMATIVOS = 1168;
    public static final int DOC_REVISAR_ASPECTOS_TECNICOS = 1169;
    public static final int DOC_REALIZAR_AJUSTES = 196;
    public static final int DOC_EMITIR_RESPUESTA_CLIENTE = 1145;
    
    
    public static final int NO_OBJECION_AVISO = 2;
    public static final int NO_OBJECION_INFORME_RESULTADOS = 7;
    public static final int NO_OBJECION_CONTRATO = 9;
    public static final int NO_OBJECION_RESULTADO_PROCESO = 10;
    public static final int NO_OBJECION_CARGO_COMPRA = 11;
    
    
    public static final int ID_ESTADO_SOLICITUD_DESEMBOLSO_CERRADO = 9;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO = 10;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDACION = 11;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO = 12;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO = 13;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_TRANSITO = 14;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO = 15;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS = 16;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO = 17;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO = 18;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO = 19;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_DISPUESTO = 20;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR = 21;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO = 22;
    public static final int ID_ESTADO_SOLICITUD_DESEMBOLSO_CREADO_POR_IMPLEMENTACION = 23;
    public static final int ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION = 24;
    public static final Integer ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDADO_POR_IMPLEMENTACION=26;
    
    public static final String PLANTILLA_DEFAULT = "Plantilla.pdf";
    public static final String PLANTILLA_AVISO_COBRO = "Plantilla_VistaPrevia_Aviso.pdf";
    public static final String PLANTILLA_INFORME_ADJUDICADO = "Plantilla_VistaPrevia_InformeResultados_Adjudicado.pdf";
    public static final String PLANTILLA_INFORME_ANULADO = "Plantilla_VistaPrevia_InformeResultados_Otros.pdf";
    public static final String PLANTILLA_INFORME_OTROS = "Plantilla_VistaPrevia_InformeResultados_Otros.pdf";
    public static final String PLANTILLA_CONTRATO = "Plantilla_VistaPrevia_Contrato.pdf";
    
    public static final Integer TIPO_NOOBJECION_AVISO_COBRO = 2;
    public static final Integer TIPO_NOOBJECION_INFORME_ADJUDICADO = 1;
    public static final Integer TIPO_NOOBJECION_INFORME_ANULADO = 2;
    public static final Integer TIPO_NOOBJECION_INFORME_OTROS = 4;
    public static final Integer TIPO_NOOBJECION_CONTRATO = 9;
    
    // IDS DE PROCESOS
    public static final int PROCESO_APROBACION = 4;
    public static final int PROCESO_FORMALIZACION = 5;
    public static final int PROCESO_IMPLEMENTACION_PCT = 25;
    
    public static final String INICIO_MANUAL = "MANUAL";
    
    /**********************************PATHS CALENDARIO COMPLEJO**********************************/
    public static final String PATH_CALENDARIO_COMPLEJO_CAPITAL = "CALENDARIO_COMPLEJO_CAPITAL";
    public static final String PATH_CALENDARIO_COMPLEJO_INTERES = "CALENDARIO_COMPLEJO_INTERES";
    
    public static final String TIPO_PAGO_CAPITAL = "CAPITAL";
    public static final String TIPO_PAGO_INTERES = "INTERES";
    /*********************************************************************************************/
    public static final String CUENTAS_BCIE = "CUENTAS_BCIE";
    public static final String DIRECTO_CLIENTE = "DIRECTO_CLIENTE";
    public static final String MONEDA_USD = "USD";
    
    /****Documento de envio al gasto*****/
    public static final int TD_SOLICITUD_DE_ENVIO_AL_GASTO = 1143;
       
    // ******************************* APLICACIONES EXTERNAS *******************************
    public static final String SERVLET_APP_EXTERNA_ID = "codapp";
    public static final String SERVLET_APP_EXTERNA_CODIGO_OPERACION = "codope";
    public static final String SERVLET_APP_EXTERNA_CODIGO_CLIENTE = "codcli";
    
    // ****************************** ESTADOS DE LA OPERACION ******************************
    public static final int ID_ESTADO_OPERACION_CANCELADA = 4;
    public static final int ID_ESTADO_OPERACION_CERRADA = 27;
    
    // ******************************** PLANTILLAS DE CORREO *******************************
    public static final int PLANTILLA_FLUJO_EVALUACION_IBCIE = 103;
}
