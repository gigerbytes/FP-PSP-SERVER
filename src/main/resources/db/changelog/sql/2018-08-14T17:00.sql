INSERT INTO data_collect.surveys(
	description, survey_definition, title)
	VALUES ('Dummy survey for DEVELOPERS ONLY', '{
    "organizations": [
        {
            "information": null,
            "code": null,
            "name": null,
            "country": null,
            "application": {
                "city": null,
                "code": null,
                "name": null,
                "information": null,
                "country": null,
                "dashboard": null,
                "file": null,
                "logoUrl": null,
                "active": false,
                "id": 1,
                "description": null
            },
            "dashboard": null,
            "file": null,
            "logoUrl": null,
            "active": false,
            "id": 1,
            "description": null
        }
    ],
    "description": "Dummy survey for DEVELOPERS ONLY",
    "title": "Survey DUMMY",
    "created_at": null,
    "last_modified_at": null,
    "survey_ui_schema": {
        "ui:group:economics": [
            "currency",
            "areaOfResidence",
            "employmentStatusPrimary",
            "activityMain",
            "employmentStatusSecondary",
            "activitySecondary",
            "householdMonthlyIncome",
            "salaryIncome",
            "benefitIncome",
            "pensionIncome",
            "savingsIncome",
            "otherIncome",
            "householdMonthlyOutgoing",
            "netSuplus",
            "educationClientLevel",
            "educationPersonMostStudied",
            "educationLevelAttained",
            "housingSituation",
            "otherUsefulInfo",
            "familyCountry",
            "familyCity",
            "familyUbication"
        ],
        "ui:group:personal": [
            "identificationType",
            "identificationNumber",
            "firstName",
            "lastName",
            "birthdate",
            "countryOfBirth",
            "gender",
            "phoneNumber"
        ],
        "ui:group:indicators": [
            "income",
            "documentation",
            "drinkingWaterAccess",
            "garbageDisposal",
            "safeHouse",
            "safeBathroom",
            "separateBed",
            "phone",
            "security",
            "readAndWrite"
        ],
        "ui:custom:fields": {
            "netSuplus": {
                "ui:field": "numberFormat"
            },
            "benefitIncome": {
                "ui:field": "numberFormat"
            },
            "pensionIncome": {
                "ui:field": "numberFormat"
            },
            "documentation": {
                "ui:field": "gallery"
            },
            "readAndWrite": {
                "ui:field": "gallery"
            },
            "safeBathroom": {
                "ui:field": "gallery"
            },
            "drinkingWaterAccess": {
                "ui:field": "gallery"
            },
            "savingsIncome": {
                "ui:field": "numberFormat"
            },
            "safeHouse": {
                "ui:field": "gallery"
            },
            "householdMonthlyOutgoing": {
                "ui:field": "numberFormat"
            },
            "separateBed": {
                "ui:field": "gallery"
            },
            "birthdate": {
                "ui:field": "date"
            },
            "phone": {
                "ui:field": "gallery"
            },
            "terereConsumption": {
                "ui:field": "gallery"
            },
            "income": {
                "ui:field": "gallery"
            },
            "familyUbication": {
                "ui:field": "gmap"
            },
            "security": {
                "ui:field": "gallery"
            },
            "otherIncome": {
                "ui:field": "numberFormat"
            },
            "garbageDisposal": {
                "ui:field": "gallery"
            },
            "householdMonthlyIncome": {
                "ui:field": "numberFormat"
            },
            "salaryIncome": {
                "ui:field": "numberFormat"
            }
        },
        "ui:order": [
            "identificationType",
            "identificationNumber",
            "firstName",
            "lastName",
            "birthdate",
            "countryOfBirth",
            "gender",
            "phoneNumber",
            "income",
            "documentation",
            "drinkingWaterAccess",
            "garbageDisposal",
            "safeHouse",
            "safeBathroom",
            "separateBed",
            "phone",
            "security",
            "readAndWrite"
        ],
        "properties": {}
    },
    "applications": [],
    "user_created": null,
    "survey_schema": {
        "description": "A simple stoplight survey example with default values.",
        "title": "Stoplight survey example with defaults",
        "required": [
            "identificationType",
            "identificationNumber",
            "firstName",
            "lastName",
            "birthdate",
            "countryOfBirth",
            "gender",
            "phoneNumber",
            "income",
            "documentation",
            "drinkingWaterAccess",
            "garbageDisposal",
            "safeHouse",
            "safeBathroom",
            "separateBed",
            "phone",
            "security",
            "readAndWrite"
        ],
        "dependencies": null,
        "type": "object",
        "properties": {
            "gender": {
                "default": "F",
                "enumNames": [
                    "Femenino",
                    "Masculino"
                ],
                "enum": [
                    "F",
                    "M"
                ],
                "type": "string",
                "title": {
                    "es": "Ingrese su género"
                }
            },
            "documentation": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/6-3.jpg",
                        "description": "Todos los miembros de la familia mayores a 12 años de edad tienen cédula de identidad vigente.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/6-3.jpg",
                            "description": "Todos los miembros de la familia mayores a 12 años de edad tienen cédula de identidad vigente.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/6-2.jpg",
                            "description": "Un miembro de mi familia mayor a 12 años de edad no tiene cédula de identidad.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/6-1.jpg",
                            "description": "Más de un miembro de mi familia mayor a 12 años de edad no tiene cédula de identidad.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos cédula"
                },
                "title": {
                    "es": "Cuenta con documentación"
                }
            },
            "readAndWrite": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/28-3.jpg",
                        "description": "Todos los miembros adultos de la familia (15 años o más) pueden leer y escribir y sienten que lo hacen con facilidad.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/28-3.jpg",
                            "description": "Todos los miembros adultos de la familia (15 años o más) pueden leer y escribir y sienten que lo hacen con facilidad.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/28-2.jpg",
                            "description": "Todos los miembros adultos (15 años o más) saben leer y escribir, pero al menos uno siente que le cuesta leer y/o escribir sus ideas.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/28-1.jpg",
                            "description": "Al menos un miembro adulto de la familia (15 años o más) no puede leer y/o escribir.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Sabemos leer y escribir"
                },
                "title": {
                    "es": "Puede leer, escribir y comprender en español?"
                }
            },
            "safeBathroom": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/17-3.jpg",
                        "description": "La familia tiene baño con las siguientes características: (a) cuenta con inodoro completo (WC), (b) cisterna, (c) asegura la privacidad, (d) cuenta con un buen sistema de evacuación, y (e) no se comparte con otra familia.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/17-3.jpg",
                            "description": "La familia tiene baño con las siguientes características: (a) cuenta con inodoro completo (WC), (b) cisterna, (c) asegura la privacidad, (d) cuenta con un buen sistema de evacuación, y (e) no se comparte con otra familia.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/17-2.jpg",
                            "description": "Aunque el baño de la familia no reúne todos los requisitos, la familia cuenta con algún tipo de letrina o inodoro con cagua, una letrina con losa y buena ventilación, o inodoro orgánico. No se comparte con otra familia.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/17-1.jpg",
                            "description": "La familia no cuenta con letrina ni baño con las características mencionadas en las demás opciones, o comparte con otra familia.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos baño moderno"
                },
                "title": {
                    "es": "Cuenta con baño seguro?"
                }
            },
            "firstName": {
                "default": "",
                "type": "string",
                "title": {
                    "es": "Ingrese su nombre"
                }
            },
            "identificationType": {
                "default": "CI",
                "enumNames": [
                    "Cédula de Identidad",
                    "DNI",
                    "Pasaporte"
                ],
                "enum": [
                    "CI",
                    "DNI",
                    "PASSPORT"
                ],
                "type": "string",
                "title": {
                    "es": "Tipo de documento"
                }
            },
            "safeHouse": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/16-3.jpg",
                        "description": "La vivienda tiene: (a) techa de tejas, chapa zinc u hormigón armado; (b) paredes solidas de madera, cemento o material cocido (ladrillo, baldosa, cemento, tejuelita, cerámica o similar), y (c) piso de madera o material cocido. Adicionalmente, las puertas y ventanas externas son de madera maciza o metal y todas se cierran de manera segura utilizando candados, cerraduras, trancas, pasadores resistentes u otros similares. La vivienda está preparada para distintas condiciciones climáticas.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/16-3.jpg",
                            "description": "La vivienda tiene: (a) techa de tejas, chapa zinc u hormigón armado; (b) paredes solidas de madera, cemento o material cocido (ladrillo, baldosa, cemento, tejuelita, cerámica o similar), y (c) piso de madera o material cocido. Adicionalmente, las puertas y ventanas externas son de madera maciza o metal y todas se cierran de manera segura utilizando candados, cerraduras, trancas, pasadores resistentes u otros similares. La vivienda está preparada para distintas condiciciones climáticas.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/16-2.jpg",
                            "description": "La vivienda tiene: (a) techa de tejas, chapa zinc u hormigón armado; (b) paredes solidas de madera, cemento o material cocido (ladrillo, baldosa, cemento, tejuelita, cerámica o similar), y (c) piso de madera o material cocido.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/16-1.jpg",
                            "description": "La vivienda no cuenta con alguno de los componentes descriptos en las demás opciones.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Nuestra casa es segura"
                },
                "title": {
                    "es": "Cuenta con una casa segura?"
                }
            },
            "lastName": {
                "default": "",
                "type": "string",
                "title": {
                    "es": "Ingrese su apellido"
                }
            },
            "separateBed": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/20-3.jpg",
                        "description": "No hay hacinamiento en la familia, y ninguno comparte dormitorio involuntariamente.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/20-3.jpg",
                            "description": "No hay hacinamiento en la familia, y ninguno comparte dormitorio involuntariamente.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/20-2.jpg",
                            "description": "No hay hacinamiento en la familia, pero por lo menos una persona comparte el dormitorio con otra de manera involuntaria.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/20-1.jpg",
                            "description": "La familia vive en condiciones de hacinamiento, es decir que en promedio, tres personas o más comparten un mismo dormitorio.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Dormimos en piezas separadas"
                },
                "title": {
                    "es": "Cuenta con camas separadas?"
                }
            },
            "drinkingWaterAccess": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/7-3.jpg",
                        "description": "Mi familia cuenta con agua potable a través de una canilla dentro del predio",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/7-3.jpg",
                            "description": "Mi familia cuenta con agua potable a través de una canilla dentro del predio",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/7-2.jpg",
                            "description": "Mi familia tiene acceso a agua potable fuera de la casa (canilla, pozo o aljibe protegido, agua de manantial o pluvial protegido) a menos de 30 minutos de caminata ida y vuelta.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/7-1.jpg",
                            "description": "El agua que bebe mi familia no es potable y/o tiene que carrearla desde un punto a más de 30 minutos de caminata ida y vuelta.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos canilla"
                },
                "title": {
                    "es": "Cuenta con acceso a agua potable?"
                }
            },
            "identificationNumber": {
                "default": "",
                "type": "string",
                "title": {
                    "es": "Ingrese su número de documento"
                }
            },
            "birthdate": {
                "format": "date",
                "type": "string",
                "title": {
                    "es": "Ingrese su fecha de nacimiento"
                }
            },
            "phone": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/25-3.jpg",
                        "description": "La familia cuenta con servicio de telefonía fija o celular disponible de manera constante.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/25-3.jpg",
                            "description": "La familia cuenta con servicio de telefonía fija o celular disponible de manera constante.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/25-2.jpg",
                            "description": "La familia cuenta con servicio de telefonía fija o celular, pero puede utilizarlo solamente parte del tiempo.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/25-1.jpg",
                            "description": "La familia no cuenta con servicio de telefonía (ni celular ni fija).",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos teléfono"
                },
                "title": {
                    "es": "Cuenta con teléfono o celular?"
                }
            },
            "phoneNumber": {
                "type": "string",
                "title": {
                    "es": "Ingrese su número de teléfono"
                }
            },
            "socialCapital": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/31-3.jpg",
                        "description": "La familia planifica su futuro económico con un presupuesto familiar escrito que utiliza en forma mensual que rige la economía permanentemente.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/31-3.jpg",
                            "description": "La familia planifica su futuro económico con un presupuesto familiar escrito que utiliza en forma mensual que rige la economía permanentemente.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/31-2.jpg",
                            "description": "La familia planifica su futuro económico con un presupuesto mensual escrito pero no lo utiliza regularmente y éste no rige la economía familiar.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/31-1.jpg",
                            "description": "La familia no planifica su futuro económico ni tiene un presupuesto familiar.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos presupuesto"
                },
                "title": {
                    "es": "Puede planificar y presupuestar a futuro?"
                }
            },
            "income": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/1-3.jpg",
                        "description": "Los ingresos de la familia superan la línea de la pobreza.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/1-3.jpg",
                            "description": "Los ingresos de la familia superan la línea de la pobreza.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/1-2.jpg",
                            "description": "Los ingresos de la familia son inferiores a la línea de pobreza general y superiores a la línea de pobreza extrema.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/1-1.jpg",
                            "description": "Los ingresos de la familia son inferiores a la línea de pobreza extrema.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Tenemos ingresos suficientes"
                },
                "title": {
                    "es": "Cuál es su nivel de ingreso?"
                }
            },
            "security": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/26-3.jpg",
                        "description": "Ningún miemrbo de la familia ha sufrido actos de violencia contra su persona en los últimos 12 meses.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/26-3.jpg",
                            "description": "Ningún miemrbo de la familia ha sufrido actos de violencia contra su persona en los últimos 12 meses.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/26-2.jpg",
                            "description": "Alguien en la familia sufrió un acto de violencia contra su persona sin consecuencias graves en los último 12 meses.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/26-1.jpg",
                            "description": "Alguien en la familia sufrió un acto de violencia contra su persona en los últimos 12 meses y como resultado no pudo trabajar por un día entero o más o fue causa de muerte.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Mi familia no fue robada"
                },
                "title": {
                    "es": "Cuenta con seguridad?"
                }
            },
            "garbageDisposal": {
                "default": [
                    {
                        "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/13-3.jpg",
                        "description": "La familia guarda sus residuos en un basurero hasta su recolección o dispone de los mismos enterrando los desechos orgánicos en un hoyo distanciado al menos 20mts. de una fuente de agua, campo de cultivo o de viviendas y reciclando los inorgánicos.",
                        "value": "GREEN"
                    }
                ],
                "items": {
                    "enum": [
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/13-3.jpg",
                            "description": "La familia guarda sus residuos en un basurero hasta su recolección o dispone de los mismos enterrando los desechos orgánicos en un hoyo distanciado al menos 20mts. de una fuente de agua, campo de cultivo o de viviendas y reciclando los inorgánicos.",
                            "value": "GREEN"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/13-2.jpg",
                            "description": "La familia entierra su basura en su terreno en un hoyo tapado que no está distanciado 20mts. de la fuente de agua, campo de cultivo o de las viviendas.",
                            "value": "YELLOW"
                        },
                        {
                            "url": "https://s3.us-east-2.amazonaws.com/fp-psp-images/13-1.jpg",
                            "description": "La familia dispone de sus basuras de manera inadecuada provocando contaminación y problemas de salud: las quema, o las tira a cielo abierto en su terreno y/o creca de viviendas, fuentes de agua o campos cultivados.",
                            "value": "RED"
                        },
                        {
                            "url": "NONE",
                            "description": "",
                            "value": "NONE"
                        }
                    ],
                    "type": "object"
                },
                "type": "array",
                "description": {
                    "es": "Manejamos bien nuestra basura"
                },
                "title": {
                    "es": "Cuenta con posibilidad de deshacerse de la basura?"
                }
            },
            "countryOfBirth": {
                "default": "PY",
                "enumNames": [
                    "Paraguay",
                    "Andorra",
                    "Emiratos Árabes Unidos",
                    "Afganistán",
                    "Antigua y Barbuda",
                    "Anguila",
                    "Albania",
                    "Armenia",
                    "Antillas Neerlandesas",
                    "Angola",
                    "Antártida",
                    "Argentina",
                    "Samoa Americana",
                    "Austria",
                    "Australia",
                    "Aruba",
                    "Islas Áland",
                    "Azerbaiyán",
                    "Bosnia y Herzegovina",
                    "Barbados",
                    "Bangladesh",
                    "Bélgica",
                    "Burkina Faso",
                    "Bulgaria",
                    "Bahréin",
                    "Burundi",
                    "Benin",
                    "San Bartolomé",
                    "Bermudas",
                    "Brunéi",
                    "Bolivia",
                    "Brasil",
                    "Bahamas",
                    "Bhután",
                    "Isla Bouvet",
                    "Botsuana",
                    "Belarús",
                    "Belice",
                    "Canadá",
                    "Islas Cocos",
                    "República Centro-Africana",
                    "Congo",
                    "Suiza",
                    "Costa de Marfil",
                    "Islas Cook",
                    "Chile",
                    "Camerún",
                    "China",
                    "Colombia",
                    "Costa Rica",
                    "Cuba",
                    "Cabo Verde",
                    "Islas Christmas",
                    "Chipre",
                    "República Checa",
                    "Alemania",
                    "Yibuti",
                    "Dinamarca",
                    "Domínica",
                    "República Dominicana",
                    "Argel",
                    "Ecuador",
                    "Estonia",
                    "Egipto",
                    "Sahara Occidental",
                    "Eritrea",
                    "España",
                    "Etiopía",
                    "Finlandia",
                    "Fiji",
                    "Islas Malvinas",
                    "Micronesia",
                    "Islas Faroe",
                    "Francia",
                    "Gabón",
                    "Reino Unido",
                    "Granada",
                    "Georgia",
                    "Guayana Francesa",
                    "Guernsey",
                    "Ghana",
                    "Gibraltar",
                    "Groenlandia",
                    "Gambia",
                    "Guinea",
                    "Guadalupe",
                    "Guinea Ecuatorial",
                    "Grecia",
                    "Georgia del Sur e Islas Sandwich del Sur",
                    "Guatemala",
                    "Guam",
                    "Guinea-Bissau",
                    "Guayana",
                    "Hong Kong",
                    "Islas Heard y McDonald",
                    "Honduras",
                    "Croacia",
                    "Haití",
                    "Hungría",
                    "Indonesia",
                    "Irlanda",
                    "Israel",
                    "Isla de Man",
                    "India",
                    "Territorio Británico del Océano Índico",
                    "Irak",
                    "Irán",
                    "Islandia",
                    "Italia",
                    "Jersey",
                    "Jamaica",
                    "Jordania",
                    "Japón",
                    "Kenia",
                    "Kirguistán",
                    "Camboya",
                    "Kiribati",
                    "Comoros",
                    "San Cristóbal y Nieves",
                    "Corea del Norte",
                    "Corea del Sur",
                    "Kuwait",
                    "Islas Caimán",
                    "Kazajstán",
                    "Laos",
                    "Líbano",
                    "Santa Lucía",
                    "Liechtenstein",
                    "Sri Lanka",
                    "Liberia",
                    "Lesotho",
                    "Lituania",
                    "Luxemburgo",
                    "Letonia",
                    "Libia",
                    "Marruecos",
                    "Mónaco",
                    "Moldova",
                    "Montenegro",
                    "Madagascar",
                    "Islas Marshall",
                    "Macedonia",
                    "Mali",
                    "Myanmar",
                    "Mongolia",
                    "Macao",
                    "Martinica",
                    "Mauritania",
                    "Montserrat",
                    "Malta",
                    "Mauricio",
                    "Maldivas",
                    "Malawi",
                    "México",
                    "Malasia",
                    "Mozambique",
                    "Namibia",
                    "Nueva Caledonia",
                    "Níger",
                    "Islas Norkfolk",
                    "Nigeria",
                    "Nicaragua",
                    "Países Bajos",
                    "Noruega",
                    "Nepal",
                    "Nauru",
                    "Niue",
                    "Nueva Zelanda",
                    "Omán",
                    "Panamá",
                    "Perú",
                    "Polinesia Francesa",
                    "Papúa Nueva Guinea",
                    "Filipinas",
                    "Pakistán",
                    "Polonia",
                    "San Pedro y Miquelón",
                    "Islas Pitcairn",
                    "Puerto Rico",
                    "Palestina",
                    "Portugal",
                    "Islas Palaos",
                    "Qatar",
                    "Reunión",
                    "Rumanía",
                    "Serbia y Montenegro",
                    "Rusia",
                    "Ruanda",
                    "Arabia Saudita",
                    "Islas Solomón",
                    "Seychelles",
                    "Sudán",
                    "Suecia",
                    "Singapur",
                    "Santa Elena",
                    "Eslovenia",
                    "Islas Svalbard y Jan Mayen",
                    "Eslovaquia",
                    "Sierra Leona",
                    "San Marino",
                    "Senegal",
                    "Somalia",
                    "Surinam",
                    "Santo Tomé y Príncipe",
                    "El Salvador",
                    "Siria",
                    "Suazilandia",
                    "Islas Turcas y Caicos",
                    "Chad",
                    "Territorios Australes Franceses",
                    "Togo",
                    "Tailandia",
                    "Tanzania",
                    "Tayikistán",
                    "Tokelau",
                    "Timor-Leste",
                    "Turkmenistán",
                    "Túnez",
                    "Tonga",
                    "Turquía",
                    "Trinidad y Tobago",
                    "Tuvalu",
                    "Taiwán",
                    "Ucrania",
                    "Uganda",
                    "Estados Unidos de América",
                    "Uruguay",
                    "Uzbekistán",
                    "Ciudad del Vaticano",
                    "San Vicente y las Granadinas",
                    "Venezuela",
                    "Islas Vírgenes Británicas",
                    "Islas Vírgenes de los Estados Unidos de América",
                    "Vietnam",
                    "Vanuatu",
                    "Wallis y Futuna",
                    "Samoa",
                    "Yemen",
                    "Mayotte",
                    "Sudáfrica"
                ],
                "enum": [
                    "PY",
                    "AD",
                    "AE",
                    "AF",
                    "AG",
                    "AI",
                    "AL",
                    "AM",
                    "AN",
                    "AO",
                    "AQ",
                    "AR",
                    "AS",
                    "AT",
                    "AU",
                    "AW",
                    "AX",
                    "AZ",
                    "BA",
                    "BB",
                    "BD",
                    "BE",
                    "BF",
                    "BG",
                    "BH",
                    "BI",
                    "BJ",
                    "BL",
                    "BM",
                    "BN",
                    "BO",
                    "BR",
                    "BS",
                    "BT",
                    "BV",
                    "BW",
                    "BY",
                    "BZ",
                    "CA",
                    "CC",
                    "CF",
                    "CG",
                    "CH",
                    "CI",
                    "CK",
                    "CL",
                    "CM",
                    "CN",
                    "CO",
                    "CR",
                    "CU",
                    "CV",
                    "CX",
                    "CY",
                    "CZ",
                    "DE",
                    "DJ",
                    "DK",
                    "DM",
                    "DO",
                    "DZ",
                    "EC",
                    "EE",
                    "EG",
                    "EH",
                    "ER",
                    "ES",
                    "ET",
                    "FI",
                    "FJ",
                    "FK",
                    "FM",
                    "FO",
                    "FR",
                    "GA",
                    "GB",
                    "GD",
                    "GE",
                    "GF",
                    "GG",
                    "GH",
                    "GI",
                    "GL",
                    "GM",
                    "GN",
                    "GP",
                    "GQ",
                    "GR",
                    "GS",
                    "GT",
                    "GU",
                    "GW",
                    "GY",
                    "HK",
                    "HM",
                    "HN",
                    "HR",
                    "HT",
                    "HU",
                    "ID",
                    "IE",
                    "IL",
                    "IM",
                    "IN",
                    "IO",
                    "IQ",
                    "IR",
                    "IS",
                    "IT",
                    "JE",
                    "JM",
                    "JO",
                    "JP",
                    "KE",
                    "KG",
                    "KH",
                    "KI",
                    "KM",
                    "KN",
                    "KP",
                    "KR",
                    "KW",
                    "KY",
                    "KZ",
                    "LA",
                    "LB",
                    "LC",
                    "LI",
                    "LK",
                    "LR",
                    "LS",
                    "LT",
                    "LU",
                    "LV",
                    "LY",
                    "MA",
                    "MC",
                    "MD",
                    "ME",
                    "MG",
                    "MH",
                    "MK",
                    "ML",
                    "MM",
                    "MN",
                    "MO",
                    "MQ",
                    "MR",
                    "MS",
                    "MT",
                    "MU",
                    "MV",
                    "MW",
                    "MX",
                    "MY",
                    "MZ",
                    "NA",
                    "NC",
                    "NE",
                    "NF",
                    "NG",
                    "NI",
                    "NL",
                    "NO",
                    "NP",
                    "NR",
                    "NU",
                    "NZ",
                    "OM",
                    "PA",
                    "PE",
                    "PF",
                    "PG",
                    "PH",
                    "PK",
                    "PL",
                    "PM",
                    "PN",
                    "PR",
                    "PS",
                    "PT",
                    "PW",
                    "QA",
                    "RE",
                    "RO",
                    "RS",
                    "RU",
                    "RW",
                    "SA",
                    "SB",
                    "SC",
                    "SD",
                    "SE",
                    "SG",
                    "SH",
                    "SI",
                    "SJ",
                    "SK",
                    "SL",
                    "SM",
                    "SN",
                    "SO",
                    "SR",
                    "ST",
                    "SV",
                    "SY",
                    "SZ",
                    "TC",
                    "TD",
                    "TF",
                    "TG",
                    "TH",
                    "TH",
                    "TJ",
                    "TK",
                    "TL",
                    "TM",
                    "TN",
                    "TO",
                    "TR",
                    "TT",
                    "TV",
                    "TW",
                    "UA",
                    "UG",
                    "US",
                    "UY",
                    "UZ",
                    "VA",
                    "VC",
                    "VE",
                    "VG",
                    "VI",
                    "VN",
                    "VU",
                    "WF",
                    "WS",
                    "YE",
                    "YT",
                    "ZA"
                ],
                "type": "string",
                "title": {
                    "es": "Ingrese su país de nacimiento"
                }
            }
        }
    },
    "id": 1
}', 'Survey DUMMY');

INSERT INTO ps_network.surveys_organizations (survey_id, organization_id, application_id) VALUES (1, 1, 1);