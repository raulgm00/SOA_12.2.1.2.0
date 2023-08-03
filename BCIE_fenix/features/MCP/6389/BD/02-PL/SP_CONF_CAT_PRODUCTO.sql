-- ##--------------------------------------------------------------------------------------------##
-- Descripción:  Script que permite agregar, modificar, desactivar/activar la configuración de  producto, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar. Además de solicitar los atributos del producto, también se puede indicar el listado de campos a agregar.
2 – Modificar. Además de indicar los  atributos del producto a modificar, también se puede indicar el listado de campos a agregar.
3 – Desactivar el producto del catálogo de productos.
4 – Activar el producto del catálogo de productos.
5 - Desactivar los campos por producto. (TRE_CAT_PRODUCTO_TCA_CAMPOS). En donde se requiere el Id de producto y la  lista de campos a desactivar.
6 - Activar los campos por producto. (TRE_CAT_PRODUCTO_TCA_CAMPOS). En donde se requiere el Id de producto y la lista de campos a activar.
7 - Listar el catálogo de productos  (CAT_PRODUCTO)
8 - Listar los atributos de un producto en específico. (CAT_PRODUCTO)
9  - Listar los campos por producto específico. (TRE_CAT_PRODUCTO_TCA_CAMPOS) 
10 - Listar los campos disponibles a asignar a los productos (TCA_CAMPOS)

*/
-- ##--------------------------------------------------------------------------------------------##



