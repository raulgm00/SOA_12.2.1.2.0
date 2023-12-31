	
/*
### PENDIENTE DE ENVIAR A PROD  ###
FECHA: 20160205



1) Se crean nuevos triggers para enviar a PROD en QA
2) Se compila el PL (P_CONSULTA_VALOR_ATRIBUTO.sql) en QA
--1.	Cambios en la base de datos (DB Updates)
Se registran todos los cambios existentes en la base de datos, con el fin de llevar total trazabilidad.
*/

--1.1		05 Febrero 2015 Se ingresan triggers para la bit�cora de clientes y contactos 

CREATE OR REPLACE TRIGGER CLIENTE_UPD_TGR 
AFTER UPDATE ON CLIENTES
FOR EACH ROW
DECLARE 
v_id_tbi_cliente NUMBER;
v_id_tbi_cliente2 NUMBER;
v_id_flexcube    NUMBER;
v_ejecutivo      VARCHAR2(20);
v_autorizo       VARCHAR2(20);
v_ban_estatus    NUMBER; 

BEGIN

  SELECT TBI_CLIENTE_SEQ.NEXTVAL INTO v_id_tbi_cliente FROM DUAL;
  
  v_ban_estatus := :NEW.BAN_ESTATUS;
  
IF ( v_ban_estatus = 0) THEN

    INSERT INTO TBI_CLIENTE  VALUES(v_id_tbi_cliente,:NEW.ID_CLIENTE,:NEW.ID_FLEXCUBE,'ELIMINAR_CLIENTE',:NEW.EJECUTIVO,SYSDATE,:NEW.AUTORIZO,NULL,NULL); 
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'BAN_ESTATUS',:NEW.BAN_ESTATUS,:OLD.BAN_ESTATUS);
ELSE    

    INSERT INTO TBI_CLIENTE  VALUES(v_id_tbi_cliente,:NEW.ID_CLIENTE,:NEW.ID_FLEXCUBE,'MODIFICAR_CLIENTE',:NEW.EJECUTIVO,SYSDATE,:NEW.AUTORIZO,NULL,NULL); 
    
IF UPDATING ('ID_FLEXCUBE') THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES (v_id_tbi_cliente,'ID_FLEXCUBE',:NEW.ID_FLEXCUBE,:OLD.ID_FLEXCUBE);
END IF;  
IF UPDATING ('RAZON_SOCIAL')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'RAZON_SOCIAL',:NEW.RAZON_SOCIAL,:OLD.RAZON_SOCIAL);
END IF;  
IF UPDATING('ABREVIATURA')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'ABREVIATURA',:NEW.ABREVIATURA,:OLD.ABREVIATURA);
END IF;  
IF UPDATING('TIPO_PERSONA')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TIPO_PERSONA',:NEW.TIPO_PERSONA,:OLD.TIPO_PERSONA);
END IF;  
IF UPDATING('TIPO_CLIENTE')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TIPO_CLIENTE',:NEW.TIPO_CLIENTE,:OLD.TIPO_CLIENTE);
END IF;  
IF UPDATING('SECTOR')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'SECTOR',:NEW.SECTOR,:OLD.SECTOR);
END IF;  
IF UPDATING('TIPO_INSTITUCION')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TIPO_INSTITUCION',:NEW.TIPO_INSTITUCION,:OLD.TIPO_INSTITUCION);
END IF;  
IF UPDATING('PAIS')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'PAIS',:NEW.PAIS,:OLD.PAIS);
END IF;  
IF UPDATING('GRUPO_ECONOMICO')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'GRUPO_ECONOMICO',:NEW.GRUPO_ECONOMICO,:OLD.GRUPO_ECONOMICO);
END IF;  
IF UPDATING('TIPO_IDENTIFICACION')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TIPO_IDENTIFICACION',:NEW.TIPO_IDENTIFICACION,:OLD.TIPO_IDENTIFICACION);
END IF;  
IF UPDATING('NUMERO_IDENTIFICACION')THEN
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'NUMERO_IDENTIFICACION',:NEW.NUMERO_IDENTIFICACION,:OLD.NUMERO_IDENTIFICACION);
END IF;  
IF UPDATING('OFICINA')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'OFICINA',:NEW.OFICINA,:OLD.OFICINA);
END IF;  
IF UPDATING('FECHA_REGISTRO')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'FECHA_REGISTRO',:NEW.FECHA_REGISTRO,:OLD.FECHA_REGISTRO);
END IF;  
IF UPDATING('FECHA_APROBACION')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'FECHA_APROBACION',:NEW.FECHA_APROBACION,:OLD.FECHA_APROBACION);
END IF;  
IF UPDATING('EJECUTIVO')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'EJECUTIVO',:NEW.EJECUTIVO,:OLD.EJECUTIVO);
END IF;  
IF UPDATING('COMENTARIO_APROBACION')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'COMENTARIO_APROBACION',:NEW.COMENTARIO_APROBACION,:OLD.COMENTARIO_APROBACION);
END IF;  
IF UPDATING('AUTORIZO')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'AUTORIZO',:NEW.AUTORIZO,:OLD.AUTORIZO);
END IF;  
IF UPDATING('BAN_ESTATUS')THEN 
   INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'BAN_ESTATUS',:NEW.BAN_ESTATUS,:OLD.BAN_ESTATUS);
