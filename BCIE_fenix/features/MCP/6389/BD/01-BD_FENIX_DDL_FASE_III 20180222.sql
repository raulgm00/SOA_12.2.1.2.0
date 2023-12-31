
	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DDL_FASE_III_9.sql
	DESCRIPCION: Registrar todos los cambios DDL en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DDL 9)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/
	
	-- ESTOS CAMBIOS SOLO SE HAN APLICADO EN DEV, HASTA NUEVO AVISO EN QA
	
	-- ##********************************************************************************************************##
	-- 20170711 Se agrega la tabla de parametrización y cambios para el gestor de comentarios  T2-T3
	-- ##********************************************************************************************************##


	ALTER TABLE COMENTARIO_CLIENTE ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE COMENTARIO_CLIENTE SET BAN_ESTATUS = 1;
	ALTER TABLE COMENTARIO_CLIENTE MODIFY BAN_ESTATUS NOT NULL;
		
	ALTER TABLE COMENTARIO_OPERACION ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE COMENTARIO_OPERACION SET BAN_ESTATUS = 1;
	ALTER TABLE COMENTARIO_OPERACION MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE COMENTARIO_REUNION ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE COMENTARIO_REUNION SET BAN_ESTATUS = 1;
	ALTER TABLE COMENTARIO_REUNION MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE RESPUESTA_COMENTARIO ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE RESPUESTA_COMENTARIO SET BAN_ESTATUS = 1;
	ALTER TABLE RESPUESTA_COMENTARIO MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE RESPUESTA_COMENTARIO_CLIENTE ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE RESPUESTA_COMENTARIO_CLIENTE SET BAN_ESTATUS = 1;
	ALTER TABLE RESPUESTA_COMENTARIO_CLIENTE MODIFY BAN_ESTATUS NOT NULL;
	
	COMMIT;

	-- CREA LAS TABLAS DE LA PARAMETRIZACIÓN DE APP EXTERNAS DEL GESTOR DE OPERACIONES
	
	CREATE TABLE TCA_APP_EXTERNA (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NOT NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		REQUIERE_NUEVA_VENTANA NUMBER(1,0) NOT NULL,
		CONSTRAINT TCA_APP_EXTERNA_PK PRIMARY KEY (ID) 
	);
	
	CREATE SEQUENCE TCA_APP_EXTERNA_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	
	-- ########## @@@@@@@@@@@@@@@@@@@@@@
	
	--- 20170804 SE MODIFICA TCA_PARAMETROS_APP
		
	-- ALTER TABLE TCA_PARAMETROS_APP DROP CONSTRAINT TCA_PARAMETRO_APP_EXTERNA_FK;

	-- DROP TABLE TCA_PARAMETROS_APP;

	CREATE TABLE TCA_PARAMETROS_APP (
		ID NUMBER(5,0) NOT NULL,
		LLAVE VARCHAR2(256) NOT NULL,
		TIPO_PARAMETRO VARCHAR2(32) NOT NULL,
		SQL_QUERY VARCHAR2(1024) NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		CONSTRAINT TCA_PARAMETROS_APP_PK PRIMARY KEY (ID) 
	);

	

	ALTER TABLE TCA_PARAMETROS_APP ADD CONSTRAINT LLAVE_UNK UNIQUE (LLAVE);
	
	CREATE SEQUENCE TCA_PARAMETROS_APP_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

	-- ########## @@@@@@@@@@@@@@@@@@@@@@
	
	-- CREA LAS TABLAS DE LAS ACCIONES DE LOS ROLES BPM CON PRIVILIGIOS 
	
	
	CREATE TABLE TCA_ACCION_ROL_BPM (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NOT NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		CONSTRAINT TCA_ACCION_ROL_BPM_PK  PRIMARY KEY (ID) 
	);
	
	CREATE SEQUENCE TCA_ACCION_ROL_BPM_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

	CREATE TABLE TRE_ACCION_ROL_BPM (
		ID NUMBER(5,0) NOT NULL,
		ID_TCA_ROL_BPM NUMBER(5,0) NOT NULL,
		ID_TCA_ACCION_ROL_BPM NUMBER(5,0) NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		CONSTRAINT TRE_ACCION_ROL_BPM_PK PRIMARY KEY (ID) 
	);
	
	CREATE SEQUENCE TRE_ACCION_ROL_BPM_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
	
	ALTER TABLE TRE_ACCION_ROL_BPM ADD CONSTRAINT TCA_ACCION_ROL_BPM_TRE_FK FOREIGN KEY (ID_TCA_ACCION_ROL_BPM) REFERENCES TCA_ACCION_ROL_BPM (ID);
	ALTER TABLE TRE_ACCION_ROL_BPM ADD CONSTRAINT TCA_ROL_BPM_TRE_ACCION_FK FOREIGN KEY (ID_TCA_ROL_BPM) REFERENCES TCA_ROL_BPM (ID);

	
	--- 20170720  SE CREA LA TABLA DE BITACORA DE DOCUMENTOS	
	

	DROP TABLE TBI_DOCUMENTO;

	--DROP TRIGGER TBI_DOCUMENTO_TGR;

	CREATE TABLE TBI_DOCUMENTO (
		ID NUMBER(12,0) NOT NULL,
		ID_DOCUMENTO NUMBER(12,0) NOT NULL,
		ESTADO VARCHAR2(20) NOT NULL,
		NUM_INSTANCIA VARCHAR2(256) NULL,
		SE_HA_NOTIFICADO NUMBER(1,0) DEFAULT 0 NULL,
		LOGIN_USUARIO VARCHAR2(32) NOT NULL,
		NOMBRE_USUARIO VARCHAR2(128) NULL,
		ID_TCA_TAREA_BPM NUMBER(5,0) NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1  NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL
	);
	
	-- INICIA SECUENCIA EN 0

	DROP SEQUENCE TBI_DOCUMENTO_SEQ;
	CREATE SEQUENCE TBI_DOCUMENTO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;


	ALTER TABLE TBI_DOCUMENTO ADD CONSTRAINT TBI_DOCTO_DOCTO_FK FOREIGN KEY (ID_DOCUMENTO) REFERENCES DOCUMENTO (ID_DOCUMENTO);
	ALTER TABLE TBI_DOCUMENTO ADD CONSTRAINT TBI_DOCTO_TCA_TAREA_FK FOREIGN KEY (ID_TCA_TAREA_BPM) REFERENCES TCA_TAREA_BPM (ID);

	ALTER TABLE TBI_DOCUMENTO ADD CONSTRAINT TBI_DOCUMENTO_ESTADO_CHK CHECK (ESTADO IN ('EN_PROCESO', 'ENVIADO', 'ERROR'));
	
	ALTER TABLE TBI_DOCUMENTO ADD  DESCRIPCION VARCHAR2(4000) NULL;
	
	-- ##*******************************************************************************************************************************************##
	-- 20170912 Se agregan nuevos campos a las tbls de Condición, Seguimiento Crediticio, Supervisión y bitácora para almacenamiento de información
	-- ##*******************************************************************************************************************************************##
	
	-- Se agregan campos a las tablas
	
	-- ALTER TABLE CONDICION ADD ID_TCA_TIPO_DESEMBOLSO NUMBER(5,0) NULL;  -- YA SE ENVIO A PROD PARA ATENDER TARJETA 7857 EL 20171214
	
	ALTER TABLE SUPERVISION MODIFY FECHA_REGISTRO DATE;
	ALTER TABLE SUPERVISION ADD LOGIN_USUARIO VARCHAR2(32) NULL;
	ALTER TABLE SUPERVISION ADD NOMBRE_USUARIO VARCHAR2(128) NULL;
	ALTER TABLE SUPERVISION ADD LOGIN_USUARIO_ULTIMO_CAMBIO VARCHAR2(32) NULL;
	ALTER TABLE SUPERVISION ADD NOMBRE_USUARIO_ULTIMO_CAMBIO VARCHAR2(128) NULL;
	ALTER TABLE SUPERVISION ADD FECHA_ULTIMO_CAMBIO DATE NULL;
	
	
	ALTER TABLE SEGUIMIENTO_CREDITICIO MODIFY ID NUMBER(12,0);
	ALTER TABLE SEGUIMIENTO_CREDITICIO MODIFY FECHA_REGISTRO DATE;
	ALTER TABLE SEGUIMIENTO_CREDITICIO ADD LOGIN_USUARIO_ULTIMO_CAMBIO VARCHAR2(32) NULL;
	ALTER TABLE SEGUIMIENTO_CREDITICIO ADD NOMBRE_USUARIO_ULTIMO_CAMBIO VARCHAR2(128) NULL;
	ALTER TABLE SEGUIMIENTO_CREDITICIO ADD FECHA_ULTIMO_CAMBIO DATE NULL;

	-- Se agregan tablas de bitácora a Seguimiento Crediticio, Supervisión y Operación
		
	CREATE TABLE TBI_OPERACION (
		ID NUMBER(12,0) NOT NULL,
		ID_OPERACION NUMBER(12,0) NOT NULL,
		TIPO_ACCION VARCHAR2(32) NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		CONSTRAINT TBI_OPERACION_PK PRIMARY KEY (ID) 
	);

	CREATE SEQUENCE TBI_OPERACION_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	CREATE TABLE TBI_OPERACION_CAMPO (
		ID_BITACORA NUMBER(12,0) NOT NULL,
		CAMPO VARCHAR2(32) NOT NULL,
		VALOR_NUEVO VARCHAR2(255) NULL,
		VALOR_ANTERIOR VARCHAR2(255) NULL
	);

	CREATE TABLE TBI_SEGUIMIENTO_CREDITICIO (
		ID NUMBER(12,0) NOT NULL,
		ID_SEGUIMIENTO NUMBER(12,0) NOT NULL,
		TIPO_ACCION VARCHAR2(32) NOT NULL,
		LOGIN_SOLICITANTE VARCHAR2(32) NOT NULL,
		NOMBRE_SOLICITANTE VARCHAR2(128) NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		CONSTRAINT TBI_SEGUIMIENTO_CRED_PK PRIMARY KEY (ID) 
	);

	CREATE SEQUENCE TBI_SEGUIMIENTO_CRED_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	CREATE TABLE TBI_SEGUIMIENTO_CRED_CAMPO (
		ID_BITACORA NUMBER(12,0) NOT NULL,
		CAMPO VARCHAR2(32) NOT NULL,
		VALOR_NUEVO VARCHAR2(255) NULL,
		VALOR_ANTERIOR VARCHAR2(255) NULL
	);

	CREATE TABLE TBI_SUPERVISION (
		ID NUMBER(12,0) NOT NULL,
		ID_SUPERVISION NUMBER(12,0) NOT NULL,
		TIPO_ACCION VARCHAR2(32) NOT NULL,
		LOGIN_SOLICITANTE VARCHAR2(32) NOT NULL,
		NOMBRE_SOLICITANTE VARCHAR2(128) NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		CONSTRAINT TBI_SUPERVISION_PK PRIMARY KEY (ID) 
	);

	CREATE SEQUENCE TBI_SUPERVISION_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	CREATE TABLE TBI_SUPERVISION_CAMPO (
		ID_BITACORA NUMBER(12,0) NOT NULL,
		CAMPO VARCHAR2(32) NOT NULL,
		VALOR_NUEVO VARCHAR2(255) NULL,
		VALOR_ANTERIOR VARCHAR2(255) NULL
	);

	CREATE TABLE TCA_TIPO_DESEMBOLSO (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		CONSTRAINT TCA_TIPO_DESEMBOLSO_PK PRIMARY KEY (ID) 
	);

	CREATE SEQUENCE TCA_TIPO_DESEMBOLSO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	---  Se agregan constraints
	
	ALTER TABLE TBI_OPERACION_CAMPO ADD CONSTRAINT BITACORA_CAMPO_OPERACION_FK FOREIGN KEY (ID_BITACORA) REFERENCES TBI_OPERACION (ID);
	ALTER TABLE TBI_SEGUIMIENTO_CRED_CAMPO ADD CONSTRAINT BITACORA_CAMPO_SEG_CRED_FK FOREIGN KEY (ID_BITACORA) REFERENCES TBI_SEGUIMIENTO_CREDITICIO (ID);
	ALTER TABLE TBI_SUPERVISION_CAMPO ADD CONSTRAINT BITACORA_CAMPO_SUPERVISION_FK FOREIGN KEY (ID_BITACORA) REFERENCES TBI_SUPERVISION (ID);
	ALTER TABLE CONDICION ADD CONSTRAINT CONDICION_TCA_TIPO_DESEM_FK FOREIGN KEY (ID_TCA_TIPO_DESEMBOLSO) REFERENCES TCA_TIPO_DESEMBOLSO (ID);

	-- %%%%%%%%%%%%%%%%
	
	-- 20170915 AGREGA NUEVAS COLUMNAS A LA TABLA CATALOGOS DE TERMINO

	ALTER TABLE TCA_TERMINO ADD REQUIERE_OT NUMBER(1,0) NULL;
	ALTER TABLE TCA_TERMINO ADD REQUIERE_ASJUR NUMBER(1,0) NULL;

	-- 20170915 AGREGA NUEVAS COLUMNAS A LA TABLA DE TERMINO

	ALTER TABLE TERMINO MODIFY PLAZO NUMBER(4);

	ALTER TABLE TERMINO ADD PORCENTAJE_MODIFICACION NUMBER(7,4) NULL;
	ALTER TABLE TERMINO ADD ID_TCA_TIPO_AVANCE NUMBER(5,0) NULL;
	ALTER TABLE TERMINO ADD ID_TCA_TIPO_PORCENTAJE NUMBER(5,0) NULL;
	ALTER TABLE TERMINO ADD PORCENTAJE NUMBER(7,4) NULL;
	ALTER TABLE TERMINO ADD PORCENTAJE_INICIAL NUMBER(7,4) NULL;
	ALTER TABLE TERMINO ADD PORCENTAJE_FINAL NUMBER(7,4) NULL;
	
	-- 20170925 Se agregan las tablas para el manejo de tipo Avance y tipo de Porcentaje para los términos T8##
	
	CREATE TABLE TCA_TIPO_AVANCE (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		FECHA_REGISTRO DATE  DEFAULT CURRENT_DATE NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		CONSTRAINT TCA_TIPO_AVANCE_PK PRIMARY KEY (ID) 
	);


	CREATE TABLE TCA_TIPO_PORCENTAJE (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		CONSTRAINT TCA_TIPO_PORCENTAJE_PK PRIMARY KEY (ID) 
	);


	ALTER TABLE TERMINO ADD CONSTRAINT TERMINO_TCA_TIPO_AVANCE_FK FOREIGN KEY (ID_TCA_TIPO_AVANCE) REFERENCES TCA_TIPO_AVANCE (ID);
	ALTER TABLE TERMINO ADD CONSTRAINT TERMINO_TCA_TIPO_PORCEN_FK FOREIGN KEY (ID_TCA_TIPO_PORCENTAJE) REFERENCES TCA_TIPO_PORCENTAJE (ID);

	-- 20170927 Se agregan las entidades necesarias para el manejo de alertas 
	
		
	CREATE TABLE TCA_CATEGORIA_EVENTO (
		ID NUMBER(5,0) NOT NULL,
		DESCRIPCION VARCHAR2(256) NULL,
		DESCRIPCION_CORTA VARCHAR2(64) NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE  NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		COD_EXTERNO VARCHAR2(32) NULL,
		CONSTRAINT TCA_CATEGORIA_EVENTO_PK PRIMARY KEY (ID) 
	);

	CREATE TABLE TRE_TAGS_PLANTILLA (
		ID_TCA_PLANTILLA_CORREO NUMBER(5,0) NOT NULL,
		ID_TCA_TAG_PLANTILLA NUMBER(5,0) NOT NULL,
		PRIMARY KEY (ID_TCA_PLANTILLA_CORREO, ID_TCA_TAG_PLANTILLA) 
	);
	
	CREATE TABLE TRE_PLANTILLA_CORREO_USER (
		ID NUMBER(12,0) NOT NULL,
		ID_TCA_PLANTILLA_CORREO NUMBER(5,0) NOT NULL,
		LOGIN_USUARIO VARCHAR2(32) NOT NULL,
		CONSTRAINT TRE_PLANTILLA_CORREO_USER_PK PRIMARY KEY (ID) 
	);


	
	ALTER TABLE TRE_TAGS_PLANTILLA ADD CONSTRAINT TRE_TAGS_TCA_PLAN_CORREO_FK FOREIGN KEY (ID_TCA_PLANTILLA_CORREO) REFERENCES TCA_PLANTILLA_CORREO (ID);
	ALTER TABLE TRE_TAGS_PLANTILLA ADD CONSTRAINT TRE_TAGS_TCA_TAG_PLANTILLA_FK FOREIGN KEY (ID_TCA_TAG_PLANTILLA) REFERENCES TCA_TAG_PLANTILLA (ID);

	-- Se agregan modificaciones al catálogo de plantilla correo

	ALTER TABLE TCA_PLANTILLA_CORREO ADD ID_TCA_CATEGORIA_EVENTO NUMBER(5,0) NULL;   --NOT NULL
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_TAGS NUMBER(1,0) NULL;

    ALTER TABLE TCA_PLANTILLA_CORREO ADD CONSTRAINT TCA_PLAN_CATEGORIA_EVTO_FK FOREIGN KEY (ID_TCA_CATEGORIA_EVENTO) REFERENCES TCA_CATEGORIA_EVENTO (ID);
    
	ALTER TABLE TRE_PLANTILLA_CORREO_USER ADD CONSTRAINT TRE_PLANTILLA_USER_TCA_PLANT FOREIGN KEY (ID_TCA_PLANTILLA_CORREO) REFERENCES TCA_PLANTILLA_CORREO (ID);

	-- 20170929 Se crea la tabla TBI_ERROR_ENVIO_CORREO, para el manejo de errores en log de envio de correo
	
	
	-- DROP TABLE TBI_ENVIO_CORREO_2;  -- BORRAR DESPUES DE APLICAR
	-- DROP SEQUENCE TBI_ENVIO_CORREO_2_SEQ;
	
	CREATE TABLE TBI_ERROR_ENVIO_CORREO (
		ID NUMBER(12,0) NOT NULL,
		ID_OPERACION NUMBER(12,0) NULL,
		ID_CLIENTE NUMBER(12,0) NULL,
		ID_TCA_PLANTILLA_CORREO NUMBER(5,0) NOT NULL,
		DESCRIPCION_ERROR VARCHAR2(4000) NULL,
		MENSAJE_ERROR CLOB NULL,
		SE_HA_NOTIFICADO_ERROR NUMBER(1,0) DEFAULT 0 NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		CONSTRAINT TBI_ERROR_ENVIO_CORREO PRIMARY KEY (ID) 
	);

	
	CREATE SEQUENCE TBI_ERROR_ENVIO_CORREO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

	CREATE SEQUENCE TRE_PLANTILLA_CORREO_USER_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
	
	
	-- 20171006 Se agregan campos a la entidad TCA_PLANTILLA_CORREO y TRE_TAGS_PLANTILLA
	
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_ET NUMBER(1,0) NULL;
	ALTER TABLE TRE_TAGS_PLANTILLA ADD REQUIERE_TAG NUMBER(1,0) NULL;
	
	-- 20171014 Se agregan campos a la entidad TCA_PLANTILLA_CORREO 
	
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_MCC NUMBER(1,0) NULL;
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_VA NUMBER(1,0) NULL;
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_RA NUMBER(1,0) NULL;
	
		
	CREATE TABLE TRE_CATEGORIA_VALIDADOR_ROL (
		ID NUMBER(5,0) NOT NULL,
		ID_TCA_CATEGORIA_ACCION NUMBER(5,0) NOT NULL,
		ID_TCA_ROL_BPM NUMBER(5,0) NOT NULL,
		CONSTRAINT TRE_CATEGORIA_VALIDADOR_ROL PRIMARY KEY (ID) 
	);

    CREATE SEQUENCE TRE_CATEGORIA_VAL_ROL_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

	ALTER TABLE TRE_CATEGORIA_VALIDADOR_ROL ADD CONSTRAINT TRE_ROL_CATEGORIA_VALIDADOR_FK FOREIGN KEY (ID_TCA_ROL_BPM) REFERENCES TCA_ROL_BPM (ID);
	ALTER TABLE TRE_CATEGORIA_VALIDADOR_ROL ADD CONSTRAINT TRE_CAT_CATEGORIA_VALIDADOR_FK FOREIGN KEY (ID_TCA_CATEGORIA_ACCION) REFERENCES TCA_CATEGORIA_ACCION (ID);
	
	
	-- 20171017 SE AGREGA LA COLUMNA DE BAN_VALIDA_RO EN TCA_PLANTILLA_CORREO
	
	ALTER TABLE TCA_PLANTILLA_CORREO ADD BAN_VALIDA_RO NUMBER(1,0) NULL;
	
	DROP TABLE TBI_ENVIO_CORREO;  
	DROP SEQUENCE TBI_ENVIO_CORREO_SEQ;
	
	-- 20171018 SE AGREGAN VALORES POR DEFAULT A LOS CAMPOS NUEVOS EN TCA_PLANTILLA_CORREO
	
		
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_TAGS DEFAULT 0;
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_ET   DEFAULT 0;
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_MCC  DEFAULT 0;
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_VA   DEFAULT 0;
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_RA   DEFAULT 0;
	ALTER TABLE TCA_PLANTILLA_CORREO MODIFY BAN_VALIDA_RO   DEFAULT 0;
	
	-- 20171018 SE AGREGA COLUMNA EN BAN_ESTATUS 
	
	ALTER TABLE TRE_TAGS_PLANTILLA ADD BAN_ESTATUS NUMBER (1) DEFAULT 1;
	
	
	-- 20171115 Se agrega el campo BAN_ESTATUS, para poder realizar el borrado lógico de parametrización
	
	ALTER TABLE PREGUNTA_PRODUCTO ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE PREGUNTA_PRODUCTO SET BAN_ESTATUS = 1;
	ALTER TABLE PREGUNTA_PRODUCTO MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TCO_ATRIBUTO_TCC ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TCO_ATRIBUTO_TCC SET BAN_ESTATUS = 1;
	ALTER TABLE TCO_ATRIBUTO_TCC MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TCO_DOCUMENTO_TAREA ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TCO_DOCUMENTO_TAREA SET BAN_ESTATUS = 1;
	ALTER TABLE TCO_DOCUMENTO_TAREA MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TCO_TIPO_PREGUNTA ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TCO_TIPO_PREGUNTA SET BAN_ESTATUS = 1;
	ALTER TABLE TCO_TIPO_PREGUNTA MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TRE_PREGUNTA_OPCION ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TRE_PREGUNTA_OPCION SET BAN_ESTATUS = 1;
	ALTER TABLE TRE_PREGUNTA_OPCION MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TRE_CATEGORIA_ACCION_ROL_BPM ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TRE_CATEGORIA_ACCION_ROL_BPM SET BAN_ESTATUS = 1;
	ALTER TABLE TRE_CATEGORIA_ACCION_ROL_BPM MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TRE_PLANTILLA_CORREO_ROL_BPM ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TRE_PLANTILLA_CORREO_ROL_BPM SET BAN_ESTATUS = 1;
	ALTER TABLE TRE_PLANTILLA_CORREO_ROL_BPM MODIFY BAN_ESTATUS NOT NULL;
	
	ALTER TABLE TRE_PLANTILLA_CORREO_USER ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TRE_PLANTILLA_CORREO_USER SET BAN_ESTATUS = 1;
	ALTER TABLE TRE_PLANTILLA_CORREO_USER MODIFY BAN_ESTATUS NOT NULL;
	
	COMMIT;
	
	-- 20171116 Se agregan reglas de constraint para tener registros unicos en  cierre de moneda, 
		
	ALTER TABLE TCA_HORA_CIERRE_MONEDA ADD CONSTRAINT TCA_HORA_CIERRE_MONEDA_UNK UNIQUE (ID_TCA_TIPO_MONEDA);
	ALTER TABLE TCO_DOCUMENTO_TAREA ADD CONSTRAINT TCO_DOCUMENTO_TAREA_UNK UNIQUE (ID_TCA_DOCUMENTO, ID_TAREA_BPM);
	ALTER TABLE TRE_CATEGORIA_ACCION_ROL_BPM ADD CONSTRAINT TRE_CAT_ACCION_ROL_BPM_UNK UNIQUE (ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM);
	ALTER TABLE TRE_CATEGORIA_VALIDADOR_ROL ADD CONSTRAINT TRE_CAT_VALIDADOR_ROL_UNK UNIQUE (ID_TCA_CATEGORIA_ACCION, ID_TCA_ROL_BPM);
	ALTER TABLE TRE_CATEGORIA_ROL_BPM  ADD CONSTRAINT TRE_CATEGORIA_ROL_BPM_UNK UNIQUE (ID_TCA_CATEGORIA, ID_TCA_ROL_BPM, ID_CAT_PRODUCTO);
	ALTER TABLE PREGUNTA_PRODUCTO ADD CONSTRAINT PREGUNTA_PRODUCTO_UNK UNIQUE (ID_PREGUNTA, ID_PRODUCTO);

	
	-- 20171121  Se agregan los campos a término para parametrización

	ALTER TABLE TERMINO ADD LOGIN_USUARIO_ULTIMO_CAMBIO VARCHAR2(32) NULL;
	ALTER TABLE TERMINO ADD NOMBRE_USUARIO_ULTIMO_CAMBIO VARCHAR2(128) NULL;
	ALTER TABLE TERMINO ADD FECHA_ULTIMO_CAMBIO DATE NULL;
	
	-- 20171121  Se eliminan los constraints para proceder a eliminar las tablas TRE_OPERACION_ROL_BPM y TRE_PREGUNTA_TIPO_INSTITUCION

	ALTER TABLE TRE_OPERACION_ROL_BPM DROP CONSTRAINT TRE_OPERACION_ROL_BPM_OPERA_FK;
	ALTER TABLE TRE_OPERACION_ROL_BPM DROP CONSTRAINT TRE_OPERACION_ROL_BPM_ROLBP_FK;
	ALTER TABLE TRE_PREGUNTA_TIPO_INSTITUCION DROP CONSTRAINT CAT_TIPO_INSTITUCION_FK;
	ALTER TABLE TRE_PREGUNTA_TIPO_INSTITUCION DROP CONSTRAINT TCO_RESPONSABLE_LAFT_FK; 	
	
	-- 20171122 Se crea la tabla de bitácora para registrar los cambios del término T101 y T603
	
	CREATE SEQUENCE TBI_TERMINO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE; 

	CREATE TABLE TBI_TERMINO (
		ID NUMBER(12,0) NOT NULL,
		ID_TERMINO NUMBER(12,0) NOT NULL,
		TIPO_ACCION VARCHAR2(32) NOT NULL,
		LOGIN_SOLICITANTE VARCHAR2(32) NOT NULL,
		NOMBRE_SOLICITANTE VARCHAR2(128) NULL,
		FECHA_REGISTRO DATE NOT NULL,
		CONSTRAINT TBI_TERMINO_PK PRIMARY KEY (ID) 
	);

	CREATE TABLE TBI_TERMINO_CAMPO (
		ID_BITACORA NUMBER(12,0) NOT NULL,
		CAMPO VARCHAR2(32) NOT NULL,
		VALOR_NUEVO VARCHAR2(255) NULL,
		VALOR_ANTERIOR VARCHAR2(255) NULL
	);

	
	ALTER TABLE TBI_TERMINO_CAMPO ADD CONSTRAINT BITACORA_CAMPO_TERMINO_FK FOREIGN KEY (ID_BITACORA) REFERENCES TBI_TERMINO (ID);

	-- 20171123 Se agrega el campo BAN_ESTATUS a la tabla TRE_CAT_PRODUCTO_TCA_CAMPOS
	
	ALTER TABLE TRE_CAT_PRODUCTO_TCA_CAMPOS ADD  BAN_ESTATUS NUMBER(1,0);
	UPDATE TRE_CAT_PRODUCTO_TCA_CAMPOS SET BAN_ESTATUS = 1;
	ALTER TABLE TRE_CAT_PRODUCTO_TCA_CAMPOS MODIFY BAN_ESTATUS NOT NULL;
	
	COMMIT;

    -- 20171123 Se agrega constraint único (registro único) sobre la HCE y producto campo
    ALTER TABLE TRE_CAT_PRODUCTO_TCA_CAMPOS ADD CONSTRAINT TRE_CAT_PROD_TCA_CAMPOS_UNK UNIQUE (ID_CAT_PRODUCTO, ID_CAMPO);
    ALTER TABLE TRE_HERRAMIENTA ADD CONSTRAINT TRE_HERRAMIENTA_CAMPOS_UNK UNIQUE (ID_INICIATIVA_ESTRATEGICA, ID_RANGO_PAISES, ID_AREA_FOCALIZACION, ID_EJE_ESTRATEGICO, ID_TCA_CICLO_ESTRATEGICO, ID_TCA_CLASIF_EMPRESARIAL, ID_TCA_PROYECTO_MUNICIPAL, ID_TCA_ACTIVIDAD_ECONOMICA_F1, CODIGO_PROGRAMA);
    
    ALTER TABLE CAT_INICIATIVA_ESTRATEGICA ADD CONSTRAINT INI_ESTRATE_TIPO_VARIABLE_CHK CHECK(TIPO_VARIABLE IN('A','D'));
    
    
    --- 20171123 Cambios del script de DML que deben estar en DDL
	-- &&&&&&&&&&&&&&&&&&&&&&&&
	 
	-- 20170811 Se agregan nuevas columnas sobre COMISION y TBI_DOCUMENTO      
		
	ALTER TABLE COMISION ADD ES_CREADO_EN_REG_COMISION NUMBER(1,0) NULL;
	ALTER TABLE TBI_DOCUMENTO ADD  ES_REG_ACTIVO NUMBER(1,0) DEFAULT 1  NULL;
    ALTER TABLE TBI_DOCUMENTO MODIFY  ES_REG_ACTIVO NOT NULL;
		
	
	-- 20170901 Se eliminan las llaves foráneas de la tabla de bitácora de documentos
	
	ALTER TABLE TBI_DOCUMENTO DROP CONSTRAINT TBI_DOCTO_DOCTO_FK;
	ALTER TABLE TBI_DOCUMENTO DROP CONSTRAINT TBI_DOCTO_TCA_TAREA_FK;

	---- %%%%%%	
	-- 20171124 Ajustes por la inserción del campo BAN_ESTATUS

	-- 20171123 AGREGAR AL SCRIPT
	ALTER TABLE TCO_ATRIBUTO_TCC MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TCO_DOCUMENTO_TAREA MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TRE_PLANTILLA_CORREO_ROL_BPM MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TRE_PLANTILLA_CORREO_USER MODIFY BAN_ESTATUS DEFAULT 1;

	-- 20171124 AGREGAR AL SCRIPT
	ALTER TABLE COMENTARIO_CLIENTE MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE COMENTARIO_OPERACION MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE COMENTARIO_REUNION MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE RESPUESTA_COMENTARIO MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE RESPUESTA_COMENTARIO_CLIENTE MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE PREGUNTA_PRODUCTO MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TCO_TIPO_PREGUNTA MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TRE_PREGUNTA_OPCION MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TRE_CATEGORIA_ACCION_ROL_BPM MODIFY BAN_ESTATUS DEFAULT 1;
	ALTER TABLE TRE_CAT_PRODUCTO_TCA_CAMPOS  MODIFY BAN_ESTATUS DEFAULT 1;

