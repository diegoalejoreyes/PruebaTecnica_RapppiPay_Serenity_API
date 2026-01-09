Feature: Validación de API de Shazam


  Scenario Outline: Consultar detalles de una canción por ID
    Given consulto los detalles de la cancion con <ID>
    Then el servicio responde con un codigo exitoso
    And la respuesta debe contener el titulo de la cancion
    Examples:
      | ID           |  |
      | "1217912247" |  |


  Scenario: Detectar canción enviando texto valido
    Given ejecuto el servicio Post con un txt de entrada para detectar una cancion
    Then el servicio responde con un codigo exitoso
    And la respuesta debe contener el track de la cancion