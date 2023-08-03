create or replace PROCEDURE SP_CONSULTAR_ADJUNTO (
   PID_ADJUNTO   IN     INTEGER,
   RECORDSET        OUT SYS_REFCURSOR)
AS
   v_operacion   NUMBER;
   v_cliente NUMBER;
   
   P_CODIGO_RES NUMBER;
   
   P_MENSAJE VARCHAR2(100);
   
BEGIN
   SELECT COUNT ( * )
     INTO v_operacion
     FROM DOCUMENTOS Documentos
          JOIN DOCUMENTO Documento
             ON (Documento.ID_DOCUMENTO = Documentos.ID_DOCUMENTO)
          JOIN ADJUNTO Adjunto
             ON (Adjunto.ID_DOCUMENTO = Documento.ID_DOCUMENTO)
    WHERE Adjunto.ID_ADJUNTO = PID_ADJUNTO;
    
    
           
    SELECT count(*)
        INTO v_cliente
         FROM     DOCUMENTOS_CLIENTE DOCUMENTOS_CLIENTE
         JOIN DOCUMENTO DOCUMENTO
          ON (DOCUMENTO.ID_DOCUMENTO = DOCUMENTOS_CLIENTE.ID_DOCUMENTO)
          JOIN ADJUNTO ADJUNTO
          ON (ADJUNTO.ID_DOCUMENTO = DOCUMENTO.ID_DOCUMENTO)
         WHERE  Adjunto.ID_ADJUNTO = PID_ADJUNTO;


   IF v_operacion = 1
   THEN
      OPEN RECORDSET FOR
         SELECT CatPaises.COD PAIS,
                CatPaises.DESCRIPCION_CORTA PAIS_DESC,
                CatDoc.Descripcion,
                Adjunto.MIME_TYPE,
                CatTipoOper.COD_EXTERNO,
                Operacion.ACTIVIDAD_ECONOMICA,
                Operacion.ID_OPERACION,
                Clientes.ID_FLEXCUBE,
                Clientes.RAZON_SOCIAL,
                Operacion.NOMBRE,
                Documento.FECHA_REGISTRO,
                Documento.ID_DOCUMENTO,
                Adjunto.FILENAME,
                CASE
                   WHEN Adjunto.CONTENT IS NOT NULL
                   THEN
                     base64encoder (Adjunto.CONTENT)
                   ELSE
                     NULL
                END
                   AS CONTENIDO,
                ConfTipoOnBase.ID ID_CATTIPOONBASE,
                ConfTipoOnBase.ID_TIPO_ON_BASE ID_TIPO_ON_BASE,
                accionDoc.ID ACCION,
                Documento.COMENTARIO,
                ADJUNTO.ID_ONBASE,
                Adjunto.ID_ADJUNTO,
                CATDOC.DESCRIPCION TIPO_DOCUMENTO,
                TCAPBPM.DESCRIPCION AS PROCESO_BPM,
                TARBPM.ID as ID_TAREA
           FROM OPERACION Operacion
                JOIN CLIENTES Clientes
                   ON (Operacion.ID_CLIENTE = Clientes.ID_CLIENTE)
                JOIN CAT_PAISES CatPaises
                   ON (Clientes.PAIS = CatPaises.ID)
                JOIN CAT_OFICINA CatOficina
                   ON (Clientes.OFICINA = CatOficina.ID)
                JOIN CAT_ACTIVIDAD_ECONOMICA CatActividadEconomica
                   ON (Operacion.ACTIVIDAD_ECONOMICA =
                          CatActividadEconomica.ID)
                JOIN DOCUMENTOS Documentos
                   ON (Documentos.ID_OPERACION = Operacion.ID_OPERACION)
                JOIN DOCUMENTO Documento
                   ON (Documento.ID_DOCUMENTO = Documentos.ID_DOCUMENTO)
                JOIN ADJUNTO Adjunto
                   ON (Adjunto.ID_DOCUMENTO = Documento.ID_DOCUMENTO)
                JOIN TCA_DOCUMENTO CatDoc
                   ON (CatDoc.ID = Documento.ID_TIPO_DOCUMENTO)
                JOIN CAT_PRODUCTO CATPROD 
                   ON (Operacion.ID_PRODUCTO=CATPROD.ID)
                JOIN TCA_TIPO_OPERACION CatTipoOper
                   ON (CatTipoOper.ID =
                          CATPROD.ID_TIPO_OPERACION)
                JOIN TCO_CONFIGURAR_TIPO_ONBASE ConfTipoOnBase
                   ON (ConfTipoOnBase.ID_TCA_DOCUMENTO = CatDoc.ID
                       AND ConfTipoOnBase.ID_CAT_PAISES = CatPaises.ID
                       AND CatTipoOper.ID = ConfTipoOnBase.ID_TCA_TIPO_OPERACION
                       AND ConfTipoOnBase.BAN_STATUS =1)
                JOIN TCA_ACCION_DOCUMENTO accionDoc
                   ON (accionDoc.ID = Documento.ID_TCA_ACCION)
                JOIN TCA_TAREA_BPM TARBPM
                   ON (TARBPM.ID = DOCUMENTO.ID_TAREA_BPM)
                JOIN TCA_PROCESO_BPM TCAPBPM
                   ON (TCAPBPM.ID = TARBPM.ID_PROCESO_BPM)
          WHERE Adjunto.ID_ADJUNTO = PID_ADJUNTO;
   END IF;
   
   
   
   
      IF v_cliente = 1
   THEN
      OPEN RECORDSET FOR
   SELECT  CATPAISES.COD PAIS,CATPAISES.DESCRIPCION_CORTA PAIS_DESC,
   CATDOC.DESCRIPCION, 
  ADJUNTO.MIME_TYPE,
