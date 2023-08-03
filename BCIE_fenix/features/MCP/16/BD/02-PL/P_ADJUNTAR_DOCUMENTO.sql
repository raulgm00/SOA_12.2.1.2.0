CREATE OR REPLACE PROCEDURE P_ADJUNTAR_DOCUMENTO (
   P_ID_TIPO_DOCUMENTO         IN     NUMBER,
   P_COMENTARIO                       VARCHAR2,
   P_FECHA_REGISTRO                   DATE,
   P_BAN_ESTATUS                      NUMBER,
   P_ES_JUSTIFICACION                 NUMBER,
   P_CODIGO_DOCUMENTO                 VARCHAR2,
   P_FECHA_DOCUMENTO                  DATE,
   P_ID_TAREA_BPM                     NUMBER,
   P_ID_TCA_ACCION                    NUMBER,
   P_LOGIN_USUARIO_CREA               VARCHAR2,
   P_NOMBRE_USUARIO_CREA              VARCHAR2,
   P_LOGIN_USUARIO_MODIFICA           VARCHAR2,
   P_NOMBRE_USUARIO_MODIFICA          VARCHAR2,
   P_NUM_SERIE_DOCUMENTO         VARCHAR2,
   P_OPERACION                        NUMBER,
   P_CLIENTE                          NUMBER,
   P_FILENAME                         VARCHAR2,
   P_MIME_TYPE                        VARCHAR2,
   P_CONTENT                   IN     CLOB,
   P_ID_ONBASE                        NUMBER,
   P_STATUS                       OUT NUMBER,
   P_ADJUNTO                      OUT NUMBER)
AS
   V_ID_DOCUMENTO            NUMBER;
   V_ID_DOCUMENTOS           NUMBER;
   V_ID_DOCUMENTOS_CLIENTE   NUMBER;
   V_ID_ADJUNTO              NUMBER;
   l_clob                    BLOB;
     P_MENSAJE             VARCHAR2(100);
     P_CODIGO_RES          NUMBER;
     
BEGIN


   SELECT F_DECODE_BASE64 (P_CONTENT) INTO l_clob FROM DUAL;

   SELECT DOCUMENTO_SEQ.NEXTVAL INTO V_ID_DOCUMENTO FROM DUAL;

   SELECT ADJUNTO_SEQ.NEXTVAL INTO V_ID_ADJUNTO FROM DUAL;

   P_ADJUNTO := V_ID_ADJUNTO;

   INSERT INTO DOCUMENTO (ID_DOCUMENTO,
                          ID_TIPO_DOCUMENTO,
                          COMENTARIO,
                          FECHA_REGISTRO,
                          BAN_ESTATUS,
                          ES_JUSTIFICACION,
                          CODIGO_DOCUMENTO,
                          FECHA_DOCUMENTO,
                          ID_TAREA_BPM,
                          ID_TCA_ACCION,
                          LOGIN_USUARIO_CREA,
                          NOMBRE_USUARIO_CREA,
                          LOGIN_USUARIO_MODIFICA,
                          NOMBRE_USUARIO_MODIFICA, 
                          NUM_SERIE_DOCUMENTO )
       VALUES (V_ID_DOCUMENTO,
               P_ID_TIPO_DOCUMENTO,
               P_COMENTARIO,
               P_FECHA_REGISTRO,
               P_BAN_ESTATUS,
               P_ES_JUSTIFICACION,
               P_CODIGO_DOCUMENTO,
               P_FECHA_DOCUMENTO,
               P_ID_TAREA_BPM,
               P_ID_TCA_ACCION,
               P_LOGIN_USUARIO_CREA,
               P_NOMBRE_USUARIO_CREA,
               P_LOGIN_USUARIO_MODIFICA,
               P_NOMBRE_USUARIO_MODIFICA,
               p_NUM_SERIE_DOCUMENTO);


   IF P_OPERACION IS NOT NULL
   THEN
      SELECT DOCUMENTOS_SEQ.NEXTVAL INTO V_ID_DOCUMENTOS FROM DUAL;

      INSERT INTO DOCUMENTOS (ID_DOCUMENTOS, ID_OPERACION, ID_DOCUMENTO)
          VALUES (V_ID_DOCUMENTOS, P_OPERACION, V_ID_DOCUMENTO);
   ELSE
      SELECT DOCUMENTOS_CLIENTE_SEQ.NEXTVAL
        INTO V_ID_DOCUMENTOS_CLIENTE
        FROM DUAL;

      INSERT INTO DOCUMENTOS_CLIENTE (ID_DOCUMENTOS,
                                      ID_CLIENTE,
                                      ID_DOCUMENTO)
          VALUES (V_ID_DOCUMENTOS_CLIENTE, P_CLIENTE, V_ID_DOCUMENTO);
   END IF;

   INSERT INTO ADJUNTO (ID_ADJUNTO,
                        FILENAME,
                        MIME_TYPE,
                        CONTENT,
                        FECHA_REGISTRO,
                        ID_ONBASE,
                        ID_DOCUMENTO)
       VALUES (V_ID_ADJUNTO,
               P_FILENAME,
               P_MIME_TYPE,
               l_clob,
               P_FECHA_REGISTRO,
               P_ID_ONBASE,
               V_ID_DOCUMENTO);

   COMMIT;

   P_STATUS := 1;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
   
        P_CODIGO_RES := SQLCODE;

      P_MENSAJE := SQLERRM;
      DBMS_OUTPUT.PUT_LINE (SQLERRM);
      
      DBMS_OUTPUT.PUT_LINE (
         'Se ejecuta cuando ocurre una excepcion de tipo NO_DATA_FOUND ');
      P_STATUS := 0;
   WHEN OTHERS
  
   THEN
   
         P_CODIGO_RES := SQLCODE;

      P_MENSAJE := SQLERRM;
      DBMS_OUTPUT.PUT_LINE (SQLERRM);
      
      IF P_OPERACION IS NOT NULL
      THEN
         DELETE FROM DOCUMENTOS
               WHERE ID_DOCUMENTOS = V_ID_DOCUMENTOS;
      ELSE
         DELETE FROM DOCUMENTOS_CLIENTE
               WHERE ID_DOCUMENTOS = V_ID_DOCUMENTOS_CLIENTE;
      END IF;

      DELETE FROM DOCUMENTO
            WHERE ID_DOCUMENTO = V_ID_DOCUMENTO;

      DELETE FROM ADJUNTO
            WHERE ID_ADJUNTO = V_ID_ADJUNTO;

      P_STATUS := 0;
      DBMS_OUTPUT.PUT_LINE ('Error ');
      
            INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'P_ADJUNTAR_DOCUMENTO',
                  P_MENSAJE,
                  SYSDATE);

      COMMIT;
END;
/