/

	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DDL_FASE_III_T2_T3_1.sql
	DESCRIPCION: Registrar todos los cambios DDL en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DDL 1)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/
	

-- 20171124 Se modifica el campo LOGIN_SOLICITANTE a null, mientras se ajustan los cambios de término
ALTER TABLE TBI_TERMINO MODIFY LOGIN_SOLICITANTE NULL;

-- 20171124 Se agregan los campos LOGIN_USUARIO y NOMBRE_USUARIO, para registrar la bitácora

ALTER TABLE TERMINO ADD LOGIN_USUARIO VARCHAR2(32) NULL;
ALTER TABLE TERMINO ADD NOMBRE_USUARIO VARCHAR2(128) NULL;


-- 20171128 Se eliminan 11 tablas y  2 procedimientos, de cualquier forma antes de ejecutar en prePROD y PROD, se debe solictar respaldo de datos y esquema de estas tbls.

DROP TABLE DECLARACION_OLD;
DROP TABLE MLOG$_CLIENTES;
DROP TABLE OPERACION_BKP;
DROP TABLE RESPALDOPREGUNTA_PRODUCTO;
DROP TABLE RUPD$_CLIENTES;
DROP TABLE TBI_PREGUNTAS;
DROP TABLE TBI_PROCESO_OPERACION_X;
DROP TABLE TBI_PRODUCTO;
DROP TABLE TRE_OPERACION_ROL_BPM;
DROP TABLE TRE_PREGUNTA_TIPO_INSTITUCION;

