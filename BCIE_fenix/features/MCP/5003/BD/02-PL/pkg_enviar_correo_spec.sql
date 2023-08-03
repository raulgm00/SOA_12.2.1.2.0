create or replace TYPE T_TAGS_PLANTILLA as object (
    NOMBRE VARCHAR2 (100)
       );
/
create or replace TYPE       T_PARAM_CORREO as object (
TAG VARCHAR2(100),
VALOR  NUMBER (12));
/
create or replace TYPE       T_TAGS AS TABLE  OF T_PARAM_CORREO;
/

CREATE OR REPLACE PACKAGE FENIX.PKG_ENVIAR_CORREO
IS
   PROCEDURE P_CONSTRUIR_CORREO (P_ID_PLATILLA     IN     NUMBER,
                                 P_ID_OPERACION    IN     NUMBER,
                                 P_ID_CLIENTE      IN     NUMBER,
                                 P_TAGS            IN     T_TAGS,
                                 P_ASUNTO          IN OUT VARCHAR2,
                                 P_MENSAJE         IN OUT VARCHAR2,
                                 P_DESTINATARIOS   IN OUT VARCHAR2,
                                 P_CC              IN OUT VARCHAR2);
END PKG_ENVIAR_CORREO;
/