END IF;  
IF UPDATING('FECHA_BAJA')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'FECHA_BAJA',:NEW.FECHA_BAJA,:OLD.FECHA_BAJA);
END IF;  
IF UPDATING('DIA_PAGO')THEN  
  INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'DIA_PAGO',:NEW.DIA_PAGO,:OLD.DIA_PAGO);
END IF;

END IF;

END;


/


CREATE OR REPLACE TRIGGER CONTACTOS_INS_TGR AFTER
  INSERT ON CONTACTOS_CLIENTE FOR EACH ROW DECLARE v_id_tbi_cliente NUMBER;
  v_id_cliente         NUMBER;
  v_id_flexcube        VARCHAR2(40);
  v_ejecutivo          VARCHAR2(20);
  v_autorizo           VARCHAR2(20);
  v_id_contacto        NUMBER;
  v_nombre_contacto    VARCHAR2(60);
  v_telefono_contacto  VARCHAR2(20);
  v_correo_contacto    VARCHAR2(40);
  v_fecha_reg_contacto DATE;
  v_cargo_contacto     VARCHAR2(40);

BEGIN
 

    SELECT TBI_CLIENTE_SEQ.NEXTVAL INTO v_id_tbi_cliente FROM DUAL;

    SELECT C.ID_CONTACTO,C.NOMBRE, C.TELEFONO, C.CORREO_ELECTRONICO, C.FECHA_REGISTRO, C.CARGO
    INTO v_id_contacto, v_nombre_contacto, v_telefono_contacto, v_correo_contacto, v_fecha_reg_contacto, v_cargo_contacto
    FROM CONTACTOS C
    WHERE C.ID_CONTACTO = :NEW.ID_CONTACTO;

    SELECT CT.ID_CLIENTE, CT.ID_FLEXCUBE, CT.EJECUTIVO, CT.AUTORIZO
    INTO v_id_cliente,v_id_flexcube,v_ejecutivo, v_autorizo
    FROM CLIENTES CT
    WHERE CT.ID_CLIENTE = :NEW.ID_CLIENTE;

    INSERT INTO TBI_CLIENTE  VALUES(v_id_tbi_cliente,v_id_cliente,v_id_flexcube,'AGREGAR_CONTACTO',v_ejecutivo,SYSDATE,v_autorizo,NULL,NULL);
  
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'ID_CONTACTO',v_id_contacto,NULL);
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'NOMBRE',v_nombre_contacto,NULL);
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CORREO_ELECTRONICO',v_correo_contacto,NULL);
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CARGO',v_cargo_contacto,NULL);
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TELEFONO', v_telefono_contacto,NULL);
    INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'FECHA_REGISTRO',v_fecha_reg_contacto,NULL);

END;

/ 



CREATE OR REPLACE TRIGGER CONTACTOS_UPD_TGR 
AFTER UPDATE ON CONTACTOS
FOR EACH ROW
DECLARE 

v_id_tbi_cliente NUMBER;
v_id_cliente     NUMBER;
v_id_flexcube    VARCHAR(40);
v_ejecutivo      VARCHAR2(20);
v_autorizo       VARCHAR2(20);