DROP PROCEDURE VALIDA_SIMILITUDES;
DROP PROCEDURE MIGRAR_CONFIG_VISIBILIDAD;



	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DDL_FASE_III_3.sql
	DESCRIPCION: Registrar todos los cambios DDL en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DDL 3)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/



	-- 20171221 Actualiza la definición del campo LOGIN SOLICITANTE para la tbl de bitácora

	ALTER TABLE TBI_TERMINO MODIFY LOGIN_SOLICITANTE NOT NULL;
	


	-- 20171221 Instrucciones DDL para eliminar secuencias no utilizadas.
	
	-- Al eliminar la entidad:  TBI_PREGUNTAS, TBI_PRODUCTO y TRE_PREGUNTA_TIPO_INSTITUCION, se eliminaron los triggers:  
	-- TBI_PREGUNTAS_TGR, TBI_PRODUCTO_TGR y TRE_PREG_TP_INST_TGR, respectivamente
	

	DROP SEQUENCE TBI_PREGUNTAS_SEQ;
	DROP SEQUENCE TBI_PRODUCTO_SEQ;
	DROP SEQUENCE TRE_PREG_TP_INST_SEQ;

	/
	
	

-- 20180123  Se agrega nueva columna a la tabla de catálogo de término para validar etapa de ingreso, TARJETA FNXII-7176

