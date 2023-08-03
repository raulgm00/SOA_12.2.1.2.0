
	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_18.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 18)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/
	
	-- 20170711 Se agregan nuevos procesoa y tareas BPM para cubrir T2-T3

	INSERT INTO TCA_PROCESO_BPM (ID, DESCRIPCION, DESCRIPCION_CORTA, CODIGO_PROCESO, BAN_ESTATUS, FECHA_REGISTRO,  ES_PROCESO_CORE) VALUES ( 33, 'Registrar Comisiones', 'Registrar Comisiones', 'PA02', 1, CURRENT_DATE, 0); 
	INSERT INTO TCA_PROCESO_BPM (ID, DESCRIPCION, DESCRIPCION_CORTA, CODIGO_PROCESO, BAN_ESTATUS, FECHA_REGISTRO,  ES_PROCESO_CORE) VALUES ( 34, 'Reasignar Responsable Cliente', 'Reasignar Responsable Cliente', 'PU02', 1, CURRENT_DATE, 0); 

	INSERT INTO TCA_TAREA_BPM (ID, ID_PROCESO_BPM, ID_ROL_BPM, DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, FECHA_REGISTRO)  VALUES (200, 33, 7, 'Validar Registro de Comisión', 'HT03ValidarRegistroComision', 1, CURRENT_DATE);
	INSERT INTO TCA_TAREA_BPM (ID, ID_PROCESO_BPM, ID_ROL_BPM, DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, FECHA_REGISTRO)  VALUES (201, 33, 1, 'Registrar Comisión', 'HT04RegistrarComision', 1, CURRENT_DATE);

	COMMIT;

	-- 20170713  INSERTA DATOS AL CATALOGO DE ACCIONES
	
	INSERT INTO TCA_ACCION_ROL_BPM (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (TCA_ACCION_ROL_BPM_SEQ.NEXTVAL, 'Eliminar comentarios', 'ELIMINAR_COMENTARIO');
	INSERT INTO TCA_ACCION_ROL_BPM (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (TCA_ACCION_ROL_BPM_SEQ.NEXTVAL, 'Enviar documento ONBASE', 'ENVIAR_DOCTO_ONBASE');

	COMMIT;
	
	-- 20170713  SE INSERTAN DATOS DUMMY AL CATALOGO DE RELACIÓN DE ACCIONES Y ROL, SOLO EN DEV, NO DEBE ENVIARSE A QA, prePROD y PROD
	-- ########## @@@@@@@@@@@@@@@@@@@@@@
	
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 11, 1);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 1, 1);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 8, 1);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 4, 1);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 3, 1);

	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 11, 2);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 7, 2);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 1, 2);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 8, 2);
	INSERT INTO TRE_ACCION_ROL_BPM (ID, ID_TCA_ROL_BPM, ID_TCA_ACCION_ROL_BPM) VALUES (TRE_ACCION_ROL_BPM_SEQ.NEXTVAL, 21, 2);

	COMMIT;
	
	
	
	-- ##*****************************************************************************************************##
	-- 20170808 Se agrega nueva plantilla de correo para comentarios de aprobación  (Requerimientos T2-T3)
	-- ##*****************************************************************************************************##

	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (90, 'PLANTILLA_COMENTARIOS_APROBACION', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Emisión de comentarios vía Móvil FÉNIX</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que <strong>[NOMBRE_USUARIO]</strong> ha emitido un comentario sobre [TIPO_TRANSACCION] <strong>[ID_OPERACION] [OPERACION]</strong> <strong>[IDCLIENTE] [CLIENTE]</strong>.  <p>Puede acceder a los resultados a través de la <a href="[URL_APLICACION_WEB_MOVIL]"><b>Aplicación Web Móvil</b></a>.        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACIÓN DE COMENTARIOS VÍA MÓVIL FÉNIX', CURRENT_DATE, 1, null);

	COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID = 90;
   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID = 90;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID = 90;
	-- COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID = 90;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID = 90;
	COMMIT;
	
	
	-- ACTUALIZA LA RELACIÓN DE LA PLANTILLA Y EL EVENTO
	
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (34, 'COMENTARIOS APROBACION', 'COMENTARIOS APROBACION');

	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(57, 4, 90, 34);

	COMMIT;
	
	-- 20170809 Se ingresan los nuevos estados y subestados para el registro de comisiones
    
    DROP TRIGGER TCA_ESTADO_TCC_TGR;
	
	INSERT INTO TCA_ESTADO_TCC (ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, ES_SUBESTADO) VALUES (TCA_ESTADO_TCC_SEQ.NEXTVAL,'Creada', 'Creada', CURRENT_DATE, 1, 1);
	INSERT INTO TCA_ESTADO_TCC (ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, ES_SUBESTADO) VALUES (TCA_ESTADO_TCC_SEQ.NEXTVAL,'En registro', 'En registro', CURRENT_DATE, 1, 1);
	INSERT INTO TCA_ESTADO_TCC (ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, ES_SUBESTADO) VALUES (TCA_ESTADO_TCC_SEQ.NEXTVAL,'Registrada', 'Registrada', CURRENT_DATE, 1, 0);

	COMMIT;
	
	-- 20170810 Se ingresan registros a la tabla TCA_APP_EXTERNA y TCA_PARAMETROS_APP, solo el primero debe migrarse a QA, prePROD, los demás son ejemplos
	
	-- Inserta registros en TCA_APP_EXTERNA
	
	INSERT INTO TCA_APP_EXTERNA (ID, DESCRIPCION, DESCRIPCION_CORTA, REQUIERE_NUEVA_VENTANA) VALUES (TCA_APP_EXTERNA_SEQ.NEXTVAL, 'http://hn-obpm-prod.bcie.org:7031/eval/default.jsp?CodigoNegocio=ID_OPERACION', 'Evaluaciones', 1);
	-- INSERT INTO TCA_APP_EXTERNA (ID, DESCRIPCION, DESCRIPCION_CORTA, REQUIERE_NUEVA_VENTANA) VALUES (TCA_APP_EXTERNA_SEQ.NEXTVAL, 'http:/hn-obpm-qa.bcie.org:7065/eval/default.jsp?CodigoNegocio=ID_OPERACION', 'Evaluaciones', 1);
	-- INSERT INTO TCA_APP_EXTERNA (ID, DESCRIPCION, DESCRIPCION_CORTA, REQUIERE_NUEVA_VENTANA) VALUES (TCA_APP_EXTERNA_SEQ.NEXTVAL, 'http://hn-obpm-dev.bcie.org:7014/bpm/workspace/?CodigoCliente=ID_CLIENTE', 'Dummy Cliente', 0);
	-- INSERT INTO TCA_APP_EXTERNA (ID, DESCRIPCION, DESCRIPCION_CORTA, REQUIERE_NUEVA_VENTANA) VALUES (TCA_APP_EXTERNA_SEQ.NEXTVAL, 'http://hn-obpm-dev.bcie.org:7014/bpm/workspace/?CodigoCliente=ID_CLIENTE' ||  chr(38) ||'CodigoNegocio=ID_OPERACION', 'Dummy Cliente/Operación', 1);
	COMMIT;

	-- Inserta registros en TCA_PARAMETROS_APP
	
	INSERT INTO TCA_PARAMETROS_APP (ID, LLAVE, TIPO_PARAMETRO, SQL_QUERY) VALUES (TCA_PARAMETROS_APP_SEQ.NEXTVAL, 'ID_OPERACION', 'ID_OPERACION', 'SELECT :ID_PARAMETRO FROM DUAL');
	INSERT INTO TCA_PARAMETROS_APP (ID, LLAVE, TIPO_PARAMETRO, SQL_QUERY) VALUES (TCA_PARAMETROS_APP_SEQ.NEXTVAL, 'ID_CLIENTE', 'ID_CLIENTE', 'SELECT :ID_PARAMETRO FROM DUAL');
	-- INSERT INTO TCA_PARAMETROS_APP (ID, LLAVE, TIPO_PARAMETRO, SQL_QUERY) VALUES (TCA_PARAMETROS_APP_SEQ.NEXTVAL, 'ID_DECLARACION_JURADA', 'ID_OPERACION', 'SELECT ID_DECLARACION FROM TRE_DECLARACION_OPERACION WHERE ID_OPERACION = :ID_PARAMETRO AND ES_VIGENTE = 1');
	COMMIT;

	-- 20170811 Agrega nuevos TAGS a TCA_TAG_PLANTILLA, para complementar las plantillas de error ONBASE

	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (41, 'TIPO_ETIQUETA_1', 'SELECT  CASE :ID_PARAMETRO WHEN 0 THEN ''Nombre del Cliente:'' WHEN 1 THEN ''Nombre de la Operación:'' END FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (42, 'TIPO_ETIQUETA_2', 'SELECT  CASE :ID_PARAMETRO WHEN 0 THEN ''Código del Cliente:'' WHEN 1 THEN ''Código de la Operación:'' END FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (43, 'ERROR_CARGA_ONBASE', 'SELECT DESCRIPCION FROM TBI_DOCUMENTO WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');

	UPDATE TCA_TAG_PLANTILLA SET DESCRIPCION_TAG = 'TIPO_DOCUMENTO', SQL_QUERY = 'SELECT DESCRIPCION FROM TCA_DOCUMENTO WHERE ID = :ID_PARAMETRO', CATEGORIA_PLANTILLA = NULL WHERE DESCRIPCION_TAG = 'TIPO_ARCHIVO' ; 

	COMMIT;


	-- 20170811 Inserta nueva plantilla para indicar el error en el tipo de documento a ONBASE

	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (91, 'PLANTILLA_ERROR_ONBASE_BITACORA', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Carga de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema al cargar el tipo de documento <b>[TIPO_DOCUMENTO]</b> a OnBase, en la tarea <b>[TAREA]</b> con el usuario <b>[ID_USUARIO]</b>, favor de revisar la información para el adjunto <b>[ADJUNTO]</b> a través del sistema de FÉNIX.<p>Se presentó el siguiente error: [ERROR_CARGA_ONBASE].</p><p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'ERROR AL CARGAR DOCUMENTO ONBASE', CURRENT_DATE, 1, null);

	COMMIT;
	
	--- &&&&&&&&  FALTA AGREGAR LAS SIGUIENTES INSTRUCCIONES.... EN DEV 20170811
	
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID = 91;
   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID = 91;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID = 91;
	--COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID = 91;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID = 91;
	COMMIT;
	
	-- NO SE AGREGA LA RELACIÓN DE LA PLANTILLA Y EL EVENTO (TCA_EVENTO_PLANTILLA y TRE_PROCESO_EVENTO_PLANTILLA), dado que se sigue la misma configuración de la plantilla 2 y 91
	
	
	
	
	

	-- 20170905 MODIFICACION A LAS PLANTILLAS 2, 4, 9 Y 10
		
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error en el Servicio de Carga de Documentos a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema en el Servicio de Carga de Documentos a OnBase, a través del Gestor de [TIPO_GESTOR] de FÉNIX.<p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 2;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Tipo de Archivo OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td height="134"><font face="arial">Ha ocurrido un problema al cargar el documento a OnBase, el Tipo de Archivo no es permitido para el Adjunto <b><font face="arial">[ADJUNTO], </font></b>favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.</font>          <p><font face="arial">Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. </p> <p> Saludos cordiales,</font></p>          <p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 4;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Tipo de Acción para Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td><font face="arial">Ha sucedido un problema al cargar el documento a OnBase debido a que el Tipo de Acción no es correcta para el Adjunto  <b>[ADJUNTO]</b>. Favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.      <p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.<p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 9;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Falta de Información de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td><font face="arial">Ha sucedido un problema al cargar el documento a OnBase debido a que no se cuenta con suficiente información en la base de datos para el Adjunto  <b>[ADJUNTO]</b>. Favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.      <p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 10;


	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (44, 'TIPO_GESTOR', 'SELECT  CASE :ID_PARAMETRO WHEN 0 THEN ''Clientes:'' WHEN 1 THEN ''Operaciones:'' END FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');

	COMMIT;
	
	--- 20170912 Se agrega la nueva columna a la configuración de la condición CON904, CON905 y CON005 
	
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_CONDICION, TIPO_VALOR) VALUES (480, 'ID_TCA_TIPO_DESEMBOLSO', 14, 0, 1, 'Aplicable a', 4, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_CONDICION, TIPO_VALOR) VALUES (481, 'ID_TCA_TIPO_DESEMBOLSO', 14, 0, 1, 'Aplicable a', 5, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_CONDICION, TIPO_VALOR) VALUES (482, 'ID_TCA_TIPO_DESEMBOLSO', 14, 0, 1, 'Aplicable a', 10, 'ONECHOICE');

	COMMIT;
	
	--- 20170912 Se agregan elementos al catalogo de TCA_TIPO_DESEMBOLSO 
    
    INSERT INTO TCA_TIPO_DESEMBOLSO (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (1, 'Solicitud de Desembolso', 'Solicitud de Desembolso');
    INSERT INTO TCA_TIPO_DESEMBOLSO (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (2, 'Contrato de Desembolso', 'Contrato de Desembolso');
    
    COMMIT;

    
    --- 20170912 Se agregan elementos de precarga para la condición CON005
    
    UPDATE condicion SET ID_TCA_TIPO_DESEMBOLSO = 1 WHERE ID = 1009;
    COMMIT;
	

	-- 20170915 INGRESA NUEVO VALOR DE TERMINO

	INSERT INTO TCA_TIPO_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, COD_EXTERNO) VALUES (8, 'Inicio', 'Inicio', CURRENT_DATE, 1, NULL);
	
	-- 20170915 MODIFICA EL NOMBRE DE LA COLUMNA
	
	UPDATE TCO_ATRIBUTO_TCC SET ETIQUETA = 'Requiere Formalización de Convenio' WHERE COLUMNA = 'REQ_FORMALIZACION_AUTOMATICA';
	
	COMMIT;
	
	-- 20170915 ACTUALIZA LOS CAMPOS OT Y ASJUR  NUEVO VALOR DE TERMINO
	
	-- T101
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 1;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 1;

	-- T102
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 2;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 2;

	-- T103
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 3;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 3;

	-- T104
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 4;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 4;

	-- T105
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 0 WHERE ID = 5;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 5;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 5;


	-- T106
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 1 WHERE ID = 6;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 6;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 6;

	-- T107
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 1 WHERE ID = 7;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 7;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 7;

	-- T108
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 8;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 8;

	-- T199
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 9;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 9;

	-- T109
	UPDATE TCA_TERMINO SET DESCRIPCION = 'Ejecución (de estudio)' WHERE ID = 35;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 35;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 35;

	-- T110
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 38;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 38;

	-- T201
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 10;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 10;

	-- T202
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 11;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 11;

	-- T203
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 12;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 12;

	-- T299
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 13;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 13;

	-- T301
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 14;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 14;

	-- T302
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 15;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 15;

	-- T303
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 16;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 16;

	-- T304
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 17;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 17;

	-- T305
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 18;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 18;

	-- T399
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 19;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 19;

	-- T401
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 20;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 20;

	-- T402
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 1 WHERE ID = 21;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 21;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 21;

	-- T403
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 22;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 22;

	-- T404
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 23;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 23;

	-- T405
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 1 WHERE ID = 24;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 24;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 1 WHERE ID = 24;

	-- T499
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 25;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 25;

	-- T501
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 26;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 26;

	-- T599
	UPDATE TCA_TERMINO SET REQUIERE_OCG = 1 WHERE ID = 27;
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 27;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 27;

	-- T601
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 28;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 28;

	-- T602
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 29;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 29;

	-- T603
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 30;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 30;

	-- T604
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 31;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 31;

	-- T699
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 32;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 32;

	-- T799
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 33;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 33;

	-- T701
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 34;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 34;

	-- T702
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 36;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 36;

	-- T703
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 37;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 37;

	-- T704
	UPDATE TCA_TERMINO SET REQUIERE_OT = 0 WHERE ID = 39;
	UPDATE TCA_TERMINO SET REQUIERE_ASJUR = 0 WHERE ID = 39;

	COMMIT;

	-- 20170918 SE INGRESAN NUEVOS VALORES EN TCA_TERMINO

	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 40, 'Modificación PGI menor a 15%', 'T705', 7, 0, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 0, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 41, 'Modificación PGI  entre 15%  y 25%', 'T706', 7, 0, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 0, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 42, 'Modificación PGI  mayor a 25%', 'T707', 7, 0, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 0, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 43, 'Liberación Parcial de Garantía', 'T708', 7, 0, 0, 0, 0, 1, CURRENT_DATE, 1, 0, 0, 0, 1);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 44, 'Enmienda', 'T709', 7, 0, 0, 0, 1, 0, CURRENT_DATE, 1, 0, 0, 0, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 45, 'I-BCIE Medio Término', 'T801', 8, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 1, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 46, 'I-BCIE Ex Post', 'T802', 8, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 1, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 47, 'Seguimiento SIEMAS', 'T803', 8, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 0, 1, 1, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 48, 'Presentar Solicitud de Financiamiento', 'T111', 1, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 1, 1, 0, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 49, 'Inicio Evaluación Medio Término', 'T112', 1, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 1, 1, 0, 0);
	INSERT INTO TCA_TERMINO (ID, DESCRIPCION, DESCRIPCION_CORTA, ID_TCA_TIPO_TERMINO, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, REQUIERE_OCG, FECHA_REGISTRO, BAN_ESTATUS, ES_VENCIMIENTO, ES_UNICO_EN_OPERACION, REQUIERE_OT, REQUIERE_ASJUR) VALUES ( 50, 'Seguimiento SIEMAS', 'T113', 1, 1, 0, 0, 0, 0, CURRENT_DATE, 1, 1, 1, 0, 0);


	COMMIT;

	-- 20170918 SE INGRESAN NUEVOS VALORES EN TCO_ATRIBUTO_TCC

	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (483, 'PORCENTAJE_MODIFICACION', 1, 0, 1, 'Porcentaje de modificación', 40, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (484, 'PORCENTAJE_MODIFICACION', 1, 0, 1, 'Porcentaje de modificación', 41, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (485, 'PORCENTAJE_MODIFICACION', 1, 0, 1, 'Porcentaje de modificación', 42, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (486, 'DESCRIPCION', 1, 0, 1, 'Descripción', 43, 'VARCHAR');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (487, 'DESCRIPCION', 1, 0, 1, 'Descripción', 44, 'VARCHAR');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (488, 'ID_TCA_TIPO_AVANCE', 1, 0, 1, 'Tipo de Avance', 45, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (489, 'ID_TCA_TIPO_PORCENTAJE', 2, 0, 1, 'Tipo de Porcentaje', 45, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (490, 'PORCENTAJE', 3, 0, 1, 'Porcentaje', 45, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (491, 'PORCENTAJE_INICIAL', 4, 0, 1, 'Porcentaje Inicial', 45, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (492, 'PORCENTAJE_FINAL', 5, 0, 1, 'Porcentaje Final', 45, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (493, 'ID_TCA_TIPO_AVANCE', 1, 0, 1, 'Tipo de Avance', 46, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (494, 'ID_TCA_TIPO_PORCENTAJE', 2, 0, 1, 'Tipo de Porcentaje', 46, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (495, 'PORCENTAJE', 3, 0, 1, 'Porcentaje', 46, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (496, 'PORCENTAJE_INICIAL', 4, 0, 1, 'Porcentaje Inicial', 46, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (497, 'PORCENTAJE_FINAL', 5, 0, 1, 'Porcentaje Final', 46, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (498, 'ID_TCA_TIPO_AVANCE', 1, 0, 1, 'Tipo de Avance', 47, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (499, 'ID_TCA_TIPO_PORCENTAJE', 2, 0, 1, 'Tipo de Porcentaje', 47, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (500, 'PORCENTAJE', 3, 0, 1, 'Porcentaje', 47, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (501, 'PORCENTAJE_INICIAL', 4, 0, 1, 'Porcentaje Inicial', 47, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (502, 'PORCENTAJE_FINAL', 5, 0, 1, 'Porcentaje Final', 47, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (503, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 48, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (504, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 48, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (505, 'PLAZO', 3, 0, 1, 'Plazo', 48, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (506, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 48, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (507, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 48, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (508, 'DESCRIPCION', 6, 1, 0, 'Descripción', 48, 'VARCHAR');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (509, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 49, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (510, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 49, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (511, 'PLAZO', 3, 0, 1, 'Plazo', 49, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (512, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 49, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (513, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 49, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (514, 'DESCRIPCION', 6, 0, 0, 'Descripción', 49, 'VARCHAR');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (515, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 50, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (516, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 50, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (517, 'PLAZO', 3, 0, 1, 'Plazo', 50, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (518, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 50, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (519, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 50, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (520, 'DESCRIPCION', 6, 0, 0, 'Descripción', 50, 'VARCHAR');

	COMMIT;

	-- 20170922 SE AGREGA NUEVA TAREA BPM PARA EL PROCESO DE APROBACIÓN OPERACION PARA EL ROL 10-Analista de Seguimiento Crediticio, Tarea "Realizar modificaciones"
	INSERT INTO TCA_TAREA_BPM (ID, ID_PROCESO_BPM, ID_ROL_BPM, DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, FECHA_REGISTRO)  VALUES (202, 4, 10, 'Realizar modificaciones', 'HT04RealizarModificaciones', 1, CURRENT_DATE);

	COMMIT;
	
	-- 20170922 SE CONFIGURAN LOS PERMISOS DE LOS DOCTOS PARA EL ROL 10-Analista de Seguimiento Crediticio, Tarea "Realizar modificaciones"
	
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Dictamen'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Presentación para Aprobación'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Solicitud, aprobación y/o rechazo de readecuación de deuda'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Solicitud, aprobación y/o rechazo de ampliación de plazo para cumplimiento condiciones previas al primer desembolso'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Solicitud, aprobación y/o rechazo de ampliación de plazo para cumplimiento condiciones previas para escriturar'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Solicitud, aprobación y/o rechazo de modificación del PGI'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Borrador del Proyecto de Resolución'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Otros Documentos Soporte para la Aprobación'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Informe I-BCIE'),202,1,1,1,1);
	INSERT  INTO TCO_DOCUMENTO_TAREA (ID, ID_TCA_DOCUMENTO, ID_TAREA_BPM, PUEDE_CREAR, PUEDE_MODIFICAR, PUEDE_BORRAR, PUEDE_CONSULTAR) VALUES ( TCO_DOCUMENTO_TAREA_SEQ.NEXTVAL,(SELECT ID FROM TCA_DOCUMENTO WHERE DESCRIPCION = 'Informe SIEMAS'),202,1,1,1,1);

	COMMIT;
	
	/
    	
	-- Asigna los permisos de consulta a todos los tipos de documentos para la nueva tarea
	-- Debe crearse primero el procedimiento (P_REGE_TCO_DOCUMENTO_TAREA)
	BEGIN
	  P_REGE_TCO_DOCUMENTO_TAREA; 
	END;

	/

	COMMIT;
	
	-- 20170925 Se agregan los valores para los catálogos tipo de Avance y tipo de Porcentaje para los terminos T8##
	
	INSERT INTO TCA_TIPO_AVANCE (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (1, 'Físico', 'Físico');
	INSERT INTO TCA_TIPO_AVANCE (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (2, 'Financiero', 'Financiero');
	
	INSERT INTO TCA_TIPO_PORCENTAJE (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (1, 'Único', 'Único');
	INSERT INTO TCA_TIPO_PORCENTAJE (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (2, 'Rango', 'Rango');
	
	COMMIT;

	-- 20170925 Se agrega la configuración del término T110 	

	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (521, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 38, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (522, 'FECHA_INICIO', 2, 0, 1, 'Fecha de Inicio', 38, 'DATE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (523, 'PLAZO', 3, 0, 1, 'Plazo', 38, 'NUMBER');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (524, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 38, 'ONECHOICE');
	INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (525, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 38, 'DATE');
	
	COMMIT;
	
	-- 20170925 Se modifican los permisos de todos los tipos de documentos para su manejo en cada uno de los gestores
	
	-- Actualiza los permisos para la tarea "Digitalizadora"
	UPDATE TCO_DOCUMENTO_TAREA SET PUEDE_CREAR = 1, PUEDE_MODIFICAR = 1, PUEDE_BORRAR = 1 WHERE ID_TAREA_BPM = 65;

	-- Actualiza los permisos para la tarea "Detalle de cliente"
	UPDATE TCO_DOCUMENTO_TAREA SET PUEDE_CREAR = 1, PUEDE_MODIFICAR = 1, PUEDE_BORRAR = 1 WHERE ID_TAREA_BPM = 112;

	-- Actualiza los permisos para la tarea "Detalle de solicitud"
	UPDATE TCO_DOCUMENTO_TAREA SET PUEDE_CREAR = 1, PUEDE_MODIFICAR = 1, PUEDE_BORRAR = 1 WHERE ID_TAREA_BPM = 192;

	COMMIT;



	-- 20170927 Se agregan datos a la entidad TCA_TIPO_PORCENTAJE
	

	INSERT INTO TCA_CATEGORIA_EVENTO (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (1, 'Evento Prestablecido', 'Evento Prestablecido');
	INSERT INTO TCA_CATEGORIA_EVENTO (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (2, 'Vencimiento de un plazo TCC', 'Vencimiento de un plazo TCC');
	INSERT INTO TCA_CATEGORIA_EVENTO (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (3, 'Alcanzar un porcentaje TCC', 'Alcanzar un porcentaje TCC');

	COMMIT;
	
	UPDATE TCA_PLANTILLA_CORREO SET ID_TCA_CATEGORIA_EVENTO = 1;
    
    COMMIT;
	
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY ID_TCA_CATEGORIA_EVENTO NOT NULL;
	
	-- 20170928 Se modifica la definición del campo 
	
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_TAGS = 0;
    
    COMMIT;
	
	-- 20170928 Se agrega un nuevo tag a la tabla de CONFIGURACION
	
	
	-- NUEVO TAG: WSDL_ENVIAR_CORREO EN DEV
	--INSERT INTO CONFIGURACION(LLAVE, VALOR, FECHA_REGISTRO, BAN_ESTATUS) VALUES('WSDL_ENVIAR_CORREO', 'http://hn-obpm-dev.bcie.org:7013/Utilidades/EnviarCorreoElectronicoService_PS/V1.0?WSDL', CURRENT_DATE, 1);
	--COMMIT;

	
	-- NUEVO TAG: WSDL_ENVIAR_CORREO EN QA
	--INSERT INTO CONFIGURACION(LLAVE, VALOR, FECHA_REGISTRO, BAN_ESTATUS) VALUES('WSDL_ENVIAR_CORREO', 'http://hn-obpm-qa-1.bcie.org:7013/Utilidades/EnviarCorreoElectronicoService_PS/V1.0?WSDL', CURRENT_DATE, 1);
	--COMMIT;
	
	
	-- NUEVO TAG: WSDL_ENVIAR_CORREO EN PREPROD
	--INSERT INTO CONFIGURACION(LLAVE, VALOR, FECHA_REGISTRO, BAN_ESTATUS) VALUES('WSDL_ENVIAR_CORREO', 'http://hn-obpm-qa.bcie.org:7064/Utilidades/EnviarCorreoElectronicoService_PS/V1.0?WSDL', CURRENT_DATE, 1);
	--COMMIT;

	-- NUEVO TAG: WSDL_ENVIAR_CORREO EN PROD
	INSERT INTO CONFIGURACION(LLAVE, VALOR, FECHA_REGISTRO, BAN_ESTATUS) VALUES('WSDL_ENVIAR_CORREO', 'http://hn-obpm-prod.bcie.org:7021/Utilidades/EnviarCorreoElectronicoService_PS/V1.0?WSDL', CURRENT_DATE, 1);
	COMMIT;
	


	-- 20170929 Se agrega la notificación de error de envio de correo para el administrador
	

	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS) VALUES (92, 'PLANTILLA_ERROR_ENVIO_CORREO', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de envió de correo: [ASUNTO]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Se le notifica que ha ocurrido un problema al enviar la notificación <b>[ASUNTO]</b> [TIPO_NOTIFICACION] [IDCLIENTE] [ID_OPERACION].<p>Se presentó el siguiente error: [ERROR_ENVIO_CORREO].</p><p>Para tener más detalles al respecto, favor de revisar el log de notificaciones fallidas con el identifiador [ID_ENVIO_CORREO]<p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE]</b></font></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'ERROR DE ENVIÓ DE CORREO', CURRENT_DATE, 1, null,1,1);


	COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID = 92;
   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID = 92;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID = 92;
	-- COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID = 92;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID = 92;
	COMMIT;

	-- 20171002 Se agregan los tags necesarios de la plantilla 92	
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (45, 'ASUNTO', 'SELECT ASUNTO FROM TCA_PLANTILLA_CORREO WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (46, 'TIPO_NOTIFICACION', 'SELECT  CASE :ID_PARAMETRO WHEN 0 THEN ''para el cliente:'' WHEN 1 THEN ''para la operación:'' END FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (47, 'ERROR_ENVIO_CORREO', 'SELECT DESCRIPCION_ERROR FROM TBI_ENVIO_CORREO_2 WHERE ID = :ID_PARAMETRO ', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (48, 'ID_ENVIO_CORREO', 'SELECT :ID_PARAMETRO FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');

	COMMIT;
	
	-- 20171010 Se agrega un nuevo estado al catálogo de la operación
	INSERT INTO TCA_ESTADO_OPERACION (ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, ID_TCA_TIPO_ESTADO, COD_EXTERNO) VALUES (27, 'Cerrada', 'Cerrada', CURRENT_DATE, 1, 1, 0);

	COMMIT;
	
	
	-- 20171016
	-- INSERTA LAS NUEVAS PLANTILLAS EN  TCA_PLANTILLA_CORREO
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (93, 'PLANTILLA_APROBACION_OPERACION', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Aprobación de operación</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que a nivel <b>[NIVEL_APROBACION]</b>, se ha aprobado la operación <strong>[ID_OPERACION] - [OPERACION]</strong>, del cliente <strong>[IDCLIENTE] - [CLIENTE]</strong>. <p>[TERMINO_APROBACION]</p><p>Para acceder a los datos de la operación, debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</p>        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACIÓN DE APROBACION DE OPERACIÓN', CURRENT_DATE, 1, null,1,1,1,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (94, 'PLANTILLA_APROBACION_ENMIENDA', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>TCC enmendados para la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que para la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B>, se han aprobado los siguientes TCC enmendados: <p>[ID_ENMIENDA_TCC]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACIÓN  DE APROBACION DE ENMIENDA', CURRENT_DATE, 1, null,1,1,1,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (95, 'PLANTILLA_ATENCION_ACCION', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Atención de acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que ha sido atendida la siguiente acción dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE]</B> <B>[CLIENTE]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE ATENCION DE ACCION', CURRENT_DATE, 1, null,1,1,0,0,1,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (96, 'PLANTILLA_NUEVA_ACCION', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Nuevas acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que han sido aprobadas nuevas acciones dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE]</B> <B>[CLIENTE]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE NUEVA ACCION', CURRENT_DATE, 1, null,1,1,0,0,0,1);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (97, 'PLANTILLA_ACTUALIZACION_AVANCE_FISICIO_FINANCIERO', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Avance físico y financiero de la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que la operación <B>[ID_OPERACION]</B> <B>[OPERACION]</B>, ha actualizado el siguiente avance: </p><p>[AVANCE_FISICO]</p><p>[AVANCE_FINANCIERO]</p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr> <tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table border="0" cellpadding="0" cellspacing="0" width="100%"><!-- Datos Generales de la Operación y la Tarea --><!-- Inicio Línea 1 --><tr><td width="70%" class="titlebold"><font face="arial">Responsable de la Operación:</font></td><td width="290" class="titlebold"><font face="arial">Supervisor</font></td></tr><tr><td width="290" class="title"><font face="arial"><B>[RESPONSABLE]</B></font></td><td width="290" class="title"><font face="arial"><B>[SUPERVISOR]</B></font></td></tr></table></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE ACTUALIZACION DE AVANCE FISICIO Y FINANCIERO', CURRENT_DATE, 1, null,1,1,0,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (98, 'PLANTILLA_NUEVO_SCR_MEJORA', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Mejora de SCR: [CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que al cliente <B>[IDCLIENTE]</B> - <B>[CLIENTE]</B> se le ha aprobado una mejora en la Calificación de Riesgo <B>[SCR_CLIENTE]</B>, su calificación anterior era <B>[SCR_ANTERIOR]</B>.</font></p><p><font face="arial">Puede acceder al Reporte de Calificación de Riesgo a través del Gestor de Clientes en la sección de Enlaces de su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE NUEVO SCR POR MEJORA', CURRENT_DATE, 1, null,1,1,0,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (99, 'PLANTILLA_INICIO_SEGUIMIENTO_SIEMAS', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Seguimiento SIEMAS: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado el proceso de Seguimiento SIEMAS, con la siguiente información: </p><p>[INICIO_SEGUIMIENTO_SIEMAS]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE INICIO SEGUIMIENTO SIEMAS', CURRENT_DATE, 1, null,3,1,0,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (100, 'PLANTILLA_INICIO_EVALUACION_EXPOST', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Evaluación Ex Post: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado la Evaluación Ex Post, con la siguiente información: </p><p>[INICIO_I-BCIE_EX_POST]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE INICIO EVALUACION EXPOST', CURRENT_DATE, 1, null,3,1,0,0,0,0);
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (101, 'PLANTILLA_INICIO_EVALUACION_MEDIO_TERMINO', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Evaluación Medio Término: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado la Evaluación Medio Término, con la siguiente información: </p><p>[INICIO_I-BCIE_MEDIO_TERMINO]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE INICIO EVALUACION MEDIO TERMINO', CURRENT_DATE, 1, null,3,1,0,0,0,0);

	COMMIT;
	
	-- 20171016
	-- INSERTA LOS EVENTOS EN TCA_EVENTO_PLANTILLA

	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (35, 'APROBACION OPERACIÓN', 'APROBACION OPERACIÓN');
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (36, 'APROBACION ENMIENDA', 'APROBACION ENMIENDA');
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (37, 'ACTUALIZACION AVANCE FISICIO FINANCIERO', 'ACTUALIZACION AVANCE FISICIO FINANCIERO');
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (38, 'NUEVO SCR MEJORA', 'NUEVO SCR MEJORA');

	COMMIT;
	
	-- 20171016
	-- INSERTA LA RELACION ENTRE EVENTO, PLANTILLA Y PROCESO EN TRE_PROCESO_EVENTO_PLANTILLA

	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(58, 4, 93, 35);
	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(59, 4, 94, 36);
	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(60, 13, 97, 37);
	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(61, 4, 98, 38);
	
	COMMIT;

	-- 20171017
	-- INSERTA LAS NUEVAS PLANTILLAS EN  TCA_PLANTILLA_CORREO
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (102, 'PLANTILLA_CLIENTE_MORA', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Cliente en mora: [CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que el cliente <B>[IDCLIENTE]</B> - <B>[CLIENTE]</B> se encuentra en mora y ha iniciado un proceso de Prepago.</strong></font></p>    <p><font face="arial">Para acceder a la información del cliente debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automaticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACION DE CLIENTE EN MORA', CURRENT_DATE, 1, null,1,0,0,0,0,0);

	-- INSERTA LOS EVENTOS EN TCA_EVENTO_PLANTILLA
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (39, 'CLIENTE EN MORA', 'CLIENTE EN MORA');
	
	-- INSERTA LA RELACION ENTRE EVENTO, PLANTILLA Y PROCESO EN TRE_PROCESO_EVENTO_PLANTILLA
	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(62, 26, 102, 39);

	COMMIT;
    
    -- 20171017
	-- INSERTA NUEVO ROL "Analista OPEP" EN "TCA_ROL_BPM"
	
	INSERT INTO TCA_ROL_BPM(ID, DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, FECHA_REGISTRO) VALUES (72, 'Analista OPEP', 'FENIX_ANALISTA_OPEP', 1, CURRENT_DATE);
	COMMIT;
	
	-- 20171017
	-- SE INSERTAN VALORES EN TRE_CATEGORIA_ACCION_ROL_BPM
	
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (1, 2, 55);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (2, 2, 61);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (3, 1, 57);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (4, 1, 61);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (5, 3, 58);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (6, 3, 62);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (7, 4, 59);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (8, 4, 62);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (9, 5, 60);
	INSERT INTO TRE_CATEGORIA_VALIDADOR_ROL (ID, ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM) VALUES (10, 5, 62);

	
	COMMIT;
	
	--- 20171017 Modifica el asunto para cada una de las plantillas
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID > 92;   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID > 92;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID > 92;
	--COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID > 92;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID > 92;
	COMMIT;
	
	-- 20171017 ACTUALIZA EN TCA_PLANTILLA_CORREO LA INFORMACIÓN DE LAS BANDERAS EN 0, PARA LAS PLANTILLAS NO PARAMETRIZABLES (ANTERIORES AL REQUERIMIENTO)	
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_ET = 0, BAN_VALIDA_MCC = 0, BAN_VALIDA_VA = 0, BAN_VALIDA_RA = 0, BAN_VALIDA_RO = 0 WHERE ID BETWEEN 1 AND 92;
	COMMIT;
	
	-- 20171017 ACTUALIZA EL MENSAJE DE LAS PLANTILLAS 47,48 Y 91   	
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Cierre de la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que el proceso de cierre de la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B>, donde el responsable de la operación fue <B>[RESPONSABLE]</B>, ha concluido satisfactoriamente.</font></p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 47;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Cierre del Fondo/Fideicomiso de la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que el proceso de cierre del Fondo/Fideicomiso de la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B>, donde el responsable de la operación fue <B>[RESPONSABLE]</B>, ha concluido satisfactoriamente.</font></p><p><font face="arial">Justificación de cierre: <p>[JUSTIFICACION_CIERRE]</p></font></p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 48;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Carga de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema al cargar el tipo de documento <b>[TIPO_DOCUMENTO]</b> a OnBase, en la tarea <b>[TAREA]</b> con el usuario <b>[ID_USUARIO]</b>, favor de revisar la información para el adjunto <b>[ADJUNTO]</b> a través del sistema de FÉNIX.<p>El <B>[FECHA_ERROR_CARGA_ONBASE]</B>, se presentó el siguiente error: [ERROR_CARGA_ONBASE].</p><p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 91;
	
	COMMIT;
	
	-- 20171018 SE INGRESA VALOR DE TIEMPO DE REENVIO EN LA TABLA DE CONFIGURACION
	
	INSERT INTO CONFIGURACION(LLAVE, VALOR, FECHA_REGISTRO, BAN_ESTATUS) VALUES('TIEMPO_REENVIO_CORREO', 'PT1H', CURRENT_DATE, 1);

	COMMIT;
	
	-- ACTUALIZA EL CAMPO DE BAN_VALIDA_RO A 0
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_RO = 0 WHERE ID  >  92;
	COMMIT;
	
	-- 20171018 INSERTA VALORES EN TRE_PLANTILLA_CORREO_ROL_BPM
	
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (59, 93, 72);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (60, 93, 48);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (61, 94, 7);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (62, 91, 48);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (63, 98, 8);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (64, 98, 27);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (65, 98, 41);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (66, 98, 29);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (67, 98, 16);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (68, 98, 9);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (69, 99, 49);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (70, 99, 14);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (71, 99, 50);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (72, 100, 49);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (73, 100, 14);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (74, 100, 66);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (75, 101, 49);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (76, 101, 14);
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (77, 101, 66);

	-- 20171018 ACTUALIZA VALOR DE BANDERAS EN EL CATALOGO DE PLANTILLAS

	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_ET =  1 WHERE ID  IN (93);
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_ET =  1,  BAN_VALIDA_MCC = 1 WHERE ID  IN (77,78);
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_ET =  1 WHERE ID = 94;
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_RO = 1 WHERE ID IN ( 97, 98, 99, 100, 101, 102);

	-- 20171018 INGRESA VALORES PARA LA NOTIFICACIÓN DE ERROR ONBASE Y ENVIO DE DOCTO SOLO PARA DEV y QA... para PREPROD  Y PROD MODIFICAR POR (yrivera)
	
	-- Datos de prueba QA y DEV
	/*
	INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (4, 91, 'mcamacho');   -- Error ONBASE
	INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (5, 92, 'mcamacho');   -- Error envió correo
	COMMIT;
	*/
	-- Configuración de los usuarios a quienes les llegará la notificación de error ONBASE y envio correo
	INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (TRE_PLANTILLA_CORREO_USER_SEQ.NEXTVAL, 91, 'yrivera');   -- Error ONBASE
	INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (TRE_PLANTILLA_CORREO_USER_SEQ.NEXTVAL, 92, 'yrivera');   -- Error envió correo	
	COMMIT;

	-- 20171018 INGRESA NUEVOS TAG DE LA PLANTILLA
	
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (49, 'TERMINO_APROBACION', 'SELECT ''Con los siguientes datos: <br> Fecha de aprobación: '' ||  NVL(T1.FECHA,'''') || '' <br> Monto: '' || NVL(T2.MONTO,''0'') || '' '' || NVL(T3.DESCRIPCION_CORTA,'''') AS TERMINO_APROBACION FROM  TERMINO T1 INNER JOIN TERMINO T2 ON T1.ID_OPERACION = T2.ID_OPERACION  INNER JOIN TCA_TIPO_MONEDA T3 ON T2.ID_TCA_MONEDA = T3.ID WHERE T1.ID_TCA_TERMINO = (29) AND T2.ID_TCA_TERMINO = (11) AND T1.ID_OPERACION  = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (50, 'ID_ENMIENDA_TCC', 'SELECT NOMBRE  FROM TERMINO WHERE ID  IN (SELECT ID_TCC FROM DETALLE_ENMIENDA_TCC WHERE TIPO_TCC = ''TERMINO'' AND  ID_ENMIENDA_TCC = :ID_PARAMETRO) UNION SELECT NOMBRE FROM COMISION WHERE ID  IN (SELECT ID_TCC FROM DETALLE_ENMIENDA_TCC WHERE TIPO_TCC = ''COMISION'' AND  ID_ENMIENDA_TCC = :ID_PARAMETRO) UNION SELECT NOMBRE FROM CONDICION WHERE ID  IN (SELECT ID_TCC FROM DETALLE_ENMIENDA_TCC WHERE TIPO_TCC = ''CONDICION'' AND  ID_ENMIENDA_TCC = :ID_PARAMETRO)', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (51, 'ID_ACCION', 'SELECT ''Responsable: '' || TCR.DESCRIPCION || '' <BR> '' || AC.ACCION AS ACCION FROM ACCION AC INNER JOIN TCA_ROL_BPM TCR ON AC.ID_TCA_ROL_ASIGNADO = TCR.ID WHERE AC.ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (52, 'AVANCE_FISICO', 'SELECT ''Porcentaje de avance físico: '' || AVANCE_FISICO FROM SUPERVISION WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (53, 'AVANCE_FINANCIERO', 'SELECT ''Porcentaje de avance financiero: '' || AVANCE_FINANCIERO FROM SUPERVISION WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (54, 'SCR_ANTERIOR', 'SELECT  TBC.valor_anterior, CASE WHEN TSCR.DESCRIPCION  IS NULL THEN ''ND'' ELSE TSCR.DESCRIPCION END AS Desc_SCR FROM ( SELECT  t2.*  FROM  TBI_CLIENTE_CAMPO t2 WHERE (t2.ID_BITACORA IN (SELECT t1.ID FROM  TBI_CLIENTE t1 WHERE t1.ID_CLIENTE = :ID_PARAMETRO)) AND  (t2.CAMPO = ''ID_TCA_SCR'') ORDER BY t2.ID_BITACORA DESC) TBC LEFT JOIN TCA_SCR TSCR ON TBC.valor_anterior = TSCR.ID WHERE ROWNUM = 1', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (55, 'INICIO_I-BCIE_MEDIO_TERMINO', 'SELECT CASE TER.ID_TCA_TIPO_PORCENTAJE WHEN 1 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || '' <br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> '' ||  ''Porcentaje: '' || TER.PORCENTAJE   WHEN 2 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || '' <br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> '' ||  ''Porcentaje inicial: '' || TER.PORCENTAJE_INICIAL || '' <br> '' || ''Porcentaje final: '' || TER.PORCENTAJE_FINAL ELSE '''' END AS Informacion FROM TERMINO TER LEFT JOIN TCA_TIPO_AVANCE TTA ON TER.ID_TCA_TIPO_AVANCE = TTA.ID LEFT JOIN TCA_TIPO_PORCENTAJE TTP ON TER.ID_TCA_TIPO_PORCENTAJE = TTP.ID WHERE TER.ID_OPERACION = :ID_PARAMETRO AND  TER.BAN_ESTATUS=1 AND TER.ID_TCA_TERMINO  IN (45)', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (56, 'INICIO_I-BCIE_EX_POST', 'SELECT CASE TER.ID_TCA_TIPO_PORCENTAJE WHEN 1 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || '' <br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> '' ||  ''Porcentaje: '' || TER.PORCENTAJE   WHEN 2 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || '' <br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> ''||  ''Porcentaje inicial: '' || TER.PORCENTAJE_INICIAL || '' <br> '' || ''Porcentaje final: '' || TER.PORCENTAJE_FINAL ELSE '''' END AS Informacion FROM TERMINO TER LEFT JOIN TCA_TIPO_AVANCE TTA ON TER.ID_TCA_TIPO_AVANCE = TTA.ID LEFT JOIN TCA_TIPO_PORCENTAJE TTP ON TER.ID_TCA_TIPO_PORCENTAJE = TTP.ID WHERE TER.ID_OPERACION = :ID_PARAMETRO AND  TER.BAN_ESTATUS=1 AND TER.ID_TCA_TERMINO  IN (46)', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (57, 'INICIO_SEGUIMIENTO_SIEMAS', 'SELECT CASE TER.ID_TCA_TIPO_PORCENTAJE WHEN 1 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || ''<br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> '' ||  ''Porcentaje: '' || TER.PORCENTAJE   WHEN 2 THEN ''Tipo de avance: '' || TTA.DESCRIPCION  || '' <br> '' ||  ''Tipo de porcentaje: '' || TTP.DESCRIPCION  || '' <br> '' ||  ''Porcentaje inicial: '' || TER.PORCENTAJE_INICIAL || '' <br> '' || ''Porcentaje final: '' || TER.PORCENTAJE_FINAL ELSE '''' END AS Informacion FROM TERMINO TER LEFT JOIN TCA_TIPO_AVANCE TTA ON TER.ID_TCA_TIPO_AVANCE = TTA.ID LEFT JOIN TCA_TIPO_PORCENTAJE TTP ON TER.ID_TCA_TIPO_PORCENTAJE = TTP.ID WHERE TER.ID_OPERACION = :ID_PARAMETRO AND  TER.BAN_ESTATUS=1 AND TER.ID_TCA_TERMINO  IN (47)', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (58, 'JUSTIFICACION_CIERRE', 'SELECT JUSTIFICACION_CIERRE FROM OPERACION WHERE ID_OPERACION = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (59, 'SUPERVISOR', 'SELECT LOGIN_USUARIO_ULTIMO_CAMBIO FROM SUPERVISION WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (60, 'FECHA_ERROR_CARGA_ONBASE', 'SELECT TO_CHAR(fecha_registro, ''DD/MM/YYYY HH24:MI:SS'') AS FECHA_ERROR FROM TBI_DOCUMENTO WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	
	COMMIT;
	
	-- &&&&& falta por ejecutar
		
	-- 20171019 SE ACTUALIZA LA BANDERA VALIDA TAGS PARA LAS PLANTILLAS
	
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_TAGS =  1 WHERE ID  IN (47, 48, 91, 102);
	COMMIT;
		
	--- 20171019 SE INSERTA REGISTRO EN TCA_PLANTILLA_CORREO DEL FLUJO DE EVALUACION BCIE
	INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA) VALUES (103, 'PLANTILLA_FLUJO_EVALUACION_IBCIE', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Evaluación [TIPO_EVALUACION]: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le informa que la Evaluación <B>[TIPO_EVALUACION]</B> de la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha concluido la tarea <b>[TAREA]</b>.</font></p>    <p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACIÓN DEL FLUJO DE EVALUACIÓN IBCIE', CURRENT_DATE, 1, null,1,1,0,0,0,0);

	--- 20171019 SE INSERTA REGISTRO EN TCA_EVENTO_PLANTILLA DEL FLUJO DE EVALUACION BCIE
	INSERT INTO TCA_EVENTO_PLANTILLA (ID, DESCRIPCION, DESCRIPCION_CORTA) VALUES (40, 'FLUJO EVALUACION IBCIE', 'FLUJO EVALUACION IBCIE');

	--- 20171019 SE INSERTA REGISTRO EN TRE_PROCESO_EVENTO_PLANTILLA DEL FLUJO DE EVALUACION BCIE
	INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(63, 6, 103, 40);

	--- 20171019 SE INSERTA REGISTRO EN TRE_PLANTILLA_CORREO_ROL_BPM PARA LA PLANTILLA DEL FLUJO DE EVALUACION BCIE
	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM(ID, ID_TCA_PLANTILLA_CORREO, ID_TCA_ROL_BPM) VALUES (78, 103, 66);

	COMMIT;

	--- 20171019 SE INSERTA REGISTRO EN TCA_TAG_PLANTILLA

	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (61, 'TIPO_DECISION_AO', 'SELECT   TAS.DESCRIPCION AS ACCION_A_SEGUIR FROM DECISION_REUNION_APROBACION DRA INNER JOIN TCA_ACCION_A_SEGUIR TAS ON DRA.ID_TCA_ACCION_A_SEGUIR = TAS.ID INNER JOIN TCA_NIVEL_APROBACION TNA ON TAS.ID_TCA_NIVEL_APROBACION = TNA.ID INNER JOIN SOLICITUD_APROBACION SA ON DRA.ID_SOLICITUD_APROBACION = SA.ID WHERE DRA.ID_SOLICITUD_APROBACION = (SELECT MAX(ID) FROM SOLICITUD_APROBACION WHERE ID_OPERACION = :ID_PARAMETRO AND ID_TCA_TIPO_SOLICITUD=1) ', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (62, 'NIVEL_APROBACION', 'SELECT  TNA.DESCRIPCION AS NIVEL_APROBACION FROM DECISION_REUNION_APROBACION DRA INNER JOIN TCA_ACCION_A_SEGUIR TAS ON DRA.ID_TCA_ACCION_A_SEGUIR = TAS.ID INNER JOIN TCA_NIVEL_APROBACION TNA ON TAS.ID_TCA_NIVEL_APROBACION = TNA.ID INNER JOIN SOLICITUD_APROBACION SA ON DRA.ID_SOLICITUD_APROBACION = SA.ID  WHERE DRA.ID_SOLICITUD_APROBACION = (SELECT MAX(ID) FROM SOLICITUD_APROBACION WHERE ID_OPERACION = :ID_PARAMETRO AND ID_TCA_TIPO_SOLICITUD=1)', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (63, 'TIPO_EVALUACION', 'SELECT DESCRIPCION_CORTA FROM TCA_TIPO_EVALUACION WHERE ID = :ID_PARAMETRO', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');

	COMMIT;
	
	-- 20171019 SE INGRESA LA RELACION ENTRE TAGS Y PLANTILLA	

	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 13, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 2, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 49, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 61, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (93, 62, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (47, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (47, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (47, 4, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (48, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (48, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (48, 4, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (48, 58, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (94, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (94, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (94, 50, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 46, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 16, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 23, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 24, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 19, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (95, 51, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 46, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 16, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 23, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 24, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 19, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (96, 51, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 52, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 53, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 4, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (97, 59, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 3, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 22, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 5, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 11, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 60, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 43, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 41, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 42, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 16, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 23, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (91, 24, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (98, 23, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (98, 24, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (98, 26, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (98, 54, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (99, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (99, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (99, 57, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (100, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (100, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (100, 56, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (101, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (101, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (101, 55, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (102, 24, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (102, 23, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (103, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (103, 16, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (103, 63, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (103, 5, 1, 1);

	COMMIT;
	
	
	--- 20171020 SE INGRESA NUEVO ROL BPM PARA COPIAR AL FENIX_SECRETARIO_GENERAL_CC
	INSERT INTO TCA_ROL_BPM(ID, DESCRIPCION, DESCRIPCION_CORTA, BAN_ESTATUS, FECHA_REGISTRO) VALUES (73, 'Secretario General CC', 'FENIX_SECRETARIO_GENERAL_CC', 1, CURRENT_DATE);
	
	COMMIT;
	
    -- 20171020 SE MODIFICA LA CATEGORIA DEL EVENTO A 3 (Alcanzar un porcentaje TCC) DE LA PLANTILLA_ACTUALIZACION_AVANCE_FISICIO_FINANCIERO
    UPDATE TCA_PLANTILLA_CORREO SET ID_TCA_CATEGORIA_EVENTO=3 WHERE ID = 97;
    COMMIT;

	
	--- 20171020 ACTUALIZA EL TEXTO Y ASUNTO DE LAS PLANTILLAS 
	
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Aprobación de operación</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que a nivel <b>[NIVEL_APROBACION]</b>, se ha aprobado la operación <strong>[ID_OPERACION] - [OPERACION]</strong>, del cliente <strong>[IDCLIENTE] - [CLIENTE]</strong>. <p>[TERMINO_APROBACION]</p><p>Para acceder a los datos de la operación, debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</p>        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE APROBACION DE OPERACIÓN' WHERE ID = 93;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>TCC enmendados para la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que para la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B>, se han aprobado los siguientes TCC enmendados: <p>[ID_ENMIENDA_TCC]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN  DE APROBACION DE ENMIENDA' WHERE ID = 94;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Atención de acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que ha sido atendida la siguiente acción dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE]</B> <B>[CLIENTE]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE ATENCIÓN DE ACCIÓN' WHERE ID = 95;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Nuevas acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que han sido aprobadas nuevas acciones dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE]</B> <B>[CLIENTE]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE NUEVA ACCIÓN' WHERE ID = 96;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Avance físico y financiero de la operación: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que la operación <B>[ID_OPERACION]</B> <B>[OPERACION]</B>, ha actualizado el siguiente avance: </p><p>[AVANCE_FISICO]</p><p>[AVANCE_FINANCIERO]</p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr> <tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table border="0" cellpadding="0" cellspacing="0" width="100%"><!-- Datos Generales de la Operación y la Tarea --><!-- Inicio Línea 1 --><tr><td width="70%" class="titlebold"><font face="arial">Responsable de la Operación:</font></td><td width="290" class="titlebold"><font face="arial">Supervisor</font></td></tr><tr><td width="290" class="title"><font face="arial"><B>[RESPONSABLE]</B></font></td><td width="290" class="title"><font face="arial"><B>[SUPERVISOR]</B></font></td></tr></table></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE ACTUALIZACIÓN DE AVANCE FISICIO Y FINANCIERO' WHERE ID = 97;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Mejora de SCR: [CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que al cliente <B>[IDCLIENTE]</B> - <B>[CLIENTE]</B> se le ha aprobado una mejora en la Calificación de Riesgo <B>[SCR_CLIENTE]</B>, su calificación anterior era <B>[SCR_ANTERIOR]</B>.</font></p><p><font face="arial">Puede acceder al Reporte de Calificación de Riesgo a través del Gestor de Clientes en la sección de Enlaces de su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE NUEVO SCR POR MEJORA' WHERE ID = 98;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Seguimiento SIEMAS: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado el proceso de Seguimiento SIEMAS, con la siguiente información: </p><p>[INICIO_SEGUIMIENTO_SIEMAS]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE INICIO SEGUIMIENTO SIEMAS' WHERE ID = 99;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Evaluación Ex Post: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado la Evaluación Ex Post, con la siguiente información: </p><p>[INICIO_I-BCIE_EX_POST]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE INICIO EVALUACION EXPOST' WHERE ID = 100;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Inicio Evaluación Medio Término: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha iniciado la Evaluación Medio Término, con la siguiente información: </p><p>[INICIO_I-BCIE_MEDIO_TERMINO]</p><p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE INICIO EVALUACION MEDIO TERMINO' WHERE ID = 101;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Cliente en mora: [CLIENTE]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que el cliente <B>[IDCLIENTE]</B> - <B>[CLIENTE]</B> se encuentra en mora y ha iniciado un proceso de Prepago.</strong></font></p>    <p><font face="arial">Para acceder a la información del cliente debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE CLIENTE EN MORA' WHERE ID = 102;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Evaluación [TIPO_EVALUACION]: [OPERACION]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le informa que la Evaluación <B>[TIPO_EVALUACION]</B> de la operación <B>[ID_OPERACION]</B> - <B>[OPERACION]</B> ha concluido la tarea <b>[TAREA]</b>.</font></p>    <p><font face="arial">Para acceder a la información de la operación debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font>    <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DEL FLUJO DE EVALUACIÓN IBCIE' WHERE ID = 103;

	COMMIT;
	
	
	--- 20171020 Modifica el asunto para cada una de las plantillas
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID > 92;   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID > 92;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID > 92;
	-- COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID > 92;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID > 92;
	COMMIT;
	
	
	-- 20171027 Se agrega nuevos tag duales para las notificaciones que requieren IdCliente ó IdOperación	
	-- INSERTA TABLA TCA_TAG_PLANTILLA

	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (64, 'IDCLIENTE_DUAL', 'SELECT ID_CLIENTE FROM CLIENTES WHERE ID_CLIENTE = :ID_PARAMETRO', SYSDATE, 1, 1, 'CLIENTE', 'NOTIFICACION CORREO');
	INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (65, 'CLIENTE_DUAL', 'SELECT RAZON_SOCIAL FROM CLIENTES WHERE ID_CLIENTE = :ID_PARAMETRO', SYSDATE, 1, 1, 'CLIENTE', 'NOTIFICACION CORREO');

	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de envió de correo: [ASUNTO]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Se le notifica que ha ocurrido un problema al enviar la notificación <b>[ASUNTO]</b> [TIPO_NOTIFICACION] [IDCLIENTE_DUAL] [ID_OPERACION].<p>Se presentó el siguiente error: [ERROR_ENVIO_CORREO].</p><p>Para tener más detalles al respecto, favor de revisar el log de notificaciones fallidas con el identifiador [ID_ENVIO_CORREO]<p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE_DUAL]</b></font></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'ERROR DE ENVÍO DE CORREO' WHERE ID = 92;

	COMMIT;	

	-- 20171027 SE ACTUALIZA LA BANDERA VALIDA TAGS PARA LA PLANTILLA 92 DE ERROR DE ENVIO DE CORREO
	
	UPDATE TCA_PLANTILLA_CORREO SET BAN_VALIDA_TAGS =  1 WHERE ID  IN (92);
	COMMIT;
	
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 45, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 46, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 47, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 41, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 42, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 48, 1, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 16, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 1, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 64, 0, 1);
	INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (92, 65, 0, 1);

	COMMIT;
	
	-- 20171030 Se actualiza el mensaje de las notificaciones 2, 4, 9, 10, 77, 78, 89, 90, 91, 92, 95, 96 para ingresar el tag IDCLIENTE_DUAL y CLIENTE_DUAL

	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error en el Servicio de Carga de Documentos a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema en el Servicio de Carga de Documentos a OnBase, a través del Gestor de [TIPO_GESTOR] de FÉNIX.<p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE_DUAL]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 2;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Tipo de Archivo OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td height="134"><font face="arial">Ha ocurrido un problema al cargar el documento a OnBase, el Tipo de Archivo no es permitido para el Adjunto <b><font face="arial">[ADJUNTO], </font></b>favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.</font>          <p><font face="arial">Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. </p> <p> Saludos cordiales,</font></p>          <p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE_DUAL]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 4;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Tipo de Acción para Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td><font face="arial">Ha sucedido un problema al cargar el documento a OnBase debido a que el Tipo de Acción no es correcta para el Adjunto  <b>[ADJUNTO]</b>. Favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.      <p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.<p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE_DUAL]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 9;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Falta de Información de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td><font face="arial">Ha sucedido un problema al cargar el documento a OnBase debido a que no se cuenta con suficiente información en la base de datos para el Adjunto  <b>[ADJUNTO]</b>. Favor de revisar la carga de este documento a través del Gestor de [TIPO_GESTOR] de FÉNIX.      <p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="290"><font face="arial"><b>[OPERACION][CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION][IDCLIENTE_DUAL]</b></font></td></tr><tr>  <td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr>    <td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 10;

	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Emisión de Decisión de Presidencia vía Móvil FÉNIX</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que <strong>[NOMBRE_USUARIO]</strong> ha emitido una  decisión de tipo <strong>[TIPO_DECISION]</strong> sobre [TIPO_TRANSACCION] <strong>[ID_OPERACION] [OPERACION]</strong> <strong>[IDCLIENTE_DUAL] [CLIENTE_DUAL]</strong>.  <p>Puede acceder a los resultados a través de la <a href="[URL_APLICACION_WEB_MOVIL]"><b>Aplicación Web Móvil</b></a>.        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 77;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Emisión de voto en Comité de Crédito vía Móvil FÉNIX</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que <strong>[NOMBRE_USUARIO]</strong> ha emitido un voto <strong>[TIPO_DECISION]</strong> sobre [TIPO_TRANSACCION] <strong>[ID_OPERACION] [OPERACION]</strong> <strong>[IDCLIENTE_DUAL] [CLIENTE_DUAL]</strong>.  <p>Puede acceder a los resultados a través de la <a href="[URL_APLICACION_WEB_MOVIL]"><b>Aplicación Web Móvil</b></a>.        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 78;


	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Eliminación de evidencia de condiciones: [OPERACION] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le notifica que la evidencia <strong>[NOMBRE_DOCTO]</strong> de la condición <strong>[NOMBRE_CONDICION]</strong> de la operación <strong>[ID_OPERACION] [OPERACION]</strong> ha sido eliminada.  <font face="arial"></font><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="360" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr>      <td colspan="2"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="2"></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 89;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Emisión de comentarios vía Móvil FÉNIX</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>    <td height="131"><font face="arial">Se le notifica que <strong>[NOMBRE_USUARIO]</strong> ha emitido un comentario sobre [TIPO_TRANSACCION] <strong>[ID_OPERACION] [OPERACION]</strong> <strong>[IDCLIENTE_DUAL] [CLIENTE_DUAL]</strong>.  <p>Puede acceder a los resultados a través de la <a href="[URL_APLICACION_WEB_MOVIL]"><b>Aplicación Web Móvil</b></a>.        <p>Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>      <td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 90;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Carga de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema al cargar el tipo de documento <b>[TIPO_DOCUMENTO]</b> a OnBase, en la tarea <b>[TAREA]</b> con el usuario <b>[ID_USUARIO]</b>, favor de revisar la información para el adjunto <b>[ADJUNTO]</b> a través del sistema de FÉNIX.<p>El <B>[FECHA_ERROR_CARGA_ONBASE]</B>, se presentó el siguiente error: [ERROR_CARGA_ONBASE].</p><p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE_DUAL]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 91;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de envió de correo: [ASUNTO]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Se le notifica que ha ocurrido un problema al enviar la notificación <b>[ASUNTO]</b> [TIPO_NOTIFICACION] <b>[IDCLIENTE_DUAL] [CLIENTE_DUAL] [ID_OPERACION] [OPERACION]</b>.<p>Se presentó el siguiente error: [ERROR_ENVIO_CORREO].</p><p>Para tener más detalles al respecto, favor de revisar el log de notificaciones fallidas con el identifiador [ID_ENVIO_CORREO]<p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE_DUAL]</b></font></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'ERROR DE ENVÍO DE CORREO' WHERE ID = 92;


	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Atención de acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE_DUAL]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que ha sido atendida la siguiente acción dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE_DUAL]</B> <B>[CLIENTE_DUAL]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE ATENCIÓN DE ACCIÓN' WHERE ID = 95;
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Nuevas acciones [TIPO_NOTIFICACION] [OPERACION][CLIENTE_DUAL]</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr>  <td width="100%"><font face="arial">Estimado:</font></td></tr><tr><td height="10"></td></tr><tr>  <td height="150"><p><font face="arial">Se le comunica que han sido aprobadas nuevas acciones dentro del <b>[PROCESO]</b> [TIPO_NOTIFICACION] <B>[ID_OPERACION]</B> <B>[OPERACION]</B><B>[IDCLIENTE_DUAL]</B> <B>[CLIENTE_DUAL]</B>: <p>[ID_ACCION]</p><p><font face="arial">Para acceder a la información de la acción debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>.</font></p><p><font face="arial">Saludos cordiales,</font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td height="40"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', ASUNTO = 'NOTIFICACIÓN DE NUEVA ACCIÓN' WHERE ID = 96;
	
	COMMIT;
	
	/
	
	
	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_T2_T3_1.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 1)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/

-- 20171127 Se agrega notificación para atender CC K-7431 Manejo de errores y Generación de Errores



INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS, ID_TCA_CATEGORIA_EVENTO, BAN_VALIDA_TAGS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA, BAN_VALIDA_RO) VALUES (104, 'PLANTILLA_ERROR_FENIX', 'BCIE', '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Notificación de errores en Fenix</b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Se le notifica que ha ocurrido un problema en [TIPO_TAREA]</b>.<p>Fecha del error: <b>[FECHA_ERROR]</b></p><p>Descripción del error:<p></p><b>[DESCRIPCION_ERROR]</b></p><p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX.</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>', 'NOTIFICACIÓN DE ERRORES EN FENIX', CURRENT_DATE, 1, null,1,0,0,0,0,0,0);

COMMIT;


-- 20171127  Inserta los datos del administrador (Pruebas QA y DEV)
/*
INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (6, 104, 'mcamacho');
INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (10, 104, 'GarciaR');
COMMIT;
*/
INSERT INTO TRE_PLANTILLA_CORREO_USER (ID, ID_TCA_PLANTILLA_CORREO, LOGIN_USUARIO) VALUES (TRE_PLANTILLA_CORREO_USER_SEQ.NEXTVAL, 104, 'yrivera');   -- Error en aplicativos, envió correo	
COMMIT;

	--- 20171020 Modifica el asunto para cada una de las plantillas
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE DEV 
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'DEV 1 - ' || ASUNTO WHERE ID = 104;   
	--COMMIT;

	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE QA
	--UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'QA 1 - ' || ASUNTO WHERE ID = 104;
	--COMMIT;
	
	-- ACTUALIZACION DEL CAMPO ASUNTO,PARA LOS AMBIENTES DE PREPROD
	-- UPDATE TCA_PLANTILLA_CORREO SET ASUNTO  = 'BCIEQDB - ' || ASUNTO WHERE ID = 104;
	-- COMMIT;
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN DEV y QA
	--UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'marco-camacho@latbc.com, uriel-flores@latbc.com.mx, victor-santoyo@latbc.com.mx, eva-mendieta@latbc.com.mx' WHERE ID = 104;
	--COMMIT;
	
	
	-- ACTUALIZA CAMPO CC_DESTINATARIOS EN PREPROD y PROD
	UPDATE TCA_PLANTILLA_CORREO SET CC_DESTINATARIOS  = 'lrivas@bcie.org' WHERE ID = 104;
	COMMIT;
	


-- 20171127 Inserta los TAGS necesarios para la nueva plantilla - 104

INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (66, 'TIPO_TAREA', 'SELECT '':ID_PARAMETRO'' FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (67, 'DESCRIPCION_ERROR', 'SELECT '':ID_PARAMETRO'' FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
INSERT INTO TCA_TAG_PLANTILLA (ID, DESCRIPCION_TAG, SQL_QUERY, FECHA_REGISTRO, BAN_ESTATUS, BAN_GENERICO, CATEGORIA_PLANTILLA, TIPO_PLANTILLA) VALUES (68, 'FECHA_ERROR', 'SELECT '':ID_PARAMETRO'' FROM DUAL', SYSDATE, 1, 0, '', 'NOTIFICACION CORREO');
COMMIT;

-- 20171127 Relaciona los nuevos tags con la nueva plantilla

INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (104, 66, 1, 1);
INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (104, 67, 1, 1);
INSERT INTO TRE_TAGS_PLANTILLA (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA, REQUIERE_TAG, BAN_ESTATUS) VALUES (104, 68, 1, 1);
COMMIT;

-- 20171128 Se modifica el ROL  BPM Vicepresidente Ejecutivo por Presidente del Comité de Crédito, para atender la tarjeta K6788

UPDATE TCA_ROL_BPM SET DESCRIPCION = 'Presidente del Comité de Crédito' WHERE ID = 26;
COMMIT;

/




	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_3.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 3)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/




-- 20171218 Se ingresa la relación de plantilla del los procesos 31 y 33 para el evento 1 (Inicio Tarea)

INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(64, 31, 1, 1);
INSERT INTO TRE_PROCESO_EVENTO_PLANTILLA (ID, ID_PROCESO_BPM, ID_PLANTILLA_CORREO, ID_EVENTO_PLANTILLA) VALUES(65, 33, 1, 1);

COMMIT;	


	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_4.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 4)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/

	-- ###--------------------------------
	-- 20180120 Se actualiza el permiso de consulta para los tipos de documento, en las nuevas tareas,
	--	Pendiente del pase a prePROD y PROD, SOLO SE HA EJECUTADO EN DEV Y QA
	-- ###--------------------------------

	BEGIN
		P_REGE_TCO_DOCUMENTO_TAREA;
	END;
	
	COMMIT;
	/
	
	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_5.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 5)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/


	-- 20180123 Agrega la etapa de ingreso de los terminos indicados en TCC;
	-- TARJETA FNXII-7176
	
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T799';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T701';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T702';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T703';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T704';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 10 WHERE DESCRIPCION_CORTA = 'T705';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 10 WHERE DESCRIPCION_CORTA = 'T706';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 10 WHERE DESCRIPCION_CORTA = 'T707';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 10 WHERE DESCRIPCION_CORTA = 'T708';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 10 WHERE DESCRIPCION_CORTA = 'T709';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T801';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T802';
	UPDATE TCA_TERMINO SET ID_TCA_PROCESO_INGRESO = 3 WHERE DESCRIPCION_CORTA = 'T803';

	COMMIT;
	
	/
	
		/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_6.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 6)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/


	--  20180211 Se agregan registros al catálogo  TCA_PRODUCTO_PROGRAMA, en base a la matriz de Producto Programa, definida en box, para atender tarjeta FNXII-6834
	
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (1, 22, 2, 1, 2, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (2, 22, 2, 1, 3, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (3, 22, 2, 0, 2, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (4, 22, 2, 0, 3, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (5, 22, 1, 1, 2, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (6, 22, 1, 1, 3, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (7, 22, 1, 0, 2, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (8, 22, 1, 0, 3, 'APE', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (9, 1, 2, 1, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (10, 1, 2, 1, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (11, 1, 2, 0, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (12, 1, 2, 0, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (13, 1, 1, 1, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (14, 1, 1, 1, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (15, 1, 1, 0, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (16, 1, 1, 0, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (17, 1, 2, 1, 2, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (18, 1, 2, 1, 3, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (19, 1, 2, 0, 2, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (20, 1, 2, 0, 3, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (21, 1, 1, 1, 2, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (22, 1, 1, 1, 3, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (23, 1, 1, 0, 2, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (24, 1, 1, 0, 3, 'COM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (25, 2, 2, 1, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (26, 2, 2, 1, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (27, 2, 2, 0, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (28, 2, 2, 0, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (29, 2, 1, 1, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (30, 2, 1, 1, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (31, 2, 1, 0, 2, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (32, 2, 1, 0, 3, 'OR', 1);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (33, 3, 2, 1, 3, 'AB1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (34, 3, 1, 1, 3, 'AB1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (35, 3, 2, 1, 3, 'AB2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (36, 3, 1, 1, 3, 'AB2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (37, 3, 2, 1, 3, 'AVE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (38, 3, 1, 1, 3, 'AVE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (39, 3, 2, 1, 3, 'DMI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (40, 3, 1, 1, 3, 'DMI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (41, 3, 2, 1, 3, 'EZA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (42, 3, 1, 1, 3, 'EZA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (43, 3, 2, 1, 3, 'FE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (44, 3, 1, 1, 3, 'FE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (45, 3, 2, 1, 3, 'FE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (46, 3, 1, 1, 3, 'FE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (47, 3, 2, 1, 3, 'FE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (48, 3, 1, 1, 3, 'FE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (49, 3, 2, 1, 3, 'FPP', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (50, 3, 1, 1, 3, 'FPP', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (51, 3, 2, 1, 3, 'FTA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (52, 3, 1, 1, 3, 'FTA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (53, 3, 2, 1, 3, 'FTB', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (54, 3, 1, 1, 3, 'FTB', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (55, 3, 2, 1, 3, 'K1D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (56, 3, 1, 1, 3, 'K1D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (57, 3, 2, 1, 3, 'K2D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (58, 3, 1, 1, 3, 'K2D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (59, 3, 2, 1, 3, 'K3D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (60, 3, 1, 1, 3, 'K3D1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (61, 3, 2, 1, 3, 'KE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (62, 3, 1, 1, 3, 'KE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (63, 3, 2, 1, 3, 'KE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (64, 3, 1, 1, 3, 'KE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (65, 3, 2, 1, 3, 'KR2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (66, 3, 1, 1, 3, 'KR2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (67, 3, 2, 1, 3, 'KR3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (68, 3, 1, 1, 3, 'KR3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (69, 3, 2, 1, 3, 'MEX', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (70, 3, 1, 1, 3, 'MEX', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (71, 3, 2, 1, 3, 'OM1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (72, 3, 1, 1, 3, 'OM1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (73, 3, 2, 1, 3, 'OP1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (74, 3, 1, 1, 3, 'OP1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (75, 3, 2, 1, 3, 'PA1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (76, 3, 1, 1, 3, 'PA1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (77, 3, 2, 1, 3, 'PA2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (78, 3, 1, 1, 3, 'PA2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (79, 3, 2, 1, 3, 'PA3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (80, 3, 1, 1, 3, 'PA3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (81, 3, 2, 1, 3, 'PAB1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (82, 3, 1, 1, 3, 'PAB1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (83, 3, 2, 1, 3, 'PAB2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (84, 3, 1, 1, 3, 'PAB2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (85, 3, 2, 1, 3, 'PBE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (86, 3, 1, 1, 3, 'PBE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (87, 3, 2, 1, 3, 'PCO', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (88, 3, 1, 1, 3, 'PCO', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (89, 3, 2, 1, 3, 'PE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (90, 3, 1, 1, 3, 'PE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (91, 3, 2, 1, 3, 'PE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (92, 3, 1, 1, 3, 'PE2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (93, 3, 2, 1, 3, 'PE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (94, 3, 1, 1, 3, 'PE3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (95, 3, 2, 1, 3, 'PME', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (96, 3, 1, 1, 3, 'PME', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (97, 3, 2, 1, 3, 'PMI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (98, 3, 1, 1, 3, 'PMI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (99, 3, 2, 1, 3, 'PPE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (100, 3, 1, 1, 3, 'PPE', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (101, 3, 2, 1, 3, 'PR', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (102, 3, 1, 1, 3, 'PR', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (103, 3, 2, 1, 3, 'PR1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (104, 3, 1, 1, 3, 'PR1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (105, 3, 2, 1, 3, 'PR2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (106, 3, 1, 1, 3, 'PR2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (107, 3, 2, 1, 3, 'PR3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (108, 3, 1, 1, 3, 'PR3', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (109, 3, 2, 1, 3, 'PSF', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (110, 3, 1, 1, 3, 'PSF', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (111, 3, 2, 1, 3, 'PVI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (112, 3, 1, 1, 3, 'PVI', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (113, 3, 2, 1, 3, 'PVM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (114, 3, 1, 1, 3, 'PVM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (115, 3, 2, 1, 3, 'SP', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (116, 3, 1, 1, 3, 'SP', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (117, 3, 2, 1, 3, 'TE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (118, 3, 1, 1, 3, 'TE1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (119, 3, 2, 1, 3, 'TM1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (120, 3, 1, 1, 3, 'TM1', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (121, 3, 2, 1, 3, 'TM2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (122, 3, 1, 1, 3, 'TM2', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (123, 3, 2, 1, 3, 'UAM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (124, 3, 1, 1, 3, 'UAM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (125, 3, 2, 1, 3, 'UCA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (126, 3, 1, 1, 3, 'UCA', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (127, 3, 2, 1, 3, 'UTC', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (128, 3, 1, 1, 3, 'UTC', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (129, 3, 2, 1, 3, 'UTM', 0);
	INSERT INTO TCA_PRODUCTO_PROGRAMA (ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) VALUES (130, 3, 1, 1, 3, 'UTM', 0);

	COMMIT;
    
    /
	
		/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_7.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 7)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/

	-- 20180213 Se modifica el mensaje de la plantilla 91, para eliminar el tag ID_USUARIO
	
	UPDATE TCA_PLANTILLA_CORREO SET MENSAJE = '<table width="800" border="0" cellspacing="0" cellpadding="0"><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr><tr><td width="1" bgcolor="#6699cc"></td><td width="19" bgcolor="#6699cc"></td><td width="760" height="35" bgcolor="#6699cc"><font color="white" face="arial"><b>Error de Carga de Documento Adjunto a OnBase vía FÉNIX: [ADJUNTO] </b></font></td><td width="19" bgcolor="#6699cc"></td><td width="1" bgcolor="#6699cc"></td></tr><tr bgcolor="#eff0f7"><td width="2" bgcolor="#6699cc"></td><td width="19"></td><td width="760"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="10"></td></tr><tr><td width="100%"><font face="arial">Estimado: </font></td></tr><tr><td height="10"></td></tr><tr><td><font face="arial">Ha ocurrido un problema al cargar el tipo de documento <b>[TIPO_DOCUMENTO]</b> a OnBase, en la tarea <b>[TAREA]</b>, favor de revisar la información para el adjunto <b>[ADJUNTO]</b>, a través del sistema de FÉNIX.<p>El <B>[FECHA_ERROR_CARGA_ONBASE]</B>, se presentó el siguiente error: [ERROR_CARGA_ONBASE].</p><p>Para acceder a la tarea debe ingresar a su <b><a href="[URL_WORKSPACE]">Espacio de Trabajo en FÉNIX</a></b>. <p> Saludos cordiales,</p></font><p><font face="arial">Equipo de Colaboradores FÉNIX</font></p></td></tr><tr><td><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="70%"><font face="arial">[TIPO_ETIQUETA_1]</font></td><td width="290"><font face="arial">[TIPO_ETIQUETA_2]</font></td></tr><tr><td width="70%"><font face="arial"><b>[OPERACION] [CLIENTE_DUAL]</b></font></td><td width="290"><font face="arial"><b>[ID_OPERACION] [IDCLIENTE_DUAL]</b></font></td></tr><tr><td colspan="2"><font face="arial">Documento Adjunto:</font></td></tr><tr><td colspan="2"><font face="arial"><b>[ADJUNTO]</b></font></td></tr><tr><td colspan="3"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr><tr><td colspan="3"><font face="arial">Notificación generada automáticamente por <a href="[URL_WORKSPACE]"><b>FÉNIX</b></a></font></td></tr><tr><td colspan="3"></td></tr><tr><td colspan="2"><font face="arial">Derechos Reservados © 2016 BCIE.</font></td></tr><tr><td height="12" colspan="2"><hr width="100%" size="1" align="left" color="#666666" noshade></td></tr></table></td></tr></table></td><td width="19"></td><td width="1" bgcolor="#6699cc"></td></tr><tr><td height="2" bgcolor="#6699cc" colspan="5"></td></tr></table>' WHERE ID = 91;

	COMMIT;
	
	/
	
		/*
	----------------------------------
	ARCHIVO: BD_FENIX_DML_FASE_III_T2_T3_8_20180214.sql
	DESCRIPCION: Registrar todos los cambios DML en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DML 8)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/

	-- 20180214 Querys que se enviaron a DEV, QA y prePROD, por mpinenda, falta enviarse a PROD, POR TANTO NO DEBE ENVIARSE HASTA PROD

	INSERT INTO TCA_ROL_BPM (ID,DESCRIPCION,DESCRIPCION_CORTA,BAN_ESTATUS,FECHA_REGISTRO,COD_EXTERNO) VALUES
	(74,'FENIX Administrador','FENIX_ADMINISTRADOR',1,SYSDATE,NULL);
	COMMIT;

	INSERT INTO TRE_PLANTILLA_CORREO_ROL_BPM (ID,ID_TCA_PLANTILLA_CORREO,ID_TCA_ROL_BPM,BAN_ESTATUS) VALUES
	(79,104,74,1);
	COMMIT;
	
	/
	