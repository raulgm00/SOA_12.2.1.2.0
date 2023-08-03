create or replace 
PROCEDURE SP_INSERTAR_DECLARACION (
   P_ID_OPERACION     IN     NUMBER,
   P_ID_DECLARACION   IN     NUMBER,
   P_FECHA_REGISTRO   IN       DATE
 )
AS
   c_id_operacion                   NUMBER;
   c_id_declaracion                 NUMBER;
   c_id_tre_declaracion_operacion   NUMBER;
   cant                             NUMBER;
BEGIN
   /*
     *
     * CONSULTA A LA TABLA DE DECLARACION, SI EXISTE EL REGISTRO, ACTUALICE LOS CAMPOS
     * SI NO, SINCRONICE EN LA TABLA LOS DATOS DE LA VISTA
     *
   */


   SELECT COUNT (ID)
     INTO c_id_declaracion
     FROM DECLARACION
    WHERE ID_DECLARACION = P_ID_DECLARACION;

   IF c_id_declaracion = 0
   THEN
      SELECT DECLARACION_SEQ.NEXTVAL INTO c_id_declaracion FROM DUAL;

      INSERT INTO DECLARACION (ID,
                               ID_DECLARACION,
                               ESTADO_DECLARACION,
                               DESCRIPCION_DECLARACION,
                               ESTADO_BUSQUEDA_CONTRAPARTES,
                               DESCRIPCION_BUSQUEDA,
                               NIVEL_RIESGO,
                               DESCRIPCION_RIESGO,
                               FECHA_REGISTRO,
                               FECHA_FIRMA_DECLARACION,
                               FECHA_VENCIMIENTO,
                               COMENTARIO_DECLARACION,
                               COMENTARIO_BUSQUEDA,
                               SE_ELEVA_OTRA_INSTANCIA,
                               TIPO_ORIGEN,
                               ES_SOLO_LECTURA,
                               BAN_ESTATUS)
         (SELECT c_id_declaracion,
                 CODIGO_DECLARACION,
                 CODIGO_ESTADO_DECLARACION,
                 ESTADO_DECLARACION,
                 CODIGO_ESTADO_BUSQUEDA,
                 ESTADO_BUSQUEDA,
                 CODIGO_TIPO_RIESGO,
                 NIVEL_RIESGO,
                 p_fecha_registro,
                 FECHA_FIRMA,
                 FECHA_VENCIMIENTO,
                 JUSTIFICACION_DECLARACION,
                 JUSTIFICACION_BUSQUEDA,
                 0,
                 'ASOCIACION',
                 1,
                 1
            FROM MIDDLE.LA_V_DECLARACION_JURADA
           WHERE CODIGO_REFERENCIA = P_ID_OPERACION
                 AND CODIGO_DECLARACION = P_ID_DECLARACION);
                 
            DBMS_OUTPUT.PUT_LINE( 'insert exitoso ' || ' ' || c_id_declaracion);

   ELSE
      SELECT ID
        INTO c_id_declaracion
        FROM DECLARACION
       WHERE ID_DECLARACION = P_ID_DECLARACION;

      UPDATE DECLARACION
         SET
             (fecha_vencimiento,
             nivel_riesgo,
             descripcion_riesgo,
             ESTADO_DECLARACION,
             DESCRIPCION_DECLARACION,
             ESTADO_BUSQUEDA_CONTRAPARTES,
             DESCRIPCION_BUSQUEDA,
             COMENTARIO_DECLARACION,
             COMENTARIO_BUSQUEDA
             ) =
                (SELECT FECHA_VENCIMIENTO,
                        CODIGO_TIPO_RIESGO,
                        NIVEL_RIESGO,
                        CODIGO_ESTADO_DECLARACION,
                        ESTADO_DECLARACION,
                        CODIGO_ESTADO_BUSQUEDA,
                        ESTADO_BUSQUEDA,
                        JUSTIFICACION_DECLARACION,
                        JUSTIFICACION_BUSQUEDA
                   FROM MIDDLE.LA_V_DECLARACION_JURADA
                  WHERE CODIGO_REFERENCIA = P_ID_OPERACION
                        AND CODIGO_DECLARACION = P_ID_DECLARACION)
       WHERE ID = c_id_declaracion;

   DBMS_OUTPUT.PUT_LINE('actualizacion exitosa');
   END IF;

   /*
     *  CONSULTA LA TABLA DE TRE_DECLARACION_OPERACION, SI NO EXISTE REGISTRO
     *  INSERTA EN LA TABLA
     *
   */

   SELECT COUNT (ID_OPERACION)
     INTO c_id_operacion
     FROM TRE_DECLARACION_OPERACION
    WHERE ID_OPERACION = P_ID_OPERACION AND ID_DECLARACION = c_id_declaracion;



   IF c_id_operacion = 0
   THEN
      SELECT TRE_DECLARACION_OPERACION_SEQ.NEXTVAL
        INTO c_id_tre_declaracion_operacion
        FROM DUAL;

      INSERT INTO TRE_DECLARACION_OPERACION (ID,
                                             ID_OPERACION,
                                             ID_DECLARACION,
                                             ES_VIGENTE)
          VALUES (c_id_tre_declaracion_operacion,
                  P_ID_OPERACION,
                  c_id_declaracion,
                  1);
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
       DBMS_OUTPUT.PUT_LINE( 'Error en el procedimiento Insertar Declaración!');
END;