CREATE OR REPLACE PROCEDURE SP_CONF_CAT_PRODUCTO (
	P_ACCION                     IN NUMBER   := 0,			-- Indica el tipo de acción a ejecutar
	P_ID_CAT_PRODUCTO            IN NUMBER   := NULL,		-- Id del  registro del catálogo de productos. (CAT_PRODUCTO)
	P_DESCRIPCION                IN VARCHAR2 := NULL,		-- Descripción del producto.
	P_DESCRIPCION_CORTA          IN VARCHAR2 := NULL,		-- Descripción corta del producto.
	P_REQUIERE_ELEGIBILIDAD      IN NUMBER	 := 0,			--  Bandera que indica si se requiere del proceso de elegibilidad (REQUIERE_ELEGIBILIDAD) (1 = Se requiere, 0 = No se requiere) 
	P_REQUIERE_LAFT              IN NUMBER	 := 0,			-- Bandera que indica si se requiere del proceso de LAFT (REQUIERE_LAFT) (1 = Se requiere, 0 = No se requiere)
	P_COD_EXTERNO                IN VARCHAR2 := NULL,		-- Clave del código externo (COD_EXTERNO) (Identificador flexcube)
	P_ID_INSTRUMENTO_FINANCIERO  IN NUMBER	 := NULL,		-- Identificador del Instrumento Financiero   (TCA_INSTRUMENTO_FINANCIERO)
	P_ID_TIPO_OPERACION          IN NUMBER	 := NULL,		-- Identificador del tipo de operación (TCA_TIPO_OPERACION) (1 = Proyecto, 2 = Intermediación)
	P_HOJA_TERMINOS_SEPRI        IN NUMBER	 := 0,			-- Bandera que indica si se requiere analizar el modelo por Ejecutivo de SEPRI (REQUIERE_HOJA_TERMINOS_SEPRI) (1 = Se requiere, 0 = No se requiere)
	P_HOJA_TERMINOS_PCT          IN NUMBER	 := 0,			 -- Bandera que indica si se requiere elaborar hoja de términos por Ejecutivo PCT (REQUIERE_HOJA_TERMINOS_PCT) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_CORE              IN NUMBER	 := 0,			 -- Bandera que indica si se requiere CORE (REQUIERE_CORE) (1 = Se requiere, 0 = No se requiere)
	P_GERIES_ELEGIBILIDAD        IN NUMBER	 := 0,			 -- Bandera que indica si se requiere GERIES en ELEGIBILIDAD (REQUIERE_GERIES_ELEGIBILIDAD) (1 = Se requiere, 0 = No se requiere)
	P_OPINION_TECNICA            IN NUMBER	 := 0,			-- Bandera que indica si se requiere formular programas por Especialista Sectorial (REQUIERE_OPINION_TECNICA) (1 = Se requiere, 0 = No se requiere)
	P_REALIZA_ANALISIS_LAFT      IN NUMBER	 := 0,			-- Bandera que indica si se requiere del representante de OFIC para el análisis de LA-FT (OFIC_REALIZA_ANALISIS_LAFT) (1 = Se requiere, 0 = No se requiere)		
	P_REQUIERE_ADQUISICIONES     IN NUMBER	 := 0,			--  Bandera que indica si se requiere analizar adquisiciones por Oficial de adquisiciones (REQUIERE_ADQUISICIONES) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_RAROC             IN NUMBER	 := 0,			-- Bandera que indica si se requiere realizar Pre-análisis por analista de crédito (REQUIERE_RAROC) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_IBCIE             IN NUMBER	 := 0,			-- Bandera que indica si se requiere evaluación ExAnte I-BCIE (REQUIERE_IBCIE) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_SIEMAS            IN NUMBER	 := 0,		    -- Bandera que indica si se requiere evaluación ExAnte SIEMAS (REQUIERE_SIEMAS) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_PREPARACION       IN NUMBER	 := 0,			-- Bandera que indica si se requiere del proceso de PREPARACION (REQUIERE_PREPARACION) (1 = Se requiere, 0 = No se requiere)
	P_TIENE_RIESGO_CREDITO       IN NUMBER	 := 0,			-- Bandera que indica si se tiene riego de crédito (TIENE_RIESGO_CREDITO) (1 = Sí tiene, 0 = No tiene)
	P_RESPONSABLE_ANALISIS       IN NUMBER	 := NULL,		-- Id. del rol BPM responsable del análisis (RESPONSABLE_ANALISIS) (Id de catálogo TCA_ROL_BPM)
	P_FIRMA_CONTRATO             IN NUMBER	 := 0,			-- Bandera que indica si se requiere firma del contrato (REQUIERE_FIRMA_CONTRATO) (1 = Se requiere, 0 = No se requiere)
	P_REGISTRO_LINEA             IN NUMBER	 := 0,			-- Bandera que indica si se requiere  registro de línea (REQUIERE_REGISTRO_LINEA) (1 = Se requiere, 0 = No se requiere)
	P_ASJUR_ANALISIS             IN NUMBER	 := 0,			-- Bandera que indica si se requiere opinión jurídica en análisis (REQUIERE_ASJUR_ANALISIS) (1 = Se requiere, 0 = No se requiere)
	P_ASJUR_ELEGIBILIDAD         IN NUMBER	 := 0,			-- Bandera que indica si se requiere opinión jurídica en elegibilidad (REQUIERE_ASJUR_ELEGIBILIDAD) (1 = Se requiere, 0 = No se requiere)
	P_ES_IFI                     IN NUMBER	 := 0,			-- Bandera que indica si el producto es IFI (ES_IFI) (1 = Es IFIE, 0 = No es IFI)
	P_REQUIERE_SCR               IN NUMBER	 := 0,			-- Bandera que indica si se requiere  SCR (REQUIERE_SCR) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_TIR               IN NUMBER	 := 0,			-- Bandera que indica si se requiere TIR (REQUIERE_TIR) (1 = Se requiere, 0 = No se requiere)
	P_REQUIERE_CIERRE            IN NUMBER	 := 0, 			-- Bandera que indica si se requiere del proceso de Cierre (REQUIERE_CIERRE) (1 = Se requiere, 0 = No se requiere)	
	P_TIPO_CIERRE                IN NUMBER	 := 0,			-- Bandera que indica el tipo de cierre que se requiere (TIPO_CIERRE) (0 = No aplica, 1 = Cierre Normal  (Es 1 cuando la respuesta es afirmativa para la pregunta, ¿Requiere proceso de cierre?, e inicia las actividades del proceso con la tarea “Solicitar cierre de operación”), 2 =  Cierre Normal (Es 2 cuando la respuesta es negativa para la pregunta, ¿Requiere proceso de cierre? Y el flujo continua con la tarea “Validar cierre con el cliente”), 3 = Cierre Fideicomiso/Fondo).
	P_CAMPOS                     IN T_ID_CAMPO,				-- Indica el listado de campos a relacionar con el producto.
	P_CODIGO_RES                 OUT NUMBER					-- Indica el código de respuesta de la transacción.   (0 - Incorrecto, 1 - Correcto).

	
	)

