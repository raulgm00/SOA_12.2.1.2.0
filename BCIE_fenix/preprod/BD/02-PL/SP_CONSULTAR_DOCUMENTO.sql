
CREATE OR REPLACE PROCEDURE FENIX.SP_CONSULTAR_DOCUMENTO (
   P_ID_CLIENTE     IN     NUMBER,
   P_ID_OPERACION   IN     NUMBER,
   P_CONTENT        IN     NUMBER,
   P_ID_FLUJO       IN     NUMBER,
   P_CODIGO_RES        OUT NUMBER,
   P_MENSAJE           OUT VARCHAR2,
   RECORDSET           OUT SYS_REFCURSOR)
AS
BEGIN
   IF P_ID_CLIENTE IS NOT NULL
   THEN
      CASE P_CONTENT
         WHEN 1
         THEN
            OPEN RECORDSET FOR
               SELECT DOC.ID_DOCUMENTO,
                      DC.ID_CLIENTE,
                      DOC.ID_TIPO_DOCUMENTO,
                      DOC.COMENTARIO,
                      DOC.FECHA_REGISTRO,
                      DOC.BAN_ESTATUS,
                      DOC.ES_JUSTIFICACION,
                      DOC.CODIGO_DOCUMENTO,
                      DOC.FECHA_DOCUMENTO,
                      DOC.ID_TAREA_BPM,
                      DOC.ID_TCA_ACCION,
                      DOC.LOGIN_USUARIO_CREA,
                      DOC.NOMBRE_USUARIO_CREA,
                      DOC.LOGIN_USUARIO_MODIFICA,
                      DOC.NOMBRE_USUARIO_MODIFICA,
                      DOC.NUM_SERIE_DOCUMENTO,
                      ADJ.FILENAME,
                      ADJ.MIME_TYPE,
                      ADJ.ID_ADJUNTO,
                      ADJ.CONTENT,
                      ADJ.ID_ONBASE
                 FROM DOCUMENTO DOC
                      JOIN DOCUMENTOS_CLIENTE DC
                         ON DC.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                      JOIN ADJUNTO ADJ
                         ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                WHERE     DC.ID_CLIENTE = P_ID_CLIENTE
                      AND DOC.BAN_ESTATUS = 1
                              AND NVL(DOC.NUM_SERIE_DOCUMENTO, 0) = NVL(NVL (P_ID_FLUJO, DOC.NUM_SERIE_DOCUMENTO),0);
         WHEN 0
         THEN
            OPEN RECORDSET FOR
               SELECT DOC.ID_DOCUMENTO,
                      DC.ID_CLIENTE,
                      DOC.ID_TIPO_DOCUMENTO,
                      DOC.COMENTARIO,
                      DOC.FECHA_REGISTRO,
                      DOC.BAN_ESTATUS,
                      DOC.ES_JUSTIFICACION,
                      DOC.CODIGO_DOCUMENTO,
                      DOC.FECHA_DOCUMENTO,
                      DOC.ID_TAREA_BPM,
                      DOC.ID_TCA_ACCION,
                      DOC.LOGIN_USUARIO_CREA,
                      DOC.NOMBRE_USUARIO_CREA,
                      DOC.LOGIN_USUARIO_MODIFICA,
                      DOC.NOMBRE_USUARIO_MODIFICA,
                      DOC.NUM_SERIE_DOCUMENTO,
                      ADJ.FILENAME,
                      ADJ.MIME_TYPE,
                      ADJ.ID_ADJUNTO,
                      ADJ.ID_ONBASE
                 FROM DOCUMENTO DOC
                      JOIN DOCUMENTOS_CLIENTE DC
                         ON DC.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                      JOIN ADJUNTO ADJ
                         ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                WHERE     DC.ID_CLIENTE = P_ID_CLIENTE
                      AND ADJ.ID_ONBASE IS NULL
                      AND DOC.BAN_ESTATUS = 1
                            AND NVL(DOC.NUM_SERIE_DOCUMENTO, 0) = NVL(NVL (P_ID_FLUJO, DOC.NUM_SERIE_DOCUMENTO),0);
      END CASE;
   ELSE
      CASE P_CONTENT
         WHEN 1
         THEN
            OPEN RECORDSET FOR
               SELECT DOC.ID_DOCUMENTO,
                      DOCS.ID_OPERACION,
                      DOC.ID_TIPO_DOCUMENTO,
                      DOC.COMENTARIO,
                      DOC.FECHA_REGISTRO,
                      DOC.BAN_ESTATUS,
                      DOC.ES_JUSTIFICACION,
                      DOC.CODIGO_DOCUMENTO,
                      DOC.FECHA_DOCUMENTO,
                      DOC.ID_TAREA_BPM,
                      DOC.ID_TCA_ACCION,
                      DOC.LOGIN_USUARIO_CREA,
                      DOC.NOMBRE_USUARIO_CREA,
                      DOC.LOGIN_USUARIO_MODIFICA,
                      DOC.NOMBRE_USUARIO_MODIFICA,
                      DOC.NUM_SERIE_DOCUMENTO,
                      ADJ.FILENAME,
                      ADJ.MIME_TYPE,
                      ADJ.ID_ADJUNTO,
                      ADJ.CONTENT,
                      ADJ.ID_ONBASE
                 FROM DOCUMENTO DOC
                      JOIN DOCUMENTOS DOCS
                         ON DOCS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                      JOIN ADJUNTO ADJ
                         ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                WHERE     DOCS.ID_OPERACION = P_ID_OPERACION
                      AND ADJ.ID_ONBASE IS NULL
                      AND DOC.BAN_ESTATUS = 1
                     AND NVL(DOC.NUM_SERIE_DOCUMENTO, 0) = NVL(NVL (P_ID_FLUJO, DOC.NUM_SERIE_DOCUMENTO),0);
         WHEN 0
         THEN
            OPEN RECORDSET FOR
               SELECT DOC.ID_DOCUMENTO,
                      DOCS.ID_OPERACION,
                      DOC.ID_TIPO_DOCUMENTO,
                      DOC.COMENTARIO,
                      DOC.FECHA_REGISTRO,
                      DOC.BAN_ESTATUS,
                      DOC.ES_JUSTIFICACION,
                      DOC.CODIGO_DOCUMENTO,
                      DOC.FECHA_DOCUMENTO,
                      DOC.ID_TAREA_BPM,
                      DOC.ID_TCA_ACCION,
                      DOC.LOGIN_USUARIO_CREA,
                      DOC.NOMBRE_USUARIO_CREA,
                      DOC.LOGIN_USUARIO_MODIFICA,
                      DOC.NOMBRE_USUARIO_MODIFICA,
                      DOC.NUM_SERIE_DOCUMENTO,
                      ADJ.FILENAME,
                      ADJ.MIME_TYPE,
                      ADJ.ID_ADJUNTO,
                      ADJ.ID_ONBASE
                 FROM DOCUMENTO DOC
                      JOIN DOCUMENTOS DOCS
                         ON DOCS.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                      JOIN ADJUNTO ADJ
                         ON ADJ.ID_DOCUMENTO = DOC.ID_DOCUMENTO
                WHERE     DOCS.ID_OPERACION = P_ID_OPERACION
                      AND ADJ.ID_ONBASE IS NULL
                      AND DOC.BAN_ESTATUS = 1
                    AND NVL(DOC.NUM_SERIE_DOCUMENTO, 0) = NVL(NVL (P_ID_FLUJO, DOC.NUM_SERIE_DOCUMENTO),0);
      END CASE;
   END IF;

   P_CODIGO_RES := 0;
EXCEPTION
   WHEN OTHERS
   THEN
      P_CODIGO_RES := SQLCODE;

      P_MENSAJE := SQLERRM;
END SP_CONSULTAR_DOCUMENTO;
/