--  CATTIPOOPER.COD_EXTERNO,
  CLIENTES.ID_FLEXCUBE,
  CLIENTES.RAZON_SOCIAL,
DOCUMENTO.FECHA_REGISTRO ,
 DOCUMENTO.ID_DOCUMENTO ,
  ADJUNTO.FILENAME,
 case  when Adjunto.CONTENT is not null then 
  base64encoder(Adjunto.CONTENT) 
  else  null end  as CONTENIDO,
  CONFTIPOONBASE.ID ID_CATTIPOONBASE,
    CONFTIPOONBASE.ID_TIPO_ON_BASE ID_TIPO_ON_BASE,
  ACCIONDOC.ID ACCION,
  DOCUMENTO.COMENTARIO,
  ADJUNTO.ID_ONBASE,
  ADJUNTO.ID_ADJUNTO,
  CATDOC.DESCRIPCION TIPO_DOCUMENTO,
  TCAPBPM.DESCRIPCION AS PROCESO_BPM,
  TARBPM.ID as ID_TAREA
  FROM  CLIENTES CLIENTES
         JOIN CAT_PAISES CATPAISES
          ON (CLIENTES.PAIS = CATPAISES.ID)
       JOIN CAT_OFICINA CATOFICINA
          ON (CLIENTES.OFICINA = CATOFICINA.ID)
        JOIN DOCUMENTOS_CLIENTE DOCUMENTOS_CLIENTE
          ON (DOCUMENTOS_CLIENTE.ID_CLIENTE = CLIENTES.ID_CLIENTE)
        JOIN DOCUMENTO DOCUMENTO
          ON (DOCUMENTO.ID_DOCUMENTO = DOCUMENTOS_CLIENTE.ID_DOCUMENTO)
        JOIN ADJUNTO ADJUNTO
          ON (ADJUNTO.ID_DOCUMENTO = DOCUMENTO.ID_DOCUMENTO)
        JOIN TCA_DOCUMENTO CATDOC
          ON (CATDOC.ID = DOCUMENTO.ID_TIPO_DOCUMENTO )
        JOIN TCO_CONFIGURAR_TIPO_ONBASE CONFTIPOONBASE
          ON (    CONFTIPOONBASE.ID_TCA_DOCUMENTO = CATDOC.ID
              AND CONFTIPOONBASE.ID_CAT_PAISES = CATPAISES.ID
              AND CONFTIPOONBASE.BAN_STATUS =1)
        JOIN TCA_ACCION_DOCUMENTO ACCIONDOC
          ON (ACCIONDOC.ID = DOCUMENTO.ID_TCA_ACCION)
          JOIN TCA_TAREA_BPM TARBPM
         ON (TARBPM.ID   = DOCUMENTO.ID_TAREA_BPM     )
         JOIN TCA_PROCESO_BPM TCAPBPM 
         ON (TCAPBPM.ID=TARBPM.ID_PROCESO_BPM)
WHERE ADJUNTO.ID_ADJUNTO = PID_ADJUNTO AND rownum=1;

END IF;



EXCEPTION
WHEN OTHERS THEN

P_CODIGO_RES := SQLCODE;

P_MENSAJE := SQLERRM;

  INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,   NOMBRE_INSUMO,      DESCRIPCION_ERROR,        FECHA_REGISTRO)
       VALUES ('SP',                'SP_CONSULTAR_ADJUNTO',                P_MENSAJE,                SYSDATE);

   COMMIT;

END SP_CONSULTAR_ADJUNTO;