Este servicio es una api de un banco para validacion de cuentas

Esta api no usa autenticacion, su proposito es ser local a ICG y temporal para el banco.

El request transport debe recoger un token de cualquier keycloak (para no alterar su fucnionamiento) pero esta api no lo validara.


recibe mensajes de la forma:

{"Destination":"CITIGTGC","payload":{"product":1,"currency":1,"account":"a"},"type":"001","UUID":"2f03009e-3a2a-4c2f-bf4e-98f486889c25","version":"3.0","Source":"INDLGTGC"}



genera respuestas del tipo:

{"Destination":"INDLGTGC","payload":{"hit":false,"found":"false","name":"","status":"ERR"},"type":"002","UUID":"2F03009E-3A2A-4C2F-BF4E-98F486889C25","Source":"INDLGTGC"}



Estatus soportados:
0: activo
2: bloqueado
3: bloqueado
6: inactivo