ALTER TABLE TCA_TERMINO ADD  ID_TCA_PROCESO_INGRESO NUMBER(5,0) NULL;

ALTER TABLE TCA_TERMINO ADD CONSTRAINT TCA_TERMINO_TCA_PROCESO_FK FOREIGN KEY (ID_TCA_PROCESO_INGRESO) REFERENCES TCA_PROCESO_BPM (ID);

/



	/*
	----------------------------------
	ARCHIVO: BD_FENIX_DDL_FASE_III_5.sql
	DESCRIPCION: Registrar todos los cambios DDL en la base de datos, con el fin de llevar total trazabilidad de los mismos para fase III. (Segmento DDL 5)
	AUTOR: LATBC 
	VERSION: 1.0	
	----------------------------------
	*/

	-- 20180211 CREA LA TABLA TCA_PRODUCTO_PROGRAMA Y SUS RELACIONES, PARA ATENDER TARJETA FNXII-6834

	CREATE TABLE TCA_PRODUCTO_PROGRAMA (
		ID NUMBER(12,0) NOT NULL,
		ID_CAT_PRODUCTO NUMBER(5,0) NOT NULL,
		ID_CAT_SECTOR_INSTITUCIONAL NUMBER(5,0) NOT NULL,
		ES_REVOLVENTE NUMBER(1,0) NOT NULL,
		ID_TCA_TIPO_TASA_DESEMBOLSO NUMBER(5,0) NOT NULL,
		CODIGO_PROGRAMA VARCHAR2(9) NOT NULL,
		ES_VALOR_POR_DEFAULT NUMBER(1,0) NOT NULL,
		BAN_ESTATUS NUMBER(1,0) DEFAULT 1 NOT NULL,
		FECHA_REGISTRO DATE DEFAULT CURRENT_DATE  NOT NULL,		
		CONSTRAINT TCA_PRODUCTO_PROGRAMA_PK PRIMARY KEY (ID) 
	);

	ALTER TABLE CONTRATO_DESEMBOLSO ADD ID_TCA_PRODUCTO_PROGRAMA NUMBER(12,0) NULL;
	
		
	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_CAT_PROD_FK FOREIGN KEY (ID_CAT_PRODUCTO) REFERENCES CAT_PRODUCTO (ID);
	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_CAT_SECTOR_FK FOREIGN KEY (ID_CAT_SECTOR_INSTITUCIONAL) REFERENCES CAT_SECTOR_INSTITUCIONAL (ID);
	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_TIPO_TASA_FK FOREIGN KEY (ID_TCA_TIPO_TASA_DESEMBOLSO) REFERENCES TCA_TIPO_TASA_DESEMBOLSO (ID);

	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_ES_REVOL_CHK CHECK(ES_REVOLVENTE IN(1,0));
	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_ES_DEFAULT_CHK CHECK(ES_VALOR_POR_DEFAULT IN(1,0));
	ALTER TABLE TCA_PRODUCTO_PROGRAMA ADD CONSTRAINT TCA_PROD_PROGR_BAN_ESTATUS_CHK CHECK(BAN_ESTATUS IN(1,0));
	

	ALTER TABLE CONTRATO_DESEMBOLSO ADD CONSTRAINT CONTRATO_DES_PROD_PROGRAMA_FK FOREIGN KEY (ID_TCA_PRODUCTO_PROGRAMA) REFERENCES TCA_PRODUCTO_PROGRAMA (ID);
	
	/
	