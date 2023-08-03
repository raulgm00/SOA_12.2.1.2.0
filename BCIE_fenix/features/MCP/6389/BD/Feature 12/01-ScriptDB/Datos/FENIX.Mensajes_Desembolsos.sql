--UPDATE DE REGLAS: 23
UPDATE TCA_REGLA_PROCESO SET DESCRIPCION='Se excede el monto máximo a desembolsar por USD X. El monto máximo a desembolsar para esta operación equivale a 8 veces el saldo en certificados de depósito, el saldo actual en CDPs es de USD Y.'
WHERE ID=23;
--UPDATE DE REGLAS: 25
UPDATE TCA_REGLA_PROCESO SET DESCRIPCION='Existe un desembolso registrado en la misma fecha de vencimiento.'
WHERE ID=25;