BEGIN
      
      SELECT CCT.ID_CLIENTE 
      INTO v_id_cliente
      FROM CONTACTOS_CLIENTE CCT
      WHERE CCT.ID_CONTACTO = :NEW.ID_CONTACTO;
      
      SELECT CT.ID_CLIENTE, CT.ID_FLEXCUBE, CT.EJECUTIVO, CT.AUTORIZO 
      INTO v_id_cliente, v_id_flexcube, v_ejecutivo, v_autorizo
      FROM CLIENTES CT
      WHERE CT.ID_CLIENTE = v_id_cliente;
      
      
      SELECT TBI_CLIENTE_SEQ.NEXTVAL INTO v_id_tbi_cliente FROM DUAL;

      INSERT INTO TBI_CLIENTE  VALUES(v_id_tbi_cliente,v_id_cliente,v_id_flexcube,'MODIFICAR_CONTACTO',v_ejecutivo,SYSDATE,v_autorizo,NULL,NULL); 

      IF UPDATING('NOMBRE')THEN  
        INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'NOMBRE',:NEW.NOMBRE,:OLD.NOMBRE);
      END IF;  
      IF UPDATING ('CORREO_ELECTRONICO')THEN
        INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CORREO_ELECTRONICO',:NEW.CORREO_ELECTRONICO,:OLD.CORREO_ELECTRONICO);
      END IF;  
      IF UPDATING ('CARGO')THEN
        INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CARGO',:NEW.CARGO,:OLD.CARGO);
      END IF;  
      IF UPDATING ('TELEFONO')THEN
        INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TELEFONO',:NEW.TELEFONO,:OLD.TELEFONO);
      END IF; 
END;

/



CREATE OR REPLACE TRIGGER CONTACTOS_DEL_TGR 
AFTER DELETE ON CONTACTOS_CLIENTE
FOR EACH ROW
DECLARE 

v_id_tbi_cliente NUMBER;
v_id_cliente     NUMBER;
v_id_flexcube    VARCHAR(40);
v_ejecutivo      VARCHAR2(20);
v_autorizo       VARCHAR2(20);
v_id_contacto        NUMBER;
v_nombre_contacto    VARCHAR2(60);
v_telefono_contacto  VARCHAR2(20);
v_correo_contacto    VARCHAR2(40);
v_fecha_reg_contacto DATE;
v_cargo_contacto     VARCHAR2(40);

BEGIN

    SELECT C.ID_CONTACTO,C.NOMBRE, C.TELEFONO, C.CORREO_ELECTRONICO, C.FECHA_REGISTRO, C.CARGO
    INTO v_id_contacto, v_nombre_contacto, v_telefono_contacto, v_correo_contacto, v_fecha_reg_contacto, v_cargo_contacto
    FROM CONTACTOS C
    WHERE C.ID_CONTACTO = :OLD.ID_CONTACTO;

    SELECT CT.ID_CLIENTE, CT.ID_FLEXCUBE, CT.EJECUTIVO, CT.AUTORIZO
    INTO v_id_cliente,v_id_flexcube,v_ejecutivo, v_autorizo
    FROM CLIENTES CT
    WHERE CT.ID_CLIENTE = :OLD.ID_CLIENTE;

      SELECT TBI_CLIENTE_SEQ.NEXTVAL INTO v_id_tbi_cliente FROM DUAL;
      
      INSERT INTO TBI_CLIENTE  VALUES(v_id_tbi_cliente,v_id_cliente,v_id_flexcube,'ELIMINAR_CONTACTO',v_ejecutivo,SYSDATE,v_autorizo,NULL,NULL);
  
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'ID_CONTACTO',NULL,v_id_contacto);
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'NOMBRE',NULL,v_nombre_contacto);
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CORREO_ELECTRONICO',NULL,v_correo_contacto);
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'CARGO',NULL,v_cargo_contacto);
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'TELEFONO',NULL,v_telefono_contacto);
      INSERT INTO TBI_CLIENTE_CAMPO VALUES(v_id_tbi_cliente,'FECHA_REGISTRO',NULL,v_fecha_reg_contacto);

END;

/


COMMIT;

--1.2	07 de Febrero de 2015 Se corrige rol de la tarea Cumplir Condiciones

UPDATE TCA_TAREA_BPM SET  ID_ROL_BPM = 1 where ID = 69;
COMMIT;

--1.3	08 de Febrero de 2015 Se ingresan los registros de (T�rminos) en la tabla TCO_ATRIBUTO_TCC y registros a la tabla TCA_PLANTILLA_CORREO


INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  352, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 1, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  353, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 1, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  354, 'PLAZO', 3, 0, 1, 'Plazo', 1, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  355, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 1, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  356, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 1, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  357, 'DESCRIPCION', 6, 1, 0, 'Descripci�n', 1, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  358, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 2, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  359, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 2, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  360, 'PLAZO', 3, 0, 1, 'Plazo', 2, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  361, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 2, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  362, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 2, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  363, 'DESCRIPCION', 6, 1, 0, 'Descripci�n', 2, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  364, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 3, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  365, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 3, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  366, 'PLAZO', 3, 0, 1, 'Plazo', 3, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  367, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 3, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  368, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 3, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  369, 'ID_TCA_TIPO_FECHA_INICIO', 1, 1, 1, 'Tipo Fecha de Inicio', 4, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  370, 'FECHA_INICIO', 2, 1, 1, 'Fecha de Inicio', 4, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  371, 'PLAZO', 3, 0, 1, 'Plazo de la L�nea', 4, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  372, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo de la L�nea', 4, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  373, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha m�xima', 4, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  374, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 5, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  375, 'FECHA_INICIO', 2, 0, 1, 'Fecha de Inicio', 5, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  376, 'PLAZO', 3, 0, 1, 'Plazo', 5, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  377, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo', 5, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  378, 'FECHA_VENCIMIENTO', 5, 1, 0, 'Fecha de vencimiento', 5, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  379, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 6, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  380, 'FECHA_INICIO', 2, 0, 1, 'Fecha de Inicio', 6, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  381, 'PLAZO', 3, 0, 1, 'Plazo de financiamiento', 6, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  382, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo Plazo de financiamiento', 6, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  383, 'PLAZO', 1, 0, 1, 'Plazo de la Garant�a', 7, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  384, 'ID_TCA_FRECUENCIA_PLAZO', 2, 0, 1, 'Tipo Plazo de la garant�a', 7, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  385, 'ID_TCA_TIPO_FECHA_INICIO', 1, 0, 1, 'Tipo Fecha de Inicio', 8, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  386, 'FECHA_INICIO', 2, 0, 0, 'Fecha de Inicio', 8, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  387, 'PLAZO', 3, 0, 1, 'Per�odo de Gracia', 8, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  388, 'ID_TCA_FRECUENCIA_PLAZO', 4, 0, 1, 'Tipo de Per�odo de Gracia', 8, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  389, 'NOMBRE', 1, 0, 1, 'Nombre', 9, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  390, 'FECHA_VENCIMIENTO', 2, 0, 1, 'Fecha', 9, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  391, 'DESCRIPCION', 3, 0, 1, 'Descripci�n', 9, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  392, 'ID_TCA_MONEDA', 1, 1, 1, 'Moneda', 10, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  393, 'MONTO', 2, 0, 1, 'Monto', 10, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  394, 'DESCRIPCION', 3, 1, 0, 'Descripci�n', 10, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  395, 'ID_TCA_MONEDA', 1, 1, 1, 'Moneda', 11, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  396, 'MONTO', 2, 1, 1, 'Monto', 11, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  397, 'ID_TCA_MONEDA', 1, 1, 1, 'Moneda', 12, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  398, 'MONTO', 2, 0, 1, 'Monto', 12, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  399, 'NOMBRE', 1, 0, 1, 'Nombre', 13, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  400, 'ID_TCA_MONEDA', 2, 0, 1, 'Moneda', 13, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  401, 'MONTO', 3, 0, 1, 'Monto', 13, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  402, 'TASA', 1, 0, 1, 'Tasa', 14, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  403, 'ID_TCA_TIPO_TASA', 2, 0, 1, 'Tipo de Tasa', 14, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  404, 'FRECUENCIA_REVISION', 3, 0, 0, 'Frecuencia de Revisi�n', 14, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  405, 'ID_TCA_FRECUENCIA_REVISION', 4, 0, 0, 'Tipo Frecuencia de Revisi�n', 14, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  406, 'FECHA_INICIO_REVISION', 5, 0, 0, 'Fecha de Inicio de Revisi�n', 14, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  407, 'FRECUENCIA_PAGO_INTERES', 6, 0, 1, 'Frecuencia de Pago de Inter�s', 14, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  408, 'ID_TCA_FRECUENCIA_PAGO_INTERES', 7, 0, 1, 'Tipo Frecuencia de Pago de Inter�s', 14, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  409, 'FECHA_INICIO_PAGO_INTERES', 8, 0, 1, 'Fecha de Inicio de Pago de Inter�s', 14, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  410, 'FRECUENCIA_AMORTIZACION', 9, 0, 1, 'Frecuencia de Amortizaci�n', 14, 'NUMBER');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  411, 'ID_TCA_FRECUENCIA_AMORTIZACION', 10, 0, 1, 'Tipo Frecuencia de Amortizaci�n', 14, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  412, 'DESCRIPCION', 11, 1, 0, 'Descripci�n', 14, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  413, 'MORA', 1, 0, 1, 'Mora', 15, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  414, 'PORCENTAJE_COBERTURA', 1, 0, 1, 'Porcentaje de Cobertura', 16, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  415, 'SE_APLICAN_RECURSOS_CONCESION', 1, 0, 1, 'Aplican recursos concesionales', 17, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  416, 'SE_APLICAN_RECURSOS_EXTERNOS', 1, 0, 1, 'Aplican recursos externos', 18, 'ONECHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  417, 'NOMBRE', 1, 0, 1, 'Nombre', 19, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  418, 'TRE_CONTRAPARTES_TERMINO', 1, 0, 1, 'Agente Estructurador', 20, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  419, 'DESCRIPCION', 2, 1, 0, 'Descripci�n', 20, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  420, 'TRE_CONTRAPARTES_TERMINO', 1, 0, 1, 'Beneficiario', 21, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  421, 'TRE_CONTRAPARTES_TERMINO', 1, 0, 1, 'Agente Administrador', 22, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  422, 'TRE_CONTRAPARTES_TERMINO', 1, 0, 1, 'Participante', 23, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  423, 'TRE_CONTRAPARTES_TERMINO', 1, 0, 1, 'Co-Financiador', 24, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  424, 'NOMBRE', 1, 0, 1, 'Nombre del T�rmino', 25, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  425, 'TRE_CONTRAPARTES_TERMINO', 2, 0, 1, 'Nombre de la Contraparte', 25, 'MULTICHOICE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  426, 'TIPO_CONTRAPARTE', 3, 0, 0, 'Tipo de Contraparte', 25, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  427, 'DESCRIPCION', 4, 0, 1, 'Descripci�n', 25, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  428, 'MONTO_MINIMO_DESEMBOLSO', 1, 0, 0, 'Monto m�nimo por desembolso', 26, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  429, 'MONTO_MAXIMO_DESEMBOLSO', 2, 0, 0, 'Monto m�ximo por desembolso', 26, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  430, 'TASA_MINIMA_DESEMBOLSO', 3, 0, 0, 'Tasa m�mina por desembolso', 26, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  431, 'TASA_MAXIMA_DESEMBOLSO', 4, 0, 0, 'Tasa m�xima por desembolso', 26, 'DECIMAL');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  432, 'DESCRIPCION', 5, 1, 0, 'Descripci�n', 26, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  433, 'NOMBRE', 1, 0, 1, 'Nombre', 27, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  434, 'DESCRIPCION', 2, 0, 1, 'Descripci�n', 27, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  435, 'FECHA', 1, 1, 1, 'Fecha de Ingreso', 28, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  436, 'DESCRIPCION', 2, 1, 0, 'Descripci�n', 28, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  437, 'FECHA', 1, 1, 1, 'Fecha de Aprobaci�n', 29, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  438, 'DESCRIPCION', 2, 1, 0, 'Descripci�n', 29, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  439, 'FECHA', 1, 1, 1, 'Fecha de firma del contrato', 30, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  440, 'FECHA', 1, 1, 1, 'Fecha de Vigencia', 31, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  441, 'NOMBRE', 1, 0, 1, 'Nombre', 32, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  442, 'FECHA', 2, 0, 1, 'Fecha', 32, 'DATE');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  443, 'DESCRIPCION', 3, 0, 1, 'Descripci�n', 32, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  444, 'NOMBRE', 1, 0, 1, 'Nombre', 33, 'VARCHAR');
INSERT INTO TCO_ATRIBUTO_TCC (ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, ID_TCA_TERMINO, TIPO_VALOR) VALUES (  445, 'DESCRIPCION', 2, 0, 1, 'Descripci�n', 33, 'VARCHAR');


