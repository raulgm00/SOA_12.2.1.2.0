create or replace PROCEDURE SP_CONSULTAR_VOTACIONES (
   P_USUARIO              IN     VARCHAR2,
   P_HISTORIAL            IN     NUMBER,
   P_APROBACION_CLIENTE   IN     NUMBER,
   P_VOTACION                OUT SYS_REFCURSOR)
IS
BEGIN

   IF P_APROBACION_CLIENTE = 0 AND P_HISTORIAL = 0
   THEN
      OPEN P_VOTACION FOR
         SELECT SA.ID ID_SOLICITUD_APROBACION,
                SA.ID_OPERACION,
                OP.NOMBRE,
                SA.ID_TCA_TIPO_SOLICITUD,
                TTS.DESCRIPCION,
                SA.ID_TCA_NIVEL_APROBACION,
                SA.ES_REUNION_VIRTUAL,
                SA.FECHA_INICIO,
                SA.FECHA_TERMINO,
                SA.HORA_REUNION,
                SA.LUGAR_REUNION,
                SA.LOGIN_USUARIO_ASIGNA,
                SA.FECHA_ASIGNACION,
                SA.FECHA_CIERRE,
                SA.LOGIN_USUARIO_CIERRE,
                SA.FECHA_REGISTRO,
                SA.BAN_ESTATUS BAN_ESTATUS_SOLI,
                URA.ID_TCA_ROL_BPM,
                URA.LOGIN_USUARIO,
                URA.FECHA_REGISTRO FECHA_REGISTRO_USUARIO_REU,
                URA.BAN_ESTATUS,
                URA.EMITE_VOTO,
                URA.ID ID_USUARIO_REUNION,
                TNA.DESCRIPCION DESC_NIVEL_APROB,
                URA.MARCADO_COMO_LEIDO
           FROM SOLICITUD_APROBACION SA
                JOIN USUARIO_REUNION_APROBACION URA
                   ON (SA.ID = URA.ID_SOLICITUD_APROBACION)
                JOIN TCA_TIPO_SOLICITUD_APROBACION TTS
                   ON (TTS.ID = SA.ID_TCA_TIPO_SOLICITUD)
                JOIN TCA_NIVEL_APROBACION TNA
                   ON (TNA.ID = SA.ID_TCA_NIVEL_APROBACION)
                JOIN OPERACION OP
                   ON (OP.ID_OPERACION = SA.ID_OPERACION)
          WHERE     SA.FECHA_CIERRE IS NULL
                AND SA.BAN_ESTATUS = 1
                AND URA.LOGIN_USUARIO = P_USUARIO
          ORDER BY SA.FECHA_INICIO DESC;
   ELSIF P_APROBACION_CLIENTE = 0 AND P_HISTORIAL = 1
   THEN
      OPEN P_VOTACION FOR
         SELECT SA.ID ID_SOLICITUD_APROBACION,
                SA.ID_OPERACION,
                OP.NOMBRE,
                SA.ID_TCA_TIPO_SOLICITUD,
                TTS.DESCRIPCION,
                SA.ID_TCA_NIVEL_APROBACION,
                SA.ES_REUNION_VIRTUAL,
                SA.FECHA_INICIO,
                SA.FECHA_TERMINO,
                SA.HORA_REUNION,
                SA.LUGAR_REUNION,
                SA.LOGIN_USUARIO_ASIGNA,
                SA.FECHA_ASIGNACION,
                SA.FECHA_CIERRE,
                SA.LOGIN_USUARIO_CIERRE,
                SA.FECHA_REGISTRO,
                SA.BAN_ESTATUS BAN_ESTATUS_SOLI,
                URA.ID_TCA_ROL_BPM,
                URA.LOGIN_USUARIO,
                URA.FECHA_REGISTRO FECHA_REGISTRO_USUARIO_REU,
                URA.BAN_ESTATUS,
                URA.EMITE_VOTO,
                URA.ID ID_USUARIO_REUNION,
                TNA.DESCRIPCION DESC_NIVEL_APROB,
                URA.MARCADO_COMO_LEIDO
           FROM SOLICITUD_APROBACION SA
                JOIN USUARIO_REUNION_APROBACION URA
                   ON (SA.ID = URA.ID_SOLICITUD_APROBACION)
                JOIN TCA_TIPO_SOLICITUD_APROBACION TTS
                   ON (TTS.ID = SA.ID_TCA_TIPO_SOLICITUD)
                JOIN TCA_NIVEL_APROBACION TNA
                   ON (TNA.ID = SA.ID_TCA_NIVEL_APROBACION)
                JOIN OPERACION OP
                   ON (OP.ID_OPERACION = SA.ID_OPERACION)
          WHERE     SA.FECHA_CIERRE IS NOT NULL
                AND URA.LOGIN_USUARIO = P_USUARIO
          ORDER BY   SA.FECHA_INICIO DESC;
   ELSIF P_APROBACION_CLIENTE = 1 AND P_HISTORIAL = 0
   THEN
      OPEN P_VOTACION FOR
         SELECT SA.ID ID_SOLICITUD_APROBACION,
                SA.ID_CLIENTE,
                CLI.RAZON_SOCIAL,
                SA.ID_TCA_TIPO_SOLICITUD,
                TTS.DESCRIPCION,
                SA.ID_TCA_NIVEL_APROBACION,
                SA.ES_REUNION_VIRTUAL,
                SA.FECHA_INICIO,
                SA.FECHA_TERMINO,
                SA.HORA_REUNION,
                SA.LUGAR_REUNION,
                SA.LOGIN_USUARIO_ASIGNA,
                SA.FECHA_ASIGNACION,
                SA.FECHA_CIERRE,
                SA.LOGIN_USUARIO_CIERRE,
                SA.FECHA_REGISTRO,
                SA.BAN_ESTATUS BAN_ESTATUS_SOLI,
                URA.ID_TCA_ROL_BPM,
                URA.LOGIN_USUARIO,
                URA.FECHA_REGISTRO FECHA_REGISTRO_USUARIO_REU,
                URA.BAN_ESTATUS,
                URA.EMITE_VOTO,
                URA.ID ID_USUARIO_REUNION,
                TNA.DESCRIPCION DESC_NIVEL_APROB,
                URA.MARCADO_COMO_LEIDO
           FROM SOLICITUD_APROBACION SA
                JOIN USUARIO_REUNION_APROBACION URA
                   ON (SA.ID = URA.ID_SOLICITUD_APROBACION)
                JOIN TCA_TIPO_SOLICITUD_APROBACION TTS
                   ON (TTS.ID = SA.ID_TCA_TIPO_SOLICITUD)
                JOIN TCA_NIVEL_APROBACION TNA
                   ON (TNA.ID = SA.ID_TCA_NIVEL_APROBACION)
                JOIN CLIENTES CLI
                   ON (CLI.ID_CLIENTE = SA.ID_CLIENTE)
          WHERE     SA.FECHA_CIERRE IS NULL
                AND SA.BAN_ESTATUS = 1
                AND URA.LOGIN_USUARIO = P_USUARIO
          ORDER BY   SA.FECHA_INICIO DESC;
   ELSIF P_APROBACION_CLIENTE = 1 AND P_HISTORIAL = 1
   THEN
      OPEN P_VOTACION FOR
         SELECT SA.ID ID_SOLICITUD_APROBACION,
                SA.ID_CLIENTE,
                CLI.RAZON_SOCIAL,
                SA.ID_TCA_TIPO_SOLICITUD,
                TTS.DESCRIPCION,
                SA.ID_TCA_NIVEL_APROBACION,
                SA.ES_REUNION_VIRTUAL,
                SA.FECHA_INICIO,
                SA.FECHA_TERMINO,
                SA.HORA_REUNION,
                SA.LUGAR_REUNION,
                SA.LOGIN_USUARIO_ASIGNA,
                SA.FECHA_ASIGNACION,
                SA.FECHA_CIERRE,
                SA.LOGIN_USUARIO_CIERRE,
                SA.FECHA_REGISTRO,
                SA.BAN_ESTATUS BAN_ESTATUS_SOLI,
                URA.ID_TCA_ROL_BPM,
                URA.LOGIN_USUARIO,
                URA.FECHA_REGISTRO FECHA_REGISTRO_USUARIO_REU,
                URA.BAN_ESTATUS,
                URA.EMITE_VOTO,
                URA.ID ID_USUARIO_REUNION,
                TNA.DESCRIPCION DESC_NIVEL_APROB,
                URA.MARCADO_COMO_LEIDO
           FROM SOLICITUD_APROBACION SA
                JOIN USUARIO_REUNION_APROBACION URA
                   ON (SA.ID = URA.ID_SOLICITUD_APROBACION)
                JOIN TCA_TIPO_SOLICITUD_APROBACION TTS
                   ON (TTS.ID = SA.ID_TCA_TIPO_SOLICITUD)
                JOIN TCA_NIVEL_APROBACION TNA
                   ON (TNA.ID = SA.ID_TCA_NIVEL_APROBACION)
                JOIN CLIENTES CLI
                   ON (CLI.ID_CLIENTE = SA.ID_CLIENTE)
          WHERE     SA.FECHA_CIERRE IS NOT NULL
                AND URA.LOGIN_USUARIO = P_USUARIO
          ORDER BY   SA.FECHA_INICIO DESC;
   END IF;
END SP_CONSULTAR_VOTACIONES;
/
