create or replace 
FUNCTION        F_ASIGNA_USUARIO (P_OPERACION    VARCHAR2,
                                             ID_ROL INTEGER)
   RETURN VARCHAR2
IS
   V_USUARIO   VARCHAR2 (250);
--Responsable de Operación
--Abogado
--GERIES
BEGIN
  IF ID_ROL = 1
   THEN      
      SELECT DISTINCT NOMBRE_USUARIO
        INTO V_USUARIO
        FROM TBI_PROCESO_OPERACION
       WHERE     ID_OPERACION = P_OPERACION
             AND ID_TAREA = 1
             AND ID_PROCESO = 1
             AND BAN_ES_FIN_ACTIVIDAD=1
         and     rownum=1;

      RETURN V_USUARIO;
   ELSE
      IF ID_ROL = 11
      THEN
         SELECT DISTINCT NOMBRE_USUARIO
           INTO V_USUARIO
           FROM TBI_PROCESO_OPERACION
          WHERE     ID_OPERACION = P_OPERACION
                AND ID_TAREA = 3
                AND ID_PROCESO = 1
             AND BAN_ES_FIN_ACTIVIDAD=1
             and    rownum=1;

         RETURN V_USUARIO;
      ELSE
         IF ID_ROL = 16
         THEN
            SELECT DISTINCT NOMBRE_USUARIO
              INTO V_USUARIO
              FROM TBI_PROCESO_OPERACION
             WHERE     ID_OPERACION = P_OPERACION
                   AND ID_TAREA = 4
                   AND ID_PROCESO = 1
           AND BAN_ES_FIN_ACTIVIDAD=1
                  and  rownum=1;

            RETURN V_USUARIO;
         ELSE
       IF ID_ROL = 13
         THEN 
         
              SELECT DISTINCT USUARIO
              INTO V_USUARIO
              FROM TBI_PROCESO_OPERACION
             WHERE     ID_OPERACION = P_OPERACION
                   AND (ID_TAREA = 39 OR  ID_TAREA = 41)
                   AND ID_PROCESO =  7
           AND BAN_ES_FIN_ACTIVIDAD=1
                  and  rownum=1;

            RETURN V_USUARIO;
            ELSE
         
         
         
         
         
         
            RETURN V_USUARIO;
            END IF;
         END IF;
      END IF;
      END IF;
   END ;