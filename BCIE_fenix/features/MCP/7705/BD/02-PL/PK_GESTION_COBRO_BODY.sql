
CREATE OR REPLACE PACKAGE BODY PK_GESTION_COBRO
IS
   PROCEDURE PR_GENERAR_AVISO_COBRO (
      P_FECHAINICIO           IN     DATE,
      P_FECHAFIN              IN     DATE,
      P_ESPUBLICO             IN     VARCHAR2,
      P_TIPOAVISO             IN     VARCHAR2,
      P_LINEA                 IN     VARCHAR2,
      P_CLIENTE               IN     VARCHAR2,
      P_MONEDA                IN     VARCHAR2,
      P_PAIS                  IN     VARCHAR2,
      P_SECTORINSTITUCIONAL   IN     VARCHAR2,
      P_PERIODICIDAD          IN     VARCHAR2,
      P_TIPOSALDO             IN     VARCHAR2,
      P_FONDOS                IN     VARCHAR2,
      P_CODIGOOPERACION       IN     VARCHAR2:= NULL,
      P_USUARIOCREADOR        IN     VARCHAR2,
      P_IDAVISO                  OUT NUMBER,
      P_MENSAJEERROR             OUT VARCHAR2)
   IS
      V_ES_DETALLADO   NUMBER (1);
   BEGIN
      MIDDLE.PKG_AVISO_VENCIMIENTO.PRC_GENERAR_AVISO (P_FECHAINICIO,
                                                      P_FECHAFIN,
                                                      P_ESPUBLICO,
                                                      P_TIPOAVISO,
                                                      P_LINEA,
                                                      P_CLIENTE,
                                                      P_MONEDA,
                                                      P_PAIS,
                                                      P_SECTORINSTITUCIONAL,
                                                      P_PERIODICIDAD,
                                                      P_TIPOSALDO,
                                                      P_FONDOS,
                                                      P_CODIGOOPERACION,
                                                      P_USUARIOCREADOR,
                                                      P_IDAVISO,
                                                      P_MENSAJEERROR);


      IF P_ESPUBLICO = 'N'
      THEN
         V_ES_DETALLADO := 1;
      ELSIF P_ESPUBLICO = 'S'
      THEN
         V_ES_DETALLADO := 0;
      END IF;



      IF P_IDAVISO IS NOT NULL AND P_MENSAJEERROR IS NULL
      THEN
         INSERT INTO LOTE_GESTION_COBRO (ID,
                                               ID_EJECUCION,
                                               ESTADO_EJECUCION,
                                               ID_TCA_MENSAJE_ERROR,
                                               FECHA_REGISTRO,
                                               BAN_ESTATUS,
                                               TIPO_LOTE,
                                               ES_RECIBO_DETALLADO)
             VALUES (LOTE_GESTION_COBRO_SEQ.NEXTVAL,
                     P_IDAVISO,
                     'PROCESO',
                     NULL,
                     SYSDATE,
                     1,
                     'GESTION',
                     V_ES_DETALLADO);
                     
      ELSIF P_IDAVISO IS NOT NULL AND P_MENSAJEERROR IS NOT NULL
      THEN
         INSERT INTO LOTE_GESTION_COBRO (ID,
                                               ID_EJECUCION,
                                               ESTADO_EJECUCION,
                                               ID_TCA_MENSAJE_ERROR,
                                               FECHA_REGISTRO,
                                               BAN_ESTATUS,
                                               TIPO_LOTE,
                                               ES_RECIBO_DETALLADO)
             VALUES (LOTE_GESTION_COBRO_SEQ.NEXTVAL,
                     P_IDAVISO,
                     'ERROR',
                     3,
                     SYSDATE,
                     1,
                     'GESTION',
                     V_ES_DETALLADO);
      ELSE
         INSERT INTO LOTE_GESTION_COBRO (ID,
                                               ID_EJECUCION,
                                               ESTADO_EJECUCION,
                                               ID_TCA_MENSAJE_ERROR,
                                               FECHA_REGISTRO,
                                               BAN_ESTATUS,
                                               TIPO_LOTE,
                                               ES_RECIBO_DETALLADO)
             VALUES (LOTE_GESTION_COBRO_SEQ.NEXTVAL,
                     0,
                     'ERROR',
                     3,
                     SYSDATE,
                     1,
                     'GESTION',
                     V_ES_DETALLADO);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT INTO LOTE_GESTION_COBRO (ID,
                                               ID_EJECUCION,
                                               ESTADO_EJECUCION,
                                               ID_TCA_MENSAJE_ERROR,
                                               FECHA_REGISTRO,
                                               BAN_ESTATUS,
                                               TIPO_LOTE,
                                               ES_RECIBO_DETALLADO)
             VALUES (LOTE_GESTION_COBRO_SEQ.NEXTVAL,
                     0,
                     'ERROR',
                     3,
                     SYSDATE,
                     1,
                     'GESTION',
                     V_ES_DETALLADO);

         P_MENSAJEERROR := 'OCURRIO UNA EXCEPCION ' + SQLERRM;
   END PR_GENERAR_AVISO_COBRO;

   PROCEDURE PR_OBTENER_AVISO_COBRO (P_IDAVISO   IN     NUMBER,
                                     XML_AVISO      OUT XMLTYPE)
   IS
   BEGIN
      XML_AVISO := MIDDLE.PKG_AVISO_VENCIMIENTO.GETAVISO (P_IDAVISO);
   END PR_OBTENER_AVISO_COBRO;
END PK_GESTION_COBRO;
/