AS
	err_num     		NUMBER;
	err_msg     		VARCHAR2 (255);	
	v_id_producto 		NUMBER := 0;
	v_campo 			NUMBER := 0;
	TYPE_CAT_PRODUCTO  CAT_PRODUCTO%ROWTYPE;
	v_accion			VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_nombrePRODUCTO	VARCHAR2 (255);
	v_Error_msg         VARCHAR2(100) := ''; 
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Inserta datos en CAT_PRODUCTO
	THEN
		SELECT MAX (ID) INTO v_id_producto FROM CAT_PRODUCTO;

		v_id_producto := v_id_producto + 1;
        
        --DBMS_OUTPUT.PUT_LINE ('ID ADD: ' || v_id_producto);

		INSERT INTO CAT_PRODUCTO(ID,
							DESCRIPCION,
                            DESCRIPCION_CORTA,
                            FECHA_REGISTRO,
                            REQUIERE_ELEGIBILIDAD,
                            REQUIERE_LAFT,
                            COD_EXTERNO,
                            BAN_ESTATUS,
                            ID_INSTRUMENTO_FINANCIERO,
                            ID_TIPO_OPERACION,   
                            REQUIERE_HOJA_TERMINOS_SEPRI,    
                            REQUIERE_HOJA_TERMINOS_PCT,     
                            REQUIERE_CORE,     
                            REQUIERE_GERIES_ELEGIBILIDAD,    
                            REQUIERE_OPINION_TECNICA,     
                            OFIC_REALIZA_ANALISIS_LAFT,     
                            REQUIERE_ADQUISICIONES,     
                            REQUIERE_RAROC,     
                            REQUIERE_IBCIE,     
                            REQUIERE_SIEMAS,     
                            REQUIERE_PREPARACION,    
                            TIENE_RIESGO_CREDITO,     
                            RESPONSABLE_ANALISIS,     
                            REQUIERE_FIRMA_CONTRATO,     
                            REQUIERE_REGISTRO_LINEA,     
                            REQUIERE_ASJUR_ANALISIS,     
                            REQUIERE_ASJUR_ELEGIBILIDAD,     
                            ES_IFI,     
                            REQUIERE_SCR,     
                            REQUIERE_TIR,     
                            REQUIERE_CIERRE,    
                            TIPO_CIERRE)
		VALUES(v_id_producto,
			P_DESCRIPCION,
            P_DESCRIPCION_CORTA,
            SYSDATE,
            P_REQUIERE_ELEGIBILIDAD,
            P_REQUIERE_LAFT,
            P_COD_EXTERNO,
            1,
            P_ID_INSTRUMENTO_FINANCIERO,
            P_ID_TIPO_OPERACION,   
            P_HOJA_TERMINOS_SEPRI,    
            P_HOJA_TERMINOS_PCT,     
            P_REQUIERE_CORE,     
            P_GERIES_ELEGIBILIDAD,    
            P_OPINION_TECNICA,     
            P_REALIZA_ANALISIS_LAFT,     
            P_REQUIERE_ADQUISICIONES,     
			P_REQUIERE_RAROC,     
			P_REQUIERE_IBCIE,     
			P_REQUIERE_SIEMAS,     
			P_REQUIERE_PREPARACION,    
			P_TIENE_RIESGO_CREDITO,     
			P_RESPONSABLE_ANALISIS,     
			P_FIRMA_CONTRATO,     
			P_REGISTRO_LINEA,     
			P_ASJUR_ANALISIS,     
			P_ASJUR_ELEGIBILIDAD,     
			P_ES_IFI,     
			P_REQUIERE_SCR,     
			P_REQUIERE_TIR,     
			P_REQUIERE_CIERRE,    
			P_TIPO_CIERRE);    

        FOR IND IN 1 .. P_CAMPOS.COUNT 
        LOOP
            INSERT INTO TRE_CAT_PRODUCTO_TCA_CAMPOS(ID,
                                                    ID_CAT_PRODUCTO,
                                                    ID_CAMPO,
													BAN_ESTATUS)    
			VALUES(TRE_CAT_PRODUCTO_TCA_CAMP_SEQ.NEXTVAL,
				v_id_producto,
				P_CAMPOS(IND),
				1);
        END LOOP;		

		COMMIT;
		
		P_CODIGO_RES := 1;
		v_accion := 'AGREGAR';       
		
	ELSIF (P_ACCION = 2)	-- Modificar datos de CAT_PRODUCTO
	THEN
		IF  (P_ID_CAT_PRODUCTO > 0) THEN			
			SELECT * INTO TYPE_CAT_PRODUCTO FROM CAT_PRODUCTO
			WHERE ID = P_ID_CAT_PRODUCTO;
        
			UPDATE CAT_PRODUCTO SET
                DESCRIPCION = NVL(P_DESCRIPCION ,TYPE_CAT_PRODUCTO.DESCRIPCION),
                DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA,TYPE_CAT_PRODUCTO.DESCRIPCION_CORTA),
                REQUIERE_ELEGIBILIDAD = NVL(P_REQUIERE_ELEGIBILIDAD,TYPE_CAT_PRODUCTO.REQUIERE_ELEGIBILIDAD),
                REQUIERE_LAFT = NVL(P_REQUIERE_LAFT ,TYPE_CAT_PRODUCTO.REQUIERE_LAFT),
                COD_EXTERNO = NVL(P_COD_EXTERNO,TYPE_CAT_PRODUCTO.COD_EXTERNO),
                ID_INSTRUMENTO_FINANCIERO = NVL(P_ID_INSTRUMENTO_FINANCIERO ,TYPE_CAT_PRODUCTO.ID_INSTRUMENTO_FINANCIERO),
                ID_TIPO_OPERACION = NVL(P_ID_TIPO_OPERACION,TYPE_CAT_PRODUCTO.ID_TIPO_OPERACION),   
                REQUIERE_HOJA_TERMINOS_SEPRI = NVL(P_HOJA_TERMINOS_SEPRI,TYPE_CAT_PRODUCTO.REQUIERE_HOJA_TERMINOS_SEPRI),    
                REQUIERE_HOJA_TERMINOS_PCT = NVL(P_HOJA_TERMINOS_PCT , TYPE_CAT_PRODUCTO.REQUIERE_HOJA_TERMINOS_PCT),     
                REQUIERE_CORE = NVL(P_REQUIERE_CORE ,TYPE_CAT_PRODUCTO.REQUIERE_CORE),     
                REQUIERE_GERIES_ELEGIBILIDAD = NVL(P_GERIES_ELEGIBILIDAD,TYPE_CAT_PRODUCTO.REQUIERE_GERIES_ELEGIBILIDAD),    
                REQUIERE_OPINION_TECNICA = NVL(P_OPINION_TECNICA,TYPE_CAT_PRODUCTO.REQUIERE_OPINION_TECNICA),     
                OFIC_REALIZA_ANALISIS_LAFT = NVL(P_REALIZA_ANALISIS_LAFT,TYPE_CAT_PRODUCTO.OFIC_REALIZA_ANALISIS_LAFT),     
                REQUIERE_ADQUISICIONES = NVL(P_REQUIERE_ADQUISICIONES,TYPE_CAT_PRODUCTO.REQUIERE_ADQUISICIONES),     
                REQUIERE_RAROC = NVL(P_REQUIERE_RAROC,TYPE_CAT_PRODUCTO.REQUIERE_RAROC),     
                REQUIERE_IBCIE = NVL(P_REQUIERE_IBCIE,TYPE_CAT_PRODUCTO.REQUIERE_IBCIE),     
                REQUIERE_SIEMAS = NVL(P_REQUIERE_SIEMAS,TYPE_CAT_PRODUCTO.REQUIERE_SIEMAS),     
                REQUIERE_PREPARACION = NVL(P_REQUIERE_PREPARACION,TYPE_CAT_PRODUCTO.REQUIERE_PREPARACION),    
                TIENE_RIESGO_CREDITO = NVL(P_TIENE_RIESGO_CREDITO,TYPE_CAT_PRODUCTO.TIENE_RIESGO_CREDITO),     
                RESPONSABLE_ANALISIS = NVL(P_RESPONSABLE_ANALISIS,TYPE_CAT_PRODUCTO.RESPONSABLE_ANALISIS),     
                REQUIERE_FIRMA_CONTRATO = NVL(P_FIRMA_CONTRATO,TYPE_CAT_PRODUCTO.REQUIERE_FIRMA_CONTRATO),     
                REQUIERE_REGISTRO_LINEA = NVL(P_REGISTRO_LINEA,TYPE_CAT_PRODUCTO.REQUIERE_REGISTRO_LINEA),     
                REQUIERE_ASJUR_ANALISIS = NVL(P_ASJUR_ANALISIS,TYPE_CAT_PRODUCTO.REQUIERE_ASJUR_ANALISIS),     
                REQUIERE_ASJUR_ELEGIBILIDAD = NVL(P_ASJUR_ELEGIBILIDAD,TYPE_CAT_PRODUCTO.REQUIERE_ASJUR_ELEGIBILIDAD),     
                ES_IFI = NVL(P_ES_IFI,TYPE_CAT_PRODUCTO.ES_IFI),     
                REQUIERE_SCR = NVL(P_REQUIERE_SCR,TYPE_CAT_PRODUCTO.REQUIERE_SCR),     
                REQUIERE_TIR = NVL(P_REQUIERE_TIR,TYPE_CAT_PRODUCTO.REQUIERE_TIR),     
                REQUIERE_CIERRE = NVL(P_REQUIERE_CIERRE,TYPE_CAT_PRODUCTO.REQUIERE_CIERRE),    
                TIPO_CIERRE = NVL(P_TIPO_CIERRE,TYPE_CAT_PRODUCTO.TIPO_CIERRE)
			WHERE ID = P_ID_CAT_PRODUCTO;
		
			-- Si se reciben nuevos campos al solicitar la transacción de modificar y no se encuentran se agregan 
			FOR IND IN 1 .. P_CAMPOS.COUNT 
			LOOP
				--SE EJECUTA CONSULTA PARA VALIDAR SI EXISTE ALGUN REGISTRO CON ESOS VALORES
				SELECT COUNT(ID) INTO v_campo 
				FROM TRE_CAT_PRODUCTO_TCA_CAMPOS
				WHERE ID_CAMPO = P_CAMPOS(IND)
				AND ID_CAT_PRODUCTO = P_ID_CAT_PRODUCTO;
				
				IF(v_campo = 0)
				THEN 
					--SE INSERTA UN NUEVO REGISTRO EN LA TABLA CON EL ID DEL PRODUCTO QUE SE QUIERE EDITAR.
					INSERT INTO TRE_CAT_PRODUCTO_TCA_CAMPOS(ID,
														ID_CAT_PRODUCTO,
														ID_CAMPO,
														BAN_ESTATUS)    
					VALUES(TRE_CAT_PRODUCTO_TCA_CAMP_SEQ.NEXTVAL,
						P_ID_CAT_PRODUCTO,
						P_CAMPOS(IND),
						1);
				END IF;
			END LOOP;
		
			COMMIT;		
			
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
		END IF;
		
        v_accion := 'MODIFICAR';       

	ELSIF (P_ACCION = 3)	-- Deshabilita el registro de CAT_PRODUCTO 
    THEN
		IF  (P_ID_CAT_PRODUCTO > 0) THEN
			UPDATE CAT_PRODUCTO
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_CAT_PRODUCTO;
			
			IF SQL%FOUND THEN
				COMMIT;
				P_CODIGO_RES := 1;
			END IF;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4) -- Habilita el registro de CAT_PRODUCTO 
    THEN
		IF  (P_ID_CAT_PRODUCTO > 0) THEN
			UPDATE CAT_PRODUCTO
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_CAT_PRODUCTO;
			
			IF SQL%FOUND THEN
				COMMIT;
				P_CODIGO_RES := 1;
			END IF;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)   -- Desactiva los campos indicados 
    THEN
		IF  (P_ID_CAT_PRODUCTO > 0) THEN
			FOR IND IN 1 .. P_CAMPOS.COUNT 
			LOOP             
				--SE CAMBIA EL BAN ESTATUS PARA REALIZAR UN BORRADO LOGICO DE LA RELACION DE CAMPOS.
				UPDATE TRE_CAT_PRODUCTO_TCA_CAMPOS
				SET BAN_ESTATUS = 0
				WHERE ID_CAMPO = P_CAMPOS(IND)
				AND ID_CAT_PRODUCTO = P_ID_CAT_PRODUCTO;            
                COMMIT;
			END LOOP;
					
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
	   
		v_accion := 'DESACTIVAR CAMPOS';
		
	ELSIF (P_ACCION = 6)   -- Activa los campos indicados 
    THEN
		IF  (P_ID_CAT_PRODUCTO > 0) THEN
			FOR IND IN 1 .. P_CAMPOS.COUNT 
			LOOP             
				--SE CAMBIA EL BAN ESTATUS PARA ACTIVAR LA RELACION DE CAMPOS.
				UPDATE TRE_CAT_PRODUCTO_TCA_CAMPOS
				SET BAN_ESTATUS = 1
				WHERE ID_CAMPO = P_CAMPOS(IND)
				AND ID_CAT_PRODUCTO = P_ID_CAT_PRODUCTO;            
                COMMIT;
			END LOOP;
					
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
	   
		v_accion := 'ACTIVAR CAMPOS';
	
	ELSIF (P_ACCION = 7) 
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT 
			CATPROD.ID, CATPROD.DESCRIPCION, CATPROD.DESCRIPCION_CORTA, CATPROD.REQUIERE_ELEGIBILIDAD, CATPROD.REQUIERE_LAFT, CATPROD.COD_EXTERNO, CATPROD.BAN_ESTATUS, CATPROD.ID_INSTRUMENTO_FINANCIERO, 
			INSTRFIN.DESCRIPCION AS INSTRUMENTO_FINANCIERO, CATPROD.ID_TIPO_OPERACION, TTO.DESCRIPCION AS TIPO_OPERACION, CATPROD.REQUIERE_HOJA_TERMINOS_SEPRI, CATPROD.REQUIERE_HOJA_TERMINOS_PCT, 
			CATPROD.REQUIERE_CORE, CATPROD.REQUIERE_GERIES_ELEGIBILIDAD, CATPROD.REQUIERE_OPINION_TECNICA, CATPROD.OFIC_REALIZA_ANALISIS_LAFT, CATPROD.REQUIERE_ADQUISICIONES, CATPROD.REQUIERE_RAROC,
			CATPROD.REQUIERE_IBCIE, CATPROD.REQUIERE_SIEMAS, CATPROD.REQUIERE_PREPARACION, CATPROD.TIENE_RIESGO_CREDITO, CATPROD.RESPONSABLE_ANALISIS, ROLBPM.DESCRIPCION AS RESPO_ANAL,
			CATPROD.REQUIERE_FIRMA_CONTRATO, CATPROD.REQUIERE_REGISTRO_LINEA, CATPROD.REQUIERE_ASJUR_ANALISIS, CATPROD.REQUIERE_ASJUR_ELEGIBILIDAD, CATPROD.ES_IFI, CATPROD.REQUIERE_SCR,
			CATPROD.REQUIERE_TIR, CATPROD.REQUIERE_CIERRE, CATPROD.TIPO_CIERRE
			FROM CAT_PRODUCTO CATPROD
			LEFT JOIN TCA_ROL_BPM ROLBPM ON CATPROD.RESPONSABLE_ANALISIS = ROLBPM.ID
			INNER JOIN TCA_INSTRUMENTO_FINANCIERO INSTRFIN ON CATPROD.ID_INSTRUMENTO_FINANCIERO = INSTRFIN.ID
			INNER JOIN TCA_TIPO_OPERACION TTO ON CATPROD.ID_TIPO_OPERACION = TTO.ID
			ORDER BY 1
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_CAT_PRODUCTO = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', REQUIERE_ELEGIBILIDAD = ' || v_rec.REQUIERE_ELEGIBILIDAD || ', REQUIERE_LAFT = ' || v_rec.REQUIERE_LAFT ||
			', COD_EXTERNO = ' || v_rec.COD_EXTERNO || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS || ', INSTRUMENTO_FINANCIERO = ' || v_rec.INSTRUMENTO_FINANCIERO || ', TIPO_OPERACION = ' || v_rec.TIPO_OPERACION ||
			', REQUIERE_HOJA_TERMINOS_SEPRI = ' || v_rec.REQUIERE_HOJA_TERMINOS_SEPRI || ', REQUIERE_HOJA_TERMINOS_PCT = ' || v_rec.REQUIERE_HOJA_TERMINOS_PCT || ', REQUIERE_CORE = ' || v_rec.REQUIERE_CORE || 
			', REQUIERE_GERIES_ELEGIBILIDAD = ' || v_rec.REQUIERE_GERIES_ELEGIBILIDAD || ', REQUIERE_OPINION_TECNICA = ' || v_rec.REQUIERE_OPINION_TECNICA || ', OFIC_REALIZA_ANALISIS_LAFT = ' || v_rec.OFIC_REALIZA_ANALISIS_LAFT ||
			', REQUIERE_ADQUISICIONES = ' || v_rec.REQUIERE_ADQUISICIONES || ', REQUIERE_RAROC = ' || v_rec.REQUIERE_RAROC || ', REQUIERE_IBCIE = ' || v_rec.REQUIERE_IBCIE || ', REQUIERE_SIEMAS = ' || v_rec.REQUIERE_SIEMAS || 
			', REQUIERE_PREPARACION = ' || v_rec.REQUIERE_PREPARACION || ', TIENE_RIESGO_CREDITO = ' || v_rec.TIENE_RIESGO_CREDITO || ', RESPONSABLE_ANALISIS = ' || v_rec.RESPO_ANAL || ', REQUIERE_FIRMA_CONTRATO = ' || v_rec.REQUIERE_FIRMA_CONTRATO || 
			', REQUIERE_REGISTRO_LINEA = ' || v_rec.REQUIERE_REGISTRO_LINEA || ', REQUIERE_ASJUR_ANALISIS = ' || v_rec.REQUIERE_ASJUR_ANALISIS || ', REQUIERE_ASJUR_ELEGIBILIDAD = ' || v_rec.REQUIERE_ASJUR_ELEGIBILIDAD || ', ES_IFI = ' || v_rec.ES_IFI ||
			', REQUIERE_SCR = ' || v_rec.REQUIERE_SCR || ', REQUIERE_TIR = '|| v_rec.REQUIERE_TIR || ', REQUIERE_CIERRE = ' || v_rec.REQUIERE_CIERRE || ', TIPO_CIERRE = ' || v_rec.TIPO_CIERRE);
		END LOOP; 
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR EL CATÁLOGO DE PRODUCTOS';		
	
	ELSIF (P_ACCION = 8) 
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_CAT_PRODUCTO > 0) THEN
			
			FOR v_rec IN (
				SELECT 
				CATPROD.ID, CATPROD.DESCRIPCION, CATPROD.DESCRIPCION_CORTA, CATPROD.REQUIERE_ELEGIBILIDAD, CATPROD.REQUIERE_LAFT, CATPROD.COD_EXTERNO, CATPROD.BAN_ESTATUS, CATPROD.ID_INSTRUMENTO_FINANCIERO, 
				INSTRFIN.DESCRIPCION AS INSTRUMENTO_FINANCIERO, CATPROD.ID_TIPO_OPERACION, TTO.DESCRIPCION AS TIPO_OPERACION, CATPROD.REQUIERE_HOJA_TERMINOS_SEPRI, CATPROD.REQUIERE_HOJA_TERMINOS_PCT, 
				CATPROD.REQUIERE_CORE, CATPROD.REQUIERE_GERIES_ELEGIBILIDAD, CATPROD.REQUIERE_OPINION_TECNICA, CATPROD.OFIC_REALIZA_ANALISIS_LAFT, CATPROD.REQUIERE_ADQUISICIONES, CATPROD.REQUIERE_RAROC,
				CATPROD.REQUIERE_IBCIE, CATPROD.REQUIERE_SIEMAS, CATPROD.REQUIERE_PREPARACION, CATPROD.TIENE_RIESGO_CREDITO, CATPROD.RESPONSABLE_ANALISIS, ROLBPM.DESCRIPCION AS RESPO_ANAL,
				CATPROD.REQUIERE_FIRMA_CONTRATO, CATPROD.REQUIERE_REGISTRO_LINEA, CATPROD.REQUIERE_ASJUR_ANALISIS, CATPROD.REQUIERE_ASJUR_ELEGIBILIDAD, CATPROD.ES_IFI, CATPROD.REQUIERE_SCR,
				CATPROD.REQUIERE_TIR, CATPROD.REQUIERE_CIERRE, CATPROD.TIPO_CIERRE
				FROM CAT_PRODUCTO CATPROD
				LEFT JOIN TCA_ROL_BPM ROLBPM ON CATPROD.RESPONSABLE_ANALISIS = ROLBPM.ID
				INNER JOIN TCA_INSTRUMENTO_FINANCIERO INSTRFIN ON CATPROD.ID_INSTRUMENTO_FINANCIERO = INSTRFIN.ID
				INNER JOIN TCA_TIPO_OPERACION TTO ON CATPROD.ID_TIPO_OPERACION = TTO.ID
				WHERE CATPROD.ID = P_ID_CAT_PRODUCTO
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_CAT_PRODUCTO = ' || v_rec.ID);
				DBMS_OUTPUT.PUT_LINE ('DESCRIPCION = ' || v_rec.DESCRIPCION);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_ELEGIBILIDAD = ' || v_rec.REQUIERE_ELEGIBILIDAD);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_LAFT = ' || v_rec.REQUIERE_LAFT);			
				DBMS_OUTPUT.PUT_LINE ('COD_EXTERNO = ' || v_rec.COD_EXTERNO);
				DBMS_OUTPUT.PUT_LINE ('BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
				DBMS_OUTPUT.PUT_LINE ('INSTRUMENTO_FINANCIERO = ' || v_rec.INSTRUMENTO_FINANCIERO);
				DBMS_OUTPUT.PUT_LINE ('TIPO_OPERACION = ' || v_rec.TIPO_OPERACION);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_HOJA_TERMINOS_SEPRI = ' || v_rec.REQUIERE_HOJA_TERMINOS_SEPRI);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_HOJA_TERMINOS_PCT = ' || v_rec.REQUIERE_HOJA_TERMINOS_PCT);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_CORE = ' || v_rec.REQUIERE_CORE);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_GERIES_ELEGIBILIDAD = ' || v_rec.REQUIERE_GERIES_ELEGIBILIDAD);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_OPINION_TECNICA = ' || v_rec.REQUIERE_OPINION_TECNICA);
				DBMS_OUTPUT.PUT_LINE ('OFIC_REALIZA_ANALISIS_LAFT = ' || v_rec.OFIC_REALIZA_ANALISIS_LAFT);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_ADQUISICIONES = ' || v_rec.REQUIERE_ADQUISICIONES);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_RAROC = ' || v_rec.REQUIERE_RAROC);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_IBCIE = ' || v_rec.REQUIERE_IBCIE);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_SIEMAS = ' || v_rec.REQUIERE_SIEMAS);  
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_PREPARACION = ' || v_rec.REQUIERE_PREPARACION);
				DBMS_OUTPUT.PUT_LINE ('TIENE_RIESGO_CREDITO = ' || v_rec.TIENE_RIESGO_CREDITO);
				DBMS_OUTPUT.PUT_LINE ('RESPONSABLE_ANALISIS = ' || v_rec.RESPO_ANAL);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_FIRMA_CONTRATO = ' || v_rec.REQUIERE_FIRMA_CONTRATO);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_REGISTRO_LINEA = ' || v_rec.REQUIERE_REGISTRO_LINEA);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_ASJUR_ANALISIS = ' || v_rec.REQUIERE_ASJUR_ANALISIS);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_ASJUR_ELEGIBILIDAD = ' || v_rec.REQUIERE_ASJUR_ELEGIBILIDAD);
				DBMS_OUTPUT.PUT_LINE ('ES_IFI = ' || v_rec.ES_IFI);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_SCR = ' || v_rec.REQUIERE_SCR);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_TIR = '|| v_rec.REQUIERE_TIR);
				DBMS_OUTPUT.PUT_LINE ('REQUIERE_CIERRE = ' || v_rec.REQUIERE_CIERRE);
				DBMS_OUTPUT.PUT_LINE ('TIPO_CIERRE = ' || v_rec.TIPO_CIERRE);
			END LOOP; 		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		v_accion := 'LISTAR LOS DATOS DE UN PRODUCTO EN ESPECÍFICO';		
	
	ELSIF (P_ACCION = 9) 
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_CAT_PRODUCTO > 0) THEN		
			
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombrePRODUCTO FROM CAT_PRODUCTO WHERE ID = P_ID_CAT_PRODUCTO;
			DBMS_OUTPUT.PUT_LINE ('Nombre del producto: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombrePRODUCTO);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT TC.ID, TC.DESCRIPCION,  TCPC.BAN_ESTATUS 
				FROM TRE_CAT_PRODUCTO_TCA_CAMPOS TCPC
				INNER JOIN TCA_CAMPOS TC ON TCPC.ID_CAMPO = TC.ID
				WHERE TCPC.ID_CAT_PRODUCTO = P_ID_CAT_PRODUCTO ORDER BY TC.ID
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_CAMPO = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
			END LOOP; 
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		v_accion := 'LISTAR LOS CAMPOS POR PRODUCTO ESPECÍFICO';		

	ELSIF (P_ACCION = 10) 
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR v_rec IN (
			SELECT TC.ID, TC.DESCRIPCION,  TC.BAN_ESTATUS, TC.FECHA_REGISTRO, TC.COD_EXTERNO 
			FROM TCA_CAMPOS TC
			ORDER BY TC.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_CAMPO = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS || ', FECHA_REGISTRO = ' || v_rec.FECHA_REGISTRO || ', COD_EXTERNO = ' || v_rec.COD_EXTERNO );
		END LOOP; 
		
		P_CODIGO_RES := 1;		
		v_accion := 'LISTAR LOS CAMPOS DISPONIBLES A ASIGNAR A LOS PRODUCTOS';				
		
	END IF;


    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN		
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente. ' || v_Error_msg);
	END IF;
   
EXCEPTION
	WHEN NO_DATA_FOUND
	THEN				
		DBMS_OUTPUT.PUT_LINE ('No existen Datos.');
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);
		
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
        VALUES ('SP',
				'SP_CONF_CAT_PRODUCTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
				SYSDATE);
		COMMIT;
	WHEN OTHERS
	THEN				
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);	  
    
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
		VALUES ('SP',
                'SP_CONF_CAT_PRODUCTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
	
/
