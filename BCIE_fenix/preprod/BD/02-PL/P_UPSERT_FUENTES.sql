create or replace TYPE       T_FUENTE FORCE AS OBJECT
 (
 ID                 NUMBER(12) ,
  ID_LINEA_CREDITO   NUMBER(12),
  ID_CONTRATO        NUMBER(12),
  ID_LINEA_PASIVA    VARCHAR2(32),
  DESCRIPCION        VARCHAR2(256),
  PORCENTAJE         NUMBER(7,4),
  FECHA_OBTENIDO     DATE,
  MONTO_ASIGNADO     NUMBER(14,2),
  NUMERO_ASIGNACION  NUMBER(12),
  COMENTARIO         VARCHAR2(1024),
  FECHA_REGISTRO     DATE,
  BAN_ESTATUS        NUMBER(1)
  );
  /
create or replace TYPE       T_LISTA_FUENTE AS TABLE  OF T_FUENTE;
/

create or replace PROCEDURE       P_UPSERT_FUENTES (
   P_FUENTES      IN     T_LISTA_FUENTE,
   P_CODIGO_RES      OUT NUMBER,
   P_MENSAJE         OUT VARCHAR2)
IS
   V_DESCRIPCION     VARCHAR2 (293);
   V_ID_FUENTE       NUMBER;
   V_FUENTE_ACTUAL   FUENTE%ROWTYPE;
BEGIN
   FOR IND IN 1 .. P_FUENTES.COUNT
   LOOP
      IF P_FUENTES (IND).ID IS NULL
      THEN
         IF P_FUENTES (IND).DESCRIPCION = ''
         THEN
            SELECT DESCRIPCION
              INTO V_DESCRIPCION
              FROM VCA_FUENTE
             WHERE ID = P_FUENTES (IND).ID_LINEA_PASIVA;
         ELSE
            V_DESCRIPCION := P_FUENTES (IND).DESCRIPCION;
         END IF;

         SELECT FUENTE_SEQ.NEXTVAL INTO V_ID_FUENTE FROM DUAL;

         INSERT INTO FUENTE (ID,
                             ID_LINEA_CREDITO,
                             ID_CONTRATO,
                             ID_LINEA_PASIVA,
                             DESCRIPCION,
                             PORCENTAJE,
                             FECHA_OBTENIDO,
                             MONTO_ASIGNADO,
                             NUMERO_ASIGNACION,
                             COMENTARIO,
                             FECHA_REGISTRO,
                             BAN_ESTATUS)
             VALUES (V_ID_FUENTE,
                     P_FUENTES (IND).ID_LINEA_CREDITO,
                     P_FUENTES (IND).ID_CONTRATO,
                     P_FUENTES (IND).ID_LINEA_PASIVA,
                     V_DESCRIPCION,
                     P_FUENTES (IND).PORCENTAJE,
                     P_FUENTES (IND).FECHA_OBTENIDO,
                     P_FUENTES (IND).MONTO_ASIGNADO,
                     P_FUENTES (IND).NUMERO_ASIGNACION,
                     P_FUENTES (IND).COMENTARIO,
                     SYSDATE,
                     P_FUENTES (IND).BAN_ESTATUS);

         P_CODIGO_RES := 0;
         P_MENSAJE := 'insert exitoso ' || ' ' || V_ID_FUENTE;
      ELSE
         SELECT 
         ID,
                ID_LINEA_CREDITO,
                ID_CONTRATO,
                ID_LINEA_PASIVA,
                DESCRIPCION,
                PORCENTAJE,
                FECHA_OBTENIDO,
                MONTO_ASIGNADO,
                NUMERO_ASIGNACION,
                COMENTARIO,
                FECHA_REGISTRO,
                BAN_ESTATUS
           INTO V_FUENTE_ACTUAL
           FROM FUENTE
          WHERE ID=P_FUENTES (IND).ID;

         UPDATE FUENTE
            SET ID_LINEA_CREDITO =
                   NVL (P_FUENTES (IND).ID_LINEA_CREDITO,V_FUENTE_ACTUAL.ID_LINEA_CREDITO),
                ID_CONTRATO = 
                   NVL (P_FUENTES (IND).ID_CONTRATO,V_FUENTE_ACTUAL.ID_CONTRATO),
                ID_LINEA_PASIVA = 
                   NVL (P_FUENTES (IND).ID_LINEA_PASIVA,V_FUENTE_ACTUAL.ID_LINEA_PASIVA),
                DESCRIPCION = 
                   NVL (P_FUENTES (IND).DESCRIPCION,V_FUENTE_ACTUAL.DESCRIPCION),
                PORCENTAJE =
                   NVL (P_FUENTES (IND).PORCENTAJE,V_FUENTE_ACTUAL.PORCENTAJE),
                FECHA_OBTENIDO =
                   NVL (P_FUENTES (IND).FECHA_OBTENIDO,V_FUENTE_ACTUAL.FECHA_OBTENIDO),
                MONTO_ASIGNADO =
                   NVL (P_FUENTES (IND).MONTO_ASIGNADO,V_FUENTE_ACTUAL.MONTO_ASIGNADO),
                NUMERO_ASIGNACION =
                   NVL (P_FUENTES (IND).NUMERO_ASIGNACION,V_FUENTE_ACTUAL.NUMERO_ASIGNACION),
                COMENTARIO =
                   NVL (P_FUENTES (IND).COMENTARIO,V_FUENTE_ACTUAL.COMENTARIO),
                BAN_ESTATUS =
                   NVL (P_FUENTES (IND).BAN_ESTATUS,V_FUENTE_ACTUAL.BAN_ESTATUS)
          WHERE ID = P_FUENTES (IND).ID;

         P_CODIGO_RES := 0;
         P_MENSAJE := 'actualizacion exitosa';
      END IF;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      P_CODIGO_RES := 1;
      P_MENSAJE := SQLERRM;
END;
  
  
  /
  