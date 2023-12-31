DROP TYPE "T_TRANSFERENCIA_BANCARIA";
/
DROP TYPE "T_LISTA_TRANSFERENCIA_BANCARIA";
/
create or replace TYPE "T_LISTA_TRANSFERENCIA_BANCARIA" as object
( ID_TRANSFERENCIA                  NUMBER(12)
, BHQ_TRANSFERENCIA    				      VARCHAR2(256)
, ID_CONSOLIDACION_PADRE            NUMBER(12)
, NUMERO_RESERVA                    NUMBER(12));
/
create or replace TYPE "T_TRANSFERENCIA_BANCARIA" as table of T_LISTA_TRANSFERENCIA_BANCARIA;
/