commit;



INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (30, 'PLANTILLA_REVISION_CONDICION_ASJUR', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION ASJUR', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (31, 'PLANTILLA_REVISION_CONDICION_SEPRI', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION SEPRI', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (32, 'PLANTILLA_REVISION_CONDICION_SUPERVISOR', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION SUPERVISOR', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (33, 'PLANTILLA_REVISION_CONDICION_AED', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION AED', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (34, 'PLANTILLA_REVISION_CONDICION_PCT', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION PCT', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (35, 'PLANTILLA_REVISION_CONDICION_COFI', 'BCIE', 'Estimado [RESPONSABLE], <br/> Se le notifica que las condiciones establecidas  para la operaci�n [OPERACION] han sido revisadas satisfactoriamente.  Espacio de Trabajo de F�nix: [URL_WORKSPACE]<br/> Atte: BCIE.', 'NOTIFICACION REVISION CONDICION COFI', SYSDATE, 1, 'fenix-notificaciones@bcie.org');
INSERT INTO TCA_PLANTILLA_CORREO (ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, FECHA_REGISTRO, BAN_ESTATUS, CC_DESTINATARIOS) VALUES (36, 'PLANTILLA_VALIDACION_CONDICION', 'BCIE', 'Estimado [RESPONSABLE] se le informa que el Reporte de condiciones se genero correctamente.', 'REPORTE DE CONDICIONES', SYSDATE, 1, 'fenix-notificaciones@bcie.org');


commit;
