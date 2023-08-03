create or replace TYPE       T_DETALLE_TRANSACCION FORCE AS OBJECT
 (
 ID                 NUMBER(12) ,
  ID_TRANSACCION   NUMBER(12),
  AGRUPADOR        NUMBER(12),
  PARAMETRO_NOMBRE    VARCHAR2(50),
  PARAMETRO_VALOR        VARCHAR2(256)
  );
  
  /
  
create or replace TYPE       T_LISTA_DETALLETRANS AS TABLE  OF T_DETALLE_TRANSACCION;
  /



create or replace PROCEDURE       SP_UPSERT_TRAN_DES (
   P_ID_TRANSACCIONC   IN     NUMBER,
   P_ID_CONT_DES       IN     NUMBER,
   P_PLATAFORMA        IN     VARCHAR2,
   P_TRANSACCION       IN     VARCHAR2,
   P_BAN_ESTATUS       IN     NUMBER,
   P_DETALLETR         IN  T_LISTA_DETALLETRANS,
   P_CODIGO_RES           OUT NUMBER,
   P_MENSAJE              OUT VARCHAR2,
   P_DETALLETR_OUT     OUT T_LISTA_DETALLETRANS)
IS
   v_count_D   NUMBER;
   V_id_transaccion number ;
   V_id_detalle number ;
   V_id_Sqac number ;
   V_id_cont_des number ;
    v_index  number := 1;
   v_plataforma varchar2(50); 
   v_transaccion varchar2(50);
   v_fecha_registro date;
   v_ban_estatus number;
       
BEGIN

   P_DETALLETR_OUT := T_LISTA_DETALLETRANS();
   P_DETALLETR_OUT.EXTEND(P_DETALLETR.COUNT);

        IF (P_ID_TRANSACCIONC IS NULL) THEN

        SELECT TRANSACCION_CONTRATO_SEQ.NEXTVAL INTO V_id_transaccion FROM DUAL;

        INSERT INTO TRANSACCION_CONTRATO
          VALUES (V_id_transaccion,
                  P_ID_CONT_DES,
                  P_PLATAFORMA,
                  P_TRANSACCION,
                  SYSDATE,
                  P_BAN_ESTATUS);

        ELSE
         V_id_transaccion:= P_ID_TRANSACCIONC;

            SELECT ID, ID_CONTRATO_DESEMBOLSO, PLATAFORMA, TRANSACCION, FECHA_REGISTRO, BAN_ESTATUS
            INTO  V_id_transaccion,V_id_cont_des, v_plataforma, v_transaccion,v_fecha_registro,  v_ban_estatus
            FROM TRANSACCION_CONTRATO WHERE ID = V_id_transaccion;

            IF (P_ID_CONT_DES IS NOT NULL) THEN
            V_id_cont_des:= P_ID_CONT_DES;
            END IF;

             IF(P_PLATAFORMA IS NOT NULL) THEN
             v_plataforma:= P_PLATAFORMA;
            END IF;

            IF(P_TRANSACCION IS NOT NULL) THEN
            v_transaccion:= P_TRANSACCION;
            END IF;

            IF(P_BAN_ESTATUS IS NOT NULL) THEN  
            v_ban_estatus:= P_BAN_ESTATUS;
            END IF;

          UPDATE TRANSACCION_CONTRATO
          
             SET ID_CONTRATO_DESEMBOLSO =V_id_cont_des,           /* P_ID_CONT_DES*/
                    PLATAFORMA = v_plataforma,      
                    TRANSACCION =v_transaccion,
                    BAN_ESTATUS = v_ban_estatus
             WHERE ID = P_ID_TRANSACCIONC;
          
         END IF;


     IF (P_DETALLETR.COUNT > 0) THEN
       FOR IND IN 1 .. P_DETALLETR.COUNT
          LOOP
           
                 IF  P_DETALLETR(IND).ID IS NOT NULL
                  THEN
                   V_id_detalle := P_DETALLETR (IND).ID;

                 UPDATE DETALLE_TRANSACCION
                  SET ID_TRANSACCION = P_DETALLETR (IND).ID_TRANSACCION, AGRUPADOR = P_DETALLETR (IND).AGRUPADOR,
                   PARAMETRO_NOMBRE = P_DETALLETR (IND).PARAMETRO_NOMBRE, PARAMETRO_VALOR= P_DETALLETR (IND).PARAMETRO_VALOR
                   WHERE ID = P_DETALLETR (IND) .ID;

                   P_DETALLETR_OUT(IND) := P_DETALLETR(IND);
                   P_DETALLETR_OUT(IND).ID := V_id_detalle;
                   P_DETALLETR_OUT(IND).ID_TRANSACCION :=V_id_transaccion;
                   P_DETALLETR_OUT(IND).PARAMETRO_NOMBRE := 'DETALLE_TRANSACCION';
                   P_DETALLETR_OUT(IND).PARAMETRO_VALOR := 'UPDATE';

                   ELSE

                   SELECT DETALLE_TRANSACCION_SEQ.NEXTVAL INTO V_id_detalle FROM DUAL;

                   INSERT INTO DETALLE_TRANSACCION
                   VALUES (V_id_detalle,
                   V_id_transaccion,
                   P_DETALLETR (IND).AGRUPADOR,
                   P_DETALLETR (IND).PARAMETRO_NOMBRE,
                   P_DETALLETR (IND).PARAMETRO_VALOR);

                 END IF;

              P_DETALLETR_OUT(IND) := P_DETALLETR(IND);
              P_DETALLETR_OUT(IND).ID := V_id_detalle;
              P_DETALLETR_OUT(IND).ID_TRANSACCION :=V_id_transaccion;
              P_DETALLETR_OUT(IND).PARAMETRO_NOMBRE := 'DETALLE_TRANSACCION';
              P_DETALLETR_OUT(IND).PARAMETRO_VALOR := 'INSERT';

          END LOOP;

    END IF;
    
    P_CODIGO_RES := 1;
    P_MENSAJE := 'Procedimiento  ejecutado  correctamente